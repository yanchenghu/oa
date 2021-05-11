package com.ruoyi.web.controller.demand;

import java.util.List;
import java.util.Map;


import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.demand.domain.MarWaitinginterview;
import com.ruoyi.demand.service.IMarWaitinginterviewService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 待面试Controller
 * 
 * @author ych
 * @date 2021-04-29
 */
@RestController
@RequestMapping("/demand/waitinginterview")
public class MarWaitinginterviewController extends BaseController
{
    @Autowired
    private IMarWaitinginterviewService marWaitinginterviewService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询待面试列表
     */
    @PreAuthorize("@ss.hasPermi('demand:waitinginterview:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarWaitinginterview marWaitinginterview)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String username = loginUser.getUsername();
        List<Map> list = marWaitinginterviewService.selectMarWaitinginterviewMap(username);
        return getDataTable(list);
    }

    /**
     * 导出待面试列表
     */
    @PreAuthorize("@ss.hasPermi('demand:waitinginterview:export')")
    @Log(title = "待面试", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarWaitinginterview marWaitinginterview)
    {
        List<MarWaitinginterview> list = marWaitinginterviewService.selectMarWaitinginterviewList(marWaitinginterview);
        ExcelUtil<MarWaitinginterview> util = new ExcelUtil<MarWaitinginterview>(MarWaitinginterview.class);
        return util.exportExcel(list, "waitinginterview");
    }
    /**
     * 修改待面试
     */
    @Log(title = "待面试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarWaitinginterview marWaitinginterview)
    {



        return toAjax(marWaitinginterviewService.updateMarWaitinginterview(marWaitinginterview));
    }

}
