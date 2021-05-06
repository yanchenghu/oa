package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.ProjectPersonnel;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @Description: 项目人员统计service
 * @Author ych
 * @Date 2021/4/22
 */
public interface IProjectPersonnelService {
    AjaxResult getProjectpersonnellist(ProjectPersonnel projectPersonnel);
}
