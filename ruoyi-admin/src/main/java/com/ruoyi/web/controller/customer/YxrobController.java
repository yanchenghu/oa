package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.customer.service.YxrobService;
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
import com.ruoyi.customer.domain.Yxrob;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抢占记录Controller
 * 
 * @author ruoyi
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/customer/yxrob")
public class YxrobController extends BaseController
{
    @Autowired
    private YxrobService yxrobService;

    /**
     * 查询抢占记录列表
     */
    @PreAuthorize("@ss.hasPermi('customer:yxrob:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yxrob yxrob)
    {
        startPage();
        List<Yxrob> list = yxrobService.selectYxrobList(yxrob);
        return getDataTable(list);
    }

    /**
     * 导出抢占记录列表
     */
    @PreAuthorize("@ss.hasPermi('customer:yxrob:export')")
    @Log(title = "抢占记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Yxrob yxrob)
    {
        List<Yxrob> list = yxrobService.selectYxrobList(yxrob);
        ExcelUtil<Yxrob> util = new ExcelUtil<Yxrob>(Yxrob.class);
        return util.exportExcel(list, "yxrob");
    }

    /**
     * 获取抢占记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:yxrob:query')")
    @GetMapping(value = "/{robId}")
    public AjaxResult getInfo(@PathVariable("robId") Integer robId)
    {
        return AjaxResult.success(yxrobService.selectYxrobById(robId));
    }

    /**
     * 新增抢占记录
     */
    @PreAuthorize("@ss.hasPermi('customer:yxrob:add')")
    @Log(title = "抢占记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Yxrob yxrob)
    {
        return toAjax(yxrobService.insertYxrob(yxrob));
    }

    /**
     * 修改抢占记录
     */
    @PreAuthorize("@ss.hasPermi('customer:yxrob:edit')")
    @Log(title = "抢占记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Yxrob yxrob)
    {
        return toAjax(yxrobService.updateYxrob(yxrob));
    }

    /**
     * 删除抢占记录
     */
    @PreAuthorize("@ss.hasPermi('customer:yxrob:remove')")
    @Log(title = "抢占记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{robIds}")
    public AjaxResult remove(@PathVariable Integer[] robIds)
    {
        return toAjax(yxrobService.deleteYxrobByIds(robIds));
    }
}
