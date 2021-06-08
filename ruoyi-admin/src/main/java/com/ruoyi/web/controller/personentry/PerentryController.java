package com.ruoyi.web.controller.personentry;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.entrycontract.service.MarEntrycontractService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
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

import java.util.ArrayList;
import java.util.List;


/**
 * 人员出入项Controller
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
    private MarEntrycontractService marEntryContractService;

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


    /**
     * 添加劳动合同
     */
    @PostMapping(value = "/addLaborcontract")
    public AjaxResult addLaborcontract( String marcusId,
                                        @RequestParam("list") MultipartFile[] list )
    {
        return  marEntryContractService.addLaborcontract(marcusId,list);
    }
    /**
     * 删除入项合同
     */

    @Log(title = "入项合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Integer id)
    {
        return toAjax(marEntryContractService.deleteMarEntrycontractById(id));
    }

    /**
     * 导出人员出入项
     */
    @PreAuthorize("@ss.hasPermi('perentry:entry:export')")
    @Log(title = "入项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarCustomerprojectpay marCustomerprojectpay)
    {
        List<Entrys> list1 = new ArrayList<>();
        List<Entry> list = marCustomerprojectpayService.selectentrylistLists(marCustomerprojectpay);

        for(Entry e : list){
            Entrys entrys = new Entrys();
            entrys.setCustomerName(e.getCustomerName());
            entrys.setCustomerTel(e.getCustomerTel());
            entrys.setCorpName(e.getCorpName());
            entrys.setSyqstartTime(e.getSyqstartTime());
            Integer technologyDirection = e.getTechnologyDirection();
            String technologyDirections = DictUtils.getDictLabel("per_customerinfo_professionid", String.valueOf(technologyDirection));
            entrys.setTechnologyDirection(technologyDirections);
            entrys.setOutofProjecttime(e.getOutofProjecttime());
            entrys.setSalary(e.getSalary());
            entrys.setServicePay(e.getServicePay());
            if(e.getSocSecopt()!=null){
                if(e.getSocSecopt()==1){
                    entrys.setSocSecopt("是");
                }else {
                    entrys.setSocSecopt("否");
                }
            }

            if(e.getTechnologyDirection()!=null){
                if(e.getTechnologyDirection()==1){
                    entrys.setSign("是");
                }else {
                    entrys.setSign("否");
                }
            }

            list1.add(entrys);
        }
        ExcelUtil<Entrys> util = new ExcelUtil<Entrys>(Entrys.class);
        return util.exportExcel(list1, "entry");
    }





    }
