package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.Logic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 09:02:31
 */

@Repository
public interface LogicDao {

    boolean addLogic(@Param("logic") Logic logic) throws SQLException;

    Logic getLogicById(@Param("id") long id) throws SQLException ;

    List<Logic> getLogicByName(@Param("applyId")String applyId,
                               @Param("logicName")String logicName) throws SQLException ;

    List<Logic> getLogicByPage(@Param("applyId")String applyId,
                               @Param("start")int start,
                               @Param("amount")int amount) throws SQLException ;

    List<Logic> getAllLogic(@Param("applyId")String applyId) throws SQLException ;

    boolean deleteLogicById(long id);

    boolean updateLogic(@Param("logic") Logic logic) throws SQLException ;

    int getAllAmount(String applyId) throws SQLException ;

    Logic getLogicByNameAndApplyId(@Param("applyId")String applyId,
                                   @Param("logicName")String logicName) throws SQLException ;
}
