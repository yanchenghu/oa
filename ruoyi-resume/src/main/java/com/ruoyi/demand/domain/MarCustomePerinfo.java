package com.ruoyi.demand.domain;

import com.ruoyi.common.annotation.Excel;

public class MarCustomePerinfo {



    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String customerName;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String customerTel;
    /** 客户电话 */
    @Excel(name = "技术方向")
    private Integer technologyDirection;

    /** 客户电话 */
    @Excel(name = "商务")
    private String operUsername;

    @Excel(name = "人事")
    private String nickName;
    @Excel(name = "回款周期")
    private String settledCycle;

    /** 客户信息 */
    private MarCustomerprojectpay marCustomerprojectpay;


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

    public MarCustomerprojectpay getMarCustomerprojectpay() {
        return marCustomerprojectpay;
    }

    public void setMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay) {
        this.marCustomerprojectpay = marCustomerprojectpay;
    }

    public Integer getTechnologyDirection() {
        return technologyDirection;
    }

    public void setTechnologyDirection(Integer technologyDirection) {
        this.technologyDirection = technologyDirection;
    }


    public String getOperUsername() {
        return operUsername;
    }

    public void setOperUsername(String operUsername) {
        this.operUsername = operUsername;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSettledCycle() {
        return settledCycle;
    }

    public void setSettledCycle(String settledCycle) {
        this.settledCycle = settledCycle;
    }
}
