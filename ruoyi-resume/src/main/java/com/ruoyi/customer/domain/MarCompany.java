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
 * @author ruoyi
 * @date 2020-11-16
 */
public class MarCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列 */
    private String corpCode;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String corpName;

    /** 客户级别 */
    @Excel(name = "客户级别")
    private Integer customerLevel;

    /** 回款周期 */
    @Excel(name = "回款周期")
    private String paybackPeriod;

    /** 合作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cooperationTime;

    /** 录入人 */
    @Excel(name = "录入人")
    private String enteredBy;

    /** 转化人 */
    @Excel(name = "转化人")
    private String transformingPeople;

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
    public void setCustomerLevel(Integer customerLevel) 
    {
        this.customerLevel = customerLevel;
    }

    public Integer getCustomerLevel() 
    {
        return customerLevel;
    }
    public void setPaybackPeriod(String paybackPeriod) 
    {
        this.paybackPeriod = paybackPeriod;
    }

    public String getPaybackPeriod() 
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
    public void setEnteredBy(String enteredBy) 
    {
        this.enteredBy = enteredBy;
    }

    public String getEnteredBy() 
    {
        return enteredBy;
    }
    public void setTransformingPeople(String transformingPeople) 
    {
        this.transformingPeople = transformingPeople;
    }

    public String getTransformingPeople() 
    {
        return transformingPeople;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("corpCode", getCorpCode())
            .append("corpName", getCorpName())
            .append("customerLevel", getCustomerLevel())
            .append("paybackPeriod", getPaybackPeriod())
            .append("cooperationTime", getCooperationTime())
            .append("enteredBy", getEnteredBy())
            .append("transformingPeople", getTransformingPeople())
            .toString();
    }
}
