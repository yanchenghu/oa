package com.ruoyi.resume.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.domain.PerEducation;
import com.ruoyi.resume.domain.PerProject;
import com.ruoyi.resume.domain.PerWork;
import org.springframework.web.multipart.MultipartFile;

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
    public AjaxResult selectPerCustomerinfoById(String customerCode);

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

    /**
     * 查询当前人是否抢占
     *
     * @param
     * @return 结果
     */
    List<PerCustomerinfo> selectlistbyNametel(PerCustomerinfo perCustomerinfo);

    /**
     * 文件上传
     *
     * @param file
     * @return 结果
     */
    AjaxResult fileUpload(MultipartFile file) ;

    /**
     *  抢占简历
     *
     * @param customerCode
     * @return 结果
     */
    AjaxResult robCustomeInfo(String customerCode,LoginUser loginUser);

    /**
     *  简历跟踪
     *
     * @param customerCode
     * @return 结果
     */
    AjaxResult followCustomeInfo(String customerCode, LoginUser loginUser);

    /**
     *  简历释放
     *
     * @param customerCode
     * @return 结果
     */
    AjaxResult releaseCustomeInfo(String customerCode, LoginUser loginUser);
    /**
     *  手动简历更新
     *
     * @param zm loginUser
     * @return 结果 AjaxResult
     */
    AjaxResult resumeUpdate(String zm, LoginUser loginUser);

    /**
     *  手动简历添加
     *
     * @param zm loginUser
     * @return 结果 AjaxResult
     */
    AjaxResult resumeInsert(String zm, LoginUser loginUser);
}
