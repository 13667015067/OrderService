package com.pjh.orderservice.entity;

import java.util.Date;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:18:20
 */
public class ApplyInfo {

    private String id ;         //应用号
    private String applyName ;  //应用名
    private String applyKey  ;  //应用密钥
    private String createUser ; //创建用户
    private Date createTime ;   //创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyKey() {
        return applyKey;
    }

    public void setApplyKey(String applyKey) {
        this.applyKey = applyKey;
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
        return "ApplyInfo{" +
                "id='" + id + '\'' +
                ", applyName='" + applyName + '\'' +
                ", applyKey='" + applyKey + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
