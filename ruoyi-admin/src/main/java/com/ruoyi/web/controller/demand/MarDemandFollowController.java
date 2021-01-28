package com.ruoyi.web.controller.demand;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 需求跟踪Controller
 * 
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/demand/follow")
public class MarDemandFollowController extends BaseController
{
    @Autowired
    private IMarDemandService marDemandService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询我的需求列表
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarDemand marDemand)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MarDemand> list = marDemandService.selectMarDemandList(marDemand,loginUser);
        return getDataTable(list);
    }


    /**
     * 导出需求列表
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:export')")
    @Log(title = "需求", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MarDemand marDemand)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MarDemand> list = marDemandService.selectMarDemandList(marDemand,loginUser);
        ExcelUtil<MarDemand> util = new ExcelUtil<MarDemand>(MarDemand.class);
        return util.exportExcel(list, "follow");
    }

    /**
     * 获取需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:query')")
    @GetMapping(value = "/{demandId}")
    public AjaxResult getInfo(@PathVariable("demandId") String demandId)
    {
        return AjaxResult.success(marDemandService.selectMarDemandById(demandId));
    }

    /**
     * 新增需求
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:add')")
    @Log(title = "需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(String zm,MultipartFile demandPic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.insertMarDemand(zm,demandPic,loginUser);
    }

    /**
     * 修改需求
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:edit')")
    @Log(title = "需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(String zm,MultipartFile demandPic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        return marDemandService.updateMarDemand(zm,demandPic,loginUser);
    }

    /**
     * 删除需求
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:remove')")
    @Log(title = "需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(marDemandService.deleteMarDemandByIds(ids));
    }


    /**
     * 需求查重
     */
    @PostMapping(value = "/demandDuplicate")
    public int demandDuplicate(String projectName,String demandId)
    {
        return marDemandService.selDemandDuplicate(projectName,demandId);
    }

    /**
     * 需求关闭
     */
    @PostMapping(value = "/demandClosure")
    public AjaxResult demandClosure(String demandId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.demandClosure(demandId,loginUser);
    }
    /**
     * 需求开启
     */
    @PostMapping(value = "/demandOpen")
    public AjaxResult demandOpen(String demandId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.demandOpen(demandId,loginUser);
    }






}
