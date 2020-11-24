package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 跟进联系内容对象 yxcontact
 * 
 * @author ych
 * @date 2020-11-23
 */
public class Yxcontact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer contactId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer entryId;

    /** 联系记录 */
    @Excel(name = "联系记录")
    private String contactDetail;

    /** 跟进人姓名 */
    @Excel(name = "跟进人姓名")
    private String nickName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date contactTime;

    /** 1录入2、改变状态 3、跟进 */
    @Excel(name = "1录入2、改变状态 3、跟进")
    private Integer status;

    public void setContactId(Integer contactId) 
    {
        this.contactId = contactId;
    }

    public Integer getContactId() 
    {
        return contactId;
    }
    public void setEntryId(Integer entryId) 
    {
        this.entryId = entryId;
    }

    public Integer getEntryId() 
    {
        return entryId;
    }
    public void setContactDetail(String contactDetail) 
    {
        this.contactDetail = contactDetail;
    }

    public String getContactDetail() 
    {
        return contactDetail;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setContactTime(Date contactTime) 
    {
        this.contactTime = contactTime;
    }

    public Date getContactTime() 
    {
        return contactTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contactId", getContactId())
            .append("entryId", getEntryId())
            .append("contactDetail", getContactDetail())
            .append("nickName", getNickName())
            .append("contactTime", getContactTime())
            .append("status", getStatus())
            .toString();
    }
}
