package com.ruoyi.web.controller.demand;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 全部需求查看（此权限只给商务和老板，普通员工无法看到）
 * @Author ych
 * @Date 2021/4/20
 */
@RestController
@RequestMapping("/demand/alldemand")
public class AllDemandSeeController extends BaseController {
    @Autowired
    private IMarDemandService marDemandService;


    /**
     * 查询我的需求列表
     */
    @PreAuthorize("@ss.hasPermi('demand:alldemand:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarDemand marDemand)
    {
        startPage();
        List<MarDemand> list = marDemandService.getAllMarDemandList(marDemand);
        return getDataTable(list);
    }



}