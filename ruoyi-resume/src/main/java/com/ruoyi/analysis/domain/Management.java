package com.ruoyi.analysis.domain;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @Description: 经营分析实体类
 * @Author ych
 * @Date 2021/3/9
 */
public class Management {

    //1、最近一年，2、等等
    private Date recentYears;
    //1、按时间回款计算，2、按销售额计算
    private int salesQuota;
    //月份
    private String customMonth;
    // 收入
    private Double  income;
    //支出
    private Double expenditure;
    //利润
    private Double profit;



    //去年利润数据
    private Double onProfitData;

    //去年收入数据
    private Double  onIncomeData;

    //去年支出数据
    private Double onExpenditureData;


    public Date getRecentYears() {
        return recentYears;
    }

    public void setRecentYears(Date recentYears) {
        this.recentYears = recentYears;
    }

    public int getSalesQuota() {
        return salesQuota;
    }

    public void setSalesQuota(int salesQuota) {
        this.salesQuota = salesQuota;
    }

    public String getCustomMonth() {
        return customMonth;
    }

    public void setCustomMonth(String customMonth) {
        this.customMonth = customMonth;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getOnProfitData() {
        return onProfitData;
    }

    public void setOnProfitData(Double onProfitData) {
        this.onProfitData = onProfitData;
    }

    public Double getOnIncomeData() {
        return onIncomeData;
    }

    public void setOnIncomeData(Double onIncomeData) {
        this.onIncomeData = onIncomeData;
    }

    public Double getOnExpenditureData() {
        return onExpenditureData;
    }

    public void setOnExpenditureData(Double onExpenditureData) {
        this.onExpenditureData = onExpenditureData;
    }
}
