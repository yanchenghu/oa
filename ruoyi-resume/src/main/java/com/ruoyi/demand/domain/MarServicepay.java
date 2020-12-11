package com.ruoyi.demand.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务费调整记录对象 mar_servicepay
 * 
 * @author ych
 * @date 2020-12-11
 */
public class MarServicepay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务费调整id */
    private Integer servicepayId;

    /** 入项ID */
    @Excel(name = "入项ID")
    private String marcusId;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String customerCode;

    /** 外派公司名称 */
    @Excel(name = "外派公司名称")
    private String corpName;

    /** 外派公司项目编号 */
    @Excel(name = "外派公司项目编号")
    private String demandId;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date syqstartTime;

    /** 调整前工资 */
    @Excel(name = "调整前工资")
    private BigDecimal beforeServicepay;

    /** 外派公司项目名称 */
    @Excel(name = "外派公司项目名称")
    private String projectName;

    /** 调整后工资 */
    @Excel(name = "调整后工资")
    private BigDecimal afterServicepay;

    /** 调整日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调整日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adjustTime;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 调整原因 */
    @Excel(name = "调整原因")
    private String adjustDetail;

    public void setServicepayId(Integer servicepayId) 
    {
        this.servicepayId = servicepayId;
    }

    public Integer getServicepayId() 
    {
        return servicepayId;
    }
    public void setMarcusId(String marcusId) 
    {
        this.marcusId = marcusId;
    }

    public String getMarcusId() 
    {
        return marcusId;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setCorpName(String corpName) 
    {
        this.corpName = corpName;
    }

    public String getCorpName() 
    {
        return corpName;
    }
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setSyqstartTime(Date syqstartTime) 
    {
        this.syqstartTime = syqstartTime;
    }

    public Date getSyqstartTime() 
    {
        return syqstartTime;
    }
    public void setBeforeServicepay(BigDecimal beforeServicepay) 
    {
        this.beforeServicepay = beforeServicepay;
    }

    public BigDecimal getBeforeServicepay() 
    {
        return beforeServicepay;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setAfterServicepay(BigDecimal afterServicepay) 
    {
        this.afterServicepay = afterServicepay;
    }

    public BigDecimal getAfterServicepay() 
    {
        return afterServicepay;
    }
    public void setAdjustTime(Date adjustTime) 
    {
        this.adjustTime = adjustTime;
    }

    public Date getAdjustTime() 
    {
        return adjustTime;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setAdjustDetail(String adjustDetail) 
    {
        this.adjustDetail = adjustDetail;
    }

    public String getAdjustDetail() 
    {
        return adjustDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("servicepayId", getServicepayId())
            .append("marcusId", getMarcusId())
            .append("customerCode", getCustomerCode())
            .append("corpName", getCorpName())
            .append("demandId", getDemandId())
            .append("syqstartTime", getSyqstartTime())
            .append("beforeServicepay", getBeforeServicepay())
            .append("projectName", getProjectName())
            .append("afterServicepay", getAfterServicepay())
            .append("adjustTime", getAdjustTime())
            .append("addTime", getAddTime())
            .append("adjustDetail", getAdjustDetail())
            .toString();
    }
}
