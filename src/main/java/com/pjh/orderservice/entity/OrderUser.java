package com.pjh.orderservice.entity;

import com.pjh.orderservice.util.StringUtil;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:07:42
 */
public class OrderUser {

    private int id ;   //用户id（主键、自增）
    private String account ;  // 用户账号
    private String pwd ;      //用户密码
    private int type ;        //用户权限。0:超级管理员； 1:公司账号
    private String phone ;    //用户联系方式
    private String company ;  //所属公司名称

    public OrderUser(String account, String pwd, int type, String phone, String company) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.type = type;
        this.phone = phone;
        this.company = company;
    }

    public OrderUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Override
    public String toString() {
        return "OrderUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
