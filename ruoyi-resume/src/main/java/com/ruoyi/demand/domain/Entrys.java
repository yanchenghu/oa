package com.ruoyi.demand.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class Entrys {
    /** 客户姓名 */
    @Excel(name = "姓名")
    private String customerName;
    /** 客户电话 */
    @Excel(name = "电话")
    private String customerTel;


    @Excel(name = "入职公司")
    private String corpName;

    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date syqstartTime;

    /** 客户电话 */
    @Excel(name = "技术方向")
    private String technologyDirection;

    @Excel(name = "出项日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outofProjecttime;

    @Excel(name = "人员成本")
    private BigDecimal salary;

    @Excel(name = "服务费用")
    private BigDecimal servicePay;

    /** 社保选项(1、交2、否) */
    @Excel(name = "社保选项(1、交2、否)")
    private String socSecopt;

    /** 社保选项(1、交2、否) */
    @Excel(name = "签署合同(1、是2、否)")
    private String sign;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public Date getSyqstartTime() {
        return syqstartTime;
    }

    public void setSyqstartTime(Date syqstartTime) {
        this.syqstartTime = syqstartTime;
    }

    public String getTechnologyDirection() {
        return technologyDirection;
    }

    public void setTechnologyDirection(String technologyDirection) {
        this.technologyDirection = technologyDirection;
    }

    public Date getOutofProjecttime() {
        return outofProjecttime;
    }

    public void setOutofProjecttime(Date outofProjecttime) {
        this.outofProjecttime = outofProjecttime;
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

    public String getSocSecopt() {
        return socSecopt;
    }

    public void setSocSecopt(String socSecopt) {
        this.socSecopt = socSecopt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
