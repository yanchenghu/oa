package com.ruoyi.analysis.domain;

import java.util.Date;

/**
 * @Description: 人员流动分析
 * @Author ych
 * @Date 2021/3/15
 */
public class PersonnelMobility {


    //1、最近一年，2、等等
    private Date recentYears;

    //月份
    private String customMonth;
    //出项人员
    private double outPeople;
    //出项人员利润
    private double outPeopleProfit;
    //入项人员
    private double entryPeople;
    //入项人员利润
    private double entryPeopleProfit;

    //人员增减
    private double gradation;
    //人员增减利润
    private double gradationProfit;


    //去年出项人员
    private double lastyearOutPeople;
    //去年出项人员利润
    private double lastyearOutPeopleProfit;
    //去年入项人员
    private double lastyearEntryPeople;
    //去年入项人员利润
    private double lastyearEntryPeopleProfit;
    //去年人员增减
    private double lastyearGradation;
    //去年同期人员增减利润
    private double lastyearProfit;


    public Date getRecentYears() {
        return recentYears;
    }

    public void setRecentYears(Date recentYears) {
        this.recentYears = recentYears;
    }

    public String getCustomMonth() {
        return customMonth;
    }

    public void setCustomMonth(String customMonth) {
        this.customMonth = customMonth;
    }

    public double getOutPeople() {
        return outPeople;
    }

    public void setOutPeople(double outPeople) {
        this.outPeople = outPeople;
    }

    public double getOutPeopleProfit() {
        return outPeopleProfit;
    }

    public void setOutPeopleProfit(double outPeopleProfit) {
        this.outPeopleProfit = outPeopleProfit;
    }

    public double getEntryPeople() {
        return entryPeople;
    }

    public void setEntryPeople(double entryPeople) {
        this.entryPeople = entryPeople;
    }

    public double getEntryPeopleProfit() {
        return entryPeopleProfit;
    }

    public void setEntryPeopleProfit(double entryPeopleProfit) {
        this.entryPeopleProfit = entryPeopleProfit;
    }

    public double getGradation() {
        return gradation;
    }

    public void setGradation(double gradation) {
        this.gradation = gradation;
    }

    public double getGradationProfit() {
        return gradationProfit;
    }

    public void setGradationProfit(double gradationProfit) {
        this.gradationProfit = gradationProfit;
    }

    public double getLastyearOutPeople() {
        return lastyearOutPeople;
    }

    public void setLastyearOutPeople(double lastyearOutPeople) {
        this.lastyearOutPeople = lastyearOutPeople;
    }

    public double getLastyearOutPeopleProfit() {
        return lastyearOutPeopleProfit;
    }

    public void setLastyearOutPeopleProfit(double lastyearOutPeopleProfit) {
        this.lastyearOutPeopleProfit = lastyearOutPeopleProfit;
    }

    public double getLastyearEntryPeople() {
        return lastyearEntryPeople;
    }

    public void setLastyearEntryPeople(double lastyearEntryPeople) {
        this.lastyearEntryPeople = lastyearEntryPeople;
    }

    public double getLastyearEntryPeopleProfit() {
        return lastyearEntryPeopleProfit;
    }

    public void setLastyearEntryPeopleProfit(double lastyearEntryPeopleProfit) {
        this.lastyearEntryPeopleProfit = lastyearEntryPeopleProfit;
    }

    public double getLastyearGradation() {
        return lastyearGradation;
    }

    public void setLastyearGradation(double lastyearGradation) {
        this.lastyearGradation = lastyearGradation;
    }

    public double getLastyearProfit() {
        return lastyearProfit;
    }

    public void setLastyearProfit(double lastyearProfit) {
        this.lastyearProfit = lastyearProfit;
    }
}
