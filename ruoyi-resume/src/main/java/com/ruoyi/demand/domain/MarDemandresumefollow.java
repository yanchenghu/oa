package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历绑定需求跟踪情况对象 mar_demandresumefollow
 * 
 * @author ych
 * @date 2020-11-13
 */
public class MarDemandresumefollow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跟踪Id */
    private Integer id;

    /** 需求绑定简历Id */
    @Excel(name = "需求绑定简历Id")
    private String demandresumeId;

    /** 跟踪人 */
    @Excel(name = "跟踪人")
    private String trackingPeople;

    /** 跟踪时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "跟踪时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trackingtime;

    /** 跟踪状态（绑定简历1、是2、否, 简历通过3、是4、否,面试通过5、是6、否,入项7、是8、否） */
    @Excel(name = "跟踪状态", readConverterExp = "绑=定简历1、是2、否,,简=历通过3、是4、否,面试通过5、是6、否,入项7、是8、否")
    private Integer followStatus;

    /** 跟踪详情 */
    @Excel(name = "跟踪详情")
    private String followDetail;

    /** 备注 */
    @Excel(name = "备注")
    private String remark1;


    /** 注意事项 */
    private String beCareful;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDemandresumeId(String demandresumeId)
    {
        this.demandresumeId = demandresumeId;
    }

    public String getDemandresumeId() 
    {
        return demandresumeId;
    }
    public void setTrackingPeople(String trackingPeople) 
    {
        this.trackingPeople = trackingPeople;
    }

    public String getTrackingPeople() 
    {
        return trackingPeople;
    }
    public void setTrackingtime(Date trackingtime) 
    {
        this.trackingtime = trackingtime;
    }

    public Date getTrackingtime() 
    {
        return trackingtime;
    }
    public void setFollowStatus(Integer followStatus) 
    {
        this.followStatus = followStatus;
    }

    public Integer getFollowStatus() 
    {
        return followStatus;
    }
    public void setFollowDetail(String followDetail) 
    {
        this.followDetail = followDetail;
    }

    public String getFollowDetail() 
    {
        return followDetail;
    }
    public void setRemark1(String remark1) 
    {
        this.remark1 = remark1;
    }

    public String getRemark1() 
    {
        return remark1;
    }
    public String getBeCareful() {
        return beCareful;
    }

    public void setBeCareful(String beCareful) {
        this.beCareful = beCareful;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("demandresumeId", getDemandresumeId())
            .append("trackingPeople", getTrackingPeople())
            .append("trackingtime", getTrackingtime())
            .append("followStatus", getFollowStatus())
            .append("followDetail", getFollowDetail())
            .append("remark1", getRemark1())
            .toString();
    }


}
