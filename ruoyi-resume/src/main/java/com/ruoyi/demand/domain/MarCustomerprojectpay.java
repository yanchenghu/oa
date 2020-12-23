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

    /** 入项协助人 */
    @Excel(name = "入项协助人")
    private String entryAssistant;
    /** 入项协助人 */
    @Excel(name = "备注")
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSettledCycle() {
        return settledCycle;
    }

    public void setSettledCycle(Integer settledCycle) {
        this.settledCycle = settledCycle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getServicePay() {
        return servicePay;
    }

    public void setServicePay(BigDecimal servicePay) {
        this.servicePay = servicePay;
    }

    public Date getOutofProjecttime() {
        return outofProjecttime;
    }

    public void setOutofProjecttime(Date outofProjecttime) {
        this.outofProjecttime = outofProjecttime;
    }

    public Integer getQuitProreason() {
        return quitProreason;
    }

    public void setQuitProreason(Integer quitProreason) {
        this.quitProreason = quitProreason;
    }

    public String getQuitRemark() {
        return quitRemark;
    }

    public void setQuitRemark(String quitRemark) {
        this.quitRemark = quitRemark;
    }

    public String getOpercode() {
        return opercode;
    }

    public void setOpercode(String opercode) {
        this.opercode = opercode;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Date getSyqstartTime() {
        return syqstartTime;
    }

    public void setSyqstartTime(Date syqstartTime) {
        this.syqstartTime = syqstartTime;
    }

    public Date getSyqEndtime() {
        return syqEndtime;
    }

    public void setSyqEndtime(Date syqEndtime) {
        this.syqEndtime = syqEndtime;
    }

    public Integer getSocSecopt() {
        return socSecopt;
    }

    public void setSocSecopt(Integer socSecopt) {
        this.socSecopt = socSecopt;
    }

    public String getEntryAssistant() {
        return entryAssistant;
    }

    public void setEntryAssistant(String entryAssistant) {
        this.entryAssistant = entryAssistant;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "MarCustomerprojectpay{" +
                "id='" + id + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", corpCode='" + corpCode + '\'' +
                ", corpName='" + corpName + '\'' +
                ", demandId='" + demandId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", settledCycle=" + settledCycle +
                ", salary=" + salary +
                ", servicePay=" + servicePay +
                ", outofProjecttime=" + outofProjecttime +
                ", quitProreason=" + quitProreason +
                ", quitRemark='" + quitRemark + '\'' +
                ", opercode='" + opercode + '\'' +
                ", operTime=" + operTime +
                ", syqstartTime=" + syqstartTime +
                ", syqEndtime=" + syqEndtime +
                ", socSecopt=" + socSecopt +
                ", entryAssistant='" + entryAssistant + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
