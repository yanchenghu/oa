package com.ruoyi.finance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务费核算请求书对象 fin_statements
 * 
 * @author ych
 * @date 2021-01-25
 */
public class FinStatements
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 甲方公司id */
    @Excel(name = "甲方公司id")
    private String corpCode;

    /** 乙方公司id */
    @Excel(name = "乙方公司id")
    private String party;

    /** 结算单开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算单开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结算单结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算单结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 姓名 */
    @Excel(name = "姓名")
    private String customerName;

    /** 电话 */
    @Excel(name = "电话")
    private String customerTel;

    /** 时间 */
    @Excel(name = "时间")
    private String time;

    /** 服务费 */
    @Excel(name = "服务费")
    private Double price;

    /** 标准工时 */
    @Excel(name = "标准工时")
    private Double standard;

    /** 出勤工时 */
    @Excel(name = "出勤工时")
    private Double attenDance;

    /** 加班小时 */
    @Excel(name = "加班小时")
    private Double overTime;

    /** 加班费 */
    @Excel(name = "加班费")
    private Double overPay;

    /** 合计 */
    @Excel(name = "合计")
    private Double comBined;

    /** 状态：1-未开票，2-已开票，3-已付款 */
    @Excel(name = "状态：1-未开票，2-已开票，3-已付款")
    private Integer status;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 添加人 */
    @Excel(name = "添加人")
    private String addPeople;

    /** 修改原因 */
    @Excel(name = "修改原因")
    private String reason;

    /** 结算单截图 */
    @Excel(name = "结算单截图")
    private String picture;

    /** excel的留底 */
    @Excel(name = "excel的留底")
    private String excelPicture;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** remark */
    @Excel(name = "remark")
    private String remark;


    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCorpCode(String corpCode) 
    {
        this.corpCode = corpCode;
    }

    public String getCorpCode() 
    {
        return corpCode;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerTel(String customerTel) 
    {
        this.customerTel = customerTel;
    }

    public String getCustomerTel() 
    {
        return customerTel;
    }
    public void setTime(String time) 
    {
        this.time = time;
    }

    public String getTime() 
    {
        return time;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setStandard(Double standard) 
    {
        this.standard = standard;
    }

    public Double getStandard() 
    {
        return standard;
    }
    public void setAttenDance(Double attenDance) 
    {
        this.attenDance = attenDance;
    }

    public Double getAttenDance() 
    {
        return attenDance;
    }
    public void setOverTime(Double overTime) 
    {
        this.overTime = overTime;
    }

    public Double getOverTime() 
    {
        return overTime;
    }
    public void setOverPay(Double overPay) 
    {
        this.overPay = overPay;
    }

    public Double getOverPay() 
    {
        return overPay;
    }
    public void setComBined(Double comBined) 
    {
        this.comBined = comBined;
    }

    public Double getComBined() 
    {
        return comBined;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setAddPeople(String addPeople) 
    {
        this.addPeople = addPeople;
    }

    public String getAddPeople() 
    {
        return addPeople;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setExcelPicture(String excelPicture) 
    {
        this.excelPicture = excelPicture;
    }

    public String getExcelPicture() 
    {
        return excelPicture;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("corpCode", getCorpCode())
            .append("party", getParty())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("customerName", getCustomerName())
            .append("customerTel", getCustomerTel())
            .append("time", getTime())
            .append("price", getPrice())
            .append("standard", getStandard())
            .append("attenDance", getAttenDance())
            .append("overTime", getOverTime())
            .append("overPay", getOverPay())
            .append("comBined", getComBined())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("addPeople", getAddPeople())
            .append("reason", getReason())
            .append("picture", getPicture())
            .append("excelPicture", getExcelPicture())
            .append("remark", getRemark())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
