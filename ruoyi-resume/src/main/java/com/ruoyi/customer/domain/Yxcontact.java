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
 * @author ruoyi
 * @date 2020-11-09
 */
public class Yxcontact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long contactId;

    /** 公司Id */
    @Excel(name = "公司Id")
    private Long entryId;

    /** 跟进详情 */
    @Excel(name = "跟进详情")
    private String contactDetail;

    /** 跟进人姓名 */
    @Excel(name = "跟进人姓名")
    private String contactName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactTime;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String mailbox;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** QQ */
    @Excel(name = "QQ")
    private String qq;

    /** 添加人 */
    @Excel(name = "添加人")
    private String usercode;

    public void setContactId(Long contactId) 
    {
        this.contactId = contactId;
    }

    public Long getContactId() 
    {
        return contactId;
    }
    public void setEntryId(Long entryId) 
    {
        this.entryId = entryId;
    }

    public Long getEntryId() 
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
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactTime(Date contactTime) 
    {
        this.contactTime = contactTime;
    }

    public Date getContactTime() 
    {
        return contactTime;
    }
    public void setMailbox(String mailbox) 
    {
        this.mailbox = mailbox;
    }

    public String getMailbox() 
    {
        return mailbox;
    }
    public void setWechat(String wechat) 
    {
        this.wechat = wechat;
    }

    public String getWechat() 
    {
        return wechat;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setUsercode(String usercode) 
    {
        this.usercode = usercode;
    }

    public String getUsercode() 
    {
        return usercode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contactId", getContactId())
            .append("entryId", getEntryId())
            .append("contactDetail", getContactDetail())
            .append("contactName", getContactName())
            .append("contactTime", getContactTime())
            .append("mailbox", getMailbox())
            .append("wechat", getWechat())
            .append("qq", getQq())
            .append("usercode", getUsercode())
            .toString();
    }
}
