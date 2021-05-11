package com.ruoyi.web.controller.demand;

import java.util.List;
import java.util.Map;

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
import com.ruoyi.demand.domain.MarWaitingentry;
import com.ruoyi.demand.service.IMarWaitingentryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * @Description: 待入项名单
 * @Author ych
 * @Date 2021/3/31
 */
@RestController
@RequestMapping("/demand/waitingentry")
public class MarWaitingEntryController extends BaseController {


    @Autowired
    private IMarWaitingentryService marWaitingentryService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询待入项列表
     */
    @PreAuthorize("@ss.hasPermi('demand:waitingentry:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarWaitingentry marWaitingentry)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String username = loginUser.getUsername();
        List<Map> list = marWaitingentryService.getMarWaitingentr(username);
        return getDataTable(list);
    }

}
