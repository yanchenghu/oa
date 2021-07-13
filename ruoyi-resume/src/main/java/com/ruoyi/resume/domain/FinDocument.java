package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学习园地对象 fin_document
 * 
 * @author ych
 * @date 2021-07-12
 */
public class FinDocument extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 文档类型 */
    @Excel(name = "文档类型")
    private Integer documentType;

    /** 文档名称 */
    @Excel(name = "文档名称")
    private String documentName;

    /** 文档简介 */
    @Excel(name = "文档简介")
    private String documentIntroduce;

    /** 文档路径 */
    @Excel(name = "文档路径")
    private String documentPath;

    /** 文档读取次数 */
    @Excel(name = "文档读取次数")
    private Integer documentReadnum;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 文档分类（1普通2视频） */
    @Excel(name = "文档分类", readConverterExp = "1=普通2视频")
    private Integer documentSort;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDocumentType(Integer documentType) 
    {
        this.documentType = documentType;
    }

    public Integer getDocumentType() 
    {
        return documentType;
    }
    public void setDocumentName(String documentName) 
    {
        this.documentName = documentName;
    }

    public String getDocumentName() 
    {
        return documentName;
    }
    public void setDocumentIntroduce(String documentIntroduce) 
    {
        this.documentIntroduce = documentIntroduce;
    }

    public String getDocumentIntroduce() 
    {
        return documentIntroduce;
    }
    public void setDocumentPath(String documentPath) 
    {
        this.documentPath = documentPath;
    }

    public String getDocumentPath() 
    {
        return documentPath;
    }
    public void setDocumentReadnum(Integer documentReadnum) 
    {
        this.documentReadnum = documentReadnum;
    }

    public Integer getDocumentReadnum() 
    {
        return documentReadnum;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
{
    return addTime;
}

    public Integer getDocumentSort() {
        return documentSort;
    }

    public void setDocumentSort(Integer documentSort) {
        this.documentSort = documentSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("documentType", getDocumentType())
            .append("documentName", getDocumentName())
            .append("documentIntroduce", getDocumentIntroduce())
            .append("documentPath", getDocumentPath())
            .append("documentReadnum", getDocumentReadnum())
            .append("addTime", getAddTime())
            .append("documentSort", getDocumentSort())
            .toString();
    }
}
