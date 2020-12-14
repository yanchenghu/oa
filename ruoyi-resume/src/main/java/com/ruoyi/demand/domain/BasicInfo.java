package com.ruoyi.demand.domain;

public class BasicInfo {
    /** 绑定人姓名 */
    private String customerName;

    /** 绑定状态 */
    private Integer followStatus;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }
}
