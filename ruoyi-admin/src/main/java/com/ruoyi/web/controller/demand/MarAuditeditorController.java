package com.ruoyi.web.controller.demand;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.MarAuditeditor;
import com.ruoyi.demand.domain.MarAuditedtorDemand;
import com.ruoyi.demand.domain.MarTopic;
import com.ruoyi.demand.service.IMarAuditeditorService;
import com.ruoyi.demand.service.IMarAuditedtorDemandService;
import com.ruoyi.demand.service.IMarTopicService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Description: 审核过后面试题（商务端）
 * @Author ych
 * @Date 2021/3/2
 */
@RestController
@RequestMapping("/demand/auditeditor")
public class MarAuditeditorController extends BaseController {
    @Autowired
    private IMarAuditeditorService marAuditeditorService;
    @Autowired
    private IMarTopicService marTopicService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IMarAuditedtorDemandService marAuditedtorDemandService;

    // 待审核的面试题
    @PreAuthorize("@ss.hasPermi('demand:auditeditor:list')")
    @GetMapping("/extopiclist")
    public TableDataInfo extopiclist(MarTopic topic)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        topic.setTopicUsername(loginUser.getUsername());
        List<MarTopic> list = marTopicService.selectextopicList(topic);
        return getDataTable(list);
    }
    // 审核发布的面试题审核
    @PutMapping("/edittopic")
    public AjaxResult edit(@RequestBody MarTopic marTopic)
    {
        return toAjax(marTopicService.updateMarTopic(marTopic));
    }
    /**
     * 整理过后面试题
     */
    @PreAuthorize("@ss.hasPermi('demand:auditeditor:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarAuditeditor marAuditeditorm)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        marAuditeditorm.setTopicUsername(loginUser.getUsername());
        List<MarAuditeditor> list = marAuditeditorService.selectAuditeditorList(marAuditeditorm);
        return getDataTable(list);
    }
    /**
     * 新增审核编辑后的面试题
     */
    @Log(title = "审核编辑后的面试题", businessType = BusinessType.INSERT)
    @PostMapping("/addauditeditor")
    public AjaxResult add(String zm, MultipartFile multipartFile)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(marAuditeditorService.insertMarAuditeditor(zm,multipartFile,loginUser));
    }

    /**
     * 查询该公司下所有的需求
     */
    @PostMapping("/querydemand")
    public AjaxResult selQuerydemand(String corpCode)
    {
        List<Map> list=marAuditeditorService.selQuerydemand(corpCode);
        return AjaxResult.success(list);
    }

    /**
     * 查询已经绑定过的需求
     */
    @PostMapping("/afterbinding")
    public AjaxResult selafterbinding(Integer auditedId)
    {
        List<MarAuditedtorDemand> list=marAuditedtorDemandService.selafterbinding(auditedId);
        return AjaxResult.success(list);
    }
    /**
     * 面试题绑定需求
     */
    @PostMapping("/addaudbindingdemand")
    public AjaxResult addaudbindingdemand(String zm)
    {
        return  marAuditedtorDemandService.addaudbindingdemand(zm);
    }




















}
