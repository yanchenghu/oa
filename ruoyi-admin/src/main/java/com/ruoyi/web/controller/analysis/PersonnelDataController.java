package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.service.IPersonnelDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 人事数据分析
 * @Author ych
 * @Date 2021/2/25
 */
@RestController
@RequestMapping("/analysis/personneldata")
public class PersonnelDataController extends BaseController {

    @Autowired
    private IPersonnelDataService personnelDataService;
    @Autowired
    private TokenService tokenService;

    @PreAuthorize("@ss.hasPermi('analysis:analysis:personnellist')")
    @GetMapping(value = "/personnellist")
    public AjaxResult personnellist(PersonnelData personnelData)
    {
        return personnelDataService.personnellist(personnelData);
    }





}
