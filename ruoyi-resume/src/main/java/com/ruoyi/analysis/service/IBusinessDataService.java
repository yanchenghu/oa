package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.BusinessData;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @Description: 商务数据分析service
 * @Author ych
 * @Date 2021/5/26
 */
public interface IBusinessDataService {
    AjaxResult businessllist(BusinessData businessData);
}
