package com.pjh.orderservice.config;

/**
 * @author:彭建辉
 * @date:2020/8/26 15:50:57
 */
public class OrderConfig {

    public static int IMGTYPENO = 0 ; //未处理的公单图片类型

    public static int IMGTYPEDEAL = 1 ; //未处理的公单图片类型

    public static int NOASSIGN = 0 ; //初始状态

    public static int ASSIGN = 1 ; //处理中状态

    public static int DEAL = 2 ; //处理完状态

    public static int COMMONUSER = 1 ; //一般用户权限

    public static int SUPERUSER = 0 ; //管理员用户权限

    public static String ORDERIDHEADER = "80" ; //订单号头部两位

    public static int NOWORK = 0 ; //待完成流程

    public static int OKWORK = 1 ; //已完成流程

    public static int GOBACKWORK = 2 ; //回退流程

    public static String key = "2020ctzk";

}
