package com.ruoyi.resume.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.resume.domain.FinDocument;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学习园地Service接口
 * 
 * @author ych
 * @date 2021-07-08
 */
public interface IFinDocumentService 
{
    /**
     * 查询学习园地
     * 
     * @param id 学习园地ID
     * @return 学习园地
     */
    public FinDocument selectFinDocumentById(Long id);

    /**
     * 查询学习园地列表
     * 
     * @param finDocument 学习园地
     * @return 学习园地集合
     */
    public List<FinDocument> selectFinDocumentList(FinDocument finDocument);

    /**
     * 新增学习园地
     * 
     * @param zm 学习园地
     * @return 结果
     */
    public AjaxResult insertFinDocument(FinDocument finDocument, MultipartFile documentPath);

    /**
     * 修改学习园地
     * 
     * @param finDocument 学习园地
     * @return 结果
     */
    public int updateFinDocument(FinDocument finDocument);

    /**
     * 批量删除学习园地
     * 
     * @param ids 需要删除的学习园地ID
     * @return 结果
     */
    public int deleteFinDocumentByIds(Long[] ids);

    /**
     * 删除学习园地信息
     * 
     * @param id 学习园地ID
     * @return 结果
     */
    public int deleteFinDocumentById(Long id);
}
