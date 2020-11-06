package com.ruoyi.resume.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerEducationMapper;
import com.ruoyi.resume.domain.PerEducation;
import com.ruoyi.resume.service.IPerEducationService;

/**
 * 简历教育经验Service业务层处理
 * 
 * @author ych
 * @date 2020-10-30
 */
@Service
public class PerEducationServiceImpl implements IPerEducationService 
{
    @Autowired
    private PerEducationMapper perEducationMapper;

    /**
     * 查询简历教育经验
     * 
     * @param id 简历教育经验ID
     * @return 简历教育经验
     */
    @Override
    public PerEducation selectPerEducationById(Long id)
    {
        return perEducationMapper.selectPerEducationById(id);
    }

    /**
     * 查询简历教育经验列表
     * 
     * @param perEducation 简历教育经验
     * @return 简历教育经验
     */
    @Override
    public List<PerEducation> selectPerEducationList(PerEducation perEducation)
    {
        return perEducationMapper.selectPerEducationList(perEducation);
    }

    /**
     * 新增简历教育经验
     * 
     * @param perEducation 简历教育经验
     * @return 结果
     */
    @Override
    public int insertPerEducation(PerEducation perEducation)
    {
        return perEducationMapper.insertPerEducation(perEducation);
    }

    /**
     * 修改简历教育经验
     * 
     * @param perEducation 简历教育经验
     * @return 结果
     */
    @Override
    public int updatePerEducation(PerEducation perEducation)
    {
        return perEducationMapper.updatePerEducation(perEducation);
    }

    /**
     * 批量删除简历教育经验
     * 
     * @param ids 需要删除的简历教育经验ID
     * @return 结果
     */
    @Override
    public int deletePerEducationByIds(Long[] ids)
    {
        return perEducationMapper.deletePerEducationByIds(ids);
    }

    /**
     * 删除简历教育经验信息
     * 
     * @param id 简历教育经验ID
     * @return 结果
     */
    @Override
    public int deletePerEducationById(Long id)
    {
        return perEducationMapper.deletePerEducationById(id);
    }
}
