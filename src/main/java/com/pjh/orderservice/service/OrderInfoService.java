package com.pjh.orderservice.service;

import com.pjh.orderservice.entity.OrderInfo;

import java.util.Date;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 15:47:05
 */
public interface OrderInfoService {
    boolean addOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> getOrderList(String applyId,
                             int orderState,
                             int page,
                             int amount) ;

    OrderInfo getOrderInfo(String orderId) ;

    List<OrderInfo> getOrderListByUser(String applyId,
                                 String user,
                                 int orderState,
                                 int page,
                                 int amount) ;

    boolean updateOrderInfo(String orderId, String orderContent, int state, String dealUser, Date endTime,String orderLogic) ;

    int getAmount(String applyId, String user, int orderState, int amount) ;

    boolean delOrderInfo(String orderId);
}
