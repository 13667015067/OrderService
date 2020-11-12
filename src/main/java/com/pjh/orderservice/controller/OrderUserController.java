package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.config.OrderConfig;
import com.pjh.orderservice.entity.OrderUser;
import com.pjh.orderservice.service.OrderUserService;
import com.pjh.orderservice.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author:彭建辉
 * @date:2020/9/10 14:39:59
 */

@RestController
@CrossOrigin
@RequestMapping("user")
public class OrderUserController {

    private Logger logger = LoggerFactory.getLogger(OrderUserController.class) ;

    @Autowired
    private OrderUserService orderUserService ;

    /**
     * 创建用户
     */
    @PostMapping(value = "addUser")
    public String addUser(@RequestBody JSONObject json){
        String account = json.getString("account") ;
        String pwd = json.getString("pwd") ;
        String phone = json.getString("phone") ;
        String company = json.getString("company") ;
        String user = json.getString("user") ;

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(account) ||
                StringUtil.getInstance().IsEmpty(pwd) ||
                StringUtil.getInstance().IsEmpty(phone) ||
                StringUtil.getInstance().IsEmpty(user)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**：account：{},pwd:{},phone：{},user:{}",account,pwd,phone,user);
            return JSONObject.toJSONString(response) ;
        }

        boolean bool = orderUserService.checkAuthority(user, OrderConfig.SUPERUSER) ;


        if (!bool){
            response.setCode(202);
            response.setMsg("权限不够！");
            logger.error("**PJH**：User-account：{}",user);
            return JSONObject.toJSONString(response) ;
        }

        OrderUser orderUser = new OrderUser(account,pwd,OrderConfig.COMMONUSER,phone,company);
        bool = orderUserService.insertOrderUser(orderUser) ;

        if (bool){
            response.setCode(200);
            response.setMsg("添加成功！");
        }else {
            logger.error("**PJH**：OrderUser{}",orderUser.toString());
            response.setCode(203);
            response.setMsg("添加失败！");
        }

        return JSONObject.toJSONString(response) ;
    }


    @PostMapping(value = "login")
    public String login(@RequestBody JSONObject json) {

        String account = json.getString("account") ;
        String pwd = json.getString("pwd") ;

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(account) ||
                StringUtil.getInstance().IsEmpty(pwd)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**：account：{},pwd:{}",account,pwd);
            return JSONObject.toJSONString(response) ;
        }

        boolean bool = orderUserService.login(account,pwd) ;

        if (bool){
            response.setCode(200);
            response.setMsg("登录成功！");
        }else{
            response.setCode(202);
            response.setMsg("用户名或密码不正确！");
        }

        return JSONObject.toJSONString(response) ;
    }
}
