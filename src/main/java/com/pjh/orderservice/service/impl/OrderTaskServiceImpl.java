package com.pjh.orderservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.dao.LogicDao;
import com.pjh.orderservice.dao.ModelDao;
import com.pjh.orderservice.dao.OrderTaskDao;
import com.pjh.orderservice.entity.Logic;
import com.pjh.orderservice.entity.Model;
import com.pjh.orderservice.entity.OrderTask;
import com.pjh.orderservice.service.OrderTaskService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:49:06
 */

@Service
public class OrderTaskServiceImpl implements OrderTaskService {

    private Logger logger = LoggerFactory.getLogger(OrderTaskServiceImpl.class) ;

    @Autowired
    private OrderTaskDao orderTaskDao;

    @Autowired
    private ModelDao modelDao ;

    @Autowired
    private LogicDao logicDao ;

    @Override
    public OrderTask getOrderTask(String applyId, String orderStyle, String taskName) {
        try {
            OrderTask orderTask = orderTaskDao.getOrderTask(applyId,orderStyle,taskName) ;
            if (orderTask != null){
                return orderTask ;
            }
        } catch (SQLException e) {
            logger.info("**PJH**:applyId:{},orderStyle:{},taskName:{}",applyId,orderStyle,taskName);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertOrderTask(OrderTask orderTask) {
        boolean bool = false ;
        if (orderTask != null){
            try {
                bool = orderTaskDao.addOrderTask(orderTask) ;
            } catch (SQLException e) {
                logger.info("**PJH**:orderTask:{}",orderTask.toString());
                e.printStackTrace();
            }
        }else {
            logger.info("**PJH**:orderTask为空！");
        }

        return bool;
    }

    @Override
    public JSONArray getTaskJson(String applyId, String orderStyle, String orderType) {
        if (StringUtil.getInstance().IsEmpty(applyId)) {
            return null;
        }

        try {
            List<OrderTask> orderTasks = orderTaskDao.getOrderTaskList(applyId,orderStyle,orderType) ;
            if (orderTasks != null && orderTasks.size()>0){
                JSONArray tasks = new JSONArray() ;
                for (OrderTask orderTask : orderTasks){
                    JSONObject task = new JSONObject() ;
                    task.put("id",orderTask.getId()) ;
                    task.put("applyId",orderTask.getApplyId()) ;
                    task.put("name",orderTask.getName()) ;
                    task.put("orderStyle",orderTask.getOrderStyle()) ;
                    task.put("orderType",orderTask.getOrderType()) ;

                    Model model = modelDao.getModelById(orderTask.getModelId()) ;
                    if (model != null){
                        task.put("model",model) ;
                    }else {
                        task.put("model","null") ;
                    }

                    Logic logic = logicDao.getLogicById(orderTask.getLogicId()) ;
                    if (logic != null){
                        task.put("logic",logic) ;
                    }else {
                        task.put("logic","null") ;
                    }


                    tasks.add(task) ;
                }
                if (tasks !=null && tasks.size()>0){
                    return tasks ;
                }
            }
        } catch (SQLException e) {
            logger.info("**PJH**:applyId:{},orderStyle:{},orderType:{}",applyId,orderStyle,orderType);
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public boolean updateOrderTask(long id, String name, long logicId, long modelId) {
        boolean bool = false ;
        try {
            if (orderTaskDao.updateOrderTask(id,name,logicId,modelId)){
                bool =  true ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**id:{},name:{},logicId:{},modelId:{}",id,name,logicId,modelId);
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public boolean deleteOrderTask(long id) {
        boolean bool = false ;
        try {
            if (orderTaskDao.getOrderTaskById(id) != null){
                bool = orderTaskDao.deleteTaskById(id) ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**:id:{}！",id);
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public List<String> getOrderStyle(String applyId) {
        List<String> orderStyle = null ;

        try {
            orderStyle = orderTaskDao.getOrderStyle(applyId) ;
        } catch (SQLException e) {
            logger.error("**PJH**applyId:{}",applyId);
            e.printStackTrace();
        }

        return orderStyle;
    }
}
