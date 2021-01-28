package com.ruoyi.demand.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class ExportItemList {
    @Excel(name = "简历姓名")
    private String customerName;

    @Excel(name = "招聘人员")
    private String nickName;

    @Excel(name = "入职公司")
    private String corpName;

    @Excel(name = "离项原因")
    private String quitProreason;

    @Excel(name = "离项备注")
    private String quitRemark;

    @Excel(name = "人员成本")
    private BigDecimal salary;

    @Excel(name = "服务费用")
    private BigDecimal servicePay;

    /*  利润*/
    @Excel(name = "利润")
    private BigDecimal profit;

    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date syqstartTime;

    @Excel(name = "出项日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outofProjecttime;

    @Excel(name = "借用物品")
    private String borrowSth;

    @Excel(name = "联系电话")
    private String customerTel;

    @Excel(name = "利润率")
    private String profitMargin;

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getQuitProreason() {
        return quitProreason;
    }

    public void setQuitProreason(String quitProreason) {
        this.quitProreason = quitProreason;
    }

    public String getQuitRemark() {
        return quitRemark;
    }

    public void setQuitRemark(String quitRemark) {
        this.quitRemark = quitRemark;
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

    public Date getSyqstartTime() {
        return syqstartTime;
    }

    public void setSyqstartTime(Date syqstartTime) {
        this.syqstartTime = syqstartTime;
    }

    public Date getOutofProjecttime() {
        return outofProjecttime;
    }

    public void setOutofProjecttime(Date outofProjecttime) {
        this.outofProjecttime = outofProjecttime;
    }

    public String getBorrowSth() {
        return borrowSth;
    }

    public void setBorrowSth(String borrowSth) {
        this.borrowSth = borrowSth;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin;
    }
}
