package com.ruoyi.resume.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerWorkMapper;
import com.ruoyi.resume.domain.PerWork;
import com.ruoyi.resume.service.IPerWorkService;

/**
 * 简历录入工作经历Service业务层处理
 * 
 * @author ych
 * @date 2020-10-30
 */
@Service
public class PerWorkServiceImpl implements IPerWorkService 
{
    @Autowired
    private PerWorkMapper perWorkMapper;

    /**
     * 查询简历录入工作经历
     * 
     * @param id 简历录入工作经历ID
     * @return 简历录入工作经历
     */
    @Override
    public PerWork selectPerWorkById(Long id)
    {
        return perWorkMapper.selectPerWorkById(id);
    }

    /**
     * 查询简历录入工作经历列表
     * 
     * @param perWork 简历录入工作经历
     * @return 简历录入工作经历
     */
    @Override
    public List<PerWork> selectPerWorkList(PerWork perWork)
    {
        return perWorkMapper.selectPerWorkList(perWork);
    }

    /**
     * 新增简历录入工作经历
     * 
     * @param perWork 简历录入工作经历
     * @return 结果
     */
    @Override
    public int insertPerWork(PerWork perWork)
    {
        return perWorkMapper.insertPerWork(perWork);
    }

    /**
     * 修改简历录入工作经历
     * 
     * @param perWork 简历录入工作经历
     * @return 结果
     */
    @Override
    public int updatePerWork(PerWork perWork)
    {
        return perWorkMapper.updatePerWork(perWork);
    }

    /**
     * 批量删除简历录入工作经历
     * 
     * @param ids 需要删除的简历录入工作经历ID
     * @return 结果
     */
    @Override
    public int deletePerWorkByIds(Integer[] ids)
    {
        return perWorkMapper.deletePerWorkByIds(ids);
    }

    /**
     * 删除简历录入工作经历信息
     * 
     * @param id 简历录入工作经历ID
     * @return 结果
     */
    @Override
    public int deletePerWorkById(Integer id)
    {
        return perWorkMapper.deletePerWorkById(id);
    }
}
