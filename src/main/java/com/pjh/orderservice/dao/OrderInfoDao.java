package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.OrderInfo;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 15:41:10
 */
@Repository
public interface OrderInfoDao {

    boolean insertOrderInfo(@Param("orderInfo")OrderInfo orderInfo) throws SQLException;

    List<OrderInfo> getOrderInfoList(@Param("applyId")String applyId,
                                     @Param("orderState")int orderState,
                                     @Param("start")int start,
                                     @Param("amount")int amount) throws SQLException;

    OrderInfo getOrderInfo(@Param("orderId") String orderId) throws SQLException ;

    List<OrderInfo> getOrderInfoListByUser(@Param("applyId")String applyId,
                                     @Param("orderState")int orderState,
                                     @Param("user")String user,
                                     @Param("start")int start,
                                     @Param("amount")int amount) throws SQLException;

    boolean updateOrderInfo(@Param("orderId")String orderId,
                               @Param("orderContent")String orderContent,
                               @Param("dealUser")String dealUser,
                               @Param("state")int state,
                               @Param("endTime") Date endTime,
                               @Param("orderLogic") String orderLogic) throws SQLException;

    int getAllAmount(String applyId, String user, int orderState) throws SQLException;

    boolean delOrderInfo(@Param("orderId")String orderId) throws SQLException;
}
