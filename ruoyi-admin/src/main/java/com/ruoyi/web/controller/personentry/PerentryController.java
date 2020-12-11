package com.ruoyi.web.controller.personentry;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.MarCustomePerinfo;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.service.IMarCustomerprojectpayService;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCuscontactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 人员出项配
     */









}
