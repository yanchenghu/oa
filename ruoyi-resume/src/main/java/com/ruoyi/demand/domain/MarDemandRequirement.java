package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求岗位要求对象 mar_demand_requirement
 * 
 * @author ych
 * @date 2021-06-23
 */
public class MarDemandRequirement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 要求ID */
    private Long id;

    /** 需求id */
    @Excel(name = "需求id")
    private String demandId;

    /** 序号 */
    @Excel(name = "序号")
    private Integer sort;

    /** 岗位要求 */
    @Excel(name = "岗位要求")
    private String jobRequirements;

    /** 是否必须满足（0、默认不满足1、满足） */
    @Excel(name = "是否必须满足", readConverterExp = "0=、默认不满足1、满足")
    private Integer isNecessary;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setJobRequirements(String jobRequirements) 
    {
        this.jobRequirements = jobRequirements;
    }

    public String getJobRequirements() 
    {
        return jobRequirements;
    }
    public void setIsNecessary(Integer isNecessary) 
    {
        this.isNecessary = isNecessary;
    }

    public Integer getIsNecessary() 
    {
        return isNecessary;
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
            .append("id", getId())
            .append("demandId", getDemandId())
            .append("sort", getSort())
            .append("jobRequirements", getJobRequirements())
            .append("isNecessary", getIsNecessary())
            .append("addTime", getAddTime())
            .toString();
    }
}
