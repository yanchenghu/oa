package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 操作记录对象 con_operationrecords
 * 
 * @author ych
 * @date 2020-11-09
 */
public class ConOperationrecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 类型：1-录入，2-跟踪，3-抢取，4-绑定 */
    @Excel(name = "类型：1-录入，2-跟踪，3-抢取，4-绑定")
    private String type;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date datetime;

    /** 添加人 */
    @Excel(name = "添加人")
    private String userName;

    /** 默认0，人事组 */
    @Excel(name = "默认0，人事组")
    private Integer zsgroup;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDatetime(Date datetime) 
    {
        this.datetime = datetime;
    }

    public Date getDatetime() 
    {
        return datetime;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setZsgroup(Integer zsgroup) 
    {
        this.zsgroup = zsgroup;
    }

    public Integer getZsgroup() 
    {
        return zsgroup;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("datetime", getDatetime())
            .append("userName", getUserName())
            .append("remark", getRemark())
            .append("zsgroup", getZsgroup())
            .toString();
    }
}
