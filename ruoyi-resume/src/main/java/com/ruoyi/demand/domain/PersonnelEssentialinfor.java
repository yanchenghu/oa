package com.ruoyi.demand.domain;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 人事入项详情
 *
 * @author ych
 * @date 2020-12-15
 */

public class PersonnelEssentialinfor {


    /** 客户姓名 */
    private String deptName;

    /** 客户姓名 */
    private String nickName;


    /** 客户姓名 */
    private String customerName;

    /** 工资 */
    private BigDecimal salary;
   /*  利润*/
    private BigDecimal profit;
    /*  利润率*/
    private BigDecimal profitMargin;
    /** 服务费用 */
    private BigDecimal servicePay;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date syqstartTime;

    /** 转正时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date syqEndtime;

    /** 社保选项(1、交2、否) */
    private Integer socSecopt;
    /** 客户电话 */
    private String customerTel;
    /** 入职地点 */
    private String entryAddress;

    /** 入职公司 */
    private String corpName;

    /** 入职状态 */
    private String joinStatus;

     /** 入项协助人 */
    private String entryAssistant;



    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        this.profitMargin = profitMargin;
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

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }


    public String getEntryAssistant() {
        return entryAssistant;
    }

    public void setEntryAssistant(String entryAssistant) {
        this.entryAssistant = entryAssistant;
    }
}