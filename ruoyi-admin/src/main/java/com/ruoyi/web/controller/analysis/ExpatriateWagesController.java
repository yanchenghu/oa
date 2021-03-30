package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.ExpatriateWages;
import com.ruoyi.analysis.domain.GeneralCollectionStatistics;
import com.ruoyi.analysis.service.IExpatriateWagesService;
import com.ruoyi.analysis.service.IReturnedMoneyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 外派人员工资统计
 * @Author ych
 * @Date 2021/2/22
 */
@RestController
@RequestMapping("/analysis/expatriatewages")
public class ExpatriateWagesController extends BaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private IExpatriateWagesService expatriateWagesService;
    @Autowired
    private IReturnedMoneyService returnedMoneyService;

    /**
     * 外派人员工列表
     */
    @PreAuthorize("@ss.hasPermi('analysis:expatriatewages:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExpatriateWages expatriateWage)
    {
       List<ExpatriateWages> list= expatriateWagesService.selectExpatriateWagelist(expatriateWage);
        return getDataTable(list);
    }

    /**
     * 获取左侧合作公司列表
     */
    @GetMapping("/comlist")
    public TableDataInfo Comlist(ExpatriateWages expatriateWages)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<Map> list= expatriateWagesService.selectComlist(loginUser,expatriateWages);
        return getDataTable(list);
    }


    /**
     * 查看当月盈利列表
     */
    @GetMapping("/samemonthProfitlist")
    public TableDataInfo samemonthProfitlist(GeneralCollectionStatistics collectionStatistics)
    {
        startPage();
        List<GeneralCollectionStatistics> list = returnedMoneyService.selectSamemonthProfitlist(collectionStatistics);
        return getDataTable(list);
    }




}
