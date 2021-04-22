package com.ruoyi.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * 每月公司其他住处费用对象 导出fin_otherexpenses
 *
 * @author ruoyi
 * @date 2021-02-02
 */


public class Expenditure {
    /** $column.columnComment */
    private Long id;

    /** 房租 */
    @Excel(name = "房租")
    private Integer rent;

    /** 水电 */
    @Excel(name = "水电")
    private Integer hydropower;

    /** 物业 */
    @Excel(name = "物业")
    private Integer property;

    /** 税费 */
    @Excel(name = "税费")
    private Integer taxation;

    /** 其他 */
    @Excel(name = "其他")
    private Integer otherCost;

    /** 社保 */
    @Excel(name = "社保")
    private Integer socialSecurity;



    /** 合并支出 */
    @Excel(name = "合并支出")
    private Integer mergeMoney;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getHydropower() {
        return hydropower;
    }

    public void setHydropower(Integer hydropower) {
        this.hydropower = hydropower;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getTaxation() {
        return taxation;
    }

    public void setTaxation(Integer taxation) {
        this.taxation = taxation;
    }

    public Integer getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Integer otherCost) {
        this.otherCost = otherCost;
    }

    public Integer getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Integer socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Integer getMergeMoney() {
        return mergeMoney;
    }

    public void setMergeMoney(Integer mergeMoney) {
        this.mergeMoney = mergeMoney;
    }
}
