package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.OrderWork;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 14:02:42
 */
@Repository
public interface OrderWorkDao {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addOrderWork(@Param("orderWork")OrderWork orderWork) throws SQLException ;

    List<OrderWork> getWorksByOrderId(@Param("orderId")String orderId) throws SQLException ;

    OrderWork getWorkById(@Param("id")long id) throws SQLException ;

    boolean updateWork(@Param("work")OrderWork work) throws SQLException ;

    boolean delWorks(@Param("orderId")String orderId) throws SQLException ;
}
