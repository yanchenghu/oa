package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合作公司联系管理对象 mar_company_contacts
 * 
 * @author ych
 * @date 2021-04-15
 */
public class MarCompanyContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司联系人id */
    private Integer id;

    /** 公司ID */
    @Excel(name = "公司ID")
    private String corpCode;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPeople;

    /** 联系人/职位 */
    @Excel(name = "联系人/职位")
    private String contactPosition;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 转化人 */
    @Excel(name = "转化人")
    private String transformingPeople;

    /** $column.columnComment */
    @Excel(name = "转化人")
    private String bz;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
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
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setTransformingPeople(String transformingPeople) 
    {
        this.transformingPeople = transformingPeople;
    }

    public String getTransformingPeople() 
    {
        return transformingPeople;
    }
    public void setBz(String bz) 
    {
        this.bz = bz;
    }

    public String getBz() 
    {
        return bz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("corpCode", getCorpCode())
            .append("contactPeople", getContactPeople())
            .append("contactPosition", getContactPosition())
            .append("contactPhone", getContactPhone())
            .append("addTime", getAddTime())
            .append("transformingPeople", getTransformingPeople())
            .append("bz", getBz())
            .toString();
    }
}
