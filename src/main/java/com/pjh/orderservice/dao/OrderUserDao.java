package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.OrderUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:10:51
 */

@Repository
public interface OrderUserDao {

    OrderUser getUserByAccount(@Param("account") String account) throws SQLException ;

    boolean addUser(@Param("user") OrderUser user) throws SQLException;
}
