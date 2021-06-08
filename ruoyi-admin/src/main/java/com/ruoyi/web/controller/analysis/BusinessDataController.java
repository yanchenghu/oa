package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.BusinessData;
import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.service.IBusinessDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 商务数据分析
 * @Author ych
 * @Date 2021/5/26
 */
@RestController
@RequestMapping(value = "/analysis/businessdata")
public class BusinessDataController extends BaseController {

    @Autowired
    private IBusinessDataService businessDataService;
    @Autowired
    private TokenService tokenService;
    @PreAuthorize("@ss.hasPermi('analysis:businessdata:businessllist')")
    @GetMapping(value = "/businessllist")
    public AjaxResult businessllist(BusinessData businessData)
    {
        return businessDataService.businessllist(businessData);
    }



}
