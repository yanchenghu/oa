package com.ruoyi.demand.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求绑定面试题对象 mar_auditedtor_topic
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public class MarAuditedtorTopic extends BaseEntity
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("demandTopic", getDemandTopic())
            .append("demandId", getDemandId())
            .append("auditedId", getAuditedId())
            .toString();
    }
}
