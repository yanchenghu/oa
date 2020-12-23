package com.ruoyi.web.controller.personentry;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.*;
import com.ruoyi.demand.service.IMarAdsalaryService;
import com.ruoyi.demand.service.IMarBorrowService;
import com.ruoyi.demand.service.IMarCustomerprojectpayService;
import com.ruoyi.demand.service.IMarServicepayService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 人员入项Controller
 *
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/perentry/entry")
public class PerentryController extends BaseController {

    @Autowired
    private IMarCustomerprojectpayService marCustomerprojectpayService;

    @Autowired
    private IMarBorrowService borrowService;

    @Autowired
    private IMarAdsalaryService marAdsalaryService;

    @Autowired
    private IMarServicepayService marServicepayService;
    @Autowired
    private TokenService tokenService;

    /**
     * 人员入项信息列表
     */
    @PreAuthorize("@ss.hasPermi('perentry:entry:list')")
    @GetMapping("/list")
    public TableDataInfo entrylist(MarCustomerprojectpay MarCustomerprojectpay)
    {
        startPage();
        List<MarCustomePerinfo> list = marCustomerprojectpayService.selectentrylistList(MarCustomerprojectpay);
        return getDataTable(list);
    }


    /**
     * 人员入项配置信息
     */
    @GetMapping(value = "/entryinfor/{id}")
    public AjaxResult entryinfor(@PathVariable String id)
    {
        return marCustomerprojectpayService.entryinfor(id);
    }



    /**
     * 修改入项信息
     */
    @PostMapping(value = "/update")
    public AjaxResult update(MarCustomerprojectpay marCustomerprojectpay)
    {
        return  marCustomerprojectpayService.updateMarCustomerprojectpay(marCustomerprojectpay);
    }
    /**
     * 添加借用记录
     */
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody MarBorrow marBorrow)
    {
        return  borrowService.insertMarBorrow(marBorrow);
    }


    /**
     * 添加工资调整
     */
    @PostMapping(value = "/addwages")
    public AjaxResult addwages(@RequestBody MarAdsalary marAdsalary)
    {
        return  marAdsalaryService.insertMarAdsalary(marAdsalary);
    }

    /**
     * 归还
     */
    @PostMapping(value = "/edit")
    public AjaxResult edit(@RequestBody MarBorrow marBorrow)
    {
        return  borrowService.updateMarBorrow(marBorrow);
    }


    /**
     * 添加服务费调整
     */
    @PostMapping(value = "/addfee")
    public AjaxResult addfee(@RequestBody MarServicepay marServicepay)
    {
        return  marServicepayService.insertMarServicepay(marServicepay);
    }

    /**
     * 添加证件
     */
    @PostMapping(value = "/addCertifi")
    public AjaxResult addCertifi(String id, String marcusId,Integer photo,MultipartFile file){
     Integer ad=null;
     if(!id.equals("null")){
         ad = Integer.valueOf(id);
     }
        return  marServicepayService.addCertifi(ad,marcusId,photo,file);
    }

    /**
     * 人员出项
     */
    @PostMapping(value = "/personnelItems")
    public AjaxResult personnelItems(@RequestBody MarCustomerprojectpay marCustomerprojectpay){
        return marServicepayService.personnelItems(marCustomerprojectpay) ;
    }



    /**
     * 检查人员借用物品是否有归还
     */
    @GetMapping(value = "/isReturnborrow/{id}")
    public int isReturnborrow(@PathVariable String id)
    {
        return marCustomerprojectpayService.isReturnborrow(id);
    }









    }
