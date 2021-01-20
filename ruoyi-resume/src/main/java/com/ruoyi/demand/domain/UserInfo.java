package com.ruoyi.demand.domain;

import com.ruoyi.common.annotation.Excel;

public class UserInfo {
    /** 用户账号 */
    @Excel(name = "登录名称")
    private String userName;

    /** 钉钉userid */
    private String dinguserid;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDinguserid() {
        return dinguserid;
    }

    public void setDinguserid(String dinguserid) {
        this.dinguserid = dinguserid;
    }
}
