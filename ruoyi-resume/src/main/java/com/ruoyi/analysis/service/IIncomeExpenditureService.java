package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.IncomeExpenditure;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @Description: 收入与支出分析
 * @Author ych
 * @Date 2021/3/17
 */
public interface IIncomeExpenditureService {

    AjaxResult getIncomeexpenditureList(IncomeExpenditure incomeExpenditure);
}
