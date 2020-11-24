package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
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
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.service.IMarCompanyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合作公司Controller
 * 
 * @author ruoyi
 * @date 2020-11-16
 */
@RestController
@RequestMapping("/customer/company")
public class MarCompanyController extends BaseController
{
    @Autowired
    private IMarCompanyService marCompanyService;

    /**
     * 查询合作公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarCompany marCompany)
    {
        startPage();
        List<MarCompany> list = marCompanyService.selectMarCompanyList(marCompany);
        return getDataTable(list);
    }

    /**
     * 导出合作公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:company:export')")
    @Log(title = "合作公司", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarCompany marCompany)
    {
        List<MarCompany> list = marCompanyService.selectMarCompanyList(marCompany);
        ExcelUtil<MarCompany> util = new ExcelUtil<MarCompany>(MarCompany.class);
        return util.exportExcel(list, "company");
    }

    /**
     * 获取合作公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:company:query')")
    @GetMapping(value = "/{corpCode}")
    public AjaxResult getInfo(@PathVariable("corpCode") String corpCode)
    {
        return AjaxResult.success(marCompanyService.selectMarCompanyById(corpCode));
    }

    /**
     * 新增合作公司
     */
    @PreAuthorize("@ss.hasPermi('customer:company:add')")
    @Log(title = "合作公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarCompany marCompany)
    {
        marCompany.setCorpCode(String.valueOf(UUID.randomUUID()));
        return toAjax(marCompanyService.insertMarCompany(marCompany));
    }

    /**
     * 修改合作公司
     */
    @PreAuthorize("@ss.hasPermi('customer:company:edit')")
    @Log(title = "合作公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarCompany marCompany)
    {
        return toAjax(marCompanyService.updateMarCompany(marCompany));
    }

    /**
     * 删除合作公司
     */
    @PreAuthorize("@ss.hasPermi('customer:company:remove')")
    @Log(title = "合作公司", businessType = BusinessType.DELETE)
	@DeleteMapping("/{corpCodes}")
    public AjaxResult remove(@PathVariable String[] corpCodes)
    {
        return toAjax(marCompanyService.deleteMarCompanyByIds(corpCodes));
    }
}
