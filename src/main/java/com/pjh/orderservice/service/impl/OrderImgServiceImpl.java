package com.pjh.orderservice.service.impl;

import com.pjh.orderservice.dao.OrderImgDao;
import com.pjh.orderservice.entity.OrderImg;
import com.pjh.orderservice.service.OrderImgService;
import com.pjh.orderservice.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:49:48
 */

@Service
public class OrderImgServiceImpl implements OrderImgService {

    private Logger logger = LoggerFactory.getLogger(OrderImgServiceImpl.class) ;

    @Autowired
    private OrderImgDao orderImgDao ;

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    @Override
    public boolean insertOrderImg(OrderImg orderImg) {
        boolean bool = false ;
        try {
            bool = orderImgDao.insertOrderImg(orderImg) ;
        } catch (SQLException e) {
            logger.info("**PJH**图片记录添加异常,orderImg:{}",orderImg.toString());
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public void dealImg(MultipartFile[] fileList, long workId) {
        String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String subPath = ymd.substring(0,4) + "/" + ymd.substring(4) + "/" + workId;
        String imgPath = subPath + "/img/";
        String thumbnailPath = subPath + "/thumbnail/";
        for (MultipartFile file : fileList) {
            if(!StringUtils.isEmpty(file.getOriginalFilename())) {
                String fileName = new Random().nextInt(100) + "-" +file.getOriginalFilename();
                try {
                    FileUtil.copyFile(file.getBytes(), uploadFilePath + imgPath, fileName);
                    //生成缩略图
                    FileUtil.thumbnailImg(file, uploadFilePath + thumbnailPath, fileName);

                    OrderImg orderImg = new OrderImg() ;
                    orderImg.setWorkId(workId);
                    orderImg.setImg(imgPath + fileName);
                    orderImg.setThumbnail(thumbnailPath + fileName);
                    orderImg.setUploadTime(new Date());

                    orderImgDao.insertOrderImg(orderImg);
                } catch (Exception e) {
                    logger.info("上传图片出错,文件名:" + file.getOriginalFilename());
                    e.printStackTrace();
                }
            }
        }
    }
}
