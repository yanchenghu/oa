package com.ruoyi.web.controller.statistic;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 营销录入公司Controller
 *
 * @author ruoyi
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/work/statistic")
public class WorkStatisticController extends BaseController {


    @Autowired
    private IYxdemandService yxdemandService;

    @Autowired
    private TokenService tokenService;


    /**
     * 工作结果
     * @param
     * @return
     * @auther ych
     */
    @PreAuthorize("@ss.hasPermi('work:statistic:workresultlist')")
    @PostMapping(value = "/workresultlist")
    public AjaxResult workresultlist(String usercode){
        if(StringUtils.isEmpty(usercode)){
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
           usercode = loginUser.getUsername();
        }

        return yxdemandService.workresultlist(usercode);
    }

    @GetMapping(value = "/rankinglist")
    public AjaxResult ranking(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return yxdemandService.rankinglist(loginUser);
    }


}
