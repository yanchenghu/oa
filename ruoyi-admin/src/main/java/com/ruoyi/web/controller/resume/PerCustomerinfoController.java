package com.ruoyi.web.controller.resume;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 简历Controller
 *
 * @author yan
 * @date 2020-10-28
 */
@Api("简历管理")
@RestController
@RequestMapping("/resume/record")
public class PerCustomerinfoController extends BaseController
{
    @Autowired
    private IPerCustomerinfoService perCustomerinfoService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询简历列表
     */
    @PreAuthorize("@ss.hasPermi('resume:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PerCustomerinfo perCustomerinfo)
    {
        startPage();
        List<PerCustomerinfo> list = perCustomerinfoService.selectPerCustomerinfoList(perCustomerinfo);
        return getDataTable(list);
    }

        /**
         * 根据姓名电话查询简历列表
         */
        @GetMapping("/listbynatel")
        public TableDataInfo selectlistbyNametel(PerCustomerinfo perCustomerinfo)
        {
            startPage();
            List<PerCustomerinfo> list = perCustomerinfoService.selectlistbyNametel(perCustomerinfo);
            return getDataTable(list);
        }

    /**
     * 获取简历详细信息
     */
    @PreAuthorize("@ss.hasPermi('resume:record:query')")
    @GetMapping(value = "/{customerCode}")
    public AjaxResult getInfo(@PathVariable("customerCode") String customerCode)
    {
        return  perCustomerinfoService.selectPerCustomerinfoById(customerCode);
    }

    /**
     * 新增简历
     */
    @PreAuthorize("@ss.hasPermi('resume:record:add')")
    @Log(title = "简历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PerCustomerinfo perCustomerinfo)
    {
        return toAjax(perCustomerinfoService.insertPerCustomerinfo(perCustomerinfo));
    }

    /**
     * 修改简历
     */
    @PreAuthorize("@ss.hasPermi('resume:record:edit')")
    @Log(title = "简历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PerCustomerinfo perCustomerinfo)
    {
        return toAjax(perCustomerinfoService.updatePerCustomerinfo(perCustomerinfo));
    }



    /**
     * 简历解析详细信息
     */
    @PostMapping(value = "/analysisResume")
    public AjaxResult analysisResume( @RequestParam("upfile") MultipartFile file, Integer resume_direction)  {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        try {
            return perCustomerinfoService.goAnalysisResume(file,resume_direction,loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("简历解析失败");
        }
    }


    /**
     * 文件上传的接口
     */
    @PostMapping(value = "/fileUpload")
    public AjaxResult fileUpload( @RequestParam("upfile") MultipartFile file)  {
     return perCustomerinfoService.fileUpload(file);
    }







}
