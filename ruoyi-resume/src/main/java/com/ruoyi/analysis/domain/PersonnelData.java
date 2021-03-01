package com.ruoyi.analysis.domain;

import javax.xml.crypto.Data;

/**
 * @Description: personneldata临时实体类
 * @Author ych
 * @Date 2021/2/25
 */
public class PersonnelData {

    /*开始时间*/
    private String startTime;
    /*结束时间*/
    private String endTime;
    /*员工编码*/
    private String corpCode;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }
}
