package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试题绑定需求对象 mar_auditedtor_demand
 * 
 * @author ych
 * @date 2021-03-03
 */
public class MarAuditedtorDemand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 面试绑定需求id */
    private Integer demandTopic;

    /** 需求ID */
    @Excel(name = "需求ID")
    private String demandId;

    /** 整理过后的ID */
    @Excel(name = "整理过后的ID")
    private Integer auditedId;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setDemandTopic(Integer demandTopic) 
    {
        this.demandTopic = demandTopic;
    }

    public Integer getDemandTopic() 
    {
        return demandTopic;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setAuditedId(Integer auditedId) 
    {
        this.auditedId = auditedId;
    }

    public Integer getAuditedId() 
    {
        return auditedId;
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
            .append("demandTopic", getDemandTopic())
            .append("demandId", getDemandId())
            .append("auditedId", getAuditedId())
            .append("addTime", getAddTime())
            .toString();
    }
}
