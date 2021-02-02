package com.ruoyi.expenditure.domain;

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
 * @date 2021-02-02
 */
public class FinOtherexpenses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 房租 */
    @Excel(name = "房租")
    private String rent;

    /** 水电 */
    @Excel(name = "水电")
    private String hydropower;

    /** 物业 */
    @Excel(name = "物业")
    private String property;

    /** 材料费 */
    @Excel(name = "材料费")
    private String materiaCost;

    /** 税费 */
    @Excel(name = "税费")
    private String taxation;

    /** 其他 */
    @Excel(name = "其他")
    private String otherCost;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

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
    public void setRent(String rent) 
    {
        this.rent = rent;
    }

    public String getRent() 
    {
        return rent;
    }
    public void setHydropower(String hydropower) 
    {
        this.hydropower = hydropower;
    }

    public String getHydropower() 
    {
        return hydropower;
    }
    public void setProperty(String property) 
    {
        this.property = property;
    }

    public String getProperty() 
    {
        return property;
    }
    public void setMateriaCost(String materiaCost) 
    {
        this.materiaCost = materiaCost;
    }

    public String getMateriaCost() 
    {
        return materiaCost;
    }
    public void setTaxation(String taxation) 
    {
        this.taxation = taxation;
    }

    public String getTaxation() 
    {
        return taxation;
    }
    public void setOtherCost(String otherCost) 
    {
        this.otherCost = otherCost;
    }

    public String getOtherCost() 
    {
        return otherCost;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("materiaCost", getMateriaCost())
            .append("taxation", getTaxation())
            .append("otherCost", getOtherCost())
            .append("remarks", getRemarks())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .append("isDelflag", getIsDelflag())
            .toString();
    }
}
