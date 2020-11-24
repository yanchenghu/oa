package com.ruoyi.resume.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerCuscontactMapper;
import com.ruoyi.resume.domain.PerCuscontact;
import com.ruoyi.resume.service.IPerCuscontactService;

/**
 * 简历跟踪Service业务层处理
 * 
 * @author ych
 * @date 2020-11-10
 */
@Service
public class PerCuscontactServiceImpl implements IPerCuscontactService 
{
    @Autowired
    private PerCuscontactMapper perCuscontactMapper;

    /**
     * 查询简历跟踪
     * 
     * @param contacDatecode 简历跟踪ID
     * @return 简历跟踪
     */
    @Override
    public PerCuscontact selectPerCuscontactById(String contacDatecode)
    {
        return perCuscontactMapper.selectPerCuscontactById(contacDatecode);
    }

    /**
     * 查询简历跟踪列表
     * 
     * @param perCuscontact 简历跟踪
     * @return 简历跟踪
     */
    @Override
    public List<PerCuscontact> selectPerCuscontactList(PerCuscontact perCuscontact)
    {
        return perCuscontactMapper.selectPerCuscontactList(perCuscontact);
    }

    /**
     * 新增简历跟踪
     * 
     * @param perCuscontact 简历跟踪
     * @return 结果
     */
    @Override
    public int insertPerCuscontact(PerCuscontact perCuscontact)
    {
        return perCuscontactMapper.insertPerCuscontact(perCuscontact);
    }

    /**
     * 修改简历跟踪
     * 
     * @param perCuscontact 简历跟踪
     * @return 结果
     */
    @Override
    public int updatePerCuscontact(PerCuscontact perCuscontact)
    {
        return perCuscontactMapper.updatePerCuscontact(perCuscontact);
    }

    /**
     * 批量删除简历跟踪
     * 
     * @param contacDatecodes 需要删除的简历跟踪ID
     * @return 结果
     */
    @Override
    public int deletePerCuscontactByIds(String[] contacDatecodes)
    {
        return perCuscontactMapper.deletePerCuscontactByIds(contacDatecodes);
    }

    /**
     * 删除简历跟踪信息
     * 
     * @param contacDatecode 简历跟踪ID
     * @return 结果
     */
    @Override
    public int deletePerCuscontactById(String contacDatecode)
    {
        return perCuscontactMapper.deletePerCuscontactById(contacDatecode);
    }
}
