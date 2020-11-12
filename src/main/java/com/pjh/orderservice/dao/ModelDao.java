package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.Model;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 09:16:51
 */
@Repository
public interface ModelDao {

    boolean addModel(@Param("model")Model model) throws SQLException ;

    List<Model> getModelByPage(@Param("applyId")String applyId,
                            @Param("start")int start,
                            @Param("amount")int amount) throws SQLException ;

    List<Model> getModelByName(@Param("applyId")String applyId,
                               @Param("modelName")String modelName) throws SQLException ;

    Model getModelById(@Param("id")long id) throws SQLException ;

    int getAllAmount(@Param("applyId")String applyId) throws SQLException ;

    boolean deleteModelById(@Param("id")long id) throws SQLException ;

    boolean updateModel(@Param("model")Model model) throws SQLException ;

    List<Model> getAllModel(@Param("applyId")String applyId) throws SQLException ;

    Model getModelByNameAndApplyId(@Param("applyId")String applyId,
                                   @Param("modelName")String modelName) throws SQLException ;
}
