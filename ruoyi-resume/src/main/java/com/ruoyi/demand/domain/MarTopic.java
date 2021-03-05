package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试题对象 mar_topic
 * 
 * @author ych
 * @date 2021-03-02
 */
public class MarTopic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 公司代码 */
    @Excel(name = "公司代码")
    private String corpCode;

    /** 需求表id */
    @Excel(name = "需求表id")
    private String demandId;

    /** 需求名字 */
    @Excel(name = "需求名字")
    private String projectName;

    /** 面试题路径 */
    @Excel(name = "面试题路径")
    private String topicPath;

    /** 备注 */
    @Excel(name = "备注")
    private String topicBz;

    /** 面试题状态（0、审核中，1、通过，2、拒绝） */
    @Excel(name = "面试题状态", readConverterExp = "0=、审核中，1、通过，2、拒绝")
    private Integer topicStatu;

    /** 通过拒绝备注 */
    @Excel(name = "通过拒绝备注")
    private String topicReason;

    /** 录入人ID */
    @Excel(name = "录入人ID")
    private String topicUsername;

    /** 录入人姓名 */
    @Excel(name = "录入人姓名")
    private String topicNickname;

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
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setTopicPath(String topicPath) 
    {
        this.topicPath = topicPath;
    }

    public String getTopicPath() 
    {
        return topicPath;
    }
    public void setTopicBz(String topicBz) 
    {
        this.topicBz = topicBz;
    }

    public String getTopicBz() 
    {
        return topicBz;
    }
    public void setTopicStatu(Integer topicStatu) 
    {
        this.topicStatu = topicStatu;
    }

    public Integer getTopicStatu() 
    {
        return topicStatu;
    }
    public void setTopicReason(String topicReason) 
    {
        this.topicReason = topicReason;
    }

    public String getTopicReason() 
    {
        return topicReason;
    }
    public void setTopicUsername(String topicUsername) 
    {
        this.topicUsername = topicUsername;
    }

    public String getTopicUsername() 
    {
        return topicUsername;
    }
    public void setTopicNickname(String topicNickname) 
    {
        this.topicNickname = topicNickname;
    }

    public String getTopicNickname() 
    {
        return topicNickname;
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
            .append("demandId", getDemandId())
            .append("projectName", getProjectName())
            .append("topicPath", getTopicPath())
            .append("topicBz", getTopicBz())
            .append("topicStatu", getTopicStatu())
            .append("topicReason", getTopicReason())
            .append("topicUsername", getTopicUsername())
            .append("topicNickname", getTopicNickname())
            .append("addTime", getAddTime())
            .toString();
    }
}
