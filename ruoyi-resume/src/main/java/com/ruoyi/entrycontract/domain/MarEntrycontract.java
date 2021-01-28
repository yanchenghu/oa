package com.ruoyi.entrycontract.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MarEntrycontract {

    //合作id
    private Integer id;
    //入项id
    private String  marcusId;
    //图片路径
    private String  imgPath;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarcusId() {
        return marcusId;
    }

    public void setMarcusId(String marcusId) {
        this.marcusId = marcusId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
