package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求绑定简历对象 mar_demandresume
 * 
 * @author ych
 * @date 2020-11-13
 */
public class MarDemandresume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 需求绑定简历Id */
    private String id;

    /** 需求Id */
    @Excel(name = "需求Id")
    private String demandId;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String customerCode;

    /** 绑定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bindTime;

    /** 绑定人 */
    @Excel(name = "绑定人")
    private String bindPeople;

    /** 下载状态（A-未下载  B-已下载） */
    @Excel(name = "下载状态", readConverterExp = "A=-未下载,B=-已下载")
    private Integer downloadStatus;

    /** 最新跟踪时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最新跟踪时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date newfollowtime;

    /** 跟踪人姓名 */
    @Excel(name = "跟踪人姓名")
    private String trackzPeoname;

    /** 简历附件地址 */
    @Excel(name = "简历附件地址")
    private String resumeEnclosurepath;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
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
    public void setBindTime(Date bindTime) 
    {
        this.bindTime = bindTime;
    }

    public Date getBindTime() 
    {
        return bindTime;
    }
    public void setBindPeople(String bindPeople) 
    {
        this.bindPeople = bindPeople;
    }

    public String getBindPeople() 
    {
        return bindPeople;
    }

    public Integer getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(Integer downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public void setNewfollowtime(Date newfollowtime)
    {
        this.newfollowtime = newfollowtime;
    }

    public Date getNewfollowtime() 
    {
        return newfollowtime;
    }
    public void setTrackzPeoname(String trackzPeoname) 
    {
        this.trackzPeoname = trackzPeoname;
    }

    public String getTrackzPeoname() 
    {
        return trackzPeoname;
    }

    public String getResumeEnclosurepath() {
        return resumeEnclosurepath;
    }

    public void setResumeEnclosurepath(String resumeEnclosurepath) {
        this.resumeEnclosurepath = resumeEnclosurepath;
    }

    @Override
    public String toString() {
        return "MarDemandresume{" +
                "id='" + id + '\'' +
                ", demandId='" + demandId + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", bindTime=" + bindTime +
                ", bindPeople='" + bindPeople + '\'' +
                ", downloadStatus=" + downloadStatus +
                ", newfollowtime=" + newfollowtime +
                ", trackzPeoname='" + trackzPeoname + '\'' +
                ", resumeEnclosurepath='" + resumeEnclosurepath + '\'' +
                '}';
    }
}
