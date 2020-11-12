package com.pjh.orderservice.entity;

import java.util.Date;

/**
 * @author:彭建辉
 * @date:2020/9/10 08:55:52
 */
public class Logic {

    private long id ;           //工单逻辑ID
    private String applyId ;    //应用号
    private String name ;       //逻辑名
    private String data ;       //逻辑数据，json字符串
    private String createUser ; //配置该逻辑的用户
    private Date createTime ;   //创建时间

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        return "Logic{" +
                "id=" + id +
                ", applyId='" + applyId + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
