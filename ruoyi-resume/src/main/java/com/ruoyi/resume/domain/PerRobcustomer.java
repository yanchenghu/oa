package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢占简历信息对象 per_robcustomer
 * 
 * @author yan
 * @date 2020-11-09
 */
public class PerRobcustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String seizeId;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String customerName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String customerTel;

    /** 简历ID */
    @Excel(name = "简历ID")
    private String resumeId;

    /** 修改状态时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改状态时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date editTime;

    /** 微信号 */
    @Excel(name = "微信号")
    private String wechat;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 抢占状态 */
    @Excel(name = "抢占状态")
    private Integer status;

    /** 抢战人 */
    @Excel(name = "抢战人")
    private String addPeople;

    /** 抢战人姓名 */
    @Excel(name = "抢战人姓名")
    private String addName;

    public void setSeizeId(String seizeId) 
    {
        this.seizeId = seizeId;
    }

    public String getSeizeId() 
    {
        return seizeId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerTel(String customerTel) 
    {
        this.customerTel = customerTel;
    }

    public String getCustomerTel() 
    {
        return customerTel;
    }
    public void setResumeId(String resumeId) 
    {
        this.resumeId = resumeId;
    }

    public String getResumeId() 
    {
        return resumeId;
    }
    public void setEditTime(Date editTime) 
    {
        this.editTime = editTime;
    }

    public Date getEditTime() 
    {
        return editTime;
    }
    public void setWechat(String wechat) 
    {
        this.wechat = wechat;
    }

    public String getWechat() 
    {
        return wechat;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setAddPeople(String addPeople) 
    {
        this.addPeople = addPeople;
    }

    public String getAddPeople() 
    {
        return addPeople;
    }
    public void setAddName(String addName) 
    {
        this.addName = addName;
    }

    public String getAddName() 
    {
        return addName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("seizeId", getSeizeId())
            .append("customerName", getCustomerName())
            .append("customerTel", getCustomerTel())
            .append("resumeId", getResumeId())
            .append("editTime", getEditTime())
            .append("wechat", getWechat())
            .append("addTime", getAddTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("addPeople", getAddPeople())
            .append("addName", getAddName())
            .toString();
    }
}
