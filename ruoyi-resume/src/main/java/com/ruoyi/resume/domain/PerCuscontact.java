package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历跟踪对象 per_cuscontact
 * 
 * @author ych
 * @date 2020-11-13
 */
public class PerCuscontact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 联系编号 */
    private String contacDatecode;

    /** 联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactTime;

    /** 联系人员编号 */
    @Excel(name = "联系人员编号")
    private String contactUsercode;

    /** 联系客户编号 */
    @Excel(name = "联系客户编号")
    private String contactCustomercode;

    /** (1、录入 2、跟进) */
    @Excel(name = "(1、录入 2、跟进 3、绑定)")
    private Integer updateStatic;

    /** 跟进详情 */
    @Excel(name = "跟进详情")
    private String memoDetail;

    /** 需求简历Id */
    @Excel(name = "需求简历Id")
    private String bindId;

    /** 需求Id */
    @Excel(name = "需求Id")
    private String demandId;

    public void setContacDatecode(String contacDatecode) 
    {
        this.contacDatecode = contacDatecode;
    }

    public String getContacDatecode() 
    {
        return contacDatecode;
    }
    public void setContactTime(Date contactTime) 
    {
        this.contactTime = contactTime;
    }

    public Date getContactTime() 
    {
        return contactTime;
    }
    public void setContactUsercode(String contactUsercode) 
    {
        this.contactUsercode = contactUsercode;
    }

    public String getContactUsercode() 
    {
        return contactUsercode;
    }
    public void setContactCustomercode(String contactCustomercode) 
    {
        this.contactCustomercode = contactCustomercode;
    }

    public String getContactCustomercode() 
    {
        return contactCustomercode;
    }
    public void setUpdateStatic(Integer updateStatic) 
    {
        this.updateStatic = updateStatic;
    }

    public Integer getUpdateStatic() 
    {
        return updateStatic;
    }
    public void setMemoDetail(String memoDetail) 
    {
        this.memoDetail = memoDetail;
    }

    public String getMemoDetail() 
    {
        return memoDetail;
    }
    public void setBindId(String bindId) 
    {
        this.bindId = bindId;
    }

    public String getBindId() 
    {
        return bindId;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contacDatecode", getContacDatecode())
            .append("contactTime", getContactTime())
            .append("contactUsercode", getContactUsercode())
            .append("contactCustomercode", getContactCustomercode())
            .append("updateStatic", getUpdateStatic())
            .append("memoDetail", getMemoDetail())
            .append("bindId", getBindId())
            .append("demandId", getDemandId())
            .toString();
    }
}
