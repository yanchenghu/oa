package com.ruoyi.web.controller.demand;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;

import com.ruoyi.demand.domain.*;

import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.demand.service.IMarDemandresumeService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import com.ruoyi.system.service.ISysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

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
    @Autowired
    private IMarDemandresumeService marDemandresumeService;

    @Value("${ruoyi.template}")
    private String template;// (路径)
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
     * 人岗匹配开的全选
     */
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
    @PostMapping(value = "/myRobresume")
    public AjaxResult myRobresume(String demandId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return perCustomerinfoService.myRobresume(loginUser,demandId);
    }

    /**
     * 简历绑定需求
     */
    @PostMapping(value = "/resumeBingDemand")
    public AjaxResult resumeBingDemand(String zm ){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.resumeBingDemand(loginUser,zm );
    }

    /**
     * 查询简历有没有毕业时间，或者薪资
     */
    @PostMapping(value = "/queryResumeSalary")
    public String queryresumesalary(String customerCode){
        return marDemandService.queryresumesalary(customerCode);
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
     * 商务处理已绑定简历 跟踪
     */
    @PostMapping(value = "/demandResumeTrack")
    public AjaxResult demandResumeTrack(MarDemandresumefollow marDemandresumefollow){

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.demandResumeTrack(marDemandresumefollow,loginUser);
    }
    /**
     * 商务处理简历为一推荐
     */
    @PostMapping(value = "/updateMarDemandresume")
    public AjaxResult edit(@RequestBody MarDemandresume marDemandresume)
    {
        return toAjax(marDemandresumeService.updateMarDemandresume(marDemandresume));
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

    /**
     *批量简历、面试、通过
     */
    @PostMapping(value = "/batchOperation")
    public AjaxResult batchOperation(String zm) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.batchOperation(zm,loginUser);
    }

    /**
     *批量重置简历绑定状态
     */
    @PostMapping(value = "/batchResumeStatus")
    public AjaxResult batchResumeStatus(String zm) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.batchResumeStatus(zm,loginUser);
    }

    /**
     * 人事处理简历面试不通过，不入项
     */

    @PostMapping(value = "/noInterviewEntry")
    public AjaxResult noInterviewEntry(String demandId,String customerCode,Integer type,String followDetail) {
        return marDemandService.noInterviewEntry(demandId,customerCode,type,followDetail);
    }

//    /**
//     * 人事处理简历面试不通过，不入项
//     */
//
//    @PostMapping(value = "/noInterviewEntry")
//    public AjaxResult noInterviewEntry(String demandId,String customerCode,Integer type,String followDetail) {
//        return marDemandService.noInterviewEntry(demandId,customerCode,type,followDetail);
//    }

    /**
     * 查询当前人是否有绑定过的简历模板
     */

    @PostMapping(value = "/isResumeTemplate")
    public AjaxResult isResumeTemplate(String customerCode) {
        return marDemandService.isResumeTemplate(customerCode);
    }


    /**
     * 获取我绑定的简历
     */
    @PostMapping(value = "/getMybindingResume")
    public TableDataInfo getMybindingResume( PerCustomerinfo perCustomerinfo) {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MybindingResume> list=marDemandresumeService.getMybindingResume(perCustomerinfo,loginUser);
        return getDataTable(list);
    }



    }
