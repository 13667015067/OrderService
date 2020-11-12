package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.config.OrderConfig;
import com.pjh.orderservice.entity.*;
import com.pjh.orderservice.service.*;
import com.pjh.orderservice.util.StringUtil;
import com.pjh.orderservice.util.TimeUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:21:33
 */

@RestController
@CrossOrigin
@RequestMapping("orderInfo")
public class OrderInfoController {

    private Logger logger = LoggerFactory.getLogger(OrderInfoController.class) ;

    @Autowired
    private OrderInfoService orderInfoService ;

    @Autowired
    private OrderImgService orderImgService ;

    @Autowired
    private OrderTaskService orderTaskService;

    @Autowired
    private LogicService logicService ;

    @Autowired
    private OrderWorkService orderWorkService ;

    @Autowired
    private ApplyInfoService applyInfoService ;

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    /**
     * 功能：生产公单
     */
    @PostMapping(value = "/addOrderInfo",consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public String addOrderInfo(@RequestParam MultipartFile[] fileList,
                               @RequestParam String applyId,
                               @RequestParam String orderStyle,
                               @RequestParam String orderType,
                               @RequestParam String taskName,
                               @RequestParam String orderContent,
                               @RequestParam String dealTime,
                               @RequestParam String user){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)          ||
                StringUtil.getInstance().IsEmpty(orderStyle)   ||
                StringUtil.getInstance().IsEmpty(orderContent) ||
                StringUtil.getInstance().IsEmpty(dealTime)     ||
                StringUtil.getInstance().IsEmpty(user)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        OrderInfo orderInfo = new OrderInfo() ;
        OrderTask orderTask = orderTaskService.getOrderTask(applyId,orderStyle,taskName) ;
        if (orderTask == null){
            response.setCode(201);
            response.setMsg("参数问题！未查询到定义好的流程配置！");
            return JSONObject.toJSONString(response) ;
        }


        String dealUser = "";
        String nodeType = "";
        Logic logic = logicService.getLogicById(orderTask.getLogicId()) ;
        String orderLogic = logic.getData() ;
        JSONObject logicJson = JSONObject.parseObject(orderLogic) ;
        JSONArray nodes = logicJson.getJSONArray("nodes") ;
        for (int i = 0 ; i<nodes.size();i++){
            JSONObject json = nodes.getJSONObject(i) ;
            if (json.getIntValue("nodeId")==1 &&
                    !StringUtil.getInstance().IsEmpty(json.getString("dealUser"))){
                dealUser = json.getString("dealUser") ;
                nodeType = json.getString("nodeType") ;
                break;
            }
        }

        orderInfo.setOrderLogic(logic.getData());

        String orderId = OrderConfig.ORDERIDHEADER + TimeUtil.getInstance().getTimeStr() + StringUtil.getInstance().getRandomInt(10,99);
        orderInfo.setId(orderId);
        orderInfo.setApplyId(applyId);
        orderInfo.setOrderStyle(orderStyle);
        if (!StringUtil.getInstance().IsEmpty(orderType)){
            orderInfo.setOrderType(orderType);
        }
        orderInfo.setOrderContent(orderContent);
        orderInfo.setOrderState(OrderConfig.NOASSIGN);
        orderInfo.setNowDealUser(dealUser);
        orderInfo.setDealTime(TimeUtil.getInstance().StringToDate(dealTime));
        orderInfo.setTaskName(taskName);
        orderInfo.setCreateTime(new Date());
        orderInfo.setCreateUser(user);

        //添加工单记录
        boolean bool = orderInfoService.addOrderInfo(orderInfo) ;

        if (!bool){
            response.setCode(203);
            response.setMsg("工单创建异常！");
            logger.info("**PJH**:工单创建异常，orderInfo:{}",orderInfo.toString());
            return JSONObject.toJSONString(response) ;
        }

        //添加流程记录
        OrderWork orderWork = new OrderWork() ;
        orderWork.setOrderId(orderId);
        orderWork.setNodeId(1);
        orderWork.setNodeType(nodeType);
        orderWork.setNodeState(OrderConfig.NOWORK);
        orderWork.setDealUser(dealUser);
        orderWork.setOrderLogic(orderLogic);
        orderWork.setCreateTime(new Date());

        orderWorkService.insertOrderWork(orderWork) ;
        long workId = orderWork.getId() ;

        //处理图片
        orderImgService.dealImg(fileList,workId) ;

        response.setCode(200);
        response.setMsg("工单创建成功！");

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 分页获取工单信息
     */
    @ApiOperation(value = "分页查询工单信息")
    @GetMapping(value = "getOrderInfo")
    public String getOrderInfo(@ApiParam(value = "应用号", required = true) @RequestParam String applyId,
                               @ApiParam(value = "应用Key", required = true) @RequestParam String applyKey,
                               @ApiParam(value = "状态 0:初始状态；1:处理中；2:已处理；-1:全部", required = true) @RequestParam int state,
                               @ApiParam(value = "页码 从1开始", required = true) @RequestParam int page,
                               @ApiParam(value = "每页数量", required = true) @RequestParam int amount){
        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
            StringUtil.getInstance().IsEmpty(applyKey)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }
        if (!applyInfoService.checkApplyKey(applyId,applyKey)){
            response.setCode(202);
            response.setMsg("应用号或者应用密码有误！");
            return JSONObject.toJSONString(response) ;
        }

        List<OrderInfo> orderInfos = orderInfoService.getOrderList(applyId,state,page,amount) ;

        if (orderInfos !=null && orderInfos.size()>0){
            int pageSize = orderInfoService.getAmount(applyId,null,state,amount) ;
            Map<String,Object> data = new HashMap<>() ;
            data.put("orders",orderInfos) ;
            data.put("pageSize",pageSize) ;
            response.setCode(200);
            response.setData(data);
        }else {
            response.setCode(203);
            response.setMsg("未查询到数据！");
        }

        return  JSONObject.toJSONString(response) ;
    }


    /**
     * 根据处理人获取工单信息
     */

    @GetMapping(value = "getOrderInfoByUser")
    public String getOrderInfoByUser(@RequestParam String applyId,
                                     @RequestParam String applyKey,
                                     @RequestParam int state,//-1：所有
                                     @RequestParam int page,
                                     @RequestParam int amount,
                                     @RequestParam String user){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(applyKey)||
                StringUtil.getInstance().IsEmpty(user)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }
        if (!applyInfoService.checkApplyKey(applyId,applyKey)){
            response.setCode(202);
            response.setMsg("应用号或者应用密码有误！");
            return JSONObject.toJSONString(response) ;
        }

        List<OrderInfo> orderInfos = orderInfoService.getOrderListByUser(applyId,user,state,page,amount) ;

        List<OrderWithWork> orders = new ArrayList<>() ;
        if (orderInfos!=null&&orderInfos.size()>0){
            for (OrderInfo orderInfo : orderInfos){
                OrderWithWork orderWithWork = new OrderWithWork();
                List<WorkWithImg> workWithImgs = orderWorkService.getWorkWithImg(orderInfo.getId()) ;
                orderWithWork.setOrderInfo(orderInfo);
                if (workWithImgs != null && workWithImgs.size()>0){
                    orderWithWork.setWorks(workWithImgs);
                }
                orders.add(orderWithWork);
            }
            int pageSize = orderInfoService.getAmount(applyId,user,state,amount) ;
            Map<String,Object> data = new HashMap<>() ;
            data.put("orders",orders) ;
            data.put("pageSize",pageSize) ;
            response.setCode(200);
            response.setData(data);
        }else{
            response.setCode(203);
            response.setMsg("未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 删除工单信息
     */

    @PostMapping(value = "delOrderInfo")
    public String deleteOrderInfo(@RequestParam String orderId){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(orderId)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId) ;
        if (orderInfo == null){
            response.setCode(202);
            response.setMsg("该工单不存在！");
            return JSONObject.toJSONString(response) ;
        }

        if (orderInfoService.delOrderInfo(orderId)){
            response.setCode(200);
            response.setMsg("删除工单成功！");
        }else {
            response.setCode(203);
            response.setMsg("删除工单失败！");
        }


        return JSONObject.toJSONString(response) ;
    }


}
