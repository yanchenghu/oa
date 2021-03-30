package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.IncomeExpenditure;
import com.ruoyi.analysis.service.IIncomeExpenditureService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 收入与支出的统计
 * @Author ych
 * @Date 2021/3/17
 */
@RestController
@RequestMapping("/analysis/incomeexpenditure")
public class IncomeExpenditureController extends BaseController {


    @Autowired
    private IIncomeExpenditureService incomeExpenditureService;


    @PreAuthorize("@ss.hasPermi('analysis:incomeexpenditure:list')")
    @GetMapping(value = "/list")
    public AjaxResult incomeexpenditurelist(IncomeExpenditure incomeExpenditure)
    {
        return incomeExpenditureService.getIncomeexpenditureList(incomeExpenditure);
    }





}
