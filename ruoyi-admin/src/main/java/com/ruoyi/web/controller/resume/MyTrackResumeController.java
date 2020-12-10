package com.ruoyi.web.controller.resume;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.domain.PerCuscontact;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 我跟踪的简历Controller
 *
 * @author yan
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/resume/mytrackresume")
public class MyTrackResumeController extends BaseController {

    @Autowired
    private IPerCustomerinfoService perCustomerinfoService;
    @Autowired
    private TokenService tokenService;

     /**
     * 查询我跟踪的简历 列表
     */
    @PreAuthorize("@ss.hasPermi('resume:mytrackresume:list')")
    @GetMapping("/list")
    public TableDataInfo list(PerCustomerinfo perCustomerinfo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        List<Map> list = perCustomerinfoService.selectmytrackresumeList(perCustomerinfo,loginUser);
        return getDataTable(list);
    }

    /**
     * 跟踪的简历
     */
    @PostMapping(value = "/resumeTracking")
    public AjaxResult resumeTracking(@RequestBody PerCuscontact perCuscontact)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return  perCustomerinfoService.resumeTracking(perCuscontact,loginUser);
    }






}
