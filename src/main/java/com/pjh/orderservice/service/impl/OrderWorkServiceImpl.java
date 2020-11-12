package com.pjh.orderservice.service.impl;

import com.pjh.orderservice.dao.OrderImgDao;
import com.pjh.orderservice.dao.OrderWorkDao;
import com.pjh.orderservice.entity.OrderImg;
import com.pjh.orderservice.entity.OrderWork;
import com.pjh.orderservice.entity.WorkWithImg;
import com.pjh.orderservice.service.OrderWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 14:05:54
 */
@Service
public class OrderWorkServiceImpl implements OrderWorkService {

    private Logger logger = LoggerFactory.getLogger(OrderWorkServiceImpl.class) ;

    @Autowired
    private OrderWorkDao orderWorkDao ;

    @Autowired
    private OrderImgDao orderImgDao ;

    @Override
    public int insertOrderWork(OrderWork orderWork) {
        int id = 0 ;
        if (orderWork != null){
            try {
                id = orderWorkDao.addOrderWork(orderWork) ;
            } catch (SQLException e) {
                logger.error("**PJH**:orderWork:{}",orderWork.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:orderWork！");
        }
        return id;
    }

    @Override
    public List<WorkWithImg> getWorksByOrderId(String orderId) {
        List<WorkWithImg> workWithImgs = new ArrayList<>() ;
        if (orderId != null){
            try {
                List<OrderWork> works = orderWorkDao.getWorksByOrderId(orderId) ;
                if (works != null && works.size()>0){
                    for (OrderWork orderWork : works){
                        WorkWithImg workWithImg = new WorkWithImg() ;
                        workWithImg.setWork(orderWork);
                        List<OrderImg> imgs = orderImgDao.getOrderImgList(orderWork.getId()) ;
                        if (imgs != null && imgs.size()>0){
                            workWithImg.setImgs(imgs);
                        }
                        workWithImgs.add(workWithImg) ;
                    }
                }

            } catch (SQLException e) {
                logger.error("**PJH**:orderId:{}",orderId);
                e.printStackTrace();
            }
        }
        return workWithImgs;
    }

    @Override
    public List<WorkWithImg> getWorkWithImg(String orderId) {
        List<WorkWithImg> workWithImgs = new ArrayList<>();
        if (orderId != null){
            try {
                List<OrderWork> works = orderWorkDao.getWorksByOrderId(orderId) ;

                if (works != null && works.size()>0){
                    for (OrderWork orderWork : works){
                        WorkWithImg workWithImg = new WorkWithImg();
                        List<OrderImg> imgs = orderImgDao.getOrderImgList(orderWork.getId()) ;
                        if (imgs!=null&&imgs.size()>0){
                            workWithImg.setImgs(imgs);
                        }
                        workWithImg.setWork(orderWork);
                        workWithImgs.add(workWithImg);
                    }
                    return workWithImgs ;
                }
            } catch (SQLException e) {
                logger.error("**PJH**:orderId:{}",orderId);
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public OrderWork getWorkById(long workId) {
        OrderWork work = null ;
        try {
            work = orderWorkDao.getWorkById(workId) ;
        } catch (SQLException e) {
            logger.error("**PJH**:workId:{}",workId);
            e.printStackTrace();
        }
        return work;
    }

    @Override
    public boolean updateWork(OrderWork work) {
        boolean bool = false ;
        if (work!=null){
            try {
                bool = orderWorkDao.updateWork(work) ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
}
