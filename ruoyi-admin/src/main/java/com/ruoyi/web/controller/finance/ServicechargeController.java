package com.ruoyi.web.controller.finance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.finance.domain.Business;
import com.ruoyi.finance.domain.FinStatements;
import com.ruoyi.finance.service.IFinStatementsService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private TokenService tokenService;

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
        List<Business> list = finStatementsService.selectFinStatementsLists(finStatements);
        ExcelUtil<Business> util = new ExcelUtil<Business>(Business.class);
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
    public AjaxResult add(MultipartFile file, String corpCode,String party){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        if(file==null || StringUtils.isEmpty(corpCode)|| party==null){
            return AjaxResult.error("上传文件或者甲方乙方不能为空");
        }
        try {
            return finStatementsService.addFinStatements(file,corpCode,party,loginUser);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败，请联系管理员");
        }
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
     * 批量开票
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:batchBilling')")
    @PostMapping(value = "/batchBilling")
    public AjaxResult batchBilling(String zm,MultipartFile file)
    {
        return finStatementsService.batchBilling(zm,file);
    }


    /**
     * 批量付款
     */
    @PreAuthorize("@ss.hasPermi('finance:servicecharge:batchPayment')")
    @PostMapping(value = "/batchPayment")
    public AjaxResult batchPayment(String zm)
    {
        return finStatementsService.batchPayment(zm);
    }













}
