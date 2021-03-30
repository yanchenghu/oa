package com.ruoyi.analysis.domain;

import java.util.Date;

/**
 * @Description: 收入与支出实体类
 * @Author ych
 * @Date 2021/3/17
 */
public class IncomeExpenditure {

    //1、最近一年，2、等等
    private Date recentYears;

    //月份
    private String customMonth;
    //收入
    private double income;

    //外派人员工资
    private double expatriateSalary;
    //内部人员工资
    private double insideSalary;
    //其他支出
    private double otherExpenses;
    //盈利
    private double profit;

    public Date getRecentYears() {
        return recentYears;
    }

    public void setRecentYears(Date recentYears) {
        this.recentYears = recentYears;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }


    public double getExpatriateSalary() {
        return expatriateSalary;
    }

    public void setExpatriateSalary(double expatriateSalary) {
        this.expatriateSalary = expatriateSalary;
    }

    public double getInsideSalary() {
        return insideSalary;
    }

    public void setInsideSalary(double insideSalary) {
        this.insideSalary = insideSalary;
    }

    public double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getCustomMonth() {
        return customMonth;
    }

    public void setCustomMonth(String customMonth) {
        this.customMonth = customMonth;
    }
}
