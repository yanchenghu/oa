package com.ruoyi.web.controller.resume;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.resume.domain.PerTemplate;
import com.ruoyi.resume.service.IPerTemplateService;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('resume:template:edit')")
    @Log(title = "简历模板", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody PerTemplate perTemplate)
    {
        return toAjax(perTemplateService.updatePerTemplate(perTemplate));
    }

    /**
     * 下载简历模板
     */
    @PreAuthorize("@ss.hasPermi('resume:template:download')")
    @Log(title = "简历模板", businessType = BusinessType.EXPORT)
    @GetMapping("/download")
    public AjaxResult export(PerTemplate perTemplate)
    {
        List<PerTemplate> list = perTemplateService.selectPerTemplateList(perTemplate);
        ExcelUtil<PerTemplate> util = new ExcelUtil<PerTemplate>(PerTemplate.class);
        return util.exportExcel(list, "template");
    }









}
