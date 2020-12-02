package com.ruoyi.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同对象 mar_contract
 * 
 * @author ych
 * @date 2020-11-30
 */
public class MarContract
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 合作公司Id */
    @Excel(name = "合作公司Id")
    private String corpCode;

    /** 甲方 */
    @Excel(name = "甲方")
    private String firstParty;

    /** 乙方 */
    @Excel(name = "乙方")
    private String party;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 客户签约人 */
    @Excel(name = "客户签约人")
    private String clientSigner;

    /** 公司签约人 */
    @Excel(name = "公司签约人")
    private String companySigner;

    /** 合同预览 */
    @Excel(name = "合同预览")
    private String contractPreview;

    /** 添加人 */
    @Excel(name = "添加人")
    private String contractName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setFirstParty(String firstParty) 
    {
        this.firstParty = firstParty;
    }

    public String getFirstParty() 
    {
        return firstParty;
    }
    public void setParty(String party) 
    {
        this.party = party;
    }

    public String getParty() 
    {
        return party;
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
    public void setClientSigner(String clientSigner) 
    {
        this.clientSigner = clientSigner;
    }

    public String getClientSigner() 
    {
        return clientSigner;
    }
    public void setCompanySigner(String companySigner) 
    {
        this.companySigner = companySigner;
    }

    public String getCompanySigner() 
    {
        return companySigner;
    }
    public void setContractPreview(String contractPreview) 
    {
        this.contractPreview = contractPreview;
    }

    public String getContractPreview() 
    {
        return contractPreview;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setContractTime(Date contractTime) 
    {
        this.contractTime = contractTime;
    }

    public Date getContractTime() 
    {
        return contractTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("corpCode", getCorpCode())
            .append("firstParty", getFirstParty())
            .append("party", getParty())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("clientSigner", getClientSigner())
            .append("companySigner", getCompanySigner())
            .append("contractPreview", getContractPreview())
            .append("contractName", getContractName())
            .append("contractTime", getContractTime())
            .toString();
    }
}
