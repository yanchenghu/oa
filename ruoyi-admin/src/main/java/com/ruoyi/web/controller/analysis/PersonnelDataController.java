package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.service.IPersonnelDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    //成员数据量对比
    @PreAuthorize("@ss.hasPermi('analysis:personneldata:personnellist')")
    @GetMapping(value = "/personnellist")
    public AjaxResult personnellist(PersonnelData personnelData)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return personnelDataService.personnellist(personnelData,loginUser);
    }


    //每日详细数据
    @PreAuthorize("@ss.hasPermi('analysis:personneldata:personnellist')")
    @GetMapping(value = "/everydaylist")
    public AjaxResult everydaylist(PersonnelData personnelData)
    {
        return personnelDataService.everydaylist(personnelData);
    }


    //查询
    @GetMapping("userlist")
    public AjaxResult userlist()
    {
        List<Map> list = personnelDataService.userlist();
        return AjaxResult.success(list);
    }




}
