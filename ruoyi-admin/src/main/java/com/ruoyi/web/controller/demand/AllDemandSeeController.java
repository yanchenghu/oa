package com.ruoyi.web.controller.demand;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.demand.service.IMarDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 全部需求查看（此权限只给商务和老板，普通员工无法看到）
 * @Author ych
 * @Date 2021/4/20
 */
@RestController
@RequestMapping("/demand/alldemand")
public class AllDemandSeeController extends BaseController {
    @Autowired
    private IMarDemandService marDemandService;





}
