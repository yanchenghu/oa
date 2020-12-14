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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 首页数据展示
     */
    @GetMapping(value = "/datadisplay")
    public AjaxResult datadisplay()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return homePageService.datadisplay(loginUser);
    }























}
