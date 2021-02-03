package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务最新跟踪对象 fin_ancecontact
 * 
 * @author ych
 * @date 2021-02-03
 */
public class FinAncecontact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer contactId;

    /** 合作公司代码 */
    @Excel(name = "合作公司")
    private String corpName;
    /** 合作公司代码 */
    @Excel(name = "合作公司代码")
    private String corpCode;

    /** 沟通情况 */
    @Excel(name = "沟通情况")
    private String contactSituation;

    /** 汇款时间 */
    @Excel(name = "汇款时间")
    private String paymentTime;

    /** 实际回款月份 */
    @Excel(name = "实际回款月份")
    private String actualMonth;

    /** 实际回款金额 */
    @Excel(name = "实际回款金额")
    private String actualMoney;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 录入已回款金额 */
    @Excel(name = "录入已回款金额")
    private String receivedPayment;

    /** 人月 */
    @Excel(name = "人月")
    private Double manMonth;

    /** 回款状态（1、已回款，2、确定，3、不确定，4、很不确定，部分回款） */
    @Excel(name = "回款状态", readConverterExp = "1=、已回款，2、确定，3、不确定，4、很不确定，部分回款")
    private Integer collectionStatus;

    /** 回款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date collectionTime;

    /** 上个月的工资 */
    @Excel(name = "上个月的工资")
    private String lastmonthWages;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addPeople;

    public void setContactId(Integer contactId) 
    {
        this.contactId = contactId;
    }

    public Integer getContactId() 
    {
        return contactId;
    }
    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
    }
    public void setContactSituation(String contactSituation) 
    {
        this.contactSituation = contactSituation;
    }

    public String getContactSituation() 
    {
        return contactSituation;
    }
    public void setPaymentTime(String paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public String getPaymentTime() 
    {
        return paymentTime;
    }
    public void setActualMonth(String actualMonth) 
    {
        this.actualMonth = actualMonth;
    }

    public String getActualMonth() 
    {
        return actualMonth;
    }
    public void setActualMoney(String actualMoney) 
    {
        this.actualMoney = actualMoney;
    }

    public String getActualMoney() 
    {
        return actualMoney;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setReceivedPayment(String receivedPayment) 
    {
        this.receivedPayment = receivedPayment;
    }

    public String getReceivedPayment() 
    {
        return receivedPayment;
    }
    public void setManMonth(Double manMonth) 
    {
        this.manMonth = manMonth;
    }

    public Double getManMonth() 
    {
        return manMonth;
    }
    public void setCollectionStatus(Integer collectionStatus) 
    {
        this.collectionStatus = collectionStatus;
    }

    public Integer getCollectionStatus() 
    {
        return collectionStatus;
    }
    public void setCollectionTime(Date collectionTime) 
    {
        this.collectionTime = collectionTime;
    }

    public Date getCollectionTime() 
    {
        return collectionTime;
    }
    public void setLastmonthWages(String lastmonthWages) 
    {
        this.lastmonthWages = lastmonthWages;
    }

    public String getLastmonthWages() 
    {
        return lastmonthWages;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setAddPeople(String addPeople) 
    {
        this.addPeople = addPeople;
    }

    public String getAddPeople() 
    {
        return addPeople;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contactId", getContactId())
            .append("corpCode", getCorpCode())
            .append("contactSituation", getContactSituation())
            .append("paymentTime", getPaymentTime())
            .append("actualMonth", getActualMonth())
            .append("actualMoney", getActualMoney())
            .append("addTime", getAddTime())
            .append("receivedPayment", getReceivedPayment())
            .append("manMonth", getManMonth())
            .append("collectionStatus", getCollectionStatus())
            .append("collectionTime", getCollectionTime())
            .append("lastmonthWages", getLastmonthWages())
            .append("remarks", getRemarks())
            .append("addPeople", getAddPeople())
            .toString();
    }
}
