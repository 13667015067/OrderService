package com.pjh.orderservice.entity;

import java.util.Date;

/**
 * @author:彭建辉
 * @date:2020/8/25 15:31:22
 */
public class OrderImg {

    private long id ;          //图片ID(自增)
    private long workId ;     //工单流程id
    private String img ;       //图片地址
    private String thumbnail ; //缩略图地址
    private Date uploadTime ;  //上传时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "OrderImg{" +
                "id=" + id +
                ", workId=" + workId +
                ", img='" + img + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
