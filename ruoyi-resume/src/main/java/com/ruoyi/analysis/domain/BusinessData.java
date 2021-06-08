package com.ruoyi.analysis.domain;

/**
 * @Description: 商务数据分析临时实体类
 * @Author ych
 * @Date 2021/5/26
 */
public class BusinessData {
    private Integer followStatus;

    //姓名编号
    private String userName;
    //姓名
    private String nickName;
    /*开始时间*/
    private String startTime;
    /*结束时间*/
    private String endTime;
    //录入
    private Long inputNum;
    //绑定
    private Long bindingNum;
    //面试通过
    private Long interviewPassedNum;
    //入项
    private Long entryPersonnelNum;


    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    public Long getInputNum() {
        return inputNum;
    }

    public void setInputNum(Long inputNum) {
        this.inputNum = inputNum;
    }

    public Long getBindingNum() {
        return bindingNum;
    }

    public void setBindingNum(Long bindingNum) {
        this.bindingNum = bindingNum;
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
}
