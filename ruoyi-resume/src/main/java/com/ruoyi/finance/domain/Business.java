package com.ruoyi.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

public class Business {

    /** 姓名 */
    @Excel(name = "姓名")
    private String customerName;


    /** 结算单开始时间 */
    @Excel(name = "结算单开始时间")
    private String time;

    /** 服务费 */
    @Excel(name = "服务费")
    private Double price;

    /** 加班小时 */
    @Excel(name = "系统服务费")
    private Double overTime;

    /** 标准工时 */
    @Excel(name = "标准工时")
    private Double standard;

    /** 出勤工时 */
    @Excel(name = "出勤工时")
    private Double attenDance;

    /** 加班费 */
    @Excel(name = "加班费")
    private Double overPay;

    /** 合计 */
    @Excel(name = "合计")
    private Double comBined;

    /** 合计 */
    @Excel(name = "系统合计")
    private Double systemcomBined;

    /** 状态：1-未开票，2-已开票，3-已付款 */
    @Excel(name = "状态：1-未开票，2-已开票，3-已付款")
    private String status;

    /** 修改原因 */
    @Excel(name = "修改原因")
    private String reason;



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getOverTime() {
        return overTime;
    }

    public void setOverTime(Double overTime) {
        this.overTime = overTime;
    }

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }

    public Double getAttenDance() {
        return attenDance;
    }

    public void setAttenDance(Double attenDance) {
        this.attenDance = attenDance;
    }

    public Double getOverPay() {
        return overPay;
    }

    public void setOverPay(Double overPay) {
        this.overPay = overPay;
    }

    public Double getComBined() {
        return comBined;
    }

    public void setComBined(Double comBined) {
        this.comBined = comBined;
    }

    public Double getSystemcomBined() {
        return systemcomBined;
    }

    public void setSystemcomBined(Double systemcomBined) {
        this.systemcomBined = systemcomBined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
