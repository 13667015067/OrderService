package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.entity.Model;
import com.pjh.orderservice.entity.Staff;
import com.pjh.orderservice.service.ApplyInfoService;
import com.pjh.orderservice.service.ModelService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:彭建辉
 * @date:2020/9/11 16:10:39
 */
@RestController
@CrossOrigin
@RequestMapping("model")
public class ModelController {

    private Logger logger = LoggerFactory.getLogger(ModelController.class) ;

    @Autowired
    private ApplyInfoService applyInfoService ;

    @Autowired
    private ModelService modelService ;


    /**
     * 创建工单模板
     * @param applyId
     * @param data
     * @param account
     * @return
     */
    @PostMapping(value = "addModel")
    public String addLogic(@RequestParam String applyId,
                           @RequestParam String modelName,
                           @RequestParam String data,
                           @RequestParam String account){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId) ||
                StringUtil.getInstance().IsEmpty(data) ||
                StringUtil.getInstance().IsEmpty(account)||
                StringUtil.getInstance().IsEmpty(modelName)){
            logger.error("**PJH**:applyId:{},data:{},account:{}",applyId,data,account);
            response.setCode(201);
            response.setMsg("参数不全！");
            return JSONObject.toJSONString(response) ;
        }


        if (modelService.isHaveLogic(applyId,modelName)) {
            response.setCode(202);
            response.setMsg("同一应用下，流程名请勿重复！");
            logger.error("**PJH**:applyId:{},name:{}",applyId,modelName);
            return JSONObject.toJSONString(response) ;
        }
        Model model = new Model() ;
        model.setApplyId(applyId);
        model.setName(modelName);
        model.setData(data);
        model.setCreateUser(account);
        model.setCreateTime(new Date());
        boolean bool = modelService.insertModel(model) ;

        if (bool){
            response.setCode(200);
            response.setMsg("保存成功！");
        }else {
            response.setCode(202);
            response.setMsg("保存失败！");
        }

        return JSONObject.toJSONString(response) ;
    }

    /**
     * 查询所有模板信息
     */

    @GetMapping(value = "getModelByPage")
    public String getModelByPage(@RequestParam String applyId,
                              @RequestParam int page,
                              @RequestParam int amount){


        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                page<1||amount<1){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        int allPage = modelService.getAllPage(applyId,amount) ;
        List<Model> models = modelService.getModelByPage(applyId,page,amount) ;
        if (models != null && models.size()>0){
            Map<String,Object> map = new HashMap<>() ;
            map.put("pageSize",allPage);
            map.put("models",models) ;
            response.setCode(200);
            response.setData(map);
        }else {
            response.setCode(203);
            response.setMsg("暂未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 根据模板名称模糊查询
     */

    @GetMapping(value = "getModelByName")
    public String getModelByName(@RequestParam String applyId,
                                 @RequestParam String modelName){


        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(modelName)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }


        List<Model> models = modelService.getModelByName(applyId,modelName) ;
        if (models != null && models.size()>0){
            response.setCode(200);
            response.setData(models);
        }else {
            response.setCode(203);
            response.setMsg("暂未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 根据模板id删除模板
     */

    @PostMapping(value = "deleteModelById")
    public String deleteModelById(@RequestBody JSONObject json){
        long id = json.getLongValue("id") ;
        Response response = new Response() ;

        if (modelService.deleteModelById(id)){
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

    @PostMapping(value = "updateModel")
    public String updateModel(@RequestParam long id,
                              @RequestParam String name,
                              @RequestParam String data){
        Response response = new Response() ;

        if (modelService.updateModelById(id,name,data)){
            response.setCode(200);
            response.setMsg("修改成功！");
        }else {
            response.setCode(202);
            response.setMsg("修改失败！");
        }
        return  JSONObject.toJSONString(response) ;
    }


    /**
     * 根据应用号获取所有模板名
     */

    @GetMapping(value = "getAllModelName")
    public String getAllModelName(@RequestParam String applyId){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)){
            response.setCode(201);
            response.setMsg("参数问题！");
            return JSONObject.toJSONString(response) ;
        }

        JSONArray data = modelService.getModelName(applyId) ;
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
