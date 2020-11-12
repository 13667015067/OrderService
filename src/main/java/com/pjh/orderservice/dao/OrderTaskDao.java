package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.OrderTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:45:08
 */

@Repository
public interface OrderTaskDao {

    OrderTask getOrderTask(@Param("applyId") String applyId,
                         @Param("orderStyle") String orderStyle,
                         @Param("name") String name) throws SQLException ;

    OrderTask getOrderTaskById(@Param("id") long id) throws SQLException ;


    boolean addOrderTask(@Param("orderTask") OrderTask orderTask) throws SQLException ;

    boolean updateOrderTask(@Param("id") long id,
                            @Param("name") String name,
                            @Param("logicId") long logicId,
                            @Param("modelId") long modelId) throws SQLException ;

    List<OrderTask> getOrderTaskList(@Param("applyId") String applyId,
                                   @Param("orderStyle") String orderStyle,
                                   @Param("orderType") String orderType) throws SQLException ;

    boolean deleteTaskById(@Param("id") long id);

    List<String> getOrderStyle(@Param("applyId") String applyId) throws SQLException ;
}
