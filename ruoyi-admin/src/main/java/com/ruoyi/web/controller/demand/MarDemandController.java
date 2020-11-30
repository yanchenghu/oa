package com.ruoyi.web.controller.demand;

import java.util.List;
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
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 需求Controller
 * 
 * @author ych
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/demand/follow")
public class MarDemandController extends BaseController
{
    @Autowired
    private IMarDemandService marDemandService;

    /**
     * 查询需求列表
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarDemand marDemand)
    {
        startPage();
        List<MarDemand> list = marDemandService.selectMarDemandList(marDemand);
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
        List<MarDemand> list = marDemandService.selectMarDemandList(marDemand);
        ExcelUtil<MarDemand> util = new ExcelUtil<MarDemand>(MarDemand.class);
        return util.exportExcel(list, "follow");
    }

    /**
     * 获取需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(marDemandService.selectMarDemandById(id));
    }

    /**
     * 新增需求
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:add')")
    @Log(title = "需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarDemand marDemand)
    {
        return toAjax(marDemandService.insertMarDemand(marDemand));
    }

    /**
     * 修改需求
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:edit')")
    @Log(title = "需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarDemand marDemand)
    {
        return toAjax(marDemandService.updateMarDemand(marDemand));
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
}
