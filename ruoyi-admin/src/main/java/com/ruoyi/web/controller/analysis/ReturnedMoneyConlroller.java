package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.GeneralCollectionStatistics;
import com.ruoyi.analysis.domain.ReturnedMoney;
import com.ruoyi.analysis.service.IReturnedMoneyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.finance.domain.FinAncecompany;
import com.ruoyi.finance.domain.FinAncecontact;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 回款统计分析
 * @Author ych
 * @Date 2021/2/18
 */
@RestController
@RequestMapping("/analysis/returnedmoney")
public class ReturnedMoneyConlroller extends BaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private IReturnedMoneyService returnedMoneyService;


    /**
     * 回款统计分析列表
     */
    @PreAuthorize("@ss.hasPermi('analysis:returnedmoney:list')")
    @GetMapping("/list")
    public TableDataInfo list(GeneralCollectionStatistics collectionStatistics)
    {
        startPage();
        List<ReturnedMoney> list = returnedMoneyService.selectReturnedmoneyList(collectionStatistics);
        return getDataTable(list);
    }
    /**
     * 累计回款列表
     */
    @GetMapping("/accumulatedCollectionlist")
    public TableDataInfo accumulatedCollectionlist(GeneralCollectionStatistics collectionStatistics)
    {
        startPage();
        List<GeneralCollectionStatistics> list = returnedMoneyService.selectAccumulatedCollectionlist(collectionStatistics);
        return getDataTable(list);
    }

    /**
     * 累计支出列表
     */
    @GetMapping("/accumulatedExpenditurelist")
    public TableDataInfo accumulatedExpenditurelist(GeneralCollectionStatistics collectionStatistics)
    {
        startPage();
        List<GeneralCollectionStatistics> list = returnedMoneyService.selectAccumulatedExpenditurelist(collectionStatistics);
        return getDataTable(list);
    }
    /**
     * 累计盈利列表
     */
    @GetMapping("/accumulatedProfitlist")
    public TableDataInfo accumulatedProfitlist(GeneralCollectionStatistics collectionStatistics)
    {
        startPage();
        List<GeneralCollectionStatistics> list = returnedMoneyService.selectAccumulatedProfitlist(collectionStatistics);
        return getDataTable(list);
    }


    /**
     * 当月盈利列表
     */
    @GetMapping("/samemonthProfitlist")
    public TableDataInfo samemonthProfitlist(GeneralCollectionStatistics collectionStatistics)
    {
        startPage();
        List<GeneralCollectionStatistics> list = returnedMoneyService.selectSamemonthProfitlist(collectionStatistics);
        return getDataTable(list);
    }

}
