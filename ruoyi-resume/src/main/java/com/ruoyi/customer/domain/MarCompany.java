package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合作公司对象 mar_company
 * 
 * @author ych
 * @date 2020-11-25
 */
public class MarCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列 */
    private String corpCode;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String corpName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPeople;

    /** 联系人/职位 */
    @Excel(name = "联系人/职位")
    private String contactPosition;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    /** 公司情况（0、外包公司，1、甲方公司，2、不确定） */
    @Excel(name = "公司情况", readConverterExp = "0=、外包公司，1、甲方公司，2、不确定")
    private Integer companySituation;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String mailbox;

    /** QQ */
    @Excel(name = "QQ")
    private String qq;

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

    /** 客户级别  */
    @Excel(name = "客户级别 ")
    private Integer customerLevel;

    /** 回款周期 */
    @Excel(name = "回款周期")
    private Integer paybackPeriod;

    /** 合作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cooperationTime;

    /** 录入人 */
    @Excel(name = "录入人")
    private String entryPeople;

    /** 转化人 */
    @Excel(name = "转化人")
    private String transformingPeople;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
    }
    public void setCorpName(String corpName) 
    {
        this.corpName = corpName;
    }

    public String getCorpName() 
    {
        return corpName;
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
    public void setCompanySituation(Integer companySituation) 
    {
        this.companySituation = companySituation;
    }

    public Integer getCompanySituation() 
    {
        return companySituation;
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
    public void setCustomerLevel(Integer customerLevel) 
    {
        this.customerLevel = customerLevel;
    }

    public Integer getCustomerLevel() 
    {
        return customerLevel;
    }
    public void setPaybackPeriod(Integer paybackPeriod) 
    {
        this.paybackPeriod = paybackPeriod;
    }

    public Integer getPaybackPeriod() 
    {
        return paybackPeriod;
    }
    public void setCooperationTime(Date cooperationTime) 
    {
        this.cooperationTime = cooperationTime;
    }

    public Date getCooperationTime() 
    {
        return cooperationTime;
    }
    public void setEntryPeople(String entryPeople) 
    {
        this.entryPeople = entryPeople;
    }

    public String getEntryPeople() 
    {
        return entryPeople;
    }
    public void setTransformingPeople(String transformingPeople) 
    {
        this.transformingPeople = transformingPeople;
    }

    public String getTransformingPeople() 
    {
        return transformingPeople;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("corpCode", getCorpCode())
            .append("corpName", getCorpName())
            .append("contactPeople", getContactPeople())
            .append("contactPosition", getContactPosition())
            .append("contactPhone", getContactPhone())
            .append("companySituation", getCompanySituation())
            .append("wechat", getWechat())
            .append("mailbox", getMailbox())
            .append("qq", getQq())
            .append("interviewCompany", getInterviewCompany())
            .append("interviewer", getInterviewer())
            .append("interviewerPosition", getInterviewerPosition())
            .append("interviewContact", getInterviewContact())
            .append("interviewAddress", getInterviewAddress())
            .append("finalParty", getFinalParty())
            .append("customerLevel", getCustomerLevel())
            .append("paybackPeriod", getPaybackPeriod())
            .append("cooperationTime", getCooperationTime())
            .append("entryPeople", getEntryPeople())
            .append("transformingPeople", getTransformingPeople())
            .append("addTime", getAddTime())
            .append("remark", getRemark())
            .toString();
    }
}
