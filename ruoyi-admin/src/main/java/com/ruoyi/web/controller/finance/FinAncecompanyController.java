package com.ruoyi.web.controller.finance;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.finance.domain.FinAncecontact;
import com.ruoyi.finance.domain.FinMonitoringcompanies;
import com.ruoyi.finance.service.IFinAncecontactService;
import com.ruoyi.finance.service.IFinMonitoringcompaniesService;
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
import com.ruoyi.finance.domain.FinAncecompany;
import com.ruoyi.finance.service.IFinAncecompanyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 添加合作公司Controller
 * 
 * @author ych
 * @date 2021-02-02
 */
@RestController
@RequestMapping("/finance/ancecompany")
public class FinAncecompanyController extends BaseController
{
    @Autowired
    private IFinAncecompanyService finAncecompanyService;
    @Autowired
    private IFinAncecontactService finAncecontactService;
    @Autowired
    private IFinMonitoringcompaniesService finMonitoringcompaniesService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询添加合作公司列表
     */
    @PreAuthorize("@ss.hasPermi('finance:ancecompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinAncecompany finAncecompany)
    {
        startPage();
        List<FinAncecontact> list = finAncecompanyService.selectFinAncecontactList(finAncecompany);
        return getDataTable(list);
    }

    /**
     * 导出添加合作公司列表
     */
    @PreAuthorize("@ss.hasPermi('finance:ancecompany:export')")
    @Log(title = "添加合作公司", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FinAncecompany finAncecompany)
    {
        List<FinAncecompany> list = finAncecompanyService.selectFinAncecompanyList(finAncecompany);
        ExcelUtil<FinAncecompany> util = new ExcelUtil<FinAncecompany>(FinAncecompany.class);
        return util.exportExcel(list, "ancecompany");
    }

    /**
     * 获取添加合作公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:ancecompany:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(finAncecompanyService.selectFinAncecontactById(id));
    }

    /**
     * 新增添加合作公司
     */
    @PreAuthorize("@ss.hasPermi('finance:ancecompany:add')")
    @Log(title = "添加合作公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insertFinAncecompany(String zm)
    {
        return finAncecompanyService.insertFinAncecompany(zm);
    }
    /**
     * 新增合作公司记录
     */
    @PreAuthorize("@ss.hasPermi('finance:ancecompany:edit')")
    @PostMapping(value = "/addAncecontact")
    public AjaxResult Ancecontact(@RequestBody FinAncecontact finAncecontact)
    {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        try {
            return finAncecontactService.insertFinAncecontact(finAncecontact,loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常");
        }
    }
    /**
     * 删除添加合作公司
     */
    @PreAuthorize("@ss.hasPermi('finance:ancecompany:remove')")
    @Log(title = "删除合作公司", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Integer id)
    {
        return toAjax(finAncecompanyService.deleteFinAncecompanyById(id));
    }
    /**
     * 查看历史跟进记录
     */
    @PostMapping(value = "/selAncecontact")
    public AjaxResult selAncecontact(@RequestBody FinAncecontact finAncecontact)
    {
        return finAncecontactService.selAncecontact(finAncecontact);
    }

    @GetMapping("/listMonitoring")
    public TableDataInfo listMonitoring(FinMonitoringcompanies finMonitoringcompanies)
    {
        List<FinMonitoringcompanies> list = finMonitoringcompaniesService.selectFinMonitoringcompaniesList(finMonitoringcompanies);
        return getDataTable(list);
    }
    /**
     * 新增重点监测公司
     */
    @PostMapping("/addMonitoring")
    public AjaxResult addMonitoring(@RequestBody FinMonitoringcompanies finMonitoringcompanies)
    {
        return toAjax(finMonitoringcompaniesService.insertFinMonitoringcompanies(finMonitoringcompanies));
    }
    /**
     * 修改重点监测公司
     */
    @PutMapping("/editMonitoring")
    public AjaxResult editMonitoring(@RequestBody FinMonitoringcompanies finMonitoringcompanies)
    {
        return toAjax(finMonitoringcompaniesService.updateFinMonitoringcompanies(finMonitoringcompanies));
    }
    /**
     * 获取重点监测公司详细信息
     */
    @GetMapping(value = "/gettMon/{id}")
    public AjaxResult getMonitoringInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(finMonitoringcompaniesService.selectFinMonitoringcompaniesById(id));
    }

    /**
     * 删除重点监测公司
     */

    @DeleteMapping("/reMonitorid/{id}")
    public AjaxResult reMonitorid(@PathVariable Integer id)
    {
        return toAjax(finMonitoringcompaniesService.deleteFinMonitoringcompaniesById(id));
    }

}
