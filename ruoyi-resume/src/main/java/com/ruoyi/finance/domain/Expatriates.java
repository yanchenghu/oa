package com.ruoyi.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

public class Expatriates {
    /** 姓名 */
    @Excel(name = "姓名")
    private String customerName;

    /** 手机号 */
    @Excel(name = "电话")
    private String customerTel;

    /** 工资日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date months;

    /** 综合工资 */
    @Excel(name = "综合工资")
    private String basicSalary;

    /** 满勤/天 */
    @Excel(name = "满勤/天")
    private String overAttence;

    /** 实际出勤/天 */
    @Excel(name = "实际出勤/天")
    private String realAttence;

    /** 应发工资 */
    @Excel(name = "应发工资")
    private String projectSalary;

    /** 补助 */
    @Excel(name = "补助")
    private String buzhuSalary;

    /** 保险 */
    @Excel(name = "保险")
    private String baoxianSalary;

    /** 实发金额 */
    @Excel(name = "实发金额")
    private String realSalary;


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

    public Date getMonths() {
        return months;
    }

    public void setMonths(Date months) {
        this.months = months;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getOverAttence() {
        return overAttence;
    }

    public void setOverAttence(String overAttence) {
        this.overAttence = overAttence;
    }

    public String getRealAttence() {
        return realAttence;
    }

    public void setRealAttence(String realAttence) {
        this.realAttence = realAttence;
    }

    public String getProjectSalary() {
        return projectSalary;
    }

    public void setProjectSalary(String projectSalary) {
        this.projectSalary = projectSalary;
    }

    public String getBuzhuSalary() {
        return buzhuSalary;
    }

    public void setBuzhuSalary(String buzhuSalary) {
        this.buzhuSalary = buzhuSalary;
    }

    public String getBaoxianSalary() {
        return baoxianSalary;
    }

    public void setBaoxianSalary(String baoxianSalary) {
        this.baoxianSalary = baoxianSalary;
    }

    public String getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(String realSalary) {
        this.realSalary = realSalary;
    }
}
