package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件简历项目经验对象 per_enclosure_project
 * 
 * @author ych
 * @date 2021-07-02
 */
public class PerEnclosureProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目经验ID */
    private Integer custproId;

    /** 简历编号 */
    @Excel(name = "简历编号")
    private String customerCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinprojectTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quitprojectTime;

    /** 项目情况 */
    @Excel(name = "项目情况")
    private String projectIntroduction;

    /** 技术要点 */
    @Excel(name = "技术要点")
    private String technologyType;

    /** 职责描述 */
    @Excel(name = "职责描述")
    private String duty;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public PerEnclosureProject() {
    }

    public PerEnclosureProject(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setCustproId(Integer custproId)
    {
        this.custproId = custproId;
    }

    public Integer getCustproId() 
    {
        return custproId;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setJoinprojectTime(Date joinprojectTime) 
    {
        this.joinprojectTime = joinprojectTime;
    }

    public Date getJoinprojectTime() 
    {
        return joinprojectTime;
    }
    public void setQuitprojectTime(Date quitprojectTime) 
    {
        this.quitprojectTime = quitprojectTime;
    }

    public Date getQuitprojectTime() 
    {
        return quitprojectTime;
    }
    public void setProjectIntroduction(String projectIntroduction) 
    {
        this.projectIntroduction = projectIntroduction;
    }

    public String getProjectIntroduction() 
    {
        return projectIntroduction;
    }
    public void setTechnologyType(String technologyType) 
    {
        this.technologyType = technologyType;
    }

    public String getTechnologyType() 
    {
        return technologyType;
    }
    public void setDuty(String duty) 
    {
        this.duty = duty;
    }

    public String getDuty() 
    {
        return duty;
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
            .append("custproId", getCustproId())
            .append("customerCode", getCustomerCode())
            .append("projectName", getProjectName())
            .append("joinprojectTime", getJoinprojectTime())
            .append("quitprojectTime", getQuitprojectTime())
            .append("projectIntroduction", getProjectIntroduction())
            .append("technologyType", getTechnologyType())
            .append("duty", getDuty())
            .append("remark", getRemark())
            .append("addTime", getAddTime())
            .toString();
    }
}
