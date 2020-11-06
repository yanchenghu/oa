package com.ruoyi.resume.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerProjectMapper;
import com.ruoyi.resume.domain.PerProject;
import com.ruoyi.resume.service.IPerProjectService;

/**
 * 简历工作经验Service业务层处理
 * 
 * @author ych
 * @date 2020-11-06
 */
@Service
public class PerProjectServiceImpl implements IPerProjectService 
{
    @Autowired
    private PerProjectMapper perProjectMapper;

    /**
     * 查询简历工作经验
     * 
     * @param custproId 简历工作经验ID
     * @return 简历工作经验
     */
    @Override
    public PerProject selectPerProjectById(String custproId)
    {
        return perProjectMapper.selectPerProjectById(custproId);
    }

    /**
     * 查询简历工作经验列表
     * 
     * @param perProject 简历工作经验
     * @return 简历工作经验
     */
    @Override
    public List<PerProject> selectPerProjectList(PerProject perProject)
    {
        return perProjectMapper.selectPerProjectList(perProject);
    }

    /**
     * 新增简历工作经验
     * 
     * @param perProject 简历工作经验
     * @return 结果
     */
    @Override
    public int insertPerProject(PerProject perProject)
    {
        return perProjectMapper.insertPerProject(perProject);
    }

    /**
     * 修改简历工作经验
     * 
     * @param perProject 简历工作经验
     * @return 结果
     */
    @Override
    public int updatePerProject(PerProject perProject)
    {
        return perProjectMapper.updatePerProject(perProject);
    }

    /**
     * 批量删除简历工作经验
     * 
     * @param custproIds 需要删除的简历工作经验ID
     * @return 结果
     */
    @Override
    public int deletePerProjectByIds(String[] custproIds)
    {
        return perProjectMapper.deletePerProjectByIds(custproIds);
    }

    /**
     * 删除简历工作经验信息
     * 
     * @param custproId 简历工作经验ID
     * @return 结果
     */
    @Override
    public int deletePerProjectById(String custproId)
    {
        return perProjectMapper.deletePerProjectById(custproId);
    }
}
