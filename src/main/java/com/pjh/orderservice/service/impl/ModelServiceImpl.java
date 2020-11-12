package com.pjh.orderservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.dao.ModelDao;
import com.pjh.orderservice.entity.Logic;
import com.pjh.orderservice.entity.Model;
import com.pjh.orderservice.service.ModelService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.APOptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 09:18:52
 */

@Service
public class ModelServiceImpl implements ModelService {

    private Logger logger = LoggerFactory.getLogger(ModelServiceImpl.class) ;

    @Autowired
    private ModelDao modelDao ;

    @Override
    public boolean insertModel(Model model) {
        boolean bool = false ;
        if (model != null){
            try {
                bool = modelDao.addModel(model) ;
            } catch (SQLException e) {
                logger.error("**PJH**:model:{}",model.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:model为空！");
        }

        return bool;
    }

    @Override
    public List<Model> getModelByPage(String applyId, int page, int amount) {
        List<Model> models = new ArrayList<>() ;
        if (!StringUtil.getInstance().IsEmpty(applyId)){
            try {
                int start = (page - 1) * amount ;
                models =  modelDao.getModelByPage(applyId,start,amount) ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyId:{}",applyId);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyId为空！");
        }
        return models;
    }

    @Override
    public List<Model> getModelByName(String applyId, String modelName) {
        List<Model> models = new ArrayList<>() ;
        if (!StringUtil.getInstance().IsEmpty(applyId)&&
                !StringUtil.getInstance().IsEmpty(modelName)){
            try {
                models =  modelDao.getModelByName(applyId,modelName) ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyId:{}",applyId);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyId为空！");
        }
        return models;
    }

    @Override
    public boolean deleteModelById(long id) {
        boolean bool = false ;
        try {
            if (modelDao.getModelById(id) != null){
                bool = modelDao.deleteModelById(id) ;
            }

        } catch (SQLException e) {
            logger.error("**PJH**:id:{}！",id);
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public boolean updateModelById(long id, String name, String data) {
        boolean bool = false ;
        try {
            Model model = modelDao.getModelById(id) ;
            if (model != null){
                if (!StringUtil.getInstance().IsEmpty(name)){
                    model.setName(name);
                }
                if (!StringUtil.getInstance().IsEmpty(data)){
                    model.setData(data);
                }
                if (modelDao.updateModel(model)){
                    bool = true ;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bool;
    }

    @Override
    public int getAllPage(String applyId, int amount) {
        if (!StringUtil.getInstance().IsEmpty(applyId) && amount != 0){
            try {
                int  allPage =(int)Math.ceil((double)modelDao.getAllAmount(applyId) / amount)  ;

                return allPage ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyId:{}",applyId);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyId为空！");
        }
        return 0;
    }

    @Override
    public JSONArray getModelName(String applyId) {
        try {
            List<Model> modelList = modelDao.getAllModel(applyId) ;
            JSONArray jsonArray = new JSONArray() ;
            if (modelList != null && modelList.size()>0){
                for (Model model : modelList) {
                    JSONObject json = new JSONObject();
                    json.put("id",model.getId()) ;
                    json.put("name",model.getName()) ;
                    jsonArray.add(json) ;
                }
                return jsonArray ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**applyId:{}",applyId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isHaveLogic(String applyId, String modelName) {
        boolean bool = false ;
        try {
            Model model = modelDao.getModelByNameAndApplyId(applyId,modelName) ;
            if (model != null){
                bool = true ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**applyId:{},name:{}",applyId,modelName);
            e.printStackTrace();
        }
        return bool;
    }
}
