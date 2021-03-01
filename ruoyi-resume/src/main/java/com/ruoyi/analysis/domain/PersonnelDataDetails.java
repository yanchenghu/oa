package com.ruoyi.analysis.domain;

/**
 * @Description: 人事数据详情
 * @Author ych
 * @Date 2021/2/26
 */
public class PersonnelDataDetails {

    //姓名
    private String nickName;
    //录入
    private String inputNum;
    //跟踪
    private String trackNum;
    //绑定
    private String bindingNum;
    //简历通过
    private String resumePassedNum;
    //面试通过
    private String interviewPassedNum;
    //入项
    private String entryPersonnelNum;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getInputNum() {
        return inputNum;
    }

    public void setInputNum(String inputNum) {
        this.inputNum = inputNum;
    }

    public String getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(String trackNum) {
        this.trackNum = trackNum;
    }

    public String getBindingNum() {
        return bindingNum;
    }

    public void setBindingNum(String bindingNum) {
        this.bindingNum = bindingNum;
    }

    public String getResumePassedNum() {
        return resumePassedNum;
    }

    public void setResumePassedNum(String resumePassedNum) {
        this.resumePassedNum = resumePassedNum;
    }

    public String getInterviewPassedNum() {
        return interviewPassedNum;
    }

    public void setInterviewPassedNum(String interviewPassedNum) {
        this.interviewPassedNum = interviewPassedNum;
    }

    public String getEntryPersonnelNum() {
        return entryPersonnelNum;
    }

    public void setEntryPersonnelNum(String entryPersonnelNum) {
        this.entryPersonnelNum = entryPersonnelNum;
    }
}
