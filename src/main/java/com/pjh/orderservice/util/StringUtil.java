package com.pjh.orderservice.util;

import java.util.Random;

/**
 * @author:彭建辉
 * @date:2020/7/8 23:46:18
 */
public class StringUtil {

    private static StringUtil stringUtil ;

    public static StringUtil getInstance(){
        if (stringUtil == null){
            stringUtil = new StringUtil();
        }
        return stringUtil;
    }
    public  boolean IsEmpty(String str){
        if (str == null || "".equals(str)){
            return true ;
        }
        return false ;
    }

    public int getRandomInt(int min , int max){
        Random random = new Random() ;
        int num = random.nextInt(max) % (max - min + 1) + min;
        return num ;
    }

    //生成随机数字和字母,
    public String getStringRandom(int length) {

        String val = "";
        Random random = new Random();
        //length为几位密码
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
