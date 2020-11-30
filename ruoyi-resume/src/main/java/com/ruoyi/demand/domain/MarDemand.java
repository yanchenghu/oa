package com.ruoyi.demand.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求对象 mar_demand
 * 
 * @author ych
 * @date 2020-11-27
 */
public class MarDemand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 需求ID */
    private String id;

    /** 客户公司编号 */
    @Excel(name = "客户公司编号")
    private String corpCode;

    /** 需求名称 */
    @Excel(name = "需求名称")
    private String projectName;

    /** 技术方向 */
    @Excel(name = "技术方向")
    private String technologyDirection;

    /** 级别(1、中级2高级) */
    @Excel(name = "级别(1、中级2高级)")
    private String demandYears;

    /** 需求级别 */
    @Excel(name = "需求级别")
    private Integer demandLevel;

    /** 学历要求 */
    @Excel(name = "学历要求")
    private String education;

    /** 需求人数 */
    @Excel(name = "需求人数")
    private String demandNumber;

    /** 具体要求 */
    @Excel(name = "具体要求")
    private String specificrequiRement;

    /** 项目地点 */
    @Excel(name = "项目地点")
    private String projectLocation;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 工作年限（工作年数） */
    @Excel(name = "工作年限", readConverterExp = "工=作年数")
    private BigDecimal directWorklife;

    /** 目标人数 */
    @Excel(name = "目标人数")
    private Integer targetNumber;

    /** 重要级别（1,2,3级 小--&gt;大） */
    @Excel(name = "重要级别", readConverterExp = "1=,2,3级,小=--&gt;大")
    private Integer importantLevel;

    /** 最小薪资 */
    @Excel(name = "最小薪资")
    private Integer minSalary;

    /** 最大薪资 */
    @Excel(name = "最大薪资")
    private Integer maxSalary;

    /** 技术要求 */
    @Excel(name = "技术要求")
    private String technicalRequirement;

    /** 语言(无       1、日语 2、英语   ) */
    @Excel(name = "语言(无       1、日语 2、英语   )")
    private Integer langue;

    /** 需求图片 */
    @Excel(name = "需求图片")
    private String demandPic;

    /** 面试官 */
    @Excel(name = "面试官")
    private String interviewer;

    /** 面试官联系方式 */
    @Excel(name = "面试官联系方式")
    private String contactPhone;

    /** 面试地点 */
    @Excel(name = "面试地点")
    private String specificLocation;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String attention;

    /** app是否能看到   1:是  2:否 */
    @Excel(name = "app是否能看到   1:是  2:否")
    private Integer ifLook;

    /** 学信网是否可查（0-否，1-是） */
    @Excel(name = "学信网是否可查", readConverterExp = "0=-否，1-是")
    private Integer chsiFlag;

    /** 合作性质（1-猎头 2-外包） */
    @Excel(name = "合作性质", readConverterExp = "1=-猎头,2=-外包")
    private Integer coopnature;

    /** 状态(0:未关闭；1：需求关闭；) */
    @Excel(name = "状态(0:未关闭；1：需求关闭；)")
    private Integer state;

    /** 关闭时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closeDate;

    /** 模板Id */
    @Excel(name = "模板Id")
    private Long tempId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String tempName;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 录入人ID */
    @Excel(name = "录入人ID")
    private String operationuser;

    /** 录入人姓名 */
    @Excel(name = "录入人姓名")
    private String operUsername;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
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
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setTechnologyDirection(String technologyDirection) 
    {
        this.technologyDirection = technologyDirection;
    }

    public String getTechnologyDirection() 
    {
        return technologyDirection;
    }
    public void setDemandYears(String demandYears) 
    {
        this.demandYears = demandYears;
    }

    public String getDemandYears() 
    {
        return demandYears;
    }
    public void setDemandLevel(Integer demandLevel) 
    {
        this.demandLevel = demandLevel;
    }

    public Integer getDemandLevel() 
    {
        return demandLevel;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setDemandNumber(String demandNumber) 
    {
        this.demandNumber = demandNumber;
    }

    public String getDemandNumber() 
    {
        return demandNumber;
    }
    public void setSpecificrequiRement(String specificrequiRement) 
    {
        this.specificrequiRement = specificrequiRement;
    }

    public String getSpecificrequiRement() 
    {
        return specificrequiRement;
    }
    public void setProjectLocation(String projectLocation) 
    {
        this.projectLocation = projectLocation;
    }

    public String getProjectLocation() 
    {
        return projectLocation;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDirectWorklife(BigDecimal directWorklife) 
    {
        this.directWorklife = directWorklife;
    }

    public BigDecimal getDirectWorklife() 
    {
        return directWorklife;
    }
    public void setTargetNumber(Integer targetNumber) 
    {
        this.targetNumber = targetNumber;
    }

    public Integer getTargetNumber() 
    {
        return targetNumber;
    }
    public void setImportantLevel(Integer importantLevel) 
    {
        this.importantLevel = importantLevel;
    }

    public Integer getImportantLevel() 
    {
        return importantLevel;
    }
    public void setMinSalary(Integer minSalary) 
    {
        this.minSalary = minSalary;
    }

    public Integer getMinSalary() 
    {
        return minSalary;
    }
    public void setMaxSalary(Integer maxSalary) 
    {
        this.maxSalary = maxSalary;
    }

    public Integer getMaxSalary() 
    {
        return maxSalary;
    }
    public void setTechnicalRequirement(String technicalRequirement) 
    {
        this.technicalRequirement = technicalRequirement;
    }

    public String getTechnicalRequirement() 
    {
        return technicalRequirement;
    }
    public void setLangue(Integer langue) 
    {
        this.langue = langue;
    }

    public Integer getLangue() 
    {
        return langue;
    }
    public void setDemandPic(String demandPic) 
    {
        this.demandPic = demandPic;
    }

    public String getDemandPic() 
    {
        return demandPic;
    }
    public void setInterviewer(String interviewer) 
    {
        this.interviewer = interviewer;
    }

    public String getInterviewer() 
    {
        return interviewer;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setSpecificLocation(String specificLocation) 
    {
        this.specificLocation = specificLocation;
    }

    public String getSpecificLocation() 
    {
        return specificLocation;
    }
    public void setAttention(String attention) 
    {
        this.attention = attention;
    }

    public String getAttention() 
    {
        return attention;
    }
    public void setIfLook(Integer ifLook) 
    {
        this.ifLook = ifLook;
    }

    public Integer getIfLook() 
    {
        return ifLook;
    }
    public void setChsiFlag(Integer chsiFlag) 
    {
        this.chsiFlag = chsiFlag;
    }

    public Integer getChsiFlag() 
    {
        return chsiFlag;
    }
    public void setCoopnature(Integer coopnature) 
    {
        this.coopnature = coopnature;
    }

    public Integer getCoopnature() 
    {
        return coopnature;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }
    public void setCloseDate(Date closeDate) 
    {
        this.closeDate = closeDate;
    }

    public Date getCloseDate() 
    {
        return closeDate;
    }
    public void setTempId(Long tempId) 
    {
        this.tempId = tempId;
    }

    public Long getTempId() 
    {
        return tempId;
    }
    public void setTempName(String tempName) 
    {
        this.tempName = tempName;
    }

    public String getTempName() 
    {
        return tempName;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setOperationuser(String operationuser) 
    {
        this.operationuser = operationuser;
    }

    public String getOperationuser() 
    {
        return operationuser;
    }
    public void setOperUsername(String operUsername) 
    {
        this.operUsername = operUsername;
    }

    public String getOperUsername() 
    {
        return operUsername;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("corpCode", getCorpCode())
            .append("projectName", getProjectName())
            .append("technologyDirection", getTechnologyDirection())
            .append("demandYears", getDemandYears())
            .append("demandLevel", getDemandLevel())
            .append("education", getEducation())
            .append("demandNumber", getDemandNumber())
            .append("specificrequiRement", getSpecificrequiRement())
            .append("projectLocation", getProjectLocation())
            .append("address", getAddress())
            .append("directWorklife", getDirectWorklife())
            .append("targetNumber", getTargetNumber())
            .append("importantLevel", getImportantLevel())
            .append("minSalary", getMinSalary())
            .append("maxSalary", getMaxSalary())
            .append("technicalRequirement", getTechnicalRequirement())
            .append("langue", getLangue())
            .append("demandPic", getDemandPic())
            .append("interviewer", getInterviewer())
            .append("contactPhone", getContactPhone())
            .append("specificLocation", getSpecificLocation())
            .append("attention", getAttention())
            .append("ifLook", getIfLook())
            .append("chsiFlag", getChsiFlag())
            .append("coopnature", getCoopnature())
            .append("state", getState())
            .append("closeDate", getCloseDate())
            .append("tempId", getTempId())
            .append("tempName", getTempName())
            .append("addTime", getAddTime())
            .append("operationuser", getOperationuser())
            .append("operUsername", getOperUsername())
            .toString();
    }
}
