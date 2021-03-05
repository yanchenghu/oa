package com.ruoyi.analysis.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 人事数据详情
 * @Author ych
 * @Date 2021/2/26
 */
public class PersonnelDataDetails {

    //日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;
    //姓名编号
    private String userName;
    //姓名
    private String nickName;
    //录入
    private Long inputNum;
    //跟踪
    private Long trackNum;
    //绑定
    private Long bindingNum;
    //简历通过
    private Long resumePassedNum;
    //面试通过
    private Long interviewPassedNum;
    //入项
    private Long entryPersonnelNum;
    //人数
    private Double peopleNum;
    //成本
    private Double costNum;
    //服务费
    private Double serviceNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getInputNum() {
        return inputNum;
    }

    public void setInputNum(Long inputNum) {
        this.inputNum = inputNum;
    }

    public Long getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(Long trackNum) {
        this.trackNum = trackNum;
    }

    public Long getBindingNum() {
        return bindingNum;
    }

    public void setBindingNum(Long bindingNum) {
        this.bindingNum = bindingNum;
    }

    public Long getResumePassedNum() {
        return resumePassedNum;
    }

    public void setResumePassedNum(Long resumePassedNum) {
        this.resumePassedNum = resumePassedNum;
    }

    public Long getInterviewPassedNum() {
        return interviewPassedNum;
    }

    public void setInterviewPassedNum(Long interviewPassedNum) {
        this.interviewPassedNum = interviewPassedNum;
    }

    public Long getEntryPersonnelNum() {
        return entryPersonnelNum;
    }

    public void setEntryPersonnelNum(Long entryPersonnelNum) {
        this.entryPersonnelNum = entryPersonnelNum;
    }

    public Double getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Double peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Double getCostNum() {
        return costNum;
    }

    public void setCostNum(Double costNum) {
        this.costNum = costNum;
    }

    public Double getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Double serviceNum) {
        this.serviceNum = serviceNum;
    }
}
