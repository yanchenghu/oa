package com.ruoyi.resume.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简历跟踪对象 per_cuscontact
 * 
 * @author ych
 * @date 2020-11-10
 */
public class PerCuscontact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 联系编号 */
    private String contacDatecode;

    /** 联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactTime;

    /** 联系人员编号 */
    @Excel(name = "联系人员编号")
    private String contactUsercode;

    /** 联系客户编号 */
    @Excel(name = "联系客户编号")
    private String contactCustomercode;

    /** 备注 */
    @Excel(name = "备注")
    private String memo;

    /** 入职概率 */
    @Excel(name = "入职概率")
    private Integer entryProbability;

    /** 最后更新状态（1:电话错误   2:不接电话   3:直接挂断电话   4:不接受外包   5:其他   6:不参加面试   7:薪资不合适   8:距离远） */
    @Excel(name = "最后更新状态", readConverterExp = "1=:电话错误,2=:不接电话,3=:直接挂断电话,4=:不接受外包,5=:其他,6=:不参加面试,7=:薪资不合适,8=:距离远")
    private Integer updateStatic;

    /** 是否考虑换工作（1-考虑   2-不考虑 ） */
    @Excel(name = "是否考虑换工作", readConverterExp = "1=-考虑,2=-不考虑")
    private Integer considerChangeJob;

    /** 意向地区 */
    @Excel(name = "意向地区")
    private String intentionArea;

    /** $column.columnComment */
    @Excel(name = "意向地区")
    private String binding;

    /** 不参加面试原因 */
    @Excel(name = "不参加面试原因")
    private String nointerviewing;

    /** 需求简历Id */
    @Excel(name = "需求简历Id")
    private String bindId;

    /** 跟踪状态(0-未入项  1-确认面试时间   2-入项) */
    @Excel(name = "跟踪状态(0-未入项  1-确认面试时间   2-入项)")
    private Integer followStatus;

    /** 需求Id */
    @Excel(name = "需求Id")
    private String demandId;

    public void setContacDatecode(String contacDatecode) 
    {
        this.contacDatecode = contacDatecode;
    }

    public String getContacDatecode() 
    {
        return contacDatecode;
    }
    public void setContactTime(Date contactTime) 
    {
        this.contactTime = contactTime;
    }

    public Date getContactTime() 
    {
        return contactTime;
    }
    public void setContactUsercode(String contactUsercode) 
    {
        this.contactUsercode = contactUsercode;
    }

    public String getContactUsercode() 
    {
        return contactUsercode;
    }
    public void setContactCustomercode(String contactCustomercode) 
    {
        this.contactCustomercode = contactCustomercode;
    }

    public String getContactCustomercode() 
    {
        return contactCustomercode;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setEntryProbability(Integer entryProbability) 
    {
        this.entryProbability = entryProbability;
    }

    public Integer getEntryProbability() 
    {
        return entryProbability;
    }
    public void setUpdateStatic(Integer updateStatic) 
    {
        this.updateStatic = updateStatic;
    }

    public Integer getUpdateStatic() 
    {
        return updateStatic;
    }
    public void setConsiderChangeJob(Integer considerChangeJob) 
    {
        this.considerChangeJob = considerChangeJob;
    }

    public Integer getConsiderChangeJob() 
    {
        return considerChangeJob;
    }
    public void setIntentionArea(String intentionArea) 
    {
        this.intentionArea = intentionArea;
    }

    public String getIntentionArea() 
    {
        return intentionArea;
    }
    public void setBinding(String binding) 
    {
        this.binding = binding;
    }

    public String getBinding() 
    {
        return binding;
    }
    public void setNointerviewing(String nointerviewing) 
    {
        this.nointerviewing = nointerviewing;
    }

    public String getNointerviewing() 
    {
        return nointerviewing;
    }
    public void setBindId(String bindId) 
    {
        this.bindId = bindId;
    }

    public String getBindId() 
    {
        return bindId;
    }
    public void setFollowStatus(Integer followStatus) 
    {
        this.followStatus = followStatus;
    }

    public Integer getFollowStatus() 
    {
        return followStatus;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contacDatecode", getContacDatecode())
            .append("contactTime", getContactTime())
            .append("contactUsercode", getContactUsercode())
            .append("contactCustomercode", getContactCustomercode())
            .append("memo", getMemo())
            .append("entryProbability", getEntryProbability())
            .append("updateStatic", getUpdateStatic())
            .append("considerChangeJob", getConsiderChangeJob())
            .append("intentionArea", getIntentionArea())
            .append("binding", getBinding())
            .append("nointerviewing", getNointerviewing())
            .append("bindId", getBindId())
            .append("followStatus", getFollowStatus())
            .append("demandId", getDemandId())
            .toString();
    }
}
