package com.ruoyi.demand.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历 绑定岗位对象 mar_demandresume_requirement
 * 
 * @author ych
 * @date 2021-06-23
 */
public class MarDemandresumeRequirement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 简历绑定需求id */
    @Excel(name = "简历绑定需求id")
    private String demandresumeId;

    /** 岗位id */
    @Excel(name = "岗位id")
    private Long requirementsId;

    /** 序号 */
    @Excel(name = "序号")
    private Integer sort;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setRequirementsId(Long requirementsId) 
    {
        this.requirementsId = requirementsId;
    }

    public Long getRequirementsId() 
    {
        return requirementsId;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }



    public String getDemandresumeId() {
        return demandresumeId;
    }

    public void setDemandresumeId(String demandresumeId) {
        this.demandresumeId = demandresumeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("demandresumeId", getDemandresumeId())
                .append("requirementsId", getRequirementsId())
                .append("sort", getSort())
                .toString();
    }
}
