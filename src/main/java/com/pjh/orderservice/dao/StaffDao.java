package com.pjh.orderservice.dao;

import com.pjh.orderservice.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/11/2 10:47:16
 */

@Repository
public interface StaffDao {

    boolean addStaff(@Param("staff") Staff staff) throws SQLException;

    Staff getStaffByAccount(@Param("applyId") String applyId,
                            @Param("account") String account) throws SQLException;

    Staff getStaffById(@Param("id") long id) throws SQLException;

    List<Staff> getStaffListByApplyId(@Param("applyId") String applyId) throws SQLException;

    boolean updateStaffById(@Param("staff") Staff staff) throws SQLException;

    boolean deleteStaff(@Param("id") long id) throws  SQLException ;

}
