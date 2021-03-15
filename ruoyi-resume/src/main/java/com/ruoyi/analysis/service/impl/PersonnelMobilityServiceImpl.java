package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.PersonnelMobility;
import com.ruoyi.analysis.mapper.PersonnelMobilityMapper;
import com.ruoyi.analysis.service.IPersonnelMobilityService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 人员流动分析Impl
 * @Author ych
 * @Date 2021/3/15
 */
@Service
public class PersonnelMobilityServiceImpl implements IPersonnelMobilityService {



    @Autowired
    private PersonnelMobilityMapper personnelMobilityMapper;
    @Override
    public AjaxResult getpersonnelmobilitylist(PersonnelMobility personnelMobility) {







        return null;
    }
}
