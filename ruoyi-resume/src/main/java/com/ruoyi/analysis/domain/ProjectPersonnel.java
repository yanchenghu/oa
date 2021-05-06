package com.ruoyi.analysis.domain;

import java.util.Date;

/**
 * @Description: 项目人员统计
 * @Author ych
 * @Date 2021/4/22
 */
public class ProjectPersonnel {

    //日期
    private Date currentDate;

    //合作公司编号
    private String corpCode;
    //合作公司名称
    private String corpName;
    //本月
    private double thismonthNum;
    //上个月
    private double lastmonthNum;
    //去年本月
    private double lastyearmonthNum;
    //回款周期
    private int collectionCycle;

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

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

    public double getThismonthNum() {
        return thismonthNum;
    }

    public void setThismonthNum(double thismonthNum) {
        this.thismonthNum = thismonthNum;
    }

    public double getLastmonthNum() {
        return lastmonthNum;
    }

    public void setLastmonthNum(double lastmonthNum) {
        this.lastmonthNum = lastmonthNum;
    }

    public double getLastyearmonthNum() {
        return lastyearmonthNum;
    }

    public void setLastyearmonthNum(double lastyearmonthNum) {
        this.lastyearmonthNum = lastyearmonthNum;
    }

    public int getCollectionCycle() {
        return collectionCycle;
    }

    public void setCollectionCycle(int collectionCycle) {
        this.collectionCycle = collectionCycle;
    }
}
