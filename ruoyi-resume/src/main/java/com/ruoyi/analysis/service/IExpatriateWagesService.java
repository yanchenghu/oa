package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.ExpatriateWages;
import com.ruoyi.common.core.domain.model.LoginUser;

import java.util.List;
import java.util.Map;

/**
 * @Description: 外派人员工资service
 * @Author ych
 * @Date 2021/2/22
 */
public interface IExpatriateWagesService {

    List<ExpatriateWages> selectExpatriateWagelist(ExpatriateWages expatriateWage);

    List<Map> selectComlist(LoginUser loginUser,ExpatriateWages expatriateWages);
}
