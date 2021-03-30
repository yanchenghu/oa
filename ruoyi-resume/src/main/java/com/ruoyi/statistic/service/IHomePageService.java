package com.ruoyi.statistic.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;

public interface IHomePageService {
    /**
     * 首页数据展示
     */
    AjaxResult datadisplay(LoginUser loginUser);
    /**
     * 首页点击弹框数据详情展示
     */
    AjaxResult viewdetailsplay(LoginUser loginUser, Integer followStatus);
    /**
     * 首页商务数据展示
     */
    AjaxResult businessData(LoginUser loginUser);
    /**
     * 商务首页点击弹框数据详情展示
     */
    AjaxResult businessdetailsplay(LoginUser loginUser, Integer followStatus);

    AjaxResult partjoblist(LoginUser loginUser);
}
