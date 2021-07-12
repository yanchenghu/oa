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
 * @date 2021-07-07
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

    /** 面试方式 */
    @Excel(name = "面试方式")
    private String interviewMode;

    /** 确定面试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "确定面试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date interviewTime;

    /** 面试地点 */
    @Excel(name = "面试地点")
    private String interviewLocation;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    /** 面试联系人 */
    @Excel(name = "面试联系人")
    private String interviewContact;

    /** $column.columnComment */
    @Excel(name = "面试联系人")
    private String bz;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String beCareful;


    private String customerName;
    private String followStatus;


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
    public void setInterviewMode(String interviewMode) 
    {
        this.interviewMode = interviewMode;
    }

    public String getInterviewMode() 
    {
        return interviewMode;
    }
    public void setInterviewTime(Date interviewTime) 
    {
        this.interviewTime = interviewTime;
    }

    public Date getInterviewTime() 
    {
        return interviewTime;
    }
    public void setInterviewLocation(String interviewLocation) 
    {
        this.interviewLocation = interviewLocation;
    }

    public String getInterviewLocation() 
    {
        return interviewLocation;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }
    public void setInterviewContact(String interviewContact) 
    {
        this.interviewContact = interviewContact;
    }

    public String getInterviewContact() 
    {
        return interviewContact;
    }
    public void setBz(String bz) 
    {
        this.bz = bz;
    }

    public String getBz() 
    {
        return bz;
    }
    public void setBeCareful(String beCareful) 
    {
        this.beCareful = beCareful;
    }

    public String getBeCareful() 
    {
        return beCareful;
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
            .append("interviewMode", getInterviewMode())
            .append("interviewTime", getInterviewTime())
            .append("interviewLocation", getInterviewLocation())
            .append("insertTime", getInsertTime())
            .append("interviewContact", getInterviewContact())
            .append("bz", getBz())
            .append("beCareful", getBeCareful())
            .toString();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }
}
