package com.ruoyi.demand.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 证件信息对象 mar_certificates
 * 
 * @author ych
 * @date 2020-12-14
 */
public class MarCertificates extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 证件信息id */
    private Integer id;

    /** 入项ID */
    @Excel(name = "入项ID")
    private String marcusId;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String customerCode;

    /** 身份证正面 */
    @Excel(name = "身份证正面")
    private String idcardPositive;

    /** 身份证反面 */
    @Excel(name = "身份证反面")
    private String idcardReverse;

    /** 学位证 */
    @Excel(name = "学位证")
    private String academic;

    /** 保密协议 */
    @Excel(name = "保密协议1")
    private String confidentialityAgreement;
    /** 保密协议 */
    @Excel(name = "保密协议2")
    private String confidentialityAgreementlo;

    /** 毕业证 */
    @Excel(name = "毕业证")
    private String diploma;

    /** 劳务合同 */
    @Excel(name = "劳务合同")
    private String serviceContract;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;


    public String getConfidentialityAgreementlo() {
        return confidentialityAgreementlo;
    }

    public void setConfidentialityAgreementlo(String confidentialityAgreementlo) {
        this.confidentialityAgreementlo = confidentialityAgreementlo;
    }

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
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setIdcardPositive(String idcardPositive) 
    {
        this.idcardPositive = idcardPositive;
    }

    public String getIdcardPositive() 
    {
        return idcardPositive;
    }
    public void setIdcardReverse(String idcardReverse) 
    {
        this.idcardReverse = idcardReverse;
    }

    public String getIdcardReverse() 
    {
        return idcardReverse;
    }
    public void setAcademic(String academic) 
    {
        this.academic = academic;
    }

    public String getAcademic() 
    {
        return academic;
    }
    public void setConfidentialityAgreement(String confidentialityAgreement) 
    {
        this.confidentialityAgreement = confidentialityAgreement;
    }

    public String getConfidentialityAgreement() 
    {
        return confidentialityAgreement;
    }
    public void setDiploma(String diploma) 
    {
        this.diploma = diploma;
    }

    public String getDiploma() 
    {
        return diploma;
    }
    public void setServiceContract(String serviceContract) 
    {
        this.serviceContract = serviceContract;
    }

    public String getServiceContract() 
    {
        return serviceContract;
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
            .append("customerCode", getCustomerCode())
            .append("idcardPositive", getIdcardPositive())
            .append("idcardReverse", getIdcardReverse())
            .append("academic", getAcademic())
            .append("confidentialityAgreement", getConfidentialityAgreement())
            .append("diploma", getDiploma())
            .append("serviceContract", getServiceContract())
            .append("addTime", getAddTime())
            .toString();
    }
}
