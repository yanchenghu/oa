package com.ruoyi.conn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 假日表对象 con_holiday
 * 
 * @author ych
 * @date 2020-11-11
 */
public class ConHoliday extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /**  类型（1-班  2-休） */
    @Excel(name = " 类型", readConverterExp = "1=-班,2=-休")
    private Integer holType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setHolType(Integer holType) 
    {
        this.holType = holType;
    }

    public Integer getHolType() 
    {
        return holType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("startDate", getStartDate())
            .append("holType", getHolType())
            .toString();
    }
}
