package com.pjh.orderservice.entity;

import java.util.Date;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:32:48
 */
public class OrderWork {

    private long id ;          //流程id
    private String orderId;    //工单号
    private int nodeId ;       //节点顺序
    private String nodeType ;  //节点类型
    private String workData;   //流程数据
    private int nodeState ;    //节点状态
    private String dealUser ;  //处理用户
    private String orderLogic; //工单逻辑
    private Date createTime ;  //创建时间
    private Date endTime ;     //结束时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getWorkData() {
        return workData;
    }

    public void setWorkData(String workData) {
        this.workData = workData;
    }

    public int getNodeState() {
        return nodeState;
    }

    public void setNodeState(int nodeState) {
        this.nodeState = nodeState;
    }

    public String getDealUser() {
        return dealUser;
    }

    public void setDealUser(String dealUser) {
        this.dealUser = dealUser;
    }

    public String getOrderLogic() {
        return orderLogic;
    }

    public void setOrderLogic(String orderLogic) {
        this.orderLogic = orderLogic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "OrderWork{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", nodeId=" + nodeId +
                ", nodeType='" + nodeType + '\'' +
                ", workData='" + workData + '\'' +
                ", nodeState=" + nodeState +
                ", dealUser='" + dealUser + '\'' +
                ", orderLogic='" + orderLogic + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                '}';
    }
}
