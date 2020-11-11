package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历录入工作经历对象 per_work
 * 
 * @author ych
 * @date 2020-11-09
 */
public class PerWork
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 求职者code */
    @Excel(name = "求职者code")
    private String customerCode;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime_month;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 是否仍在，1-仍在其位，0-离职 */
    @Excel(name = "是否仍在，1-仍在其位，0-离职")
    private Integer stillActive;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private String department;

    /** 地点 */
    @Excel(name = "地点")
    private String location;

    /** 职位名 */
    @Excel(name = "职位名")
    private String jobTitle;

    /** 工作描述 */
    @Excel(name = "工作描述")
    private String description;

    /** 公司行业 */
    @Excel(name = "公司行业")
    private String industry;

    /** 职位职能 */
    @Excel(name = "职位职能")
    private String jobFunction;

    /** 公司规模，公司人数，100-499人，1000人以上等 */
    @Excel(name = "公司规模，公司人数，100-499人，1000人以上等")
    private String companySize;

    /** 公司类型，lg：民营，国家机关，个体等，具体以简历面熟为准 */
    @Excel(name = "公司类型，lg：民营，国家机关，个体等，具体以简历面熟为准")
    private String companyType;

    /** 工资水平，该岗位工资水平，lg：3000-5000元/月 */
    @Excel(name = "工资水平，该岗位工资水平，lg：3000-5000元/月")
    private String salary;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addpeople;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;
    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date starttime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setStartTime_month(Date startTime_month) 
    {
        this.startTime_month = startTime_month;
    }

    public Date getStartTime_month() 
    {
        return startTime_month;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setStillActive(Integer stillActive) 
    {
        this.stillActive = stillActive;
    }

    public Integer getStillActive() 
    {
        return stillActive;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() 
    {
        return jobTitle;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setJobFunction(String jobFunction) 
    {
        this.jobFunction = jobFunction;
    }

    public String getJobFunction() 
    {
        return jobFunction;
    }
    public void setCompanySize(String companySize) 
    {
        this.companySize = companySize;
    }

    public String getCompanySize() 
    {
        return companySize;
    }
    public void setCompanyType(String companyType) 
    {
        this.companyType = companyType;
    }

    public String getCompanyType() 
    {
        return companyType;
    }
    public void setSalary(String salary) 
    {
        this.salary = salary;
    }

    public String getSalary() 
    {
        return salary;
    }
    public void setAddpeople(String addpeople) 
    {
        this.addpeople = addpeople;
    }

    public String getAddpeople() 
    {
        return addpeople;
    }
    public void setAddtime(Date addtime) 
    {
        this.addtime = addtime;
    }

    public Date getAddtime() 
    {
        return addtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerCode", getCustomerCode())
            .append("startTime_month", getStartTime_month())
            .append("endTime", getEndTime())
            .append("stillActive", getStillActive())
            .append("companyName", getCompanyName())
            .append("department", getDepartment())
            .append("location", getLocation())
            .append("jobTitle", getJobTitle())
            .append("description", getDescription())
            .append("industry", getIndustry())
            .append("jobFunction", getJobFunction())
            .append("companySize", getCompanySize())
            .append("companyType", getCompanyType())
            .append("salary", getSalary())
            .append("addpeople", getAddpeople())
            .append("addtime", getAddtime())
            .toString();
    }
}
