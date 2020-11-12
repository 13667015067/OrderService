package com.pjh.orderservice.service;

import com.alibaba.fastjson.JSONArray;
import com.pjh.orderservice.entity.OrderTask;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:47:27
 */
public interface OrderTaskService {

    OrderTask getOrderTask(String applyId, String orderStyle, String taskName) ;

    boolean insertOrderTask(OrderTask orderTask) ;

    JSONArray getTaskJson(String applyId, String orderStyle, String orderType) ;

    boolean updateOrderTask(long id, String name, long logicId, long modelId) ;

    boolean deleteOrderTask(long id) ;

    List<String> getOrderStyle(String applyId);
}
