package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;

import java.util.List;
import java.util.Map;

/**
 * @Description: 人事数据分析的service层
 * @Author ych
 * @Date 2021/2/25
 */
public interface IPersonnelDataService {


    AjaxResult personnellist(PersonnelData personnelData, LoginUser loginUser);

    AjaxResult everydaylist(PersonnelData personnelData);


    List<Map> userlist();
}
