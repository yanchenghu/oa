package com.ruoyi.analysis.domain;

/**
 * @Description: 回款统计的简历的对象
 * @Author ych
 * @Date 2021/2/18
 */
public class ReturnedMoney {

    /** 合作公司名 */
    private String corpName;
    /** 合作公司编码 */
    private String corpCode;
    /** 累计回款 */
    private double accumulatedCollection;
    /** 累计支出 */
    private double accumulatedExpenditure;
    /** 累计盈利 */
    private double accumulatedProfit;
    /** 累计欠款 请求书的状态为未回款的*/
    private double accumulatedArrears;
    /** 最近回款月份 */
    private String latestcollectionMonth;

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public double getAccumulatedCollection() {
        return accumulatedCollection;
    }

    public void setAccumulatedCollection(double accumulatedCollection) {
        this.accumulatedCollection = accumulatedCollection;
    }

    public double getAccumulatedExpenditure() {
        return accumulatedExpenditure;
    }

    public void setAccumulatedExpenditure(double accumulatedExpenditure) {
        this.accumulatedExpenditure = accumulatedExpenditure;
    }

    public double getAccumulatedProfit() {
        return accumulatedProfit;
    }

    public void setAccumulatedProfit(double accumulatedProfit) {
        this.accumulatedProfit = accumulatedProfit;
    }

    public double getAccumulatedArrears() {
        return accumulatedArrears;
    }

    public void setAccumulatedArrears(double accumulatedArrears) {
        this.accumulatedArrears = accumulatedArrears;
    }

    public String getLatestcollectionMonth() {
        return latestcollectionMonth;
    }

    public void setLatestcollectionMonth(String latestcollectionMonth) {
        this.latestcollectionMonth = latestcollectionMonth;
    }
}
