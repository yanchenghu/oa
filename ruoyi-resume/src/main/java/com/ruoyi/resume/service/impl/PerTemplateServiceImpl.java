package com.ruoyi.resume.service.impl;

import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.resume.domain.PerTemplate;
import com.ruoyi.resume.mapper.PerTemplateMapper;
import com.ruoyi.resume.service.IPerTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 简历模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-04
 */
@Service
public class PerTemplateServiceImpl implements IPerTemplateService
{
    @Autowired
    private PerTemplateMapper perTemplateMapper;

    /**
     * 查询简历模板
     * 
     * @param templateId 简历模板ID
     * @return 简历模板
     */
    @Override
    public PerTemplate selectPerTemplateById(String templateId)
    {
        return perTemplateMapper.selectPerTemplateById(templateId);
    }

    /**
     * 查询简历模板列表
     * 
     * @param perTemplate 简历模板
     * @return 简历模板
     */
    @Override
    public List<PerTemplate> selectPerTemplateList(PerTemplate perTemplate)
    {
        return perTemplateMapper.selectPerTemplateList(perTemplate);
    }

    /**
     * 新增简历模板
     * 
     * @param perTemplate 简历模板
     * @return 结果
     */
    @Override
    public int insertPerTemplate(PerTemplate perTemplate)
    {
        perTemplate.setTemplateId(String.valueOf(UUID.randomUUID()));
        return perTemplateMapper.insertPerTemplate(perTemplate);
    }

    /**
     * 修改简历模板
     * 
     * @param perTemplate 简历模板
     * @return 结果
     */
    @Override
    public int updatePerTemplate(PerTemplate perTemplate)
    {
        return perTemplateMapper.updatePerTemplate(perTemplate);
    }

    /**
     * 批量删除简历模板
     * 
     * @param templateIds 需要删除的简历模板ID
     * @return 结果
     */
    @Override
    public int deletePerTemplateByIds(String[] templateIds)
    {
        return perTemplateMapper.deletePerTemplateByIds(templateIds);
    }

    /**
     * 删除简历模板信息
     * 
     * @param templateId 简历模板ID
     * @return 结果
     */
    @Override
    public int deletePerTemplateById(String templateId)
    {
        return perTemplateMapper.deletePerTemplateById(templateId);
    }


}
