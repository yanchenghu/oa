package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.Management;
import com.ruoyi.analysis.service.IManagementService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 经营分析
 * @Author ych
 * @Date 2021/3/9
 */
@RestController
@RequestMapping("/analysis/management")
public class ManagementController  extends BaseController {

    @Autowired
    private IManagementService managementService;




    @PreAuthorize("@ss.hasPermi('analysis:management:managementlist')")
    @GetMapping(value = "/managementlist")
    public AjaxResult managementlist(Management management)
    {
        return managementService.managementlist(management);
    }











}
