package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 待面试对象 mar_waitinginterview
 * 
 * @author ych
 * @date 2021-04-29
 */
public class MarWaitinginterview
{
    private static final long serialVersionUID = 1L;

    /** 待面试人主键 */
    private Integer id;

    /** 人事 */
    @Excel(name = "人事")
    private String nickName;

    /** 人事ID */
    @Excel(name = "人事ID")
    private String userName;

    /** 需求ID */
    @Excel(name = "需求ID")
    private String demandId;

    /** 简历编号 */
    @Excel(name = "简历编号")
    private String customerCode;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 确定面试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "确定面试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date interviewTime;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    /** $column.columnComment */
    @Excel(name = "添加时间")
    private String bz;

    private String customerName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setInterviewTime(Date interviewTime) 
    {
        this.interviewTime = interviewTime;
    }

    public Date getInterviewTime() 
    {
        return interviewTime;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
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
            .append("id", getId())
            .append("nickName", getNickName())
            .append("userName", getUserName())
            .append("demandId", getDemandId())
            .append("customerCode", getCustomerCode())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("interviewTime", getInterviewTime())
            .append("insertTime", getInsertTime())
            .append("bz", getBz())
            .toString();
    }
}
