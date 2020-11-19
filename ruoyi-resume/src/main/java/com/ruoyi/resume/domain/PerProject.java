package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历工作经验对象 per_project
 * 
 * @author ych
 * @date 2020-11-06
 */
public class PerProject
{
    private static final long serialVersionUID = 1L;

    /** 项目经验ID */
    private Integer custproId;

    /** 简历编号 */
    @Excel(name = "简历编号")
    private String customerCode;

    /** 行业 */
    @Excel(name = "行业")
    private Integer industry;

    /** 角色 */
    @Excel(name = "角色")
    private Integer role;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinprojectTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quitprojectTime;

    /** 项目使用环境 */
    @Excel(name = "项目使用环境")
    private String softwareEnvironment;

    /** 项目情况 */
    @Excel(name = "项目情况")
    private String projectIntroduction;

    /** 技术类型 */
    @Excel(name = "技术类型")
    private String technologyType;

    /** 我负责内容 */
    @Excel(name = "我负责内容")
    private String duty;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** $column.columnComment */
    @Excel(name = "项目名称")
    private String remark1;

    /** 团队大小 */
    @Excel(name = "团队大小")
    private String teamSize;
    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public Integer getCustproId() {
        return custproId;
    }

    public void setCustproId(Integer custproId) {
        this.custproId = custproId;
    }

    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setIndustry(Integer industry) 
    {
        this.industry = industry;
    }

    public Integer getIndustry() 
    {
        return industry;
    }
    public void setRole(Integer role) 
    {
        this.role = role;
    }

    public Integer getRole() 
    {
        return role;
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
    public void setSoftwareEnvironment(String softwareEnvironment) 
    {
        this.softwareEnvironment = softwareEnvironment;
    }

    public String getSoftwareEnvironment() 
    {
        return softwareEnvironment;
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
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setRemark1(String remark1) 
    {
        this.remark1 = remark1;
    }

    public String getRemark1() 
    {
        return remark1;
    }
    public void setTeamSize(String teamSize) 
    {
        this.teamSize = teamSize;
    }

    public String getTeamSize() 
    {
        return teamSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "PerProject{" +
                "custproId=" + custproId +
                ", customerCode='" + customerCode + '\'' +
                ", industry=" + industry +
                ", role=" + role +
                ", joinprojectTime=" + joinprojectTime +
                ", quitprojectTime=" + quitprojectTime +
                ", softwareEnvironment='" + softwareEnvironment + '\'' +
                ", projectIntroduction='" + projectIntroduction + '\'' +
                ", technologyType='" + technologyType + '\'' +
                ", duty='" + duty + '\'' +
                ", projectName='" + projectName + '\'' +
                ", remark1='" + remark1 + '\'' +
                ", teamSize='" + teamSize + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
