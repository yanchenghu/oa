package com.ruoyi.resume.service;

import java.util.List;
import com.ruoyi.resume.domain.PerEducation;

/**
 * 简历教育经验Service接口
 * 
 * @author ych
 * @date 2020-10-30
 */
public interface IPerEducationService 
{
    /**
     * 查询简历教育经验
     * 
     * @param id 简历教育经验ID
     * @return 简历教育经验
     */
    public PerEducation selectPerEducationById(Long id);

    /**
     * 查询简历教育经验列表
     * 
     * @param perEducation 简历教育经验
     * @return 简历教育经验集合
     */
    public List<PerEducation> selectPerEducationList(PerEducation perEducation);

    /**
     * 新增简历教育经验
     * 
     * @param perEducation 简历教育经验
     * @return 结果
     */
    public int insertPerEducation(PerEducation perEducation);

    /**
     * 修改简历教育经验
     * 
     * @param perEducation 简历教育经验
     * @return 结果
     */
    public int updatePerEducation(PerEducation perEducation);

    /**
     * 批量删除简历教育经验
     * 
     * @param ids 需要删除的简历教育经验ID
     * @return 结果
     */
    public int deletePerEducationByIds(Long[] ids);

    /**
     * 删除简历教育经验信息
     * 
     * @param id 简历教育经验ID
     * @return 结果
     */
    public int deletePerEducationById(Long id);
}
