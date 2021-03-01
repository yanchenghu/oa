package com.ruoyi.analysis.domain;

/**
 * @Description: 外派人员临时表
 * @Author ych
 * @Date 2021/2/22
 */
public class ExpatriateWages {
    /** 公司编码 */
    private String corpCode;
    /** 公司姓名 */
    private String corpName;
    /** 姓名 */
    private String customerName;
    /** 编号 */
    private String customerCode;
    /** 电话 */
    private String customerTel;

    /** 入职时间 */
    private String syqstartTime;
    /** 出项日期时间 */
    private String outofProjecttime;
    /** 工资 */
    private String salary;
    /** 服务费 */
    private String  servicePay;
    /** 最近回款月份 */
    private String lastcollecMoth;
    /** 累计工资 */
    private String cumulativeSalary;
    /** 累计服务费 */
    private String cumulativeServicepay;

    /*开始时间*/
    private String startTime;
    /*结束时间*/
    private String endTime;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getSyqstartTime() {
        return syqstartTime;
    }

    public void setSyqstartTime(String syqstartTime) {
        this.syqstartTime = syqstartTime;
    }

    public String getOutofProjecttime() {
        return outofProjecttime;
    }

    public void setOutofProjecttime(String outofProjecttime) {
        this.outofProjecttime = outofProjecttime;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getServicePay() {
        return servicePay;
    }

    public void setServicePay(String servicePay) {
        this.servicePay = servicePay;
    }

    public String getLastcollecMoth() {
        return lastcollecMoth;
    }

    public void setLastcollecMoth(String lastcollecMoth) {
        this.lastcollecMoth = lastcollecMoth;
    }

    public String getCumulativeSalary() {
        return cumulativeSalary;
    }

    public void setCumulativeSalary(String cumulativeSalary) {
        this.cumulativeSalary = cumulativeSalary;
    }

    public String getCumulativeServicepay() {
        return cumulativeServicepay;
    }

    public void setCumulativeServicepay(String cumulativeServicepay) {
        this.cumulativeServicepay = cumulativeServicepay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
