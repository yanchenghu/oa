package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 待入项对象 mar_waitingentry
 * 
 * @author ruoyi
 * @date 2021-03-31
 */
public class MarWaitingentry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 待入项人主键 */
    private Integer id;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 需求ID */
    @Excel(name = "需求ID")
    private String demandId;

    /** 面试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "面试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date interviewTime;

    /** 简历编号 */
    @Excel(name = "简历编号")
    private String customerCode;

    /** 预计入项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计入项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stayTime;

    /** 备注 */
    @Excel(name = "备注")
    private String bz;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setInterviewTime(Date interviewTime) 
    {
        this.interviewTime = interviewTime;
    }

    public Date getInterviewTime() 
    {
        return interviewTime;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setStayTime(Date stayTime) 
    {
        this.stayTime = stayTime;
    }

    public Date getStayTime() 
    {
        return stayTime;
    }
    public void setBz(String bz) 
    {
        this.bz = bz;
    }

    public String getBz() 
    {
        return bz;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nickName", getNickName())
            .append("demandId", getDemandId())
            .append("interviewTime", getInterviewTime())
            .append("customerCode", getCustomerCode())
            .append("stayTime", getStayTime())
            .append("bz", getBz())
            .append("insertTime", getInsertTime())
            .toString();
    }
}
