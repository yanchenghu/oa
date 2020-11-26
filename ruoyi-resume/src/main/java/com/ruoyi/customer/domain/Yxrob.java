package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢占记录对象 yxrob
 * 
 * @author ruoyi
 * @date 2020-11-26
 */
public class Yxrob extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer robId;

    /** 需求ID */
    @Excel(name = "需求ID")
    private Integer entryId;

    /** 抢占人ID */
    @Excel(name = "抢占人ID")
    private String robUsercode;

    /** 抢占人姓名 */
    @Excel(name = "抢占人姓名")
    private String robName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date robTime;

    /** 备注 */
    @Excel(name = "备注")
    private String bz;

    public void setRobId(Integer robId) 
    {
        this.robId = robId;
    }

    public Integer getRobId() 
    {
        return robId;
    }
    public void setEntryId(Integer entryId) 
    {
        this.entryId = entryId;
    }

    public Integer getEntryId() 
    {
        return entryId;
    }
    public void setRobUsercode(String robUsercode) 
    {
        this.robUsercode = robUsercode;
    }

    public String getRobUsercode() 
    {
        return robUsercode;
    }
    public void setRobName(String robName) 
    {
        this.robName = robName;
    }

    public String getRobName() 
    {
        return robName;
    }
    public void setRobTime(Date robTime) 
    {
        this.robTime = robTime;
    }

    public Date getRobTime() 
    {
        return robTime;
    }
    public void setBz(String bz) 
    {
        this.bz = bz;
    }

    public String getBz() 
    {
        return bz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("robId", getRobId())
            .append("entryId", getEntryId())
            .append("robUsercode", getRobUsercode())
            .append("robName", getRobName())
            .append("robTime", getRobTime())
            .append("bz", getBz())
            .toString();
    }
}
