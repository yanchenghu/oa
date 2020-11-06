package com.ruoyi.web.controller.resume;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.resume.domain.PerTemplate;
import com.ruoyi.resume.service.IPerTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume/template")
public class PerTemplateController extends BaseController {
    @Autowired
    private IPerTemplateService perTemplateService;

    /**
     * 查询简历模板列表
     */
    @PreAuthorize("@ss.hasPermi('resume:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(PerTemplate perTemplate)
    {
        startPage();
        List<PerTemplate> list = perTemplateService.selectPerTemplateList(perTemplate);
        return getDataTable(list);
    }

    /**
     * 导出简历模板列表
     */
    @PreAuthorize("@ss.hasPermi('resume:template:export')")
    @Log(title = "简历模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PerTemplate perTemplate)
    {
        List<PerTemplate> list = perTemplateService.selectPerTemplateList(perTemplate);
        ExcelUtil<PerTemplate> util = new ExcelUtil<PerTemplate>(PerTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 获取简历模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('resume:template:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") String templateId)
    {
        return AjaxResult.success(perTemplateService.selectPerTemplateById(templateId));
    }

    /**
     * 新增简历模板
     */
    @PreAuthorize("@ss.hasPermi('resume:template:add')")
    @Log(title = "简历模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PerTemplate perTemplate)
    {
        return toAjax(perTemplateService.insertPerTemplate(perTemplate));
    }

    /**
     * 修改简历模板
     */
    @PreAuthorize("@ss.hasPermi('resume:template:edit')")
    @Log(title = "简历模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PerTemplate perTemplate)
    {
        return toAjax(perTemplateService.updatePerTemplate(perTemplate));
    }

    /**
     * 删除简历模板
     */
    @PreAuthorize("@ss.hasPermi('resume:template:remove')")
    @Log(title = "简历模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable String[] templateIds)
    {
        return toAjax(perTemplateService.deletePerTemplateByIds(templateIds));
    }


    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('resume:template:edit')")
    @Log(title = "简历模板", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody PerTemplate perTemplate)
    {
        return toAjax(perTemplateService.updatePerTemplate(perTemplate));
    }





}
