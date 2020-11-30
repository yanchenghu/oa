package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户跟进联系记录对象 mar_contractfollow
 * 
 * @author ruoyi
 * @date 2020-11-27
 */
public class MarContractfollow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer contractId;

    /** 序列 */
    @Excel(name = "序列")
    private String corpCode;

    /** 联系记录 */
    @Excel(name = "联系记录")
    private String contactDetail;

    /** 跟进人姓名 */
    @Excel(name = "跟进人姓名")
    private String nickName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public void setContractId(Integer contractId) 
    {
        this.contractId = contractId;
    }

    public Integer getContractId() 
    {
        return contractId;
    }
    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
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
            .append("contractId", getContractId())
            .append("corpCode", getCorpCode())
            .append("contactDetail", getContactDetail())
            .append("nickName", getNickName())
            .append("contactTime", getContactTime())
            .append("status", getStatus())
            .toString();
    }
}
