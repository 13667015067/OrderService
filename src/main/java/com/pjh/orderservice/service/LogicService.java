package com.pjh.orderservice.service;

import com.alibaba.fastjson.JSONArray;
import com.pjh.orderservice.entity.Logic;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 09:04:46
 */
public interface LogicService {

    boolean insertLogic(Logic logic) ;

    Logic getLogicById(long id) ;

    List<Logic> getLogicByPage(String applyId,int page, int amount) ;

    List<Logic> getLogicByName(String applyId, String logicName) ;

    boolean deleteModelById(long id);

    boolean updateModelById(long id, String name, String data);

    int getAllPage(String applyId, int amount) ;

    JSONArray getLogicName(String applyId) ;

    boolean isHaveLogic(String applyId, String name);
}
