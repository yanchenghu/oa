package com.ruoyi.demand.domain;

/**
 * @author ych
 * @Description 我绑定的简历的实体类（临时实体类）
 * @create 2021-07-07 15:55
 */
public class MybindingResume {

   //简历id
    private String customerCode;
    //绑定时间
    private String bindTime;
    //姓名
    private String customerName;
    //电话
    private String   customerTel;
    //期望薪资
    private String  expectationSalary;
    //毕业时间
    private String  customerUniversityTime;
    //学历
    private String education;
    //工作年限
    private String  workYear;
    //技术方向
    private String professionId;
    //项目名
    private String projectName;
    //项目id
    private String demandId;
    //跟踪状态
    private Integer followStatus;
    //简历原件
    private String resumePath;
    //简历附件
    private String resumeEnclosurepath;

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getResumeEnclosurepath() {
        return resumeEnclosurepath;
    }

    public void setResumeEnclosurepath(String resumeEnclosurepath) {
        this.resumeEnclosurepath = resumeEnclosurepath;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getExpectationSalary() {
        return expectationSalary;
    }

    public void setExpectationSalary(String expectationSalary) {
        this.expectationSalary = expectationSalary;
    }

    public String getCustomerUniversityTime() {
        return customerUniversityTime;
    }

    public void setCustomerUniversityTime(String customerUniversityTime) {
        this.customerUniversityTime = customerUniversityTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }
}
