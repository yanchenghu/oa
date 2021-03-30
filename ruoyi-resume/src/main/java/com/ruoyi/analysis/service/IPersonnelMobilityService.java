package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.PersonnelMobility;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @Description: 人员流动service
 * @Author ych
 * @Date 2021/3/15
 */
public interface IPersonnelMobilityService {


    AjaxResult getpersonnelmobilitylist(PersonnelMobility personnelMobility);
}
