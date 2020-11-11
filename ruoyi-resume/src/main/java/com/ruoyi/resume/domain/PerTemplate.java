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
 * @date 2020-11-04
 */
public class PerTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板id */
    @Excel(name = "模板id")
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板文件 */
    @Excel(name = "模板文件")
    private String templateFile;

    /** 模板图片 */
    @Excel(name = "模板图片")
    private String templatePrc;

    /** 模板顺序 */
    @Excel(name = "模板顺序")
    private Long templateOrder;

    /** 模板状态（1.启用 2.作废） */
    @Excel(name = "模板状态", readConverterExp = "1=.启用,2=.作废")
    private String templateStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addpeople;

    /** 作废时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "作废时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invalidtime;

    /** 作废人 */
    @Excel(name = "作废人")
    private String invalidpeople;

    /** 命名格式  */
    private String  namingFormat;

    public String getNamingFormat() {
        return namingFormat;
    }

    public void setNamingFormat(String namingFormat) {
        this.namingFormat = namingFormat;
    }

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
    public void setTemplateOrder(Long templateOrder) 
    {
        this.templateOrder = templateOrder;
    }

    public Long getTemplateOrder() 
    {
        return templateOrder;
    }

    public String getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(String templateStatus) {
        this.templateStatus = templateStatus;
    }

    public void setAddtime(Date addtime)
    {
        this.addtime = addtime;
    }

    public Date getAddtime() 
    {
        return addtime;
    }
    public void setAddpeople(String addpeople) 
    {
        this.addpeople = addpeople;
    }

    public String getAddpeople() 
    {
        return addpeople;
    }
    public void setInvalidtime(Date invalidtime) 
    {
        this.invalidtime = invalidtime;
    }

    public Date getInvalidtime() 
    {
        return invalidtime;
    }
    public void setInvalidpeople(String invalidpeople) 
    {
        this.invalidpeople = invalidpeople;
    }

    public String getInvalidpeople() 
    {
        return invalidpeople;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("templateFile", getTemplateFile())
            .append("templatePrc", getTemplatePrc())
            .append("templateOrder", getTemplateOrder())
            .append("templateStatus", getTemplateStatus())
            .append("addtime", getAddtime())
            .append("addpeople", getAddpeople())
            .append("invalidtime", getInvalidtime())
            .append("invalidpeople", getInvalidpeople())
            .toString();
    }
}
