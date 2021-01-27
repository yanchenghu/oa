package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 外派人员工资对象 fin_expatriatesalary
 * 
 * @author ych
 * @date 2021-01-27
 */
public class FinExpatriatesalary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String customerName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String customerTel;

    /** 客户code */
    @Excel(name = "客户code")
    private String customerCode;

    /** 满勤/天 */
    @Excel(name = "满勤/天")
    private String overAttence;

    /** 实际出勤/天 */
    @Excel(name = "实际出勤/天")
    private String realAttence;

    /** 基本工资 */
    @Excel(name = "基本工资")
    private String basicSalary;

    /** 项目工资 */
    @Excel(name = "项目工资")
    private String projectSalary;

    /** 补助 */
    @Excel(name = "补助")
    private String buzhuSalary;

    /** 保险 */
    @Excel(name = "保险")
    private String baoxianSalary;

    /** 实发金额 */
    @Excel(name = "实发金额")
    private String realSalary;

    /** 工资日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工资日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date months;

    /** 底片路径 */
    @Excel(name = "底片路径")
    private String excelPath;

    /** 添加日期 */
    @Excel(name = "添加日期")
    private String addDate;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addPeople;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
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
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setOverAttence(String overAttence) 
    {
        this.overAttence = overAttence;
    }

    public String getOverAttence() 
    {
        return overAttence;
    }
    public void setRealAttence(String realAttence) 
    {
        this.realAttence = realAttence;
    }

    public String getRealAttence() 
    {
        return realAttence;
    }
    public void setBasicSalary(String basicSalary) 
    {
        this.basicSalary = basicSalary;
    }

    public String getBasicSalary() 
    {
        return basicSalary;
    }
    public void setProjectSalary(String projectSalary) 
    {
        this.projectSalary = projectSalary;
    }

    public String getProjectSalary() 
    {
        return projectSalary;
    }
    public void setBuzhuSalary(String buzhuSalary) 
    {
        this.buzhuSalary = buzhuSalary;
    }

    public String getBuzhuSalary() 
    {
        return buzhuSalary;
    }
    public void setBaoxianSalary(String baoxianSalary) 
    {
        this.baoxianSalary = baoxianSalary;
    }

    public String getBaoxianSalary() 
    {
        return baoxianSalary;
    }
    public void setRealSalary(String realSalary) 
    {
        this.realSalary = realSalary;
    }

    public String getRealSalary() 
    {
        return realSalary;
    }
    public void setMonths(Date months) 
    {
        this.months = months;
    }

    public Date getMonths() 
    {
        return months;
    }
    public void setExcelPath(String excelPath) 
    {
        this.excelPath = excelPath;
    }

    public String getExcelPath() 
    {
        return excelPath;
    }
    public void setAddDate(String addDate) 
    {
        this.addDate = addDate;
    }

    public String getAddDate() 
    {
        return addDate;
    }
    public void setAddPeople(String addPeople) 
    {
        this.addPeople = addPeople;
    }

    public String getAddPeople() 
    {
        return addPeople;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerName", getCustomerName())
            .append("customerTel", getCustomerTel())
            .append("customerCode", getCustomerCode())
            .append("overAttence", getOverAttence())
            .append("realAttence", getRealAttence())
            .append("basicSalary", getBasicSalary())
            .append("projectSalary", getProjectSalary())
            .append("buzhuSalary", getBuzhuSalary())
            .append("baoxianSalary", getBaoxianSalary())
            .append("realSalary", getRealSalary())
            .append("months", getMonths())
            .append("excelPath", getExcelPath())
            .append("addDate", getAddDate())
            .append("addPeople", getAddPeople())
            .toString();
    }
}
