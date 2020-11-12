package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.entity.Staff;
import com.pjh.orderservice.service.StaffService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/11/2 11:00:10
 */
@RestController
@CrossOrigin
@RequestMapping("staff")
public class StaffController {

    private Logger logger = LoggerFactory.getLogger(StaffController.class) ;


    @Autowired
    private StaffService staffService ;


    @PostMapping(value = "addStaff")
    public String addStaff(@RequestBody JSONObject json){
        String applyId = json.getString("applyId") ;
        String account = json.getString("account") ;
        String phone = json.getString("phone") ;
        String name = json.getString("name") ;
        String department = json.getString("department") ;

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)||
            StringUtil.getInstance().IsEmpty(account)){
            response.setCode(201);
            response.setMsg("参数有误！");
            return JSONObject.toJSONString(response) ;
        }
        if (staffService.isHaveStaff(applyId,account)){
            response.setCode(202);
            response.setMsg("该员工已存在！");
            return JSONObject.toJSONString(response) ;
        }
        Staff staff = new Staff() ;
        staff.setApplyId(applyId);
        staff.setAccount(account);
        if (!StringUtil.getInstance().IsEmpty(phone)){
            staff.setPhone(phone);
        }
        if (!StringUtil.getInstance().IsEmpty(name)){
            staff.setName(name);
        }
        if (!StringUtil.getInstance().IsEmpty(department)){
            staff.setDepartment(department);
        }

        if (staffService.insertStaff(staff)){
            response.setCode(200);
            response.setMsg("创建成功!");
        }else {
            response.setCode(203);
            response.setMsg("创建失败!");
        }
        return JSONObject.toJSONString(response);
    }

    @GetMapping(value = "getStaff")
    public String getStaff(@RequestParam String applyId){
        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyId)){
            response.setCode(201);
            response.setMsg("参数有误！");
            return JSONObject.toJSONString(response) ;
        }

        List<Staff> staffs = staffService.getStaffList(applyId) ;
        if (staffs!=null && staffs.size()>0){
            response.setCode(200);
            response.setData(staffs);
        }else {
            response.setCode(202);
            response.setMsg("暂未查询到数据！");
        }
        return  JSONObject.toJSONString(response) ;
    }


    @PostMapping(value = "updateStaff")
    public String updateStaff(@RequestBody JSONObject json){
        long id = json.getLongValue("id") ;
        String phone = json.getString("phone") ;
        String name = json.getString("name") ;
        String department = json.getString("department") ;
        Response response = new Response() ;

        Staff staff = staffService.getStaffById(id) ;
        if (staff != null){
            if (!StringUtil.getInstance().IsEmpty(phone)){
                staff.setPhone(phone);
            }
            if (!StringUtil.getInstance().IsEmpty(name)){
                staff.setName(name);
            }
            if (!StringUtil.getInstance().IsEmpty(department)){
                staff.setDepartment(department);
            }
            if (staffService.updateStaffById(staff)){
                response.setCode(200);
                response.setMsg("修改成功！");
            }else {
                response.setCode(201);
                response.setMsg("修改失败！");
            }
        }else {
            response.setCode(202);
            response.setMsg("该员工不存在！");
        }
        return  JSONObject.toJSONString(response) ;
    }


    @PostMapping(value = "deleteStaff")
    public String deleteStaff(@RequestBody JSONObject json){
        long id = json.getLongValue("id") ;

        Response response = new Response() ;

        Staff staff = staffService.getStaffById(id) ;
        if (staff != null){

            if (staffService.deleteStaff(id)){
                response.setCode(200);
                response.setMsg("删除成功！");
            }else {
                response.setCode(201);
                response.setMsg("删除失败！");
            }
        }else {
            response.setCode(202);
            response.setMsg("该员工不存在！");
        }
        return  JSONObject.toJSONString(response) ;
    }
}
