package com.ruoyi.expenditure.domain;

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
    /** 序号 */
    @Excel(name = "序号")
    private Long id;

    /** 房租 */
    @Excel(name = "房租")
    private String rent;

    /** 水电 */
    @Excel(name = "水电")
    private String hydropower;

    /** 物业 */
    @Excel(name = "物业")
    private String property;

    /** 材料费 */
    @Excel(name = "材料费")
    private String materiaCost;

    /** 税费 */
    @Excel(name = "税费")
    private String taxation;

    /** 其他 */
    @Excel(name = "其他")
    private String otherCost;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getHydropower() {
        return hydropower;
    }

    public void setHydropower(String hydropower) {
        this.hydropower = hydropower;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMateriaCost() {
        return materiaCost;
    }

    public void setMateriaCost(String materiaCost) {
        this.materiaCost = materiaCost;
    }

    public String getTaxation() {
        return taxation;
    }

    public void setTaxation(String taxation) {
        this.taxation = taxation;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
