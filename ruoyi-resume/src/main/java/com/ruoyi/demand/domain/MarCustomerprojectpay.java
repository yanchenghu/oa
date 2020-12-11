package com.ruoyi.demand.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入项对象 mar_customerprojectpay
 * 
 * @author ych
 * @date 2020-12-07
 */
public class MarCustomerprojectpay
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String customerCode;

    /** 外派公司编号 */
    @Excel(name = "外派公司编号")
    private String corpCode;

    /** 外派公司名称 */
    @Excel(name = "外派公司名称")
    private String corpName;

    /** 外派公司项目编号 */
    @Excel(name = "外派公司项目编号")
    private String demandId;

    /** 外派公司项目名称 */
    @Excel(name = "外派公司项目名称")
    private String projectName;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 结算周期（1-月付  2-隔月付 3-季付5-每半年4其他） */
    @Excel(name = "结算周期", readConverterExp = "1=-月付,2=-隔月付,3=-季付5-每半年4其他")
    private Integer settledCycle;

    /** 工资 */
    @Excel(name = "工资")
    private BigDecimal salary;

    /** 服务费用 */
    @Excel(name = "服务费用")
    private BigDecimal servicePay;

    /** 出项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outofProjecttime;

    /** 离项原因 */
    @Excel(name = "离项原因")
    private Integer quitProreason;

    /** 离项备注 */
    @Excel(name = "离项备注")
    private String quitRemark;

    /** 操作人编号 */
    @Excel(name = "操作人编号")
    private String opercode;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operTime;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date syqstartTime;

    /** 转正时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转正时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date syqEndtime;

    /** 社保选项(1、交2、否) */
    @Excel(name = "社保选项(1、交2、否)")
    private Integer socSecopt;

    /** 操作人编号 */
    @Excel(name = "备注")
    private String remark;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
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
    public void setDemandId(String demandId) 
    {
        this.demandId = demandId;
    }

    public String getDemandId() 
    {
        return demandId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setSettledCycle(Integer settledCycle) 
    {
        this.settledCycle = settledCycle;
    }

    public Integer getSettledCycle() 
    {
        return settledCycle;
    }
    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }
    public void setServicePay(BigDecimal servicePay)
    {
        this.servicePay = servicePay;
    }

    public BigDecimal getServicePay()
    {
        return servicePay;
    }
    public void setOutofProjecttime(Date outofProjecttime) 
    {
        this.outofProjecttime = outofProjecttime;
    }

    public Date getOutofProjecttime() 
    {
        return outofProjecttime;
    }
    public void setQuitProreason(Integer quitProreason) 
    {
        this.quitProreason = quitProreason;
    }

    public Integer getQuitProreason() 
    {
        return quitProreason;
    }
    public void setQuitRemark(String quitRemark) 
    {
        this.quitRemark = quitRemark;
    }

    public String getQuitRemark() 
    {
        return quitRemark;
    }
    public void setOpercode(String opercode) 
    {
        this.opercode = opercode;
    }

    public String getOpercode() 
    {
        return opercode;
    }
    public void setOperTime(Date operTime) 
    {
        this.operTime = operTime;
    }

    public Date getOperTime() 
    {
        return operTime;
    }
    public void setSyqstartTime(Date syqstartTime) 
    {
        this.syqstartTime = syqstartTime;
    }

    public Date getSyqstartTime() 
    {
        return syqstartTime;
    }
    public void setSyqEndtime(Date syqEndtime) 
    {
        this.syqEndtime = syqEndtime;
    }

    public Date getSyqEndtime() 
    {
        return syqEndtime;
    }
    public void setSocSecopt(Integer socSecopt) 
    {
        this.socSecopt = socSecopt;
    }

    public Integer getSocSecopt() 
    {
        return socSecopt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerCode", getCustomerCode())
            .append("corpCode", getCorpCode())
            .append("corpName", getCorpName())
            .append("demandId", getDemandId())
            .append("projectName", getProjectName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("settledCycle", getSettledCycle())
            .append("salary", getSalary())
            .append("servicePay", getServicePay())
            .append("outofProjecttime", getOutofProjecttime())
            .append("quitProreason", getQuitProreason())
            .append("quitRemark", getQuitRemark())
            .append("opercode", getOpercode())
            .append("operTime", getOperTime())
            .append("syqstartTime", getSyqstartTime())
            .append("syqEndtime", getSyqEndtime())
            .append("socSecopt", getSocSecopt())
            .append("remark", getRemark())
            .toString();
    }
}
