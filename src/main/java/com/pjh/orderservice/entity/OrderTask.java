package com.pjh.orderservice.entity;

/**
 * @author:彭建辉
 * @date:2020/8/25 15:26:30
 */
public class OrderTask {

    private long id ;            //配置ID(自增)
    private String applyId ;     //应用号
    private String name ;        //流程名
    private String orderStyle ;  //工单种类
    private String orderType ; //工单类型  0:自动派发；1:人工指派；2:自动抢单
    private long logicId ;   //工单流程ID
    private long modelId ;   //工单模板ID

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getLogicId() {
        return logicId;
    }

    public void setLogicId(long logicId) {
        this.logicId = logicId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    @Override
    public String toString() {
        return "OrderTask{" +
                "id=" + id +
                ", applyId='" + applyId + '\'' +
                ", name='" + name + '\'' +
                ", orderStyle='" + orderStyle + '\'' +
                ", orderType='" + orderType + '\'' +
                ", logicId='" + logicId + '\'' +
                ", modelId='" + modelId + '\'' +
                '}';
    }
}
