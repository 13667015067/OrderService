package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.config.OrderConfig;
import com.pjh.orderservice.entity.OrderImg;
import com.pjh.orderservice.entity.OrderWork;
import com.pjh.orderservice.entity.WorkWithImg;
import com.pjh.orderservice.service.*;
import com.pjh.orderservice.util.FileUtil;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author:彭建辉
 * @date:2020/10/22 15:25:21
 */

@RestController
@CrossOrigin
@RequestMapping("work")
public class OrderWorkController {

    private Logger logger = LoggerFactory.getLogger(OrderWorkController.class) ;

    @Autowired
    private OrderWorkService orderWorkService ;

    @Autowired
    private ApplyInfoService applyInfoService ;

    @Autowired
    private OrderInfoService orderInfoService ;

    @Autowired
    private OrderImgService orderImgService ;

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    /**
     * 根据工单号获取流程信息
     */
    @GetMapping(value = "getWork")
    public String addUser(@RequestParam String applyId ,
                          @RequestParam String applyKey,
                          @RequestParam String orderId){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId) ||
                StringUtil.getInstance().IsEmpty(applyKey)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**：applyId：{},applyKey:{}",applyId,applyKey);
            return JSONObject.toJSONString(response) ;
        }

        if (!applyInfoService.checkApplyKey(applyId,applyKey)){
            response.setCode(202);
            response.setMsg("应用号与密钥不匹配！");
            logger.error("**PJH**：应用号与密钥不匹配！applyId：{},applyKey:{}",applyId,applyKey);
            return JSONObject.toJSONString(response) ;
        }

        List<WorkWithImg> works = orderWorkService.getWorksByOrderId(orderId) ;

        if (works != null && works.size()>0){
            response.setCode(200);
            response.setData(works);
        }else {
            response.setCode(204);
            response.setMsg("未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }



    /**
     *审核流程接口
     */

    @PostMapping(value = "checkWork")
    public String checkNode(@RequestBody JSONObject json){
        String applyId = json.getString("applyId") ;
        String applyKey = json.getString("applyKey") ;

        String orderId = json.getString("orderId");
        long workId = json.getLongValue("workId") ;
        int isPass = json.getIntValue("isPass");//1:通过，0:回退（驳回）
        String workData = json.getString("workData") ;

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(applyKey)||
                StringUtil.getInstance().IsEmpty(orderId)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        if (!applyInfoService.checkApplyKey(applyId,applyKey)) {
            response.setCode(202);
            response.setMsg("应用号或者应用密码有误！");
            return JSONObject.toJSONString(response);
        }

        OrderWork work = orderWorkService.getWorkById(workId);

        if (work.getNodeState() != OrderConfig.NOWORK){
            response.setCode(204);
            response.setMsg("已处理，请勿重复处理！");
            return JSONObject.toJSONString(response);
        }
        if (work != null){
            if (!StringUtil.getInstance().IsEmpty(workData)) {
                work.setWorkData(workData);
            }
            if (isPass == 0){
                work.setNodeState(OrderConfig.GOBACKWORK);
            }else {
                work.setNodeState(OrderConfig.OKWORK);
            }
            work.setEndTime(new Date());

            orderWorkService.updateWork(work) ;

            String dealUser = "" ;
            OrderWork orderWork = new OrderWork() ;
            orderWork.setOrderId(orderId);
            orderWork.setNodeState(OrderConfig.NOWORK);
            orderWork.setOrderLogic(work.getOrderLogic());
            orderWork.setCreateTime(new Date());

            JSONObject jsonObject = JSON.parseObject(work.getOrderLogic()) ;
            JSONArray nodes = jsonObject.getJSONArray("nodes");
            if (isPass == 0){//回滚
                orderWork.setNodeId(work.getNodeId()-1);
                for (int i = 0 ; i < nodes.size();i++){
                    JSONObject node = (JSONObject) nodes.get(i);
                    if (node.getIntValue("nodeId") == work.getNodeId()-1){
                        dealUser = node.getString("dealUser");
                        orderWork.setNodeType(node.getString("nodeType"));
                    }
                }
                orderWork.setDealUser(dealUser);


                orderWorkService.insertOrderWork(orderWork) ;
                orderInfoService.updateOrderInfo(orderId,null,OrderConfig.ASSIGN,dealUser,null,null) ;

                response.setMsg("回退完成！");
            }else {//通过
                if (work.getNodeId()<nodes.size()){
                    orderWork.setNodeId(work.getNodeId()+1);
                    for (int i = 0 ; i < nodes.size();i++){
                        JSONObject node = (JSONObject) nodes.get(i);
                        if (node.getIntValue("nodeId") == work.getNodeId()+1){
                            dealUser = node.getString("dealUser");
                            orderWork.setNodeType(node.getString("nodeType"));
                        }
                    }
                    orderWork.setDealUser(dealUser);
                    orderWorkService.insertOrderWork(orderWork) ;
                    orderInfoService.updateOrderInfo(orderId,null,OrderConfig.ASSIGN,dealUser,null,null) ;

                }else {//该工单完成
                    orderInfoService.updateOrderInfo(orderId,null,OrderConfig.DEAL,dealUser,new Date(),null) ;
                }


                response.setMsg("审核完成！");
            }
        }else {
            response.setCode(203);
            response.setMsg("流程号有误！");
        }
        response.setCode(200);

        return JSONObject.toJSONString(response) ;

    }


    /**
     *指派流程接口
     */
    @PostMapping(value = "assignWork")
    public String assignWork(@RequestBody JSONObject json){

        String applyId = json.getString("applyId") ;
        String applyKey = json.getString("applyKey") ;

        String orderId = json.getString("orderId");
        long workId  = json.getLongValue("workId") ;
        int nodeId = json.getIntValue("nodeId") ; //被指派处理的
        String dealUser = json.getString("dealUser");//该节点处理人

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(applyKey)||
                StringUtil.getInstance().IsEmpty(orderId)||
                StringUtil.getInstance().IsEmpty(dealUser)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        if (!applyInfoService.checkApplyKey(applyId,applyKey)) {
            response.setCode(202);
            response.setMsg("应用号或者应用密码有误！");
            return JSONObject.toJSONString(response);
        }

        OrderWork work = orderWorkService.getWorkById(workId);
        if (work != null) {

            JSONObject orderLogic = JSON.parseObject(work.getOrderLogic()) ;
            JSONArray nodes = orderLogic.getJSONArray("nodes");

            for (int i = 0 ; i < nodes.size();i++){
                JSONObject node = nodes.getJSONObject(i);
                if (node.getIntValue("nodeId") == nodeId){
                    node.put("dealUser",dealUser) ;
                }
            }

            work.setOrderLogic(orderLogic.toJSONString());
            work.setNodeState(OrderConfig.OKWORK);
            work.setEndTime(new Date());
            orderWorkService.updateWork(work);

            //新增下一个处理节点
            OrderWork orderWork = new OrderWork() ;
            orderWork.setOrderId(orderId);
            orderWork.setNodeId(work.getNodeId() + 1) ;
            orderWork.setNodeState(OrderConfig.NOWORK);
            orderWork.setOrderLogic(orderLogic.toJSONString());
            orderWork.setCreateTime(new Date());

            for (int i = 0 ; i < nodes.size();i++){
                JSONObject node = nodes.getJSONObject(i);
                if (node.getIntValue("nodeId") == (work.getNodeId() + 1)){
                    orderWork.setDealUser(node.getString("dealUser"));
                    orderWork.setNodeType(node.getString("nodeType"));
                }
            }

            orderWorkService.insertOrderWork(orderWork) ;
            orderInfoService.updateOrderInfo(orderId,null,OrderConfig.ASSIGN,orderWork.getDealUser(),null,orderLogic.toJSONString()) ;
            response.setCode(200);
            response.setMsg("指派成功！");
        }else {
            response.setCode(203);
            response.setMsg("流程号有误！");
        }



        return JSONObject.toJSONString(response) ;
    }




    /**
     *填写（处理）流程接口
     */
    @PostMapping(value = "/dealWork",consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public String dealWork(@RequestParam MultipartFile[] fileList,
                           @RequestParam String applyId,
                           @RequestParam String applyKey,
                           @RequestParam String orderId,
                           @RequestParam long workId,
                           @RequestParam String orderContent){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(applyKey)||
                StringUtil.getInstance().IsEmpty(orderId)||
                StringUtil.getInstance().IsEmpty(orderContent)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        if (!applyInfoService.checkApplyKey(applyId,applyKey)) {
            response.setCode(202);
            response.setMsg("应用号或者应用密码有误！");
            return JSONObject.toJSONString(response);
        }

        OrderWork work = orderWorkService.getWorkById(workId);
        if (work.getNodeState() != OrderConfig.NOWORK){
            response.setCode(204);
            response.setMsg("已处理，请勿重复处理！");
            return JSONObject.toJSONString(response);
        }
        if (work != null){
            if (work.getNodeState() == OrderConfig.OKWORK
                    ||work.getNodeState() == OrderConfig.GOBACKWORK){
                response.setCode(204);
                response.setMsg("该节点已处理！不用重复处理");
                return JSONObject.toJSONString(response) ;
            }
            work.setWorkData(orderContent);
            work.setNodeState(OrderConfig.OKWORK);
            work.setEndTime(new Date());

            //处理图片
            orderImgService.dealImg(fileList,workId);

            orderWorkService.updateWork(work) ;

            //判断是否还有下一个节点
            JSONObject orderLogic = JSON.parseObject(work.getOrderLogic()) ;
            JSONArray nodes = orderLogic.getJSONArray("nodes");
            if (work.getNodeId() < nodes.size()) {
                //新增下一个处理节点
                OrderWork orderWork = new OrderWork();
                orderWork.setOrderId(orderId);
                orderWork.setNodeId(work.getNodeId() + 1);
                orderWork.setNodeState(OrderConfig.NOWORK);
                orderWork.setOrderLogic(work.getOrderLogic());
                orderWork.setCreateTime(new Date());


                for (int i = 0; i < nodes.size(); i++) {
                    JSONObject node = nodes.getJSONObject(i);
                    if (node.getIntValue("nodeId") == (work.getNodeId() + 1)) {
                        orderWork.setDealUser(node.getString("dealUser"));
                        orderWork.setNodeType(node.getString("nodeType"));
                    }
                }

                orderWorkService.insertOrderWork(orderWork);
                orderInfoService.updateOrderInfo(orderId,orderContent,OrderConfig.ASSIGN,orderWork.getDealUser(),null,null) ;
            }else {
                orderInfoService.updateOrderInfo(orderId,orderContent,OrderConfig.DEAL,null,new Date(),null) ;
            }
            response.setCode(200);
            response.setMsg("处理成功！");


        }else {
            response.setCode(203);
            response.setMsg("流程号有误！");
        }

        return JSONObject.toJSONString(response) ;
    }

}
