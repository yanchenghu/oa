package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.PersonnelMobility;
import com.ruoyi.analysis.service.IPersonnelMobilityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 人员流动分析
 * @Author ych
 * @Date 2021/3/15
 */
@RestController
@RequestMapping("/analysis/personnelmobility")
public class PersonnelMobilityController extends BaseController {


    @Autowired
    private IPersonnelMobilityService personnelMobilityService;

    @PreAuthorize("@ss.hasPermi('analysis:personnelmobility:list')")
    @GetMapping(value = "/list")
    public AjaxResult personnelmobilitylist(PersonnelMobility personnelMobility)
    {
        return personnelMobilityService.getpersonnelmobilitylist(personnelMobility);
    }




}
