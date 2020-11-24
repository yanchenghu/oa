package com.ruoyi.web.controller.customer;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 线索公海
 *
 * @author ych
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/customer/highseas")
public class HighseasController extends BaseController {
    @Autowired
    private IYxdemandService yxdemandService;

    @Autowired
    private TokenService tokenService;


    /**
     * 商务意向客户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:highseas:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yxdemand yxdemand)throws Exception
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        List<Yxdemand> list = yxdemandService.selectBusinessList(yxdemand,loginUser);
        return getDataTable(list);
    }





}
