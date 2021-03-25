package com.ruoyi.web.controller.customer;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.customer.service.YxrobService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
     * 线索公海列表
     */
    @PreAuthorize("@ss.hasPermi('customer:highseas:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yxdemand yxdemand)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
//        if(loginUser.getUser().getDeptId()==104){//商务
        List<Yxdemand> list = yxdemandService.selectByDepartBus(yxdemand,loginUser);
        return getDataTable(list);
//        }else{
//            List<Yxdemand> list = yxdemandService.selectByDepartMark(yxdemand,loginUser);
//            return getDataTable(list);
//        }
    }

}
