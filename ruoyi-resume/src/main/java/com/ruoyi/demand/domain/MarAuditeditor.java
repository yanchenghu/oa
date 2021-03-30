package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审核编辑后的面试题对象 mar_auditeditor
 * 
 * @author ych
 * @date 2021-03-02
 */
public class MarAuditeditor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 整理过后的ID */
    private Integer auditedId;

    /** 公司代码 */
    @Excel(name = "公司代码")
    private String corpCode;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String corpName;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String auditedName;

    /** 整理过后面试题路径 */
    @Excel(name = "整理过后面试题路径")
    private String auditedPath;

    /** 面试题备注 */
    @Excel(name = "面试题备注")
    private String auditedBz;

    /** 录入人ID */
    @Excel(name = "录入人ID")
    private String topicUsername;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setAuditedId(Integer auditedId) 
    {
        this.auditedId = auditedId;
    }

    public Integer getAuditedId() 
    {
        return auditedId;
    }
    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
    }
    public void setCorpName(String corpName) 
    {
        this.corpName = corpName;
    }

    public String getCorpName() 
    {
        return corpName;
    }
    public void setAuditedPath(String auditedPath) 
    {
        this.auditedPath = auditedPath;
    }

    public String getAuditedName() {
        return auditedName;
    }

    public void setAuditedName(String auditedName) {
        this.auditedName = auditedName;
    }

    public String getAuditedPath()
    {
        return auditedPath;
    }
    public void setAuditedBz(String auditedBz) 
    {
        this.auditedBz = auditedBz;
    }

    public String getAuditedBz() 
    {
        return auditedBz;
    }
    public void setTopicUsername(String topicUsername) 
    {
        this.topicUsername = topicUsername;
    }

    public String getTopicUsername() 
    {
        return topicUsername;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

}
