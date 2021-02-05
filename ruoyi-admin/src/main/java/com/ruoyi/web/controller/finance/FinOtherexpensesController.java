package com.ruoyi.web.controller.finance;

import java.util.List;

import com.ruoyi.finance.domain.Expenditure;
import com.ruoyi.finance.domain.FinOtherexpenses;
import com.ruoyi.finance.service.IFinOtherexpensesService;
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
 * 每月公司其他住处费用Controller
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
@RestController
@RequestMapping("/finance/otherexpenses")
public class FinOtherexpensesController extends BaseController
{
    @Autowired
    private IFinOtherexpensesService finOtherexpensesService;

    /**
     * 查询每月公司其他住处费用列表
     */
    @PreAuthorize("@ss.hasPermi('finance:otherexpenses:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinOtherexpenses finOtherexpenses)
    {
        startPage();
        List<FinOtherexpenses> list = finOtherexpensesService.selectFinOtherexpensesList(finOtherexpenses);
        return getDataTable(list);
    }

    /**
     * 导出每月公司其他住处费用列表
     */
    @PreAuthorize("@ss.hasPermi('finance:otherexpenses:export')")
    @Log(title = "每月公司其他住处费用", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FinOtherexpenses finOtherexpenses)
    {
        List<Expenditure> list = finOtherexpensesService.selectFinOtherexpensesListt(finOtherexpenses);
        ExcelUtil<Expenditure> util = new ExcelUtil<Expenditure>(Expenditure.class);
        return util.exportExcel(list, "otherexpenses");
    }

    /**
     * 获取每月公司其他住处费用详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:otherexpenses:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(finOtherexpensesService.selectFinOtherexpensesById(id));
    }

    /**
     * 新增每月公司其他住处费用
     */
    @PreAuthorize("@ss.hasPermi('finance:otherexpenses:add')")
    @Log(title = "每月公司其他住处费用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinOtherexpenses finOtherexpenses)
    {
        return finOtherexpensesService.insertFinOtherexpenses(finOtherexpenses);
    }

    /**
     * 修改每月公司其他住处费用
     */
    @PreAuthorize("@ss.hasPermi('finance:otherexpenses:edit')")
    @Log(title = "每月公司其他住处费用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinOtherexpenses finOtherexpenses)
    {
        return toAjax(finOtherexpensesService.updateFinOtherexpenses(finOtherexpenses));
    }

    /**
     * 删除每月公司其他住处费用
     */
    @PreAuthorize("@ss.hasPermi('finance:otherexpenses:remove')")
    @Log(title = "每月公司其他住处费用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(finOtherexpensesService.deleteFinOtherexpensesByIds(ids));
    }
}
