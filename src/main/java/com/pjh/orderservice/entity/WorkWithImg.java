package com.pjh.orderservice.entity;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/10/26 10:28:16
 */
public class WorkWithImg {

    private OrderWork work ;
    private List<OrderImg> imgs ;

    public OrderWork getWork() {
        return work;
    }

    public void setWork(OrderWork work) {
        this.work = work;
    }

    public List<OrderImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<OrderImg> imgs) {
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "WorkWithImg{" +
                "work=" + work +
                ", imgs=" + imgs +
                '}';
    }
}
