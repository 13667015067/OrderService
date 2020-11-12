package com.pjh.orderservice.service.impl;

import com.pjh.orderservice.dao.OrderUserDao;
import com.pjh.orderservice.entity.OrderUser;
import com.pjh.orderservice.service.OrderUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:11:39
 */

@Service
public class OrderUserServiceImpl implements OrderUserService {

    private Logger logger = LoggerFactory.getLogger(OrderUserServiceImpl.class) ;

    @Autowired
    private OrderUserDao orderUserDao ;

    @Override
    public boolean checkAuthority(String account, int authority) {

        try {
            OrderUser user =  orderUserDao.getUserByAccount(account) ;
            if (user != null && user.getType() == authority){
                return true ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertOrderUser(OrderUser orderUser) {
        boolean bool = false ;
        if (orderUser != null){
            try {
                bool = orderUserDao.addUser(orderUser) ;
            } catch (SQLException e) {
                logger.error("**PJH**:orderUser:{}",orderUser.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:orderUser！");
        }
        return bool;
    }

    @Override
    public boolean login(String account, String pwd) {
        boolean bool = false ;
        try {
            OrderUser user = orderUserDao.getUserByAccount(account) ;
            if (user !=null && user.getPwd().equals(pwd)){
                bool = true ;
            }
        } catch (SQLException e) {
            logger.error("**PJH**:account:{},pwd:{}",account,pwd);
            e.printStackTrace();
        }
        return bool;
    }
}
