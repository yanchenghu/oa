package com.ruoyi.web.controller.customer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.resume.SerialNumber;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.customer.domain.MarCompanyContacts;
import com.ruoyi.customer.domain.MarContract;
import com.ruoyi.customer.service.IMarCompanyContactsService;
import com.ruoyi.customer.service.IMarContractService;
import com.ruoyi.customer.service.IYxdemandService;
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
    @Autowired
    private IMarCompanyContactsService marCompanyContactsService;
    @Autowired
    private IYxdemandService yxdemandService;

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
     * 总合作公司列表
     */
    @PreAuthorize("@ss.hasPermi('customer:company:booslist')")
    @GetMapping("/booslist")
    public TableDataInfo booslist(MarCompany marCompany)
    {
        LoginUser loginUser =null;
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
        marCompany.setCorpCode(SerialNumber.createSerial("hzgs", 6));
        return marCompanyService.insertMarCompany(marCompany,loginUser);
    }
    /**
     * 新增合作公司
     */
    /**
     * 根据公司名字获取营销录入公司
     */
    @PostMapping(value = "/companyName")
    public String getcompanyName(@RequestParam(value = "companyName", defaultValue = "") String companyName)
    {
        return marCompanyService.getcompanyName(companyName);
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
     * 修改合作公司名称查重
     */
    @PostMapping("/checkingcompany")
    public String checkingcompany(String corpCode,String corpName)
    {
        return marCompanyService.selcheckingcompany(corpCode,corpName);
    }

    /**
     * 修改合作公司详细信息
     */
    @Log(title = "合作公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarCompany marCompany)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        try {
            return marCompanyService.updateMarCompany(marCompany,loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("系统异常，请联系管理员");

        }
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
    /**
     * 获取所有的合作公司
     * @return
     */
    @GetMapping("/alllistcom")
    public List<MarCompany> alllistcom()
    {
        List<MarCompany> list = marCompanyService.selectMarCompanyAlllistcom();
        return list;
    }
    /**
     * 新增合作公司联系管理
     */


    @PostMapping("/addcontacts")
    public AjaxResult add(@RequestBody MarCompanyContacts marCompanyContacts)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        marCompanyContacts.setAddTime(new Date());
        marCompanyContacts.setTransformingPeople(loginUser.getUsername());
        return toAjax(marCompanyContactsService.insertMarCompanyContacts(marCompanyContacts));
    }

    /**
     * 删除合作公司联系管理联系人呢
     */
    @PostMapping("/del")
    public AjaxResult remove(Integer id)
    {
        return toAjax(marCompanyContactsService.deleteMarCompanyContactsById(id));
    }




}
