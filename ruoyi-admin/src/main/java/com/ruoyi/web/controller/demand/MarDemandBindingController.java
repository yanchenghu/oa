package com.ruoyi.web.controller.demand;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.domain.MarDemandresumefollow;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 需求绑定Controller
 *
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/demand/binding")
public class MarDemandBindingController extends BaseController {

    @Autowired
    private IMarDemandService marDemandService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private IPerCustomerinfoService perCustomerinfoService;
    /**
     * 需求绑定表的查询
     */
    @PreAuthorize("@ss.hasPermi('demand:binding:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarDemand marDemand)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MarDemand> list = marDemandService.selectMarDemandbindingList(marDemand,loginUser);
        return getDataTable(list);
    }

    /**
     * 获取我抢占的简历
     */
    @GetMapping(value = "/myRobresume")
    public AjaxResult myRobresume()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return perCustomerinfoService.myRobresume(loginUser);
    }

    /**
     * 简历绑定需求
     */
    @PostMapping(value = "/resumeBingDemand")
    public AjaxResult resumeBingDemand(String zm){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.resumeBingDemand(loginUser,zm);
    }

    /**
     *需求已绑定简历列表
     */
    @PostMapping(value = "/demandDetailsList")
    public TableDataInfo demandDetailsList(String demandId ,String customerName){
        startPage();
        List<Map> list= marDemandService.demandDetailsList(demandId,customerName);
        return getDataTable(list);
    }
    /**
     *需求已绑定简历跟踪
     */
    @PostMapping(value = "/demandResumeTrack")
    public AjaxResult demandResumeTrack(MarDemandresumefollow marDemandresumefollow){

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.demandResumeTrack(marDemandresumefollow,loginUser);
    }





}
