package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历对象 per_customerinfo
 * 
 * @author yan
 * @date 2020-11-06
 */
public class PerCustomerinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 简历编号 */
    private String customerCode;

    /** 简历姓名 */
    @Excel(name = "简历姓名")
    private String customerName;

    /** 简历性别（0-男，1-女） */
    private Integer customerSex;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String customerTel;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date customerBirth;

    /** 毕业院校 */
    private String customerUniversity;

    /** 毕业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "毕业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date customerUniversityTime;
    /** 专业 */
    @Excel(name = "专业")
    private String customerSpecialities;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private Integer workYear;

    /** 简历来源(1、Boos直聘，2、智联招聘3、58同城) */
    @Excel(name = "简历来源(1、Boos直聘，2、智联招聘3、58同城)")
    private Integer resumeSource;

    /** 简历ID */
    @Excel(name = "简历ID")
    private String resumeId;

    /** 简历地址 */
    @Excel(name = "简历地址")
    private String resumePath;

    /** 与梓钦关系（1-已离职、2-在职、3-提交简历未通过、4-待入职、5-无关系 6-入职） */
    @Excel(name = "与梓钦关系", readConverterExp = "1=-已离职、2-在职、3-提交简历未通过、4-待入职、5-无关系,6=-入职")
    private Integer relationshipZq;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    /** 操作员编号 */
    private String opertCode;

    /** 操作员姓名 */
    @Excel(name = "操作员姓名")
    private String opertName;

    /** 期望薪资 */
    @Excel(name = "期望薪资")
    private String expectationSalary;

    /** 技术方向(1、java 2、c++ 3、python) */
    @Excel(name = "技术方向(1、java 2、c++ 3、python)")
    private String professionId;

    /** 意向地区 */
    @Excel(name = "意向地区")
    private String intentionArea;

    /** 学历(1、初中2、高中3、专科4、本科5、研究生6、博士7、其它) */
    @Excel(name = "学历(1、初中2、高中3、专科4、本科5、研究生6、博士7、其它)")
    private Integer education;

    /** 入项次数 */
    private Long entryTimes;

    /** 入项状态（1-未入项   2-已入项  3-已离项   4-已离职） */
    private Integer joinStatus;

    /** 抢占人（如果为空，说明未被抢占，否则说明被抢占） */
    private String signPeople;

    /** 学信网是否可查（0-否，1-是） */
    private Integer chsiFlag;

    /** 简历方向 : 1:国内 2:对日 */
    private Integer resumeDirection;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCustomerUniversityTime() {
        return customerUniversityTime;
    }

    public void setCustomerUniversityTime(Date customerUniversityTime) {
        this.customerUniversityTime = customerUniversityTime;
    }

    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerSex(Integer customerSex) 
    {
        this.customerSex = customerSex;
    }

    public Integer getCustomerSex() 
    {
        return customerSex;
    }
    public void setCustomerTel(String customerTel) 
    {
        this.customerTel = customerTel;
    }

    public String getCustomerTel() 
    {
        return customerTel;
    }
    public void setCustomerBirth(Date customerBirth) 
    {
        this.customerBirth = customerBirth;
    }

    public Date getCustomerBirth() 
    {
        return customerBirth;
    }
    public void setCustomerUniversity(String customerUniversity) 
    {
        this.customerUniversity = customerUniversity;
    }

    public String getCustomerUniversity() 
    {
        return customerUniversity;
    }
    public void setCustomerSpecialities(String customerSpecialities) 
    {
        this.customerSpecialities = customerSpecialities;
    }

    public String getCustomerSpecialities() 
    {
        return customerSpecialities;
    }
    public void setEntryTime(Date entryTime) 
    {
        this.entryTime = entryTime;
    }

    public Date getEntryTime() 
    {
        return entryTime;
    }
    public void setWorkYear(Integer workYear) 
    {
        this.workYear = workYear;
    }

    public Integer getWorkYear() 
    {
        return workYear;
    }
    public void setResumeSource(Integer resumeSource) 
    {
        this.resumeSource = resumeSource;
    }

    public Integer getResumeSource() 
    {
        return resumeSource;
    }
    public void setResumeId(String resumeId) 
    {
        this.resumeId = resumeId;
    }

    public String getResumeId() 
    {
        return resumeId;
    }
    public void setResumePath(String resumePath) 
    {
        this.resumePath = resumePath;
    }

    public String getResumePath() 
    {
        return resumePath;
    }
    public void setRelationshipZq(Integer relationshipZq) 
    {
        this.relationshipZq = relationshipZq;
    }

    public Integer getRelationshipZq() 
    {
        return relationshipZq;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setOpertCode(String opertCode) 
    {
        this.opertCode = opertCode;
    }

    public String getOpertCode() 
    {
        return opertCode;
    }
    public void setOpertName(String opertName) 
    {
        this.opertName = opertName;
    }

    public String getOpertName() 
    {
        return opertName;
    }
    public void setExpectationSalary(String expectationSalary) 
    {
        this.expectationSalary = expectationSalary;
    }

    public String getExpectationSalary() 
    {
        return expectationSalary;
    }
    public void setProfessionId(String professionId) 
    {
        this.professionId = professionId;
    }

    public String getProfessionId() 
    {
        return professionId;
    }
    public void setIntentionArea(String intentionArea) 
    {
        this.intentionArea = intentionArea;
    }

    public String getIntentionArea() 
    {
        return intentionArea;
    }
    public void setEducation(Integer education) 
    {
        this.education = education;
    }

    public Integer getEducation() 
    {
        return education;
    }
    public void setEntryTimes(Long entryTimes) 
    {
        this.entryTimes = entryTimes;
    }

    public Long getEntryTimes() 
    {
        return entryTimes;
    }
    public void setJoinStatus(Integer joinStatus) 
    {
        this.joinStatus = joinStatus;
    }

    public Integer getJoinStatus() 
    {
        return joinStatus;
    }
    public void setSignPeople(String signPeople) 
    {
        this.signPeople = signPeople;
    }

    public String getSignPeople() 
    {
        return signPeople;
    }
    public void setChsiFlag(Integer chsiFlag) 
    {
        this.chsiFlag = chsiFlag;
    }

    public Integer getChsiFlag() 
    {
        return chsiFlag;
    }
    public void setResumeDirection(Integer resumeDirection) 
    {
        this.resumeDirection = resumeDirection;
    }

    public Integer getResumeDirection() 
    {
        return resumeDirection;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerCode", getCustomerCode())
            .append("customerName", getCustomerName())
            .append("customerSex", getCustomerSex())
            .append("customerTel", getCustomerTel())
            .append("customerBirth", getCustomerBirth())
            .append("customerUniversity", getCustomerUniversity())
            .append("customerSpecialities", getCustomerSpecialities())
            .append("entryTime", getEntryTime())
            .append("workYear", getWorkYear())
            .append("resumeSource", getResumeSource())
            .append("resumeId", getResumeId())
            .append("resumePath", getResumePath())
            .append("relationshipZq", getRelationshipZq())
            .append("email", getEmail())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("opertCode", getOpertCode())
            .append("opertName", getOpertName())
            .append("expectationSalary", getExpectationSalary())
            .append("professionId", getProfessionId())
            .append("intentionArea", getIntentionArea())
            .append("education", getEducation())
            .append("entryTimes", getEntryTimes())
            .append("joinStatus", getJoinStatus())
            .append("signPeople", getSignPeople())
            .append("chsiFlag", getChsiFlag())
            .append("resumeDirection", getResumeDirection())
            .toString();
    }
}
