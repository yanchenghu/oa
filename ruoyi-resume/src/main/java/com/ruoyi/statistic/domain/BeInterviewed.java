package com.ruoyi.statistic.domain;

/**
 * @Description: 待面试实体类
 * @Author ych
 * @Date 2021/4/29
 */
public class BeInterviewed {

    private String  projectName;
    private String customerTel;
    private String customerName;
    private String  customerCode;
    private String  demandId;
    private String  trackingtime;
    private String  interviewTime;
    private String  beginTime;
    private String  endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getTrackingtime() {
        return trackingtime;
    }

    public void setTrackingtime(String trackingtime) {
        this.trackingtime = trackingtime;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }
}
