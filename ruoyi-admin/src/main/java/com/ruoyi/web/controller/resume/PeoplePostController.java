package com.ruoyi.web.controller.resume;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 人岗匹配Controller
 *
 * @author yan
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/resume/peopost")
public class PeoplePostController extends BaseController {
    @Autowired
    private IPerCustomerinfoService perCustomerinfoService;
    @Autowired
    private TokenService tokenService;


    /**
     * 智能人岗匹配简历信息列表
     */
    @PreAuthorize("@ss.hasPermi('resume:peopost:list')")
    @GetMapping("/list")
    public TableDataInfo peopostlist( PerCustomerinfo perCustomerinfo)
    {
        startPage();
        List<Map> list = perCustomerinfoService.selectPeopostlist(perCustomerinfo);
        return getDataTable(list);
    }
}
