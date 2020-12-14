package com.ruoyi.web.controller.customer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.customer.domain.MarContract;
import com.ruoyi.customer.service.IMarContractService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.service.IMarCompanyService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private IMarContractService marContractService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询合作公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarCompany marCompany)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        List<MarCompany> list = marCompanyService.selectMarCompanyList(marCompany,loginUser);
        return getDataTable(list);
    }

    /**
     * 新增合作公司
     */

    @Log(title = "合作公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarCompany marCompany)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        marCompany.setCorpCode(String.valueOf(UUID.randomUUID()));
        return marCompanyService.insertMarCompany(marCompany,loginUser);
    }


    /**
     * 获取合作公司详细信息
     */
    @GetMapping(value = "/{corpCode}")
    public AjaxResult getInfo(@PathVariable("corpCode") String corpCode)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(marCompanyService.selectMarCompanyById(corpCode,loginUser));
    }


    /**
     * 修改合作公司详细信息
     */
    @Log(title = "合作公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarCompany marCompany)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marCompanyService.updateMarCompany(marCompany,loginUser);
    }


    /**
     * 查询合同信息
     */
    @GetMapping("/contract")
    public TableDataInfo contract(MarContract marContract)
    {
        startPage();
        List<MarContract> list = marContractService.selectMarContractList(marContract);
        return getDataTable(list);
    }

    /**
     * 添加合同信息
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestParam("contractPreview") MultipartFile file, String firstParty,
    String party, String startTime, String endTime,String clientSigner, String companySigner,String corpCode)
    {
        try {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            Date startTime1 = DateUtils.parseDate(startTime);
            Date endTime1 = DateUtils.parseDate(endTime);
            return marContractService.insertMarContract(file,firstParty,party, startTime1,endTime1,clientSigner,companySigner,loginUser,corpCode);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("上传错误");
        }
    }

    /**
     * 获取我的合作公司
     * @return
     */
    @GetMapping("/listcom")
    public List<MarCompany> listcom()
    {
        MarCompany marCompany=new MarCompany();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MarCompany> list = marCompanyService.selectMarCompanyList(marCompany,loginUser);
        return list;
    }




}
