package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 重点监测公司对象 fin_monitoringcompanies
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
public class FinMonitoringcompanies extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 合作公司代码 */
    @Excel(name = "合作公司")
    private String corpName;
    /** 合作公司代码 */
    @Excel(name = "合作公司代码")
    private String corpCode;

    /** 回款到那个月 */
    @Excel(name = "回款到那个月")
    private String backMonth;

    /** 目前欠款 */
    @Excel(name = "目前欠款")
    private String currentArrears;

    /** 最新一次的沟通情况 */
    @Excel(name = "最新一次的沟通情况")
    private String contactSituation;

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
    public void setBackMonth(String backMonth) 
    {
        this.backMonth = backMonth;
    }

    public String getBackMonth() 
    {
        return backMonth;
    }
    public void setCurrentArrears(String currentArrears) 
    {
        this.currentArrears = currentArrears;
    }

    public String getCurrentArrears() 
    {
        return currentArrears;
    }
    public void setContactSituation(String contactSituation) 
    {
        this.contactSituation = contactSituation;
    }

    public String getContactSituation() 
    {
        return contactSituation;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("corpCode", getCorpCode())
            .append("backMonth", getBackMonth())
            .append("currentArrears", getCurrentArrears())
            .append("contactSituation", getContactSituation())
            .append("addTime", getAddTime())
            .toString();
    }
}
