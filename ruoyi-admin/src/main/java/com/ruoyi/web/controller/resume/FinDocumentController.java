package com.ruoyi.web.controller.resume;

import java.util.List;
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
import com.ruoyi.resume.domain.FinDocument;
import com.ruoyi.resume.service.IFinDocumentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学习园地Controller
 * 
 * @author ych
 * @date 2021-07-08
 */
@RestController
@RequestMapping("/resume/document")
public class FinDocumentController extends BaseController
{
    @Autowired
    private IFinDocumentService finDocumentService;

    /**
     * 查询学习园地列表
     */
    @PreAuthorize("@ss.hasPermi('resume:document:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinDocument finDocument)
    {
        startPage();
        Integer documentSort = finDocument.getDocumentSort();
        if(documentSort==0){
            finDocument.setDocumentSort(null);
        }
        List<FinDocument> list = finDocumentService.selectFinDocumentList(finDocument);
        return getDataTable(list);
    }

    /**
     * 导出学习园地列表
     */
    @PreAuthorize("@ss.hasPermi('resume:document:export')")
    @Log(title = "学习园地", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FinDocument finDocument)
    {
        List<FinDocument> list = finDocumentService.selectFinDocumentList(finDocument);
        ExcelUtil<FinDocument> util = new ExcelUtil<FinDocument>(FinDocument.class);
        return util.exportExcel(list, "document");
    }

    /**
     * 获取学习园地详细信息
     */
    @PreAuthorize("@ss.hasPermi('resume:document:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(finDocumentService.selectFinDocumentById(id));
    }

    /**
     * 新增学习园地
     */
    @PreAuthorize("@ss.hasPermi('resume:document:add')")
    @Log(title = "学习园地", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public AjaxResult add(Integer documentType,String documentName,String documentIntroduce ,MultipartFile documentPath)
    {
        FinDocument finDocument =new  FinDocument();
        finDocument.setDocumentType(documentType);
        finDocument.setDocumentName(documentName);
        finDocument.setDocumentIntroduce(documentIntroduce);
        return finDocumentService.insertFinDocument(finDocument,documentPath);
    }

    /**
     * 修改学习园地
     */
    @Log(title = "学习园地", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinDocument finDocument)
    {
        return toAjax(finDocumentService.updateFinDocument(finDocument));
    }

    /**
     * 删除学习园地
     */
    @PreAuthorize("@ss.hasPermi('resume:document:remove')")
    @Log(title = "学习园地", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(finDocumentService.deleteFinDocumentByIds(ids));
    }
}
