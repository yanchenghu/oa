package com.ruoyi.web.controller.demand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.ServletUtils;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.resume.downloadFile;
import com.ruoyi.customer.domain.MarCompanyContacts;
import com.ruoyi.demand.domain.MarDemandRequirement;
import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.domain.MarTopic;
import com.ruoyi.demand.service.IMarTopicService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private IMarTopicService marTopicService;

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
     * 复制需求
     */
    @GetMapping(value = "copydemand/{demandId}")
    public AjaxResult copydemand(@PathVariable("demandId") String demandId)
    {
        return AjaxResult.success(marDemandService.copydemand(demandId));
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
    /**
     * 发布需求面试题
     */
    @PreAuthorize("@ss.hasPermi('demand:follow:postInterview')")
    @Log(title = "需求绑定面试题", businessType = BusinessType.INSERT)
    @PostMapping(value = "/postInterview")
    public AjaxResult postInterview(String zm,MultipartFile multipartFile)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marTopicService.postInterview(zm, multipartFile,loginUser);
    }

    @Log(title = "查询是否发布过需求绑定面试题", businessType = BusinessType.INSERT)
    @PostMapping(value = "/ispostInterview")
    public String ispostInterview(MarTopic marTopic)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        marTopic.setTopicUsername(loginUser.getUsername());
        return marTopicService.ispostInterview(marTopic);
    }




    /**
     * 获取绑定需求上限制
     */
    @PostMapping(value = "/bindingUpper")
    public AjaxResult bindingUpper( String demandId)
    {
        return marDemandService.bindingUpper(demandId);
    }
    /**
     *批量简历下载,生成zip
     * @auth ych
     */
    @GetMapping(value = "/batchDownload")
    public void batchDownloads(String custels, HttpServletResponse response) throws IOException {
        String[] tableNames = Convert.toStrArray(custels);
        List<File> userList = new ArrayList<>();
        for(String file :tableNames){
            String localPath = RuoYiConfig.getProfile();
            String downloadPath = localPath + StringUtils.substringAfter(file, Constants.RESOURCE_PREFIX);
            File fi=new File(downloadPath);
            if(fi.exists()){
                userList.add(fi);
            }
        }
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=resume.zip");
        downloadFile.toZip(userList,  response.getOutputStream());
    }









    /**
     * 新增岗位的需求
     */

    @PostMapping("/addemandAnalysi")
    public AjaxResult addemandAnalysi(@RequestBody MarDemandRequirement marDemandRequirement)
  {
        return toAjax(marDemandService.insertdemandAnalysis(marDemandRequirement)) ;
    }

    /**
     * 删除岗位的需求
     */
    @GetMapping(value = "/deldemandAnalysis/{id}")
    public AjaxResult deldemandAnalysis(@PathVariable("id") Long id)
    {
        return toAjax(marDemandService.deleteMarDemandRequirementById(id)) ;
    }

    /**
     * 根据需求id获取岗位要求(和当前人近10天的简历通过率)
     */
    @GetMapping(value = "/acquisitionList/{demandId}")
    public AjaxResult demandIDAcquisition(@PathVariable("demandId") String demandId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return marDemandService.demandIDAcquisition(demandId,loginUser);
    }

    /**
     * 修改需求岗位要求
     */

    @PostMapping(value = "/editMarDemandRequi")
    public AjaxResult edit(@RequestBody MarDemandRequirement marDemandRequirement)
    {
        return toAjax(marDemandService.updateMarDemandRequirement(marDemandRequirement));
    }




}
