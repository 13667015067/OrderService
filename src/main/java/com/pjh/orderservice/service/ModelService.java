package com.pjh.orderservice.service;

import com.alibaba.fastjson.JSONArray;
import com.pjh.orderservice.entity.Logic;
import com.pjh.orderservice.entity.Model;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 09:18:25
 */
public interface ModelService {

    boolean insertModel(Model model) ;

    List<Model> getModelByPage(String applyId, int page, int amount) ;

    List<Model> getModelByName(String applyId, String modelName) ;

    boolean deleteModelById(long id) ;

    boolean updateModelById(long id,String name,String data) ;

    int getAllPage(String applyId, int amount) ;

    JSONArray getModelName(String applyId);

    boolean isHaveLogic(String applyId, String modelName);
}
