package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历模板对象 per_template
 * 
 * @author ruoyi
 * @date 2020-12-23
 */
public class PerTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板id */
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板命名格式 */
    @Excel(name = "模板命名格式")
    private String templateNominate;

    /** 模板文件 */
    @Excel(name = "模板文件")
    private String templateFile;

    /** 模板图片 */
    @Excel(name = "模板图片")
    private String templatePrc;

    /** 模板状态（1.启用 2.作废） */
    @Excel(name = "模板状态", readConverterExp = "1=.启用,2=.作废")
    private String templateStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addPeople;

    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setTemplateNominate(String templateNominate) 
    {
        this.templateNominate = templateNominate;
    }

    public String getTemplateNominate() 
    {
        return templateNominate;
    }
    public void setTemplateFile(String templateFile) 
    {
        this.templateFile = templateFile;
    }

    public String getTemplateFile() 
    {
        return templateFile;
    }
    public void setTemplatePrc(String templatePrc) 
    {
        this.templatePrc = templatePrc;
    }

    public String getTemplatePrc() 
    {
        return templatePrc;
    }
    public void setTemplateStatus(String templateStatus) 
    {
        this.templateStatus = templateStatus;
    }

    public String getTemplateStatus() 
    {
        return templateStatus;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setAddPeople(String addPeople) 
    {
        this.addPeople = addPeople;
    }

    public String getAddPeople() 
    {
        return addPeople;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("templateNominate", getTemplateNominate())
            .append("templateFile", getTemplateFile())
            .append("templatePrc", getTemplatePrc())
            .append("templateStatus", getTemplateStatus())
            .append("addTime", getAddTime())
            .append("addPeople", getAddPeople())
            .toString();
    }
}
