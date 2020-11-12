package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.entity.OrderTask;
import com.pjh.orderservice.service.ApplyInfoService;
import com.pjh.orderservice.service.OrderTaskService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/15 16:55:52
 */

@RestController
@CrossOrigin
@RequestMapping("orderTask")
public class OrderTaskController {

    private Logger logger = LoggerFactory.getLogger(OrderTaskController.class) ;

    @Autowired
    private OrderTaskService orderTaskService ;

    @Autowired
    private ApplyInfoService applyInfoService ;

    @PostMapping(value = "addOrderTask")
    public String addOrderTask(@RequestBody JSONObject json){

        String applyId = json.getString("applyId") ;
        String name = json.getString("name") ;
        String orderStyle = json.getString("orderStyle") ;
        String orderType = json.getString("orderType") ;
        long logicId = json.getLong("logicId") ;
        long modelId = json.getLong("modelId") ;

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)  ||
                StringUtil.getInstance().IsEmpty(name) ||
                StringUtil.getInstance().IsEmpty(orderStyle)){

            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        OrderTask orderTask = orderTaskService.getOrderTask(applyId,orderStyle,name) ;

        if (orderTask != null){
            response.setCode(202);
            response.setMsg("该流程名已存在！");
            return JSONObject.toJSONString(response) ;
        }

        orderTask = new OrderTask() ;
        orderTask.setApplyId(applyId);
        orderTask.setName(name);
        orderTask.setOrderStyle(orderStyle);
        if (!StringUtil.getInstance().IsEmpty(orderType)){
            orderTask.setOrderType(orderType);
        }
        orderTask.setLogicId(logicId);
        orderTask.setModelId(modelId);
        boolean bool = orderTaskService.insertOrderTask(orderTask) ;
        if (bool){
            response.setCode(200);
            response.setMsg("添加成功！");
        }else {
            response.setCode(202);
            response.setMsg("添加失败！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 查询所有配置信息(供接入系统使用)
     */

    @GetMapping(value = "getTask")
    public String getTask(@RequestParam String applyId,
                          @RequestParam String applyKey,
                          @RequestParam String orderStyle,
                          @RequestParam String orderType){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(applyKey)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        if (!applyInfoService.checkApplyKey(applyId,applyKey)) {
            response.setCode(202);
            response.setMsg("应用号或者应用密码有误！");
            return JSONObject.toJSONString(response);
        }

        JSONArray data = orderTaskService.getTaskJson(applyId,orderStyle,orderType) ;
        if (data!=null && data.size()>0){
            response.setCode(200);
            response.setData(data);
        }else {
            response.setCode(203);
            response.setMsg("未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 查询所有配置信息
     */

    @GetMapping(value = "getTaskList")
    public String getTaskList(@RequestParam String applyId,
                          @RequestParam String orderStyle,
                          @RequestParam String orderType){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        JSONArray data = orderTaskService.getTaskJson(applyId,orderStyle,orderType) ;
        if (data!=null && data.size()>0){
            response.setCode(200);
            response.setData(data);
        }else {
            response.setCode(203);
            response.setMsg("未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 修改任务
     */

    @PostMapping(value ="updateOrderTask")
    public String updateOrderTask(@RequestBody JSONObject json){
        long id = json.getLongValue("id") ;
        String name = json.getString("name") ;
        long logicId = json.getLongValue("logicId") ;
        long modelId = json.getLongValue("modelId") ;

        Response response = new Response() ;
        if (orderTaskService.updateOrderTask(id,name,logicId,modelId)){
            response.setCode(200);
            response.setMsg("修改成功！");
        }else {
            response.setCode(202);
            response.setMsg("修改失败！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 删除任务
     */
    @PostMapping(value = "deleteOrderTask")
    public String deleteOrderTask(@RequestBody JSONObject json){
        long id = json.getLongValue("id") ;
        Response response = new Response() ;

        if (orderTaskService.deleteOrderTask(id)){
            response.setCode(200);
            response.setMsg("删除成功！");
        }else {
            response.setCode(202);
            response.setMsg("删除失败！");
        }
        return JSONObject.toJSONString(response) ;
    }


    /**
     * 根据应用号获取已有的工单种类
     */

    @GetMapping(value = "getOrderStyle")
    public String getOrderStyle(@RequestParam String applyId,
                                @RequestParam String applyKey){
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
            return JSONObject.toJSONString(response);
        }

        List<String> orderStyles = orderTaskService.getOrderStyle(applyId) ;

        if (orderStyles != null && orderStyles.size()>0){
            response.setCode(200);
            response.setData(orderStyles);
        }else {
            response.setCode(203);
            response.setMsg("获取失败！");
        }

        return JSONObject.toJSONString(response) ;
    }
}
