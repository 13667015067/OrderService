package com.pjh.orderservice.service.impl;

import com.pjh.orderservice.dao.StaffDao;
import com.pjh.orderservice.entity.Staff;
import com.pjh.orderservice.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/11/2 10:56:07
 */
@Service
public class StaffServiceImpl implements StaffService {
    private Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class) ;

    @Autowired
    private StaffDao staffDao ;
    @Override
    public boolean insertStaff(Staff staff) {
        boolean bool = false ;
        if (staff != null){
            try {
                bool = staffDao.addStaff(staff) ;
            } catch (SQLException e) {
                logger.error("**PJH**:staff:{}！",staff.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:staff为空！");
        }
        return bool;
    }

    @Override
    public boolean isHaveStaff(String applyId, String account) {
        boolean bool = false ;

        try {
            Staff staff = staffDao.getStaffByAccount(applyId,account) ;
            if (staff != null){
                bool = true ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public Staff getStaffById(long id) {

        try {
            Staff staff = staffDao.getStaffById(id) ;
            if (staff != null){
                return staff ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Staff> getStaffList(String applyId) {
        List<Staff> staffs = null ;

        try {
            staffs = staffDao.getStaffListByApplyId(applyId) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

    @Override
    public boolean updateStaffById(Staff staff) {
        boolean bool = false ;
        if (staff != null){
            try {
                bool = staffDao.updateStaffById(staff) ;
            } catch (SQLException e) {
                logger.error("**PJH**:staff:{}！",staff.toString());
                e.printStackTrace();
            }
        }else {
            logger.error("**PJH**:staff为空！");
        }
        return bool;
    }

    @Override
    public boolean deleteStaff(long id) {
        boolean bool = false ;
        try {
            bool = staffDao.deleteStaff(id) ;
        } catch (SQLException e) {
            logger.error("**PJH**:id:{}！",id);
            e.printStackTrace();
        }
        return bool;
    }
}
