package com.ruoyi.resume.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.resume.domain.PerCustomerinfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 简历Service接口
 *
 * @author yan
 * @date 2020-10-28
 */
public interface IPerCustomerinfoService
{
    /**
     * 查询简历
     *
     * @param customerCode 简历ID
     * @return 简历
     */
    public PerCustomerinfo selectPerCustomerinfoById(String customerCode);

    /**
     * 查询简历列表
     *
     * @param perCustomerinfo 简历
     * @return 简历集合
     */
    public List<PerCustomerinfo> selectPerCustomerinfoList(PerCustomerinfo perCustomerinfo);

    /**
     * 新增简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    public int insertPerCustomerinfo(PerCustomerinfo perCustomerinfo);

    /**
     * 修改简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    public int updatePerCustomerinfo(PerCustomerinfo perCustomerinfo);

    /**
     * 批量删除简历
     *
     * @param customerCodes 需要删除的简历ID
     * @return 结果
     */
    public int deletePerCustomerinfoByIds(String[] customerCodes);

    /**
     * 删除简历信息
     *
     * @param customerCode 简历ID
     * @return 结果
     */
    public int deletePerCustomerinfoById(String customerCode);
    /**
     * 解析简历
     *
     * @param
     * @return 结果
     */
    AjaxResult goAnalysisResume(MultipartFile file, Integer resumeDirection1, LoginUser loginUser) throws Exception;
}
