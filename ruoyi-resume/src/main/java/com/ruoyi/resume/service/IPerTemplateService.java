package com.ruoyi.resume.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.resume.domain.PerTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPerTemplateService {
    /**
     * 查询简历模板
     *
     * @param templateId 简历模板ID
     * @return 简历模板
     */
    public PerTemplate selectPerTemplateById(String templateId);

    /**
     * 查询简历模板列表
     *
     * @param perTemplate 简历模板
     * @return 简历模板集合
     */
    public List<PerTemplate> selectPerTemplateList(PerTemplate perTemplate);

    /**
     * 新增简历模板
     *
     * @param perTemplate 简历模板
     * @return 结果
     */
    public AjaxResult insertPerTemplate(MultipartFile file, String templateName,
                                 String company,String technicalDirection,Integer workingYears,String name) throws IOException;

    /**
     * 修改简历模板
     *
     * @param perTemplate 简历模板
     * @return 结果
     */
    public int updatePerTemplate(PerTemplate perTemplate);

    /**
     * 批量删除简历模板
     *
     * @param templateIds 需要删除的简历模板ID
     * @return 结果
     */
    public int deletePerTemplateByIds(String[] templateIds);

    /**
     * 删除简历模板信息
     *
     * @param templateId 简历模板ID
     * @return 结果
     */
    public int deletePerTemplateById(String templateId);

    Map<String, String> previewCode(String templateId);


}
