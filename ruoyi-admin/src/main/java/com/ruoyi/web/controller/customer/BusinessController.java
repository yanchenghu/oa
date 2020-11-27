package com.ruoyi.web.controller.customer;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.service.IHighseasService;
import com.ruoyi.customer.service.IYxcontactService;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商务意向客户Controller
 * 
 * @author ruoyi
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/customer/business")
public class BusinessController extends BaseController
{
    @Autowired
    private IYxdemandService yxdemandService;


    @Autowired
    private TokenService tokenService;
    /**
     * 商务意向客户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:business:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yxdemand yxdemand)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        List<Yxdemand> list = yxdemandService.selectBusinessList(yxdemand,loginUser);
        return getDataTable(list);
    }

    /**
     * 转化为合作客户
     */
    @PostMapping(value = "/turnCustomers")
    public AjaxResult turnCustomers(@RequestBody MarCompany marCompany)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return yxdemandService.turnCustomers(marCompany,loginUser);
    }

    /**
     * 商务抢占功能营销
     */
    @PostMapping(value = "/rebBusByEnId")
    public AjaxResult  rebClientByEnId(@RequestParam(value = "entryId", defaultValue = "") Integer entryId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        return yxdemandService.rebBusByEnId(entryId,loginUser);
    }











}
