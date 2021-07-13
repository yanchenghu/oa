package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专业技能对象 per_enclosure_skills
 * 
 * @author ych
 * @date 2021-07-02
 */
public class PerEnclosureSkills extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 人员code */
    @Excel(name = "人员code")
    private String customerCode;

    /** 技能描述 */
    @Excel(name = "技能描述")
    private String skillsDescribe;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "技能描述", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public PerEnclosureSkills() {
    }

    public PerEnclosureSkills(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setSkillsDescribe(String skillsDescribe) 
    {
        this.skillsDescribe = skillsDescribe;
    }

    public String getSkillsDescribe() 
    {
        return skillsDescribe;
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
            .append("customerCode", getCustomerCode())
            .append("skillsDescribe", getSkillsDescribe())
            .append("addTime", getAddTime())
            .toString();
    }
}
