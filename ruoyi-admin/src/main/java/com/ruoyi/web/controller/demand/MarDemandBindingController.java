package com.ruoyi.web.controller.demand;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.domain.MarDemandresumefollow;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private ISysUserService userService;
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

    @GetMapping("/list1")
    public TableDataInfo list1(MarDemand marDemand)
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
    /**
     *获取入项信息的基本
     */
    @PostMapping(value = "/getInputInformation")
    public AjaxResult getInputInformation(String id){
        return marDemandService.getInputInformation(id);
    }




    /**
     *人员安排入项
     */
    @PostMapping(value = "/entryPersonnel")
    public AjaxResult entryPersonnel(@RequestBody MarCustomerprojectpay marCustomerprojectpay){

        if(StringUtils.isNotEmpty(marCustomerprojectpay.getOpercode())){
           LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
           SysUser user= userService.selectUserByUserName(marCustomerprojectpay.getOpercode());
           return marDemandService.entryPersonnel(marCustomerprojectpay,user,loginUser);
        }else{
            return AjaxResult.error("简历需求绑定人为空");
        }

    }





}
