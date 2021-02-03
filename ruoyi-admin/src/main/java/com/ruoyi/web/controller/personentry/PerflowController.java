package com.ruoyi.web.controller.personentry;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.demand.domain.ExportItemList;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.domain.PersonnelEssentialinfor;

import com.ruoyi.demand.domain.PersonnelEssentialinforExcel;
import com.ruoyi.demand.service.IMarCustomerprojectpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 人员流动Controller
 *
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/perentry/perflow")
public class PerflowController extends BaseController {


    @Autowired
    private IMarCustomerprojectpayService marCustomerprojectpayService;

    /**
     * 人员流动信息列表
     */
    @PreAuthorize("@ss.hasPermi('perentry:perflow:list')")
    @GetMapping("/list")
    public TableDataInfo personnelTurnoverList(MarCustomerprojectpay marCustomerprojectpay)
    {
        startPage();
        List<PersonnelEssentialinfor> list = marCustomerprojectpayService.selectpersonnelTurnoverList(marCustomerprojectpay);
        return getDataTable(list);
    }
    /*入项人员信息*/
    @GetMapping("/entryDetail")
    public Map entryDetail(MarCustomerprojectpay marCustomerprojectpay)  {
        return marCustomerprojectpayService.entryDetail(marCustomerprojectpay);
    }

    /**
     * 人员流动出项信息
     */
    @PreAuthorize("@ss.hasPermi('perentry:perflow:list')")
    @GetMapping("/outItemlist")
    public TableDataInfo outItemlist(MarCustomerprojectpay marCustomerprojectpay)
    {
        startPage();
        List<Map> list = marCustomerprojectpayService.outItemlist(marCustomerprojectpay);
        return getDataTable(list);
    }

    /**
     * 导出入项列表
     */
    @PreAuthorize("@ss.hasPermi('perentry:entry:export')")
    @Log(title = "入项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarCustomerprojectpay marCustomerprojectpay)
    {
        List<PersonnelEssentialinforExcel> listFile = new ArrayList<>();
        List<PersonnelEssentialinfor> list = marCustomerprojectpayService.selectpersonnelTurnoverList(marCustomerprojectpay);

        if(list != null && list.size()>0){
            for(PersonnelEssentialinfor p : list){
                PersonnelEssentialinforExcel fileExcel = new PersonnelEssentialinforExcel();
                if(p.getSocSecopt()==1){
                    fileExcel.setSocSecoptStr("交");
                }else{
                    fileExcel.setSocSecoptStr("不交");
                }
                fileExcel.setDeptName(p.getDeptName());
                fileExcel.setNickName(p.getNickName());
                fileExcel.setCustomerName(p.getCustomerName());
                fileExcel.setSalary(p.getSalary());
                fileExcel.setProfit(p.getProfit());
                fileExcel.setProfitMargin(p.getProfitMargin().toString()+"%");
                fileExcel.setServicePay(p.getServicePay());
                fileExcel.setSyqstartTime(p.getSyqstartTime());
                fileExcel.setSyqEndtime(p.getSyqEndtime());
                fileExcel.setCustomerTel(p.getCustomerTel());
                fileExcel.setEntryAddress(p.getEntryAddress());
                fileExcel.setCorpName(p.getCorpName());
                fileExcel.setEntryAssistant(p.getEntryAssistant());
                listFile.add(fileExcel);
            }
        }
        ExcelUtil<PersonnelEssentialinforExcel> util = new ExcelUtil<PersonnelEssentialinforExcel>(PersonnelEssentialinforExcel.class);
        return util.exportExcel(listFile, "entry");
    }

    /**
     * 导出出项列表
     */
    @PreAuthorize("@ss.hasPermi('perentry:entry:export')")
    @Log(title = "入项", businessType = BusinessType.EXPORT)
    @GetMapping("/out")
    public AjaxResult out(MarCustomerprojectpay marCustomerprojectpay)
    {
        List<ExportItemList> list = marCustomerprojectpayService.outItemlists(marCustomerprojectpay);

        ExcelUtil<ExportItemList> util = new ExcelUtil<ExportItemList>(ExportItemList.class);
        return util.exportExcel(list, "entry");
    }



}
