package com.pjh.orderservice.service;

import com.pjh.orderservice.entity.ApplyInfo;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:25:16
 */
public interface ApplyInfoService {

    boolean insertApplyInfo(ApplyInfo applyInfo) ;

    boolean checkApplyKey(String applyId, String applyKey) ;

    List<ApplyInfo> getAllApply(String account,String applyName) ;

    String getApplyKey(String applyId) ;

    boolean isHaveApply(String account,String applyName) ;
}
