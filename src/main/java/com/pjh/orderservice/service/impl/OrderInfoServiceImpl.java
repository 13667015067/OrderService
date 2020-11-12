package com.pjh.orderservice.service.impl;

import com.pjh.orderservice.dao.OrderImgDao;
import com.pjh.orderservice.dao.OrderInfoDao;
import com.pjh.orderservice.dao.OrderWorkDao;
import com.pjh.orderservice.entity.OrderImg;
import com.pjh.orderservice.entity.OrderInfo;
import com.pjh.orderservice.entity.OrderWork;
import com.pjh.orderservice.service.OrderInfoService;
import com.pjh.orderservice.util.FileUtil;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 15:51:10
 */

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class) ;

    @Autowired
    private OrderInfoDao orderInfoDao ;

    @Autowired
    private OrderImgDao orderImgDao ;

    @Autowired
    private OrderWorkDao orderWorkDao ;

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    @Override
    public boolean addOrderInfo(OrderInfo orderInfo) {
        boolean bool = false ;
        try {
            bool = orderInfoDao.insertOrderInfo(orderInfo);
        } catch (SQLException e) {
            logger.info("**PJH**公单添加异常,orderInfo:{}",orderInfo.toString());
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public List<OrderInfo> getOrderList(String applyId, int orderState, int page, int amount) {

        int start = (page-1) * amount ;

        if (start < 0 || amount < 0){
            return null ;
        }

        try {
            List<OrderInfo> orderInfoList = orderInfoDao.getOrderInfoList(applyId,orderState,start,amount) ;


            if (orderInfoList != null && orderInfoList.size() > 0){
                return orderInfoList ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**SQLException异常，applyId:{},projectId:{},orderState:{},page:{},amount:{}",applyId,orderState,page,amount);
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {

        try {
            OrderInfo orderInfo = orderInfoDao.getOrderInfo(orderId) ;
            if (orderInfo != null){
                return orderInfo ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**SQLException异常，orderId:{},ErrorCode:{},SQLState",orderId,e.getErrorCode(),e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<OrderInfo> getOrderListByUser(String applyId, String user, int orderState, int page, int amount) {
        int start = (page-1) * amount ;

        if (start < 0 || amount < 0){
            return null ;
        }

        try {
            List<OrderInfo> orderInfoList = orderInfoDao.getOrderInfoListByUser(applyId,orderState,user,start,amount) ;

            if (orderInfoList != null && orderInfoList.size() > 0){
                return orderInfoList ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**SQLException异常，applyId:{},projectId:{},orderState:{},page:{},amount:{}",applyId,orderState,page,amount);
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean updateOrderInfo(String orderId, String orderContent, int state, String dealUser, Date endTime,String orderLogic) {
        boolean bool = false ;
        try {
            bool = orderInfoDao.updateOrderInfo(orderId,orderContent,dealUser,state,endTime,orderLogic);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bool;
    }

    @Override
    public int getAmount(String applyId, String user, int orderState, int amount) {
        if (!StringUtil.getInstance().IsEmpty(applyId) && amount != 0){
            try {
                int  allPage =(int)Math.ceil((double)orderInfoDao.getAllAmount(applyId,user,orderState) / amount)  ;

                return allPage ;
            } catch (SQLException e) {
                logger.error("**PJH**:applyId:{}",applyId);
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:applyId为空！");
        }
        return 0;
    }

    @Override
    public boolean delOrderInfo(String orderId) {
        boolean bool = false ;
        try {
            List<OrderWork> works = orderWorkDao.getWorksByOrderId(orderId) ;
            if (works != null && works.size()>0){
                for (OrderWork orderWork : works){
                    List<OrderImg> imgs = orderImgDao.getOrderImgList(orderWork.getId()) ;
                    if (imgs == null || imgs.size()<1){
                        continue;
                    }
                    String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    String subPath = ymd.substring(0,4) + "/" + ymd.substring(4) + "/" + orderWork.getId();
                    String imgPath = uploadFilePath +  subPath;
                    FileUtil.delFile(imgPath) ;
                    bool = orderImgDao.delImgs(orderWork.getId()) ;
                    if (!bool){
                        logger.error("**PJH**图片删除失败!workId:{}",orderWork.getId());
                    }
                }
            }

            if (orderWorkDao.delWorks(orderId)){
                bool = orderInfoDao.delOrderInfo(orderId) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }
}
