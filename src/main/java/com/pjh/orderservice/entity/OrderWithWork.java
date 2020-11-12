package com.pjh.orderservice.entity;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/10/26 10:30:06
 */
public class OrderWithWork {

    private OrderInfo orderInfo ;
    private List<WorkWithImg> works ;

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<WorkWithImg> getWorks() {
        return works;
    }

    public void setWorks(List<WorkWithImg> works) {
        this.works = works;
    }

    @Override
    public String toString() {
        return "OrderWithWork{" +
                "orderInfo=" + orderInfo +
                ", works=" + works +
                '}';
    }
}
