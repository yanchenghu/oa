package com.ruoyi.web.controller.finance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.finance.domain.FinStatements;
import com.ruoyi.finance.service.IFinStatementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人员入项Controller
 *
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/finance/servicecharge")
public class ServicechargeController extends BaseController {

    @Autowired
    private IFinStatementsService finStatementsService;


    /**
     * 查询服务费核算请求书列表
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinStatements finStatements)
    {
        startPage();
        List<FinStatements> list = finStatementsService.selectFinStatementsList(finStatements);
        return getDataTable(list);
    }

    /**
     * 导出服务费核算请求书列表
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:export')")
    @Log(title = "服务费核算请求书", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FinStatements finStatements)
    {
        List<FinStatements> list = finStatementsService.selectFinStatementsList(finStatements);
        ExcelUtil<FinStatements> util = new ExcelUtil<FinStatements>(FinStatements.class);
        return util.exportExcel(list, "servicecharge");
    }

    /**
     * 获取服务费核算请求书详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(finStatementsService.selectFinStatementsById(id));
    }

    /**
     * 新增服务费核算请求书
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:add')")
    @Log(title = "服务费核算请求书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinStatements finStatements)
    {
        return toAjax(finStatementsService.insertFinStatements(finStatements));
    }

    /**
     * 修改服务费核算请求书
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:edit')")
    @Log(title = "服务费核算请求书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinStatements finStatements)
    {
        return toAjax(finStatementsService.updateFinStatements(finStatements));
    }

    /**
     * 删除服务费核算请求书
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:remove')")
    @Log(title = "服务费核算请求书", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(finStatementsService.deleteFinStatementsByIds(ids));
    }














}
