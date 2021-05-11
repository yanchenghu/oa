package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内部人员工资对象 fin_insidesalary
 * 
 * @author ych
 * @date 2021-04-22
 */
public class FinInsidesalary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 内部人员工资id */
    private Integer id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 基本工资 */
    @Excel(name = "基本工资")
    private String realAttence;

    /** 实际出勤 */
    @Excel(name = "实际出勤")
    private String actualAttendance;

    /** 法定出勤 */
    @Excel(name = "法定出勤")
    private String statutoryAttendance;

    /** 应发工资 */
    @Excel(name = "应发工资")
    private String actualIncome;

    /** 绩效 */
    @Excel(name = "绩效")
    private String taxes;

    /** 全勤奖 */
    @Excel(name = "全勤奖")
    private String totalManagement;

    /** 实发工资 */
    @Excel(name = "实发工资")
    private String realSalary;

    /** 保险 */
    @Excel(name = "保险")
    private String insurance;

    /** 提成 */
    @Excel(name = "提成")
    private String commission;

    /** 工资日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工资日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sideMonths;

    /** 底片路径 */
    @Excel(name = "底片路径")
    private String excelPath;

    /** 添加日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addDate;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setRealAttence(String realAttence) 
    {
        this.realAttence = realAttence;
    }

    public String getRealAttence() 
    {
        return realAttence;
    }
    public void setActualAttendance(String actualAttendance) 
    {
        this.actualAttendance = actualAttendance;
    }

    public String getActualAttendance() 
    {
        return actualAttendance;
    }
    public void setStatutoryAttendance(String statutoryAttendance) 
    {
        this.statutoryAttendance = statutoryAttendance;
    }

    public String getStatutoryAttendance() 
    {
        return statutoryAttendance;
    }
    public void setActualIncome(String actualIncome) 
    {
        this.actualIncome = actualIncome;
    }

    public String getActualIncome() 
    {
        return actualIncome;
    }
    public void setTaxes(String taxes) 
    {
        this.taxes = taxes;
    }

    public String getTaxes() 
    {
        return taxes;
    }
    public void setTotalManagement(String totalManagement) 
    {
        this.totalManagement = totalManagement;
    }

    public String getTotalManagement() 
    {
        return totalManagement;
    }
    public void setRealSalary(String realSalary) 
    {
        this.realSalary = realSalary;
    }

    public String getRealSalary() 
    {
        return realSalary;
    }
    public void setInsurance(String insurance) 
    {
        this.insurance = insurance;
    }

    public String getInsurance() 
    {
        return insurance;
    }
    public void setCommission(String commission) 
    {
        this.commission = commission;
    }

    public String getCommission() 
    {
        return commission;
    }
    public void setSideMonths(Date sideMonths) 
    {
        this.sideMonths = sideMonths;
    }

    public Date getSideMonths() 
    {
        return sideMonths;
    }
    public void setExcelPath(String excelPath) 
    {
        this.excelPath = excelPath;
    }

    public String getExcelPath() 
    {
        return excelPath;
    }
    public void setAddDate(Date addDate) 
    {
        this.addDate = addDate;
    }

    public Date getAddDate() 
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
            .append("name", getName())
            .append("realAttence", getRealAttence())
            .append("actualAttendance", getActualAttendance())
            .append("statutoryAttendance", getStatutoryAttendance())
            .append("actualIncome", getActualIncome())
            .append("taxes", getTaxes())
            .append("totalManagement", getTotalManagement())
            .append("realSalary", getRealSalary())
            .append("insurance", getInsurance())
            .append("commission", getCommission())
            .append("sideMonths", getSideMonths())
            .append("excelPath", getExcelPath())
            .append("addDate", getAddDate())
            .append("addPeople", getAddPeople())
            .toString();
    }
}
