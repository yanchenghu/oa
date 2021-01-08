package com.ruoyi.conn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 钉钉token对象 con_dingtoken
 * 
 * @author ych
 * @date 2021-01-08
 */
public class ConDingtoken extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String token;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /** 1、个人钉钉 2、公司群钉钉 */
    @Excel(name = "1、个人钉钉 2、公司群钉钉")
    private Integer dingType;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setExpireTime(Date expireTime) 
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() 
    {
        return expireTime;
    }
    public void setDingType(Integer dingType) 
    {
        this.dingType = dingType;
    }

    public Integer getDingType() 
    {
        return dingType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("token", getToken())
            .append("addTime", getAddTime())
            .append("expireTime", getExpireTime())
            .append("dingType", getDingType())
            .append("remark", getRemark())
            .toString();
    }
}
