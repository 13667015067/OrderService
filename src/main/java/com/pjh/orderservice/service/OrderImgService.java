package com.pjh.orderservice.service;

import com.pjh.orderservice.entity.OrderImg;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author:彭建辉
 * @date:2020/8/25 16:48:02
 */
public interface OrderImgService {

    boolean insertOrderImg(OrderImg orderImg) ;

    void dealImg(MultipartFile[] fileList, long workId) ;
}
