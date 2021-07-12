package com.ruoyi.web.controller.statistic;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.service.IMarCustomerprojectpayService;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.statistic.service.IHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 首页统计Controller
 *
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/statistc/homepage")
public class HomePageController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IMarCustomerprojectpayService marCustomerprojectpayService;
    @Autowired
    private IHomePageService homePageService;


    /**
     * 获取轮播入项信息
     */
    @GetMapping(value = "/rotation")
    public AjaxResult rotation()
    {
        return marCustomerprojectpayService.rotation();
    }
    /**
     * 人事首页数据展示
     */
    @PreAuthorize("@ss.hasPermi('statistc:homepage:datadisplay')")
    @GetMapping(value = "/datadisplay")
    public AjaxResult datadisplay()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.datadisplay(loginUser);
    }
    /**
     * 人事首页点击弹框数据详情展示
     */

    @PostMapping(value = "/viewdetailsplay")
    public AjaxResult viewdetailsplay(@RequestParam("followStatus")Integer followStatus)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.viewdetailsplay(loginUser,followStatus);
    }

    /**
     * 首页商务数据展示
     */
    @PreAuthorize("@ss.hasPermi('statistc:homepage:businessData')")
    @GetMapping(value = "/businessData")
    public AjaxResult businessData()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.businessData(loginUser);
    }
    /**
     * 商务首页点击弹框数据详情展示
     */

    @PostMapping(value = "/businessdetailsplay")
    public AjaxResult businessdetailsplay(@RequestParam("followStatus")Integer followStatus)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.businessdetailsplay(loginUser,followStatus);
    }

    /**
     * 预留接口 行政、
     */
    @GetMapping(value = "/reservedisplay")
    public AjaxResult reservedisplay()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.datadisplay(loginUser);
    }


    /**
     * 兼职hr首页数据展示
     */
    @PreAuthorize("@ss.hasPermi('statistc:homepage:partjob')")
    @GetMapping(value = "/partjob")
    public AjaxResult partjoblist()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.partjoblist(loginUser);
    }























}
