package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.service.IGetAllMethodService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ych
 * @Description
 * @create 2021-06-17 16:57
 */
@RestController
@RequestMapping("/analysis/getallmethod")
public class GetAllMethodController extends BaseController {


    @Autowired
    private IGetAllMethodService getAllMethodService;


    /**
     * 获取查询所有在职的商务
     * 获取查询所有人事分组
     */
    @GetMapping(value ="/getPersonnelgroup")
    public AjaxResult getPersonnelgroup()
    {
        return AjaxResult.success(getAllMethodService.getPersonnelgroup());
    }


}
