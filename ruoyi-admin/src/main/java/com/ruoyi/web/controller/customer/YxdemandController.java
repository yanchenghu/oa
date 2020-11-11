package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.service.IYxcontactService;
import com.ruoyi.customer.service.IYxdemandService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
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
    private IYxcontactService yxcontactService;

    /**
     * 查询营销录入公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:yxdemand:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yxdemand yxdemand)
    {
        startPage();
        List<Yxdemand> list = yxdemandService.selectYxdemandList(yxdemand);
        return getDataTable(list);
    }

    /**
     * 导出营销录入公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:yxdemand:export')")
    @Log(title = "营销录入公司", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Yxdemand yxdemand)
    {
        List<Yxdemand> list = yxdemandService.selectYxdemandList(yxdemand);
        ExcelUtil<Yxdemand> util = new ExcelUtil<Yxdemand>(Yxdemand.class);
        return util.exportExcel(list, "yxdemand");
    }

    /**
     * 获取营销录入公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:yxdemand:query')")
    @GetMapping(value = "/{entryId}")
    public AjaxResult getInfo(@PathVariable("entryId") Integer entryId)
    {
        return AjaxResult.success(yxdemandService.selectYxdemandById(entryId));
    }

    /**
     * 新增营销录入公司
     */
    @PreAuthorize("@ss.hasPermi('customer:yxdemand:add')")
    @Log(title = "营销录入公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Yxdemand yxdemand)
    {
        return toAjax(yxdemandService.insertYxdemand(yxdemand));
    }

    /**
     * 查看跟进营销录入公司
     */
    @PreAuthorize("@ss.hasPermi('customer:yxdemand:see')")
    @Log(title = "营销录入公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Yxdemand yxdemand)
    {
        return toAjax(yxdemandService.updateYxdemand(yxdemand));
    }


    /**
     * 查看和跟进营销录入公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:yxdemand:follow')")
    @GetMapping("/follow")
    public TableDataInfo follow(Yxdemand yxdemand)
    {
        startPage();
        List<Yxdemand> list = yxdemandService.selectYxdemandList(yxdemand);
        return getDataTable(list);
    }

}
