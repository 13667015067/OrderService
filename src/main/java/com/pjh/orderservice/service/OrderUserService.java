package com.pjh.orderservice.service;


import com.pjh.orderservice.entity.OrderUser;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:11:23
 */
public interface OrderUserService {

    boolean checkAuthority(String account , int authority) ;

    boolean insertOrderUser(OrderUser orderUser) ;

    boolean login(String account ,String pwd ) ;
}
