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


    /** 客户姓名 */
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

}
