package com.ruoyi.web.controller.customer;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.service.IHighseasService;
import com.ruoyi.customer.service.IYxcontactService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 营销录入公司Controller
 * 
 * @author ruoyi
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/customer/highseas")
public class HighseasController extends BaseController
{
    @Autowired
    private IHighseasService highseasService;

    @Autowired
    private IYxcontactService yxcontactService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询营销录入公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:highseas:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yxdemand yxdemand)throws Exception
    {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        List<Yxdemand> list = highseasService.selectYxdemandList(yxdemand,loginUser);
        return getDataTable(list);
    }


    /**
        * 获取营销录入公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:highseas:query')")
    @GetMapping(value = "/{entryId}")
    public AjaxResult getInfo(@PathVariable("entryId") Integer entryId)
    {
        return AjaxResult.success(highseasService.selectYxdemandById(entryId));
    }

    /**
     * 新增营销录入公司
     */
    @PreAuthorize("@ss.hasPermi('customer:highseas:add')")
    @Log(title = "营销录入公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Yxdemand yxdemand)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(highseasService.insertYxdemand(yxdemand,loginUser));
    }

    /**
     * 跟进营销录入公司
     */
    @PreAuthorize("@ss.hasPermi('customer:highseas:edit')")
    @Log(title = "营销录入公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Yxdemand yxdemand)
    {
        return toAjax(highseasService.updateYxdemand(yxdemand));
    }






    /**
     * 移交
     */












}
