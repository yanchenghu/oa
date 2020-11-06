package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历教育经验对象 per_education
 * 
 * @author ych
 * @date 2020-11-06
 */
public class PerEducation
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 人员code */
    @Excel(name = "人员code")
    private String customerCode;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 学校 */
    @Excel(name = "学校")
    private String schoolName;

    /** 学历 */
    @Excel(name = "学历")
    private String degree;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 是否仍在，1-仍未毕业，0-已毕业 */
    @Excel(name = "是否仍在，1-仍未毕业，0-已毕业")
    private Integer stillActive;

    /** 学校级别，985 211等 */
    @Excel(name = "学校级别，985 211等")
    private String schoolLevel;

    /** 上课模式，全职，兼职 */
    @Excel(name = "上课模式，全职，兼职")
    private String studyModel;

    /** 地点，城市名 */
    @Excel(name = "地点，城市名")
    private String location;

    /** $column.columnComment */
    @Excel(name = "地点，城市名")
    private String gpa;

    /** 排名，学生在年级排名 */
    @Excel(name = "排名，学生在年级排名")
    private String ranking;

    /** 所学课程 */
    @Excel(name = "所学课程")
    private String courses;

    /** 学院 */
    @Excel(name = "学院")
    private String department;

    /** 学校排名，中国大学最新排名 */
    @Excel(name = "学校排名，中国大学最新排名")
    private String schoolRank;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addpeople;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;
    /** 备注 */
    @Excel(name = "备注")
    private String remark;

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
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setSchoolName(String schoolName) 
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName() 
    {
        return schoolName;
    }
    public void setDegree(String degree) 
    {
        this.degree = degree;
    }

    public String getDegree() 
    {
        return degree;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setStillActive(Integer stillActive) 
    {
        this.stillActive = stillActive;
    }

    public Integer getStillActive() 
    {
        return stillActive;
    }
    public void setSchoolLevel(String schoolLevel) 
    {
        this.schoolLevel = schoolLevel;
    }

    public String getSchoolLevel() 
    {
        return schoolLevel;
    }
    public void setStudyModel(String studyModel) 
    {
        this.studyModel = studyModel;
    }

    public String getStudyModel() 
    {
        return studyModel;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setGpa(String gpa) 
    {
        this.gpa = gpa;
    }

    public String getGpa() 
    {
        return gpa;
    }
    public void setRanking(String ranking) 
    {
        this.ranking = ranking;
    }

    public String getRanking() 
    {
        return ranking;
    }
    public void setCourses(String courses) 
    {
        this.courses = courses;
    }

    public String getCourses() 
    {
        return courses;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setSchoolRank(String schoolRank) 
    {
        this.schoolRank = schoolRank;
    }

    public String getSchoolRank() 
    {
        return schoolRank;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerCode", getCustomerCode())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("schoolName", getSchoolName())
            .append("degree", getDegree())
            .append("major", getMajor())
            .append("stillActive", getStillActive())
            .append("schoolLevel", getSchoolLevel())
            .append("studyModel", getStudyModel())
            .append("location", getLocation())
            .append("gpa", getGpa())
            .append("ranking", getRanking())
            .append("courses", getCourses())
            .append("department", getDepartment())
            .append("schoolRank", getSchoolRank())
            .append("addpeople", getAddpeople())
            .append("addtime", getAddtime())
            .append("remark", getRemark())
            .toString();
    }
}
