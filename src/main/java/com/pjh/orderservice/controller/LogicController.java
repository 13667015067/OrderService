package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.entity.Logic;
import com.pjh.orderservice.service.ApplyInfoService;
import com.pjh.orderservice.service.LogicService;
import com.pjh.orderservice.util.StringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:彭建辉
 * @date:2020/9/11 14:29:14
 */
@RestController
@CrossOrigin
@RequestMapping("logic")
public class LogicController {

    private Logger logger = LoggerFactory.getLogger(LogicController.class) ;

    @Autowired
    private ApplyInfoService applyInfoService ;

    @Autowired
    private LogicService logicService ;


    /**
     * 创建工单流程（逻辑）
     * @param applyId
     * @param name
     * @param data
     * @param account
     * @return
     */
    @PostMapping(value = "addLogic")
    public String addLogic(@RequestParam String applyId,
                           @RequestParam String name,
                           @RequestParam String data,
                           @RequestParam String account){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId) ||
                StringUtil.getInstance().IsEmpty(data) ||
                StringUtil.getInstance().IsEmpty(name)||
                StringUtil.getInstance().IsEmpty(account)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**:applyId:{},data:{},account:{},name:{}",applyId,data,account,name);
            return JSONObject.toJSONString(response) ;
        }

        if (logicService.isHaveLogic(applyId,name)) {
            response.setCode(202);
            response.setMsg("同一应用下，流程名请勿重复！");
            logger.error("**PJH**:applyId:{},name:{}",applyId,name);
            return JSONObject.toJSONString(response) ;
        }
        Logic logic = new Logic() ;
        logic.setApplyId(applyId);
        logic.setName(name);
        logic.setData(data);
        logic.setCreateUser(account);
        logic.setCreateTime(new Date());
        boolean bool = logicService.insertLogic(logic) ;

        if (bool){
            response.setCode(200);
            response.setMsg("保存成功！");
        }else {
            response.setCode(203);
            response.setMsg("保存失败！");
        }


        return JSONObject.toJSONString(response) ;
    }

    /**
     * 查询所有流程模板信息
     */

    @GetMapping(value = "getLogicByPage")
    public String getLogicByPage(@RequestParam String applyId,
                              @RequestParam int page,
                              @RequestParam int amount){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                page<1||amount<1){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        int allPage = logicService.getAllPage(applyId,amount) ;

        List<Logic> logics = logicService.getLogicByPage(applyId,page,amount) ;
        if (logics != null && logics.size()>0){
            Map<String,Object> map = new HashMap<>() ;
            map.put("pageSize",allPage);
            map.put("logics",logics) ;
            response.setCode(200);
            response.setData(map);
        }else {
            response.setCode(203);
            response.setMsg("暂未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 根据流程名称模糊查询
     */

    @GetMapping(value = "getLogicByName")
    public String getLogicByName(@RequestParam String applyId,
                                 @RequestParam String logicName){


        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(logicName)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }


        List<Logic> logics = logicService.getLogicByName(applyId,logicName) ;
        if (logics != null && logics.size()>0){
            response.setCode(200);
            response.setData(logics);
        }else {
            response.setCode(203);
            response.setMsg("暂未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }

    /**
     * 根据模板id删除模板
     */

    @PostMapping(value = "deleteLogicById")
    public String deleteLogicById(@RequestBody JSONObject json){
        long id = json.getLongValue("id") ;
        Response response = new Response() ;

        if (logicService.deleteModelById(id)){
            response.setCode(200);
            response.setMsg("删除成功！");
        }else {
            response.setCode(201);
            response.setMsg("删除失败！");
        }
        return  JSONObject.toJSONString(response) ;
    }


    /**
     * 根据模板id修改模板
     */

    @PostMapping(value = "updateLogic")
    public String updateLogic(@RequestParam long id,
                              @RequestParam String name,
                              @RequestParam String data){
        Response response = new Response() ;

        if (logicService.updateModelById(id,name,data)){
            response.setCode(200);
            response.setMsg("修改成功！");
        }else {
            response.setCode(202);
            response.setMsg("修改失败！");
        }
        return  JSONObject.toJSONString(response) ;
    }

    /**
     * 根据应用号获取所有流程名
     */

    @GetMapping(value = "getAllLogicName")
    public String getAllLogicName(@RequestParam String applyId){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        JSONArray data = logicService.getLogicName(applyId) ;
        if (data!=null && data.size()>0){
            response.setCode(200);
            response.setData(data);
        }else {
            response.setCode(203);
            response.setMsg("未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }
}
