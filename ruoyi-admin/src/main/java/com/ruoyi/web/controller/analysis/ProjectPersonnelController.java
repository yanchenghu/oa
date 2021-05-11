package com.ruoyi.web.controller.analysis;


import com.ruoyi.analysis.domain.ProjectPersonnel;
import com.ruoyi.analysis.service.IProjectPersonnelService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 项目人员统计
 * @Author ych
 * @Date 2021/4/22
 */
@RestController
@RequestMapping("/analysis/projectpersonnel")
public class ProjectPersonnelController extends BaseController {

   @Autowired
   private IProjectPersonnelService projectPersonnelService;

    @PreAuthorize("@ss.hasPermi('analysis:projectpersonnel:list')")
    @GetMapping(value = "/list")
    public AjaxResult projectpersonnellist(ProjectPersonnel projectPersonnel)
    {
        return projectPersonnelService.getProjectpersonnellist(projectPersonnel);
    }












}
