package com.ruoyi.web.controller.finance;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
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
import com.ruoyi.finance.domain.FinExpatriatesalary;
import com.ruoyi.finance.service.IFinExpatriatesalaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description: 外派人员工资
 * @Author ych
 * @Date 2021/1/26
 */
@RestController
@RequestMapping("/finance/expatriatesalary")
public class ExpatriateSalaryController  extends BaseController {

    @Autowired
    private IFinExpatriatesalaryService finExpatriatesalaryService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询外派人员工资列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expatriatesalary:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinExpatriatesalary finExpatriatesalary)
    {
        startPage();
        List<FinExpatriatesalary> list = finExpatriatesalaryService.selectFinExpatriatesalaryList(finExpatriatesalary);
        return getDataTable(list);
    }

    /**
     * 导出外派人员工资列表
     */
    @PreAuthorize("@ss.hasPermi('finance:expatriatesalary:export')")
    @Log(title = "外派人员工资", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FinExpatriatesalary finExpatriatesalary)
    {
        List<FinExpatriatesalary> list = finExpatriatesalaryService.selectFinExpatriatesalaryList(finExpatriatesalary);
        ExcelUtil<FinExpatriatesalary> util = new ExcelUtil<FinExpatriatesalary>(FinExpatriatesalary.class);
        return util.exportExcel(list, "expatriatesalary");
    }

    /**
     * 获取外派人员工资详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:expatriatesalary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(finExpatriatesalaryService.selectFinExpatriatesalaryById(id));
    }

    /**
     * 新增外派人员工资
     */
    @PreAuthorize("@ss.hasPermi('finance:expatriatesalary:add')")
    @Log(title = "外派人员工资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(MultipartFile file, Date months)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        try {
            return finExpatriatesalaryService.addfinExpatriatesalary(file,months,loginUser);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("上传文件格式有误会，请核对后上传");
        }
    }

    /**
     * 修改外派人员工资
     */
    @PreAuthorize("@ss.hasPermi('finance:expatriatesalary:edit')")
    @Log(title = "外派人员工资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinExpatriatesalary finExpatriatesalary)
    {
        return toAjax(finExpatriatesalaryService.updateFinExpatriatesalary(finExpatriatesalary));
    }

    /**
     * 删除外派人员工资
     */
    @PreAuthorize("@ss.hasPermi('finance:expatriatesalary:remove')")
    @Log(title = "外派人员工资", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(finExpatriatesalaryService.deleteFinExpatriatesalaryByIds(ids));
    }



}
