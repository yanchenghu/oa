package com.ruoyi.web.controller.resume;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.resume.domain.PerEducation;
import com.ruoyi.resume.domain.PerProject;
import com.ruoyi.resume.domain.PerWork;
import com.ruoyi.resume.service.IPerEducationService;
import com.ruoyi.resume.service.IPerProjectService;
import com.ruoyi.resume.service.IPerWorkService;
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
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IPerEducationService perEducationService;
    @Autowired
    private IPerProjectService perProjectService;
    @Autowired
    private IPerWorkService perWorkService;
//    /**
//     * 查询简历列表
//     */
//    @PreAuthorize("@ss.hasPermi('resume:record:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(PerCustomerinfo perCustomerinfo)
//    {
//        startPage();
//        List<PerCustomerinfo> list = perCustomerinfoService.selectPerCustomerinfoList(perCustomerinfo);
//        return getDataTable(list);
//    }

    /**
     * 修改简历基本信息
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
    /**
     * 根据姓名电话查询简历列表
     */
    @GetMapping("/listbynatel")
    public TableDataInfo selectlistbyNametel(PerCustomerinfo perCustomerinfo)
    {

        List<PerCustomerinfo> list = perCustomerinfoService.selectlistbyNametel(perCustomerinfo);
        return getDataTable(list);
    }
    /**
     * 获取简历详细信息
     */
    @PreAuthorize("@ss.hasPermi('resume:record:query')")
    @PostMapping(value = "/query")
    public AjaxResult getInfo(String customerCode)
    {
        return  perCustomerinfoService.selectPerCustomerinfoById(customerCode);
    }
    /**
     * 抢占简历
     */
    @PostMapping(value = "/rob")
    public AjaxResult robCustomeInfo(@RequestParam("customerCode")String customerCode)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String captcha = redisCache.getCacheObject(customerCode);
        if(null == captcha) {
            redisCache.setCacheObject(customerCode, customerCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        }else{
            return AjaxResult.error("当前人正在被抢占");
        }
        return  perCustomerinfoService.robCustomeInfo(customerCode,loginUser);
    }


    /**
     * 简历释放
     */
    @PostMapping(value = "/release")
    public AjaxResult releaseCustomeInfo(@RequestParam("customerCode")String customerCode)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return  perCustomerinfoService.releaseCustomeInfo(customerCode,loginUser);
    }
    /**
     * 简历更新（工作经验、工作信息、学历信息）
     */
    @PostMapping(value = "/resumeUpdate")
    public AjaxResult resumeUpdate(String zm,MultipartFile file)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return  perCustomerinfoService.resumeUpdate(zm,file,loginUser);
    }

    /**
     * 简历添加（工作经验、工作信息、学历信息）
     */
    @PostMapping(value = "/resumeInsert")
    public AjaxResult resumeInsert(String zm,MultipartFile file)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return  perCustomerinfoService.resumeInsert(zm,file,loginUser);
    }


    /**
     * 工作经验删除
     */
    @GetMapping(value = "/workDel")
    public AjaxResult workDel(@RequestParam("id")Integer id)
    {
        return toAjax(perWorkService.deletePerWorkById(id));
    }
    /**
     * 工作信息删除
     */
    @GetMapping(value = "/projdeDel")
    public AjaxResult projdeDel(@RequestParam("custproId")Integer custproId)
    {
        return toAjax(perProjectService.deletePerProjectById(custproId));
    }
    /**
     * 学历信息
     */
    @GetMapping(value = "/educaDel")
    public AjaxResult educaDel(@RequestParam("id")Integer id)
    {
        return toAjax(perEducationService.deletePerEducationById(id));

    }




}
