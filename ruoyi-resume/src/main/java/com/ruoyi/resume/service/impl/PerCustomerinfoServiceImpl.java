package com.ruoyi.resume.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.resume.ResumeParserUtil;
import com.ruoyi.common.utils.resume.MultipartFileToFile;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerCustomerinfoMapper;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 简历Service业务层处理
 *
 * @author yan
 * @date 2020-10-28
 */
@Service
public class PerCustomerinfoServiceImpl implements IPerCustomerinfoService
{
    @Autowired
    private PerCustomerinfoMapper perCustomerinfoMapper;

    /**
     * 查询简历
     *
     * @param customerCode 简历ID
     * @return 简历
     */
    @Override
    public PerCustomerinfo selectPerCustomerinfoById(String customerCode)
    {
        return perCustomerinfoMapper.selectPerCustomerinfoById(customerCode);
    }

    /**
     * 查询简历列表
     *
     * @param perCustomerinfo 简历
     * @return 简历
     */
    @Override
    public List<PerCustomerinfo> selectPerCustomerinfoList(PerCustomerinfo perCustomerinfo)
    {
        return perCustomerinfoMapper.selectPerCustomerinfoList(perCustomerinfo);
    }

    /**
     * 新增简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    @Override
    public int insertPerCustomerinfo(PerCustomerinfo perCustomerinfo)
    {
        return perCustomerinfoMapper.insertPerCustomerinfo(perCustomerinfo);
    }

    /**
     * 修改简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    @Override
    public int updatePerCustomerinfo(PerCustomerinfo perCustomerinfo)
    {
        perCustomerinfo.setUpdateTime(DateUtils.getNowDate());
        return perCustomerinfoMapper.updatePerCustomerinfo(perCustomerinfo);
    }

    /**
     * 批量删除简历
     *
     * @param customerCodes 需要删除的简历ID
     * @return 结果
     */
    @Override
    public int deletePerCustomerinfoByIds(String[] customerCodes)
    {
        return perCustomerinfoMapper.deletePerCustomerinfoByIds(customerCodes);
    }

    /**
     * 删除简历信息
     *
     * @param customerCode 简历ID
     * @return 结果
     */
    @Override
    public int deletePerCustomerinfoById(String customerCode)
    {
        return perCustomerinfoMapper.deletePerCustomerinfoById(customerCode);
    }

    @Override
    @Transactional
    public AjaxResult goAnalysisResume(MultipartFile  file, Integer resumeDirection1, LoginUser loginUser) throws Exception {
        if(file == null){
            return AjaxResult.error("上传失败，文件为空");
        }

         File f =MultipartFileToFile.multipartFileToFile(file);

        JSONObject analyticalResults ,contact_info=new JSONObject();
        PerCustomerinfo perCustomerinfo=new PerCustomerinfo();
        try {
             analyticalResults =  ResumeParserUtil.resumeParser(ResumeParserUtil.URL,f);
             contact_info = analyticalResults.getJSONObject("parsing_result").getJSONObject("contact_info");
             String phone_number = contact_info.getString("phone_number");
             if(phone_number.equals("")){
                return AjaxResult.error("该简历无联系方式，请查证");
             }
            perCustomerinfo.setCustomerTel(phone_number);

        } catch (Exception e) {

            e.printStackTrace();
            return AjaxResult.error("简历解析失败");
        }



        return null;
    }




}