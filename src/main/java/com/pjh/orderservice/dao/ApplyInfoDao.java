package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.ApplyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 11:24:42
 */
@Repository
public interface ApplyInfoDao {

    boolean addApplyInfo(@Param("applyInfo")ApplyInfo applyInfo) throws SQLException ;

    ApplyInfo getApplyInfoById(@Param("id") String id) throws  SQLException ;

    List<ApplyInfo> getALLApply(@Param("account") String account,
                                @Param("applyName") String applyName) throws  SQLException ;

    ApplyInfo getApplyByAccountAndName(@Param("account") String account,
                         @Param("applyName") String applyName) throws  SQLException ;

}
