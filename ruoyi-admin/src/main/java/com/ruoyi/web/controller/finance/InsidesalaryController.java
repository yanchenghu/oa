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
import com.ruoyi.finance.domain.FinInsidesalary;
import com.ruoyi.finance.service.IFinInsidesalaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 内部人员工资Controller
 * 
 * @author ych
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/finance/insidesalary")
public class InsidesalaryController extends BaseController
{
    @Autowired
    private IFinInsidesalaryService finInsidesalaryService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询内部人员工资列表
     */
    @PreAuthorize("@ss.hasPermi('finance:insidesalary:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinInsidesalary finInsidesalary)
    {
        startPage();
        List<FinInsidesalary> list = finInsidesalaryService.selectFinInsidesalaryList(finInsidesalary);
        return getDataTable(list);
    }

    /**
     * 导出内部人员工资列表
     */
    @PreAuthorize("@ss.hasPermi('finance:insidesalary:export')")
    @Log(title = "内部人员工资", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FinInsidesalary finInsidesalary)
    {
        List<FinInsidesalary> list = finInsidesalaryService.selectFinInsidesalaryList(finInsidesalary);
        ExcelUtil<FinInsidesalary> util = new ExcelUtil<FinInsidesalary>(FinInsidesalary.class);
        return util.exportExcel(list, "insidesalary");
    }

    /**
     * 获取内部人员工资详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:insidesalary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(finInsidesalaryService.selectFinInsidesalaryById(id));
    }

    /**
     * 新增内部人员工资
     */
    @PreAuthorize("@ss.hasPermi('finance:insidesalary:add')")
    @Log(title = "内部人员工资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(MultipartFile file, Date months)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        try {
            return finInsidesalaryService.addfinFinInsidesalary(file,months,loginUser);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("上传文件格式有误会，请核对后上传");
        }
    }

    /**
     * 修改内部人员工资
     */
    @PreAuthorize("@ss.hasPermi('finance:insidesalary:edit')")
    @Log(title = "内部人员工资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinInsidesalary finInsidesalary)
    {
        return toAjax(finInsidesalaryService.updateFinInsidesalary(finInsidesalary));
    }

    /**
     * 删除内部人员工资
     */
    @PreAuthorize("@ss.hasPermi('finance:insidesalary:remove')")
    @Log(title = "内部人员工资", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(finInsidesalaryService.deleteFinInsidesalaryByIds(ids));
    }
}
