package com.pjh.orderservice.service;

import com.pjh.orderservice.entity.OrderWork;
import com.pjh.orderservice.entity.WorkWithImg;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 14:03:33
 */
public interface OrderWorkService {

    int insertOrderWork(OrderWork orderWork);

    List<WorkWithImg> getWorksByOrderId(String orderId) ;

    List<WorkWithImg> getWorkWithImg(String orderId) ;

    OrderWork getWorkById(long workId) ;

    boolean updateWork(OrderWork work) ;
}
