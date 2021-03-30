package com.ruoyi.analysis.domain;

/**
 * @Description: 通用点击回款统计
 * @Author ych
 * @Date 2021/2/20
 */
public class GeneralCollectionStatistics {

    /** 公司编码 */
    private String corpCode;
    /** 公司姓名 */
    private String corpName;
    /** 姓名 */
    private String customerName;
    /** 编号 */
    private String customerCode;
    /** 回款总额 、累计回款 */
    private String collectionTotal;

    /*开始时间*/
    private String startTime;
    /*结束时间*/
    private String endTime;
    /** 支出总额 、累计支出 */
    private String expenditureTotal;

    /** 当月回款 */
    private String samemonthCollection;
    /** 当月支出 */
    private String samemonthExpenditure;
    /** 当月盈利 */
    private String samemonthProfit;
    /** 当月盈利 */
    private String wagesTime;


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCollectionTotal() {
        return collectionTotal;
    }

    public void setCollectionTotal(String collectionTotal) {
        this.collectionTotal = collectionTotal;
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

    public String getExpenditureTotal() {
        return expenditureTotal;
    }

    public void setExpenditureTotal(String expenditureTotal) {
        this.expenditureTotal = expenditureTotal;
    }

    public String getSamemonthCollection() {
        return samemonthCollection;
    }

    public void setSamemonthCollection(String samemonthCollection) {
        this.samemonthCollection = samemonthCollection;
    }

    public String getSamemonthExpenditure() {
        return samemonthExpenditure;
    }

    public void setSamemonthExpenditure(String samemonthExpenditure) {
        this.samemonthExpenditure = samemonthExpenditure;
    }

    public String getSamemonthProfit() {
        return samemonthProfit;
    }

    public void setSamemonthProfit(String samemonthProfit) {
        this.samemonthProfit = samemonthProfit;
    }

    public String getWagesTime() {
        return wagesTime;
    }

    public void setWagesTime(String wagesTime) {
        this.wagesTime = wagesTime;
    }
}
