package com.pjh.orderservice.service.impl;

import com.pjh.orderservice.dao.ApplyInfoDao;
import com.pjh.orderservice.entity.ApplyInfo;
import com.pjh.orderservice.service.ApplyInfoService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:27:07
 */
@Service
public class ApplyInfoServiceImpl implements ApplyInfoService {

    private Logger logger = LoggerFactory.getLogger(ApplyInfoServiceImpl.class) ;

    @Autowired
    private ApplyInfoDao applyInfoDao ;


    @Override
    public boolean insertApplyInfo(ApplyInfo applyInfo) {
        boolean bool = false ;

        if (applyInfo != null ){
            try {
                bool = applyInfoDao.addApplyInfo(applyInfo) ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyInfo:{}",applyInfo.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyInfo为空！");
        }

        return bool;
    }

    @Override
    public boolean checkApplyKey(String applyId, String applyKey) {
        boolean bool = false ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
                StringUtil.getInstance().IsEmpty(applyKey)){
            logger.error("**PJH**参数为空:applyId:{},applyKey:{}",applyId,applyKey);
            return false ;
        }

        try {
            ApplyInfo applyInfo = applyInfoDao.getApplyInfoById(applyId) ;
            if (applyInfo != null && applyInfo.getApplyKey()!= null){
                if (applyKey.equals(applyInfo.getApplyKey())){
                    bool = true ;
                }
            }else {
                logger.error("**PJH**应用号找不到对应的应用信息:applyId:{}",applyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public List<ApplyInfo> getAllApply(String account, String applyName) {
        List<ApplyInfo> applys = null ;

        if (account != null ){
            try {
                applys = applyInfoDao.getALLApply(account,applyName) ;
            } catch (SQLException e) {
                logger.error("**PJH**:account:{}",account);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:account为空！");
        }

        return applys;
    }

    @Override
    public String getApplyKey(String applyId) {

        if (StringUtil.getInstance().IsEmpty(applyId)){
            logger.error("**PJH**参数为空:applyId:{}",applyId);
            return null ;
        }

        try {
            ApplyInfo applyInfo = applyInfoDao.getApplyInfoById(applyId) ;
            if (applyInfo != null && applyInfo.getApplyKey()!= null){
                return applyInfo.getApplyKey() ;
            }else {
                logger.error("**PJH**应用号找不到对应的应用信息:applyId:{}",applyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isHaveApply(String account, String applyName) {
        boolean bool = false ;
        if (StringUtil.getInstance().IsEmpty(account)||
                StringUtil.getInstance().IsEmpty(applyName)){
            logger.error("**PJH**参数为空:account:{},applyName:{}",account,applyName);
            return false ;
        }

        try {
            ApplyInfo applyInfo = applyInfoDao.getApplyByAccountAndName(account,applyName) ;
            if (applyInfo != null ){
                bool = true ;
            }else {
                logger.error("**PJH**应用号找不到对应的应用信息:account:{},applyName:{}",account,applyName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }
}
