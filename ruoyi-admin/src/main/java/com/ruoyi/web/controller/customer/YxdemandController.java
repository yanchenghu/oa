package com.ruoyi.web.controller.customer;

import java.util.List;


import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.customer.domain.Yxcontact;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.service.IYxcontactService;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 营销录入公司Controller
 * 
 * @author ruoyi
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/customer/yxdemand")
public class YxdemandController extends BaseController
{
    @Autowired
    private IYxdemandService yxdemandService;


    @Autowired
    private TokenService tokenService;

//    /**
//     * 查询营销录入公司列表
//     */
//    @PreAuthorize("@ss.hasPermi('customer:yxdemand:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(Yxdemand yxdemand)
//    {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        startPage();
//        List<Yxdemand> list = yxdemandService.selectYxdemandList(yxdemand,loginUser);
//        return getDataTable(list);
//    }


    /**
        * 获取营销录入公司详细信息
     */
    @GetMapping(value = "/{entryId}")
    public AjaxResult getInfo(@PathVariable("entryId") Integer entryId)
    {
        return AjaxResult.success(yxdemandService.selectYxdemandById(entryId));
    }

//    /**
//     * 新增营销录入公司
//     */
//    @Log(title = "营销录入公司", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody Yxdemand yxdemand)
//    {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        return yxdemandService.insertYxdemand(yxdemand,loginUser);
//    }

    /**
     * 跟进营销录入公司
     */
    @Log(title = "营销跟进公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Yxdemand yxdemand)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return yxdemandService.updateYxdemand(yxdemand,loginUser);
    }
    /**
     * 发布信息
     */
    @PostMapping(value = "/release")
    public AjaxResult release(Yxcontact yxcontact)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return yxdemandService.insertYxcontact(yxcontact,loginUser);
    }
//    /**
//     * 客户移交
//     */
//    @PostMapping(value = "/transfer")
//    public AjaxResult  Customertransfer(Yxdemand yxdemand)
//    {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        return toAjax(yxdemandService.Customertransfer(yxdemand,loginUser));
//    }
//    /**
//     * 营销抢占功能营销
//     */
//    @PostMapping(value = "/rebMarByEnId")
//    public AjaxResult  rebClientByEnId(@RequestParam(value = "entryId", defaultValue = "") Integer entryId)
//    {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        return yxdemandService.rebMarByEnId(entryId,loginUser);
//    }
    /**
     * 根据公司名字获取营销录入公司
     */
    @PostMapping(value = "/yxdemByName")
    public String getInfo(@RequestParam(value = "companyName", defaultValue = "") String companyName)
    {
        return yxdemandService.yxdemByName(companyName);
    }


    /**
     * 商务新建意向客户
     */
    @PostMapping(value = "/newintention")
    public AjaxResult  newintention(@RequestBody Yxdemand yxdemand)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return yxdemandService.newIntention(yxdemand,loginUser);
    }
}
