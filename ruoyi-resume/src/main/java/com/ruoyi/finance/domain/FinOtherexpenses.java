package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 每月公司其他住处费用对象 fin_otherexpenses
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public class FinOtherexpenses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 房租 */
    @Excel(name = "房租")
    private Integer rent;

    /** 水电 */
    @Excel(name = "水电")
    private Integer hydropower;

    /** 物业 */
    @Excel(name = "物业")
    private Integer property;

    /** 税费 */
    @Excel(name = "税费")
    private Integer taxation;

    /** 其他 */
    @Excel(name = "其他")
    private Integer otherCost;

    /** 社保 */
    @Excel(name = "社保")
    private Integer socialSecurity;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 合并支出 */
    @Excel(name = "合并支出")
    private Integer mergeMoney;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    /** 是否删除(0、否 1、是) */
    @Excel(name = "是否删除(0、否 1、是)")
    private Integer isDelflag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRent(Integer rent) 
    {
        this.rent = rent;
    }

    public Integer getRent() 
    {
        return rent;
    }
    public void setHydropower(Integer hydropower) 
    {
        this.hydropower = hydropower;
    }

    public Integer getHydropower() 
    {
        return hydropower;
    }
    public void setProperty(Integer property) 
    {
        this.property = property;
    }

    public Integer getProperty() 
    {
        return property;
    }
    public void setTaxation(Integer taxation) 
    {
        this.taxation = taxation;
    }

    public Integer getTaxation() 
    {
        return taxation;
    }
    public void setOtherCost(Integer otherCost) 
    {
        this.otherCost = otherCost;
    }

    public Integer getOtherCost() 
    {
        return otherCost;
    }
    public void setSocialSecurity(Integer socialSecurity) 
    {
        this.socialSecurity = socialSecurity;
    }

    public Integer getSocialSecurity() 
    {
        return socialSecurity;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setMergeMoney(Integer mergeMoney) 
    {
        this.mergeMoney = mergeMoney;
    }

    public Integer getMergeMoney() 
    {
        return mergeMoney;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }
    public void setIsDelflag(Integer isDelflag) 
    {
        this.isDelflag = isDelflag;
    }

    public Integer getIsDelflag() 
    {
        return isDelflag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rent", getRent())
            .append("hydropower", getHydropower())
            .append("property", getProperty())
            .append("taxation", getTaxation())
            .append("otherCost", getOtherCost())
            .append("socialSecurity", getSocialSecurity())
            .append("remarks", getRemarks())
            .append("mergeMoney", getMergeMoney())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .append("isDelflag", getIsDelflag())
            .toString();
    }
}
