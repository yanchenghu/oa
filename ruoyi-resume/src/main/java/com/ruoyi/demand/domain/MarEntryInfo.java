package com.ruoyi.demand.domain;

import java.util.List;

public class MarEntryInfo {

    /** 需求ID */
    private String demandId;

    /** 需求名称 */
    private String projectName;


    private List<BasicInfo> basicInfo;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<BasicInfo> getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(List<BasicInfo> basicInfo) {
        this.basicInfo = basicInfo;
    }


}