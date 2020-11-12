package com.pjh.orderservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.dao.LogicDao;
import com.pjh.orderservice.entity.Logic;
import com.pjh.orderservice.service.LogicService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 09:05:21
 */

@Service
public class LogicServiceImpl implements LogicService {

    private Logger logger = LoggerFactory.getLogger(LogicServiceImpl.class) ;


    @Autowired
    private LogicDao logicDao ;

    @Override
    public boolean insertLogic(Logic logic) {

        boolean bool = false ;
        if (logic != null){
            try {
                bool = logicDao.addLogic(logic) ;
            } catch (SQLException e) {
                logger.error("**PJH**:logic:{}",logic.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:logic为空！");
    }

        return bool;
    }

    @Override
    public Logic getLogicById(long id) {
        try {
            Logic logic = logicDao.getLogicById(id) ;
            if (logic != null){
                return logic ;
            }
        } catch (SQLException e) {
            logger.info("**PJH**:id:{}",id);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Logic> getLogicByPage(String applyId,int page, int amount) {
        List<Logic> logics = new ArrayList<>() ;
        if (!StringUtil.getInstance().IsEmpty(applyId)){
            try {
                int start = (page - 1) * amount ;
                logics =  logicDao.getLogicByPage(applyId,start,amount) ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyId:{}",applyId);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyId为空！");
        }
        return logics;
    }

    @Override
    public List<Logic> getLogicByName(String applyId, String logicName) {
        List<Logic> logics = new ArrayList<>() ;
        if (!StringUtil.getInstance().IsEmpty(applyId)&&
                !StringUtil.getInstance().IsEmpty(logicName)){
            try {
                logics =  logicDao.getLogicByName(applyId,logicName) ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyId:{}",applyId);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyId为空！");
        }
        return logics;
    }

    @Override
    public boolean deleteModelById(long id) {
        boolean bool = false ;
        try {
            if (logicDao.getLogicById(id) != null){
                bool = logicDao.deleteLogicById(id) ;
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
            Logic logic = logicDao.getLogicById(id) ;
            if (logic != null){
                if (!StringUtil.getInstance().IsEmpty(name)){
                    logic.setName(name);
                }
                if (!StringUtil.getInstance().IsEmpty(data)){
                    logic.setData(data);
                }
                if (logicDao.updateLogic(logic)){
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
                int  allPage =(int)Math.ceil((double)logicDao.getAllAmount(applyId) / amount)  ;

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
    public JSONArray getLogicName(String applyId) {

        try {
            List<Logic> logicList = logicDao.getAllLogic(applyId) ;
            JSONArray jsonArray = new JSONArray() ;
            if (logicList != null && logicList.size()>0){
                for (Logic logic : logicList) {
                    JSONObject json = new JSONObject();
                    json.put("id",logic.getId()) ;
                    json.put("name",logic.getName()) ;
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
    public boolean isHaveLogic(String applyId, String name) {
        boolean bool = false ;
        try {
            Logic logic = logicDao.getLogicByNameAndApplyId(applyId,name) ;
            if (logic != null){
                bool = true ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**applyId:{},name:{}",applyId,name);
            e.printStackTrace();
        }
        return bool;
    }
}
