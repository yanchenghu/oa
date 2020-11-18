package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 营销录入公司对象 yxdemand
 * 
 * @author ruoyi
 * @date 2020-11-12
 */
public class Yxdemand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer entryId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 招聘岗位 */
    @Excel(name = "招聘岗位")
    private String recruitmentJob;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPeople;

    /** 联系人/职位 */
    @Excel(name = "联系人/职位")
    private String contactPosition;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    /** 信息来源 */
    @Excel(name = "信息来源")
    private String infoSourse;

    /** 公司情况（0、外包公司，1、甲方公司，2、不确定） */
    @Excel(name = "公司情况", readConverterExp = "0=、外包公司，1、甲方公司，2、不确定")
    private Integer companySituation;

    /** 是否投递简历（0、是，1、否） */
    @Excel(name = "是否投递简历", readConverterExp = "0=、是，1、否")
    private Integer isSendResume;

    /** 面试名义公司姓名 */
    @Excel(name = "面试名义公司姓名")
    private String interviewCompany;

    /** 面试官 */
    @Excel(name = "面试官")
    private String interviewer;

    /** 面试官职位 */
    @Excel(name = "面试官职位")
    private String interviewerPosition;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String interviewContact;

    /** 面试地点 */
    @Excel(name = "面试地点")
    private String interviewAddress;

    /** 最终甲方 */
    @Excel(name = "最终甲方")
    private String finalParty;

    /** 录入人 */
    @Excel(name = "录入人")
    private String entryPeople;

    /** 录入人ID */
    @Excel(name = "录入人ID")
    private String entryPeopleId;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    /** 营销转化情况（0、暂未回复，1、无意向，2、需跟进，3、无效联系，4、意向客户） */
    @Excel(name = "营销转化情况", readConverterExp = "0=、暂未回复，1、无意向，2、需跟进，3、无效联系，4、意向客户")
    private Integer isFollowSubmit;

    /** 抢占人ID */
    @Excel(name = "抢占人ID")
    private String robPeopleId;

    /** 抢占人姓名 */
    @Excel(name = "抢占人姓名")
    private String robPeople;

    /** 抢占时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抢占时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date robTime;

    /** 是否提交给商务（0、是1、否） */
    @Excel(name = "是否提交给商务", readConverterExp = "0=、是1、否")
    private Integer isAccept;

    /** 跟进人ID */
    @Excel(name = "跟进人ID")
    private String businessId;

    /** 跟进人姓名 */
    @Excel(name = "跟进人姓名")
    private String businessPeople;

    /** 提交给商务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交给商务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 商务转化（0、无意向1、需跟进2、成为客户3、无效联系） */
    @Excel(name = "商务转化", readConverterExp = "0=、无意向1、需跟进2、成为客户3、无效联系")
    private String isBusiness;

    /** 合作项目（0、代理招聘1、外派2、猎头） */
    @Excel(name = "合作项目", readConverterExp = "0=、代理招聘1、外派2、猎头")
    private Integer cooperationProjects;

    /** 是否签合同（0、是1、否） */
    @Excel(name = "是否签合同", readConverterExp = "0=、是1、否")
    private Integer isSigning;

    /** 签署时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签署时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date singTime;

    /** 人员是否入项（0、是1否） */
    @Excel(name = "人员是否入项", readConverterExp = "0=、是1否")
    private Integer personnelInto;

    /** 是否回款（0、是1、否） */
    @Excel(name = "是否回款", readConverterExp = "0=、是1、否")
    private Integer isReturnMoney;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 最近一次联系情况 */
    @Excel(name = "最近一次联系情况")
    private String contactInformation;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String mailbox;

    /** QQ */
    @Excel(name = "QQ")
    private String qq;

    public void setEntryId(Integer entryId) 
    {
        this.entryId = entryId;
    }

    public Integer getEntryId() 
    {
        return entryId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setRecruitmentJob(String recruitmentJob) 
    {
        this.recruitmentJob = recruitmentJob;
    }

    public String getRecruitmentJob() 
    {
        return recruitmentJob;
    }
    public void setContactPeople(String contactPeople) 
    {
        this.contactPeople = contactPeople;
    }

    public String getContactPeople() 
    {
        return contactPeople;
    }
    public void setContactPosition(String contactPosition) 
    {
        this.contactPosition = contactPosition;
    }

    public String getContactPosition() 
    {
        return contactPosition;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setInfoSourse(String infoSourse) 
    {
        this.infoSourse = infoSourse;
    }

    public String getInfoSourse() 
    {
        return infoSourse;
    }
    public void setCompanySituation(Integer companySituation) 
    {
        this.companySituation = companySituation;
    }

    public Integer getCompanySituation() 
    {
        return companySituation;
    }
    public void setIsSendResume(Integer isSendResume) 
    {
        this.isSendResume = isSendResume;
    }

    public Integer getIsSendResume() 
    {
        return isSendResume;
    }
    public void setInterviewCompany(String interviewCompany) 
    {
        this.interviewCompany = interviewCompany;
    }

    public String getInterviewCompany() 
    {
        return interviewCompany;
    }
    public void setInterviewer(String interviewer) 
    {
        this.interviewer = interviewer;
    }

    public String getInterviewer() 
    {
        return interviewer;
    }
    public void setInterviewerPosition(String interviewerPosition) 
    {
        this.interviewerPosition = interviewerPosition;
    }

    public String getInterviewerPosition() 
    {
        return interviewerPosition;
    }
    public void setInterviewContact(String interviewContact) 
    {
        this.interviewContact = interviewContact;
    }

    public String getInterviewContact() 
    {
        return interviewContact;
    }
    public void setInterviewAddress(String interviewAddress) 
    {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewAddress() 
    {
        return interviewAddress;
    }
    public void setFinalParty(String finalParty) 
    {
        this.finalParty = finalParty;
    }

    public String getFinalParty() 
    {
        return finalParty;
    }
    public void setEntryPeople(String entryPeople) 
    {
        this.entryPeople = entryPeople;
    }

    public String getEntryPeople() 
    {
        return entryPeople;
    }
    public void setEntryPeopleId(String entryPeopleId) 
    {
        this.entryPeopleId = entryPeopleId;
    }

    public String getEntryPeopleId() 
    {
        return entryPeopleId;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }
    public void setIsFollowSubmit(Integer isFollowSubmit) 
    {
        this.isFollowSubmit = isFollowSubmit;
    }

    public Integer getIsFollowSubmit() 
    {
        return isFollowSubmit;
    }
    public void setRobPeopleId(String robPeopleId) 
    {
        this.robPeopleId = robPeopleId;
    }

    public String getRobPeopleId() 
    {
        return robPeopleId;
    }
    public void setRobPeople(String robPeople) 
    {
        this.robPeople = robPeople;
    }

    public String getRobPeople() 
    {
        return robPeople;
    }
    public void setRobTime(Date robTime) 
    {
        this.robTime = robTime;
    }

    public Date getRobTime() 
    {
        return robTime;
    }
    public void setIsAccept(Integer isAccept) 
    {
        this.isAccept = isAccept;
    }

    public Integer getIsAccept() 
    {
        return isAccept;
    }
    public void setBusinessId(String businessId) 
    {
        this.businessId = businessId;
    }

    public String getBusinessId() 
    {
        return businessId;
    }
    public void setBusinessPeople(String businessPeople) 
    {
        this.businessPeople = businessPeople;
    }

    public String getBusinessPeople() 
    {
        return businessPeople;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setIsBusiness(String isBusiness) 
    {
        this.isBusiness = isBusiness;
    }

    public String getIsBusiness() 
    {
        return isBusiness;
    }
    public void setCooperationProjects(Integer cooperationProjects) 
    {
        this.cooperationProjects = cooperationProjects;
    }

    public Integer getCooperationProjects() 
    {
        return cooperationProjects;
    }
    public void setIsSigning(Integer isSigning) 
    {
        this.isSigning = isSigning;
    }

    public Integer getIsSigning() 
    {
        return isSigning;
    }
    public void setSingTime(Date singTime) 
    {
        this.singTime = singTime;
    }

    public Date getSingTime() 
    {
        return singTime;
    }
    public void setPersonnelInto(Integer personnelInto) 
    {
        this.personnelInto = personnelInto;
    }

    public Integer getPersonnelInto() 
    {
        return personnelInto;
    }
    public void setIsReturnMoney(Integer isReturnMoney) 
    {
        this.isReturnMoney = isReturnMoney;
    }

    public Integer getIsReturnMoney() 
    {
        return isReturnMoney;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }
    public void setContactInformation(String contactInformation) 
    {
        this.contactInformation = contactInformation;
    }

    public String getContactInformation() 
    {
        return contactInformation;
    }
    public void setWechat(String wechat) 
    {
        this.wechat = wechat;
    }

    public String getWechat() 
    {
        return wechat;
    }
    public void setMailbox(String mailbox) 
    {
        this.mailbox = mailbox;
    }

    public String getMailbox() 
    {
        return mailbox;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("entryId", getEntryId())
            .append("companyName", getCompanyName())
            .append("recruitmentJob", getRecruitmentJob())
            .append("contactPeople", getContactPeople())
            .append("contactPosition", getContactPosition())
            .append("contactPhone", getContactPhone())
            .append("infoSourse", getInfoSourse())
            .append("companySituation", getCompanySituation())
            .append("isSendResume", getIsSendResume())
            .append("interviewCompany", getInterviewCompany())
            .append("interviewer", getInterviewer())
            .append("interviewerPosition", getInterviewerPosition())
            .append("interviewContact", getInterviewContact())
            .append("interviewAddress", getInterviewAddress())
            .append("finalParty", getFinalParty())
            .append("entryPeople", getEntryPeople())
            .append("entryPeopleId", getEntryPeopleId())
            .append("insertTime", getInsertTime())
            .append("isFollowSubmit", getIsFollowSubmit())
            .append("robPeopleId", getRobPeopleId())
            .append("robPeople", getRobPeople())
            .append("robTime", getRobTime())
            .append("isAccept", getIsAccept())
            .append("businessId", getBusinessId())
            .append("businessPeople", getBusinessPeople())
            .append("submitTime", getSubmitTime())
            .append("isBusiness", getIsBusiness())
            .append("cooperationProjects", getCooperationProjects())
            .append("isSigning", getIsSigning())
            .append("singTime", getSingTime())
            .append("personnelInto", getPersonnelInto())
            .append("isReturnMoney", getIsReturnMoney())
            .append("updateDate", getUpdateDate())
            .append("contactInformation", getContactInformation())
            .append("wechat", getWechat())
            .append("mailbox", getMailbox())
            .append("qq", getQq())
            .toString();
    }
}
