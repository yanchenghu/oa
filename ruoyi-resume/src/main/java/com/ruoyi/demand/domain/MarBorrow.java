package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 借用物品对象 mar_borrow
 * 
 * @author ych
 * @date 2020-12-10
 */
public class MarBorrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 证件信息id */
    private Integer id;

    /** 入项ID */
    @Excel(name = "入项ID")
    private String marcusId;

    /** 借用物品 */
    @Excel(name = "借用物品")
    private String borrowSth;

    /** 借用型号 */
    @Excel(name = "借用型号")
    private String borrowModel;

    /** 借用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date borrowTime;

    /** 是否签署协议（1、是，2否） */
    @Excel(name = "是否签署协议", readConverterExp = "1=、是，2否")
    private Integer isAgreement;

    /** 归还日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /** 是否归还（1、是，2否） */
    @Excel(name = "是否归还", readConverterExp = "1=、是，2否")
    private Integer isReturn;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setMarcusId(String marcusId) 
    {
        this.marcusId = marcusId;
    }

    public String getMarcusId() 
    {
        return marcusId;
    }
    public void setBorrowSth(String borrowSth) 
    {
        this.borrowSth = borrowSth;
    }

    public String getBorrowSth() 
    {
        return borrowSth;
    }
    public void setBorrowModel(String borrowModel) 
    {
        this.borrowModel = borrowModel;
    }

    public String getBorrowModel() 
    {
        return borrowModel;
    }
    public void setBorrowTime(Date borrowTime) 
    {
        this.borrowTime = borrowTime;
    }

    public Date getBorrowTime() 
    {
        return borrowTime;
    }
    public void setIsAgreement(Integer isAgreement) 
    {
        this.isAgreement = isAgreement;
    }

    public Integer getIsAgreement() 
    {
        return isAgreement;
    }
    public void setReturnTime(Date returnTime) 
    {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() 
    {
        return returnTime;
    }
    public void setIsReturn(Integer isReturn) 
    {
        this.isReturn = isReturn;
    }

    public Integer getIsReturn() 
    {
        return isReturn;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("marcusId", getMarcusId())
            .append("borrowSth", getBorrowSth())
            .append("borrowModel", getBorrowModel())
            .append("borrowTime", getBorrowTime())
            .append("isAgreement", getIsAgreement())
            .append("returnTime", getReturnTime())
            .append("isReturn", getIsReturn())
            .append("addTime", getAddTime())
            .toString();
    }
}
