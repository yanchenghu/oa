package com.ruoyi.web.controller.resume;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 查询简历列表
     */
    @PreAuthorize("@ss.hasPermi('resume:peopost:list')")
    @GetMapping("/list")
    public TableDataInfo list(PerCustomerinfo perCustomerinfo)
    {
        startPage();
        List<PerCustomerinfo> list = perCustomerinfoService.selectPerCustomerinfoList(perCustomerinfo);
        return getDataTable(list);
    }
}
