package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 添加合作公司对象 fin_ancecompany
 * 
 * @author ych
 * @date 2021-04-01
 */
public class FinAncecompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 合作公司代码 */
    @Excel(name = "合作公司代码")
    private String corpCode;

    /** 合作公司 */
    @Excel(name = "合作公司")
    private String corpName;

    /** 1、添加状态2、移除状态 */
    @Excel(name = "1、添加状态2、移除状态")
    private Integer statu;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
    }
    public void setCorpName(String corpName) 
    {
        this.corpName = corpName;
    }

    public String getCorpName() 
    {
        return corpName;
    }
    public void setStatu(Integer statu) 
    {
        this.statu = statu;
    }

    public Integer getStatu() 
    {
        return statu;
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
            .append("id", getId())
            .append("corpCode", getCorpCode())
            .append("corpName", getCorpName())
            .append("statu", getStatu())
            .append("addTime", getAddTime())
            .toString();
    }
}
