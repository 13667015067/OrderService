package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.OrderImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:46:50
 */

@Repository
public interface OrderImgDao {

    boolean insertOrderImg(@Param("orderImg")OrderImg orderImg) throws SQLException ;

    List<OrderImg> getOrderImgList(@Param("workId")long workId) throws SQLException ;

    boolean delImgs(@Param("workId") long workId) throws SQLException ;
}
