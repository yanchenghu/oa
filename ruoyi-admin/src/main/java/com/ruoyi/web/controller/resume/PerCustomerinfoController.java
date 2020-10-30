package com.ruoyi.resume.controller;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 录入解析简历Controller
 *
 * @author yan
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/resume/record")
public class PerCustomerinfoController extends BaseController
{
    @Autowired
    private IPerCustomerinfoService perCustomerinfoService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询简历列表
     */
    @PreAuthorize("@ss.hasPermi('resume:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PerCustomerinfo perCustomerinfo)
    {
        startPage();
        List<PerCustomerinfo> list = perCustomerinfoService.selectPerCustomerinfoList(perCustomerinfo);
        return getDataTable(list);
    }

    /**
     * 导出简历列表
     */
    @PreAuthorize("@ss.hasPermi('resume:record:export')")
    @Log(title = "简历", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PerCustomerinfo perCustomerinfo)
    {
        List<PerCustomerinfo> list = perCustomerinfoService.selectPerCustomerinfoList(perCustomerinfo);
        ExcelUtil<PerCustomerinfo> util = new ExcelUtil<PerCustomerinfo>(PerCustomerinfo.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取简历详细信息
     */
    @PreAuthorize("@ss.hasPermi('resume:record:query')")
    @GetMapping(value = "/{customerCode}")
    public AjaxResult getInfo(@PathVariable("customerCode") String customerCode)
    {
        return AjaxResult.success(perCustomerinfoService.selectPerCustomerinfoById(customerCode));
    }

    /**
     * 新增简历
     */
    @PreAuthorize("@ss.hasPermi('resume:record:add')")
    @Log(title = "简历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PerCustomerinfo perCustomerinfo)
    {
        return toAjax(perCustomerinfoService.insertPerCustomerinfo(perCustomerinfo));
    }

    /**
     * 修改简历
     */
    @PreAuthorize("@ss.hasPermi('resume:record:edit')")
    @Log(title = "简历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PerCustomerinfo perCustomerinfo)
    {
        return toAjax(perCustomerinfoService.updatePerCustomerinfo(perCustomerinfo));
    }

    /**
     * 删除简历
     */
    @PreAuthorize("@ss.hasPermi('resume:record:remove')")
    @Log(title = "简历", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customerCodes}")
    public AjaxResult remove(@PathVariable String[] customerCodes)
    {
        return toAjax(perCustomerinfoService.deletePerCustomerinfoByIds(customerCodes));
    }




    /**
     * 获取简历详细信息
     */
    @PutMapping("/analysisResume")
    public AjaxResult analysisResume(@RequestBody MultipartFile file,
                                   Integer resumeDirection1) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult dsa=  perCustomerinfoService.goAnalysisResume(file,resumeDirection1,loginUser);

        return dsa;
    }




}
