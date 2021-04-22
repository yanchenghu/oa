package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合作公司更改记录对象 mar_company_changerecord
 * 
 * @author ych
 * @date 2021-04-21
 */
public class MarCompanyChangerecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 序列 */
    @Excel(name = "序列")
    private String corpCode;

    /** 更改类型（1、更改合作公司、2、更改电话） */
    @Excel(name = "更改类型", readConverterExp = "1=、更改合作公司、2、更改电话")
    private Integer changeType;

    /** 更改内容 */
    @Excel(name = "更改内容")
    private String changeContent;

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
    public void setChangeType(Integer changeType) 
    {
        this.changeType = changeType;
    }

    public Integer getChangeType() 
    {
        return changeType;
    }
    public void setChangeContent(String changeContent) 
    {
        this.changeContent = changeContent;
    }

    public String getChangeContent() 
    {
        return changeContent;
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
            .append("changeType", getChangeType())
            .append("changeContent", getChangeContent())
            .append("addTime", getAddTime())
            .toString();
    }
}
