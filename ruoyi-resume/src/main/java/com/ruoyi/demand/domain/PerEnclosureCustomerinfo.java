package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件简历基本信息对象 per_enclosure_customerinfo
 * 
 * @author ych
 * @date 2021-07-02
 */
public class PerEnclosureCustomerinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 简历编号 */
    private String customerCode;

    /** 简历姓名 */
    @Excel(name = "简历姓名")
    private String customerName;

    /** 简历性别（0-男，1-女） */
    @Excel(name = "简历性别", readConverterExp = "0=-男，1-女")
    private Integer customerSex;

    /** 岁数 */
    @Excel(name = "岁数")
    private Integer customerAge;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private Integer workYear;

    /** 职位 */
    @Excel(name = "职位")
    private String professionId;

    /** 学历(1、初中2、高中3、专科4、本科5、研究生6、博士7、其它) */
    @Excel(name = "学历(1、初中2、高中3、专科4、本科5、研究生6、博士7、其它)")
    private Integer education;

    /** 自我评价 */
    @Excel(name = "自我评价")
    private String selfEvaluation;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerSex(Integer customerSex) 
    {
        this.customerSex = customerSex;
    }

    public Integer getCustomerSex() 
    {
        return customerSex;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public void setWorkYear(Integer workYear)
    {
        this.workYear = workYear;
    }

    public Integer getWorkYear() 
    {
        return workYear;
    }
    public void setProfessionId(String professionId) 
    {
        this.professionId = professionId;
    }

    public String getProfessionId() 
    {
        return professionId;
    }
    public void setEducation(Integer education) 
    {
        this.education = education;
    }

    public Integer getEducation() 
    {
        return education;
    }
    public void setSelfEvaluation(String selfEvaluation) 
    {
        this.selfEvaluation = selfEvaluation;
    }

    public String getSelfEvaluation() 
    {
        return selfEvaluation;
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
            .append("customerCode", getCustomerCode())
            .append("customerName", getCustomerName())
            .append("customerSex", getCustomerSex())
            .append("customerAge", getCustomerAge())
            .append("workYear", getWorkYear())
            .append("professionId", getProfessionId())
            .append("education", getEducation())
            .append("selfEvaluation", getSelfEvaluation())
            .append("addTime", getAddTime())
            .toString();
    }
}
