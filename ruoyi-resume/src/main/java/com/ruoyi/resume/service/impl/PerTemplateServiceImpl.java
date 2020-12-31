package com.ruoyi.resume.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.resume.domain.PerTemplate;
import com.ruoyi.resume.mapper.PerTemplateMapper;
import com.ruoyi.resume.service.IPerTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


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

    @Value("${ruoyi.profile}")
    private String profile;// (路径)
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
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertPerTemplate(MultipartFile file, String templateName,
                                        String templateNominate) throws IOException {

        String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        PerTemplate perTemplate = new PerTemplate();
        perTemplate.setTemplateId(String.valueOf(UUID.randomUUID()));
        perTemplate.setTemplateName(templateName);
        perTemplate.setTemplateNominate(templateNominate);
        perTemplate.setTemplateFile(avatar);
        perTemplateMapper.insertPerTemplate(perTemplate);
        return AjaxResult.success();
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

    @Override
    public Map<String, String> previewCode(String templateId) {
        return (Map<String, String>) perTemplateMapper.selectPerTemplateById(templateId);
    }



}
