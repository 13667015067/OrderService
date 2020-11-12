package com.pjh.orderservice.service;

import com.pjh.orderservice.entity.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/11/2 10:54:55
 */

public interface StaffService {
    boolean insertStaff(Staff staff) ;

    boolean isHaveStaff(String applyId, String account) ;

    Staff getStaffById(long id) ;

    List<Staff> getStaffList(String applyId) ;

    boolean updateStaffById(Staff staff) ;

    boolean deleteStaff(long id) ;
}
