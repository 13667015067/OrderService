package com.pjh.orderservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.pjh.orderservice.common.Response;
import com.pjh.orderservice.config.OrderConfig;
import com.pjh.orderservice.entity.ApplyInfo;
import com.pjh.orderservice.service.ApplyInfoService;
import com.pjh.orderservice.service.OrderUserService;
import com.pjh.orderservice.util.DESEncryptTools;
import com.pjh.orderservice.util.Md5Util;
import com.pjh.orderservice.util.StringUtil;
import com.pjh.orderservice.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author:彭建辉
 * @date:2020/9/10 14:41:29
 */

@RestController
@CrossOrigin
@RequestMapping("apply")
public class ApplyInfoController {

    private Logger logger = LoggerFactory.getLogger(ApplyInfoController.class) ;


    @Autowired
    private ApplyInfoService applyInfoService ;

    @Autowired
    private OrderUserService orderUserService ;


    /**
     * 创建应用
     * @param json
     * @return
     */
    @PostMapping(value = "/addApplyInfo")
    public String addOrderInfo(@RequestBody JSONObject json){
        String applyName = json.getString("applyName") ;
        String account = json.getString("account") ;
        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(applyName) ||
                StringUtil.getInstance().IsEmpty(account)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**：applyName：{},account:{}",applyName,account);
            return JSONObject.toJSONString(response) ;
        }

        if (applyInfoService.isHaveApply(account,applyName)){
            response.setCode(202);
            response.setMsg("该应用已存在！");
            logger.error("**PJH**：applyName：{},account:{}",applyName,account);
            return JSONObject.toJSONString(response) ;
        }

        int num = StringUtil.getInstance().getRandomInt(10,99);
        String applyId = TimeUtil.getInstance().getTimeStr() + num ;

        String applyKey = StringUtil.getInstance().getStringRandom(12) ;

        ApplyInfo applyInfo = new ApplyInfo() ;
        applyInfo.setId(applyId);
        applyInfo.setApplyName(applyName);
        applyInfo.setApplyKey(applyKey);
        applyInfo.setCreateUser(account);
        applyInfo.setCreateTime(new Date());

        boolean bool = applyInfoService.insertApplyInfo(applyInfo) ;

        if(bool){
           response.setCode(200);
           response.setData(applyInfo);
        }else{
            response.setCode(202);
            response.setMsg("应用创建失败！");
        }

        return JSONObject.toJSONString(response) ;
    }


    /**
     * 查看应用
     */
    @PostMapping(value = "/getApplyInfos")
    public String getApplyInfos(@RequestBody JSONObject json){
        String account = json.getString("account") ;
        String applyName = json.getString("applyName") ;

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(account)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**：account:{}",account);
            return JSONObject.toJSONString(response) ;
        }

        List<ApplyInfo> applys = applyInfoService.getAllApply(account,applyName) ;

        if (applys != null && applys.size()>0){
            for (ApplyInfo applyInfo : applys){
                applyInfo.setApplyKey("******");
            }
            response.setCode(200);
            response.setData(applys);
        }else {
            response.setCode(204);
            response.setMsg("未查询到数据！");
        }

        return JSONObject.toJSONString(response) ;
    }

    /**
     * 查看应用密钥
     */
    @GetMapping(value = "getApplyKey")
    public String getApplyKey(@RequestParam String account,
                              @RequestParam String pwd ,
                              @RequestParam String applyId){

        Response response = new Response() ;
        if (StringUtil.getInstance().IsEmpty(account)||
                StringUtil.getInstance().IsEmpty(pwd)||
                StringUtil.getInstance().IsEmpty(applyId)){
            response.setCode(201);
            response.setMsg("参数不全！");
            logger.error("**PJH**：account:{}",account);
            return JSONObject.toJSONString(response) ;
        }

        if (orderUserService.login(account,pwd)){
            String applyKey = applyInfoService.getApplyKey(applyId) ;
            if (StringUtil.getInstance().IsEmpty(applyKey)){
                response.setCode(202);
                response.setMsg("应用号有误");
            }else {
                //applyKey += StringUtil.getInstance().getStringRandom(4) ;
                //applyKey = Md5Util.getMD5Str(applyKey) ;

                //DES加密解密
//                String msg = "hello world. hello ucom, 林诺欧巴";
//                System.out.println("加密前："+msg);
//                byte[] encryptBytes = DESEncryptTools.encrypt(msg.getBytes(), OrderConfig.key.getBytes());
//                System.out.println("加密后："+new String(encryptBytes));
//                byte[] deMsgBytes = DESEncryptTools.decrypt(encryptBytes,OrderConfig.key.getBytes());
//                System.out.println("解密后："+new String(deMsgBytes));

                response.setCode(200);
                response.setData(applyKey);
            }
        }else {
            response.setCode(203);
            response.setMsg("用户密码不正确");
        }

        return JSONObject.toJSONString(response);
    }
}
