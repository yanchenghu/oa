package com.ruoyi.web.controller.demand;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 需求绑定Controller
 *
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/demand/binding")
public class MarDemandBindingController extends BaseController {

    @Autowired
    private IMarDemandService marDemandService;
    @Autowired
    private TokenService tokenService;
    /**
     * 需求绑定表的查询
     */
    @PreAuthorize("@ss.hasPermi('demand:binding:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarDemand marDemand)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MarDemand> list = marDemandService.selectMarDemandbindingList(marDemand,loginUser);
        return getDataTable(list);
    }

}
