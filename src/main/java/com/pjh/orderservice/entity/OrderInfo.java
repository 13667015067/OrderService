package com.pjh.orderservice.entity;

import java.util.Date;

/**
 * @author:彭建辉
 * @date:2020/8/25 15:13:01
 */
public class OrderInfo {
    private String id ;              //公单号（主键）
    private String applyId ;         //应用号
    private String orderStyle ;      //工单种类
    private String orderType ;       //公单类型
    private String orderContent ;    //公单内容
    private int orderState ;         //0:初始状态；1:已经指派（处理中）；2:已处理
    private Date dealTime ;          //预处理日期 规定工单结束时间
    private String orderLogic ;      //工单逻辑
    private String nowDealUser ;     //当前处理用户
    private String taskName ;        //任务名
    private Date endTime ;           //工单实际结束时间
    private String createUser ;      //创建公单用户（可为空，如烟感报警）
    private Date createTime ;        //创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getOrderStyle() {
        return orderStyle;
    }

    public void setOrderStyle(String orderStyle) {
        this.orderStyle = orderStyle;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getOrderLogic() {
        return orderLogic;
    }

    public void setOrderLogic(String orderLogic) {
        this.orderLogic = orderLogic;
    }

    public String getNowDealUser() {
        return nowDealUser;
    }

    public void setNowDealUser(String nowDealUser) {
        this.nowDealUser = nowDealUser;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", applyId='" + applyId + '\'' +
                ", orderStyle='" + orderStyle + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderContent='" + orderContent + '\'' +
                ", orderState=" + orderState +
                ", dealTime=" + dealTime +
                ", orderLogic='" + orderLogic + '\'' +
                ", nowDealUser='" + nowDealUser + '\'' +
                ", taskName='" + taskName + '\'' +
                ", endTime=" + endTime +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
