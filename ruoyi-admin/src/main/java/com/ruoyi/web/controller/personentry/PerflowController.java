package com.ruoyi.web.controller.personentry;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.domain.PersonnelEssentialinfor;

import com.ruoyi.demand.service.IMarCustomerprojectpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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



}
