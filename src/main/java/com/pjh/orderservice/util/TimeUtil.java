package com.pjh.orderservice.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.entity.Model;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:彭建辉
 * @date:2020/9/10 15:31:55
 */
public class TimeUtil {

    private static TimeUtil timeUtil ;

    public static TimeUtil getInstance(){
        if (timeUtil == null){
            timeUtil = new TimeUtil();
        }
        return timeUtil;
    }

    /**
     * 获取当前时间的字符串
     * @return 例如：20200910041134
     */
    public String getTimeStr(){
        Date date = new Date() ;
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        String timeStr = dateFormat.format(date) ;

        return timeStr ;
    }

    public Date StringToDate(String timeStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(timeStr, pos);
        return date ;
    }

    public String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = formatter.format(date);
        return timeStr ;
    }

}
