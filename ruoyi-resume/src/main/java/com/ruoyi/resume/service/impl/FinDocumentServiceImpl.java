package com.ruoyi.resume.service.impl;

import java.io.*;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.resume.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.FinDocumentMapper;
import com.ruoyi.resume.domain.FinDocument;
import com.ruoyi.resume.service.IFinDocumentService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学习园地Service业务层处理
 * 
 * @author ych
 * @date 2021-07-08
 */
@Service
public class FinDocumentServiceImpl implements IFinDocumentService 
{
    @Autowired
    private FinDocumentMapper finDocumentMapper;

    /**
     * 查询学习园地
     * 
     * @param id 学习园地ID
     * @return 学习园地
     */
    @Override
    public FinDocument selectFinDocumentById(Long id)
    {
        return finDocumentMapper.selectFinDocumentById(id);
    }

    /**
     * 查询学习园地列表
     * 
     * @param finDocument 学习园地
     * @return 学习园地
     */
    @Override
    public List<FinDocument> selectFinDocumentList(FinDocument finDocument)
    {
        return finDocumentMapper.selectFinDocumentList(finDocument);
    }

    /**
     * 新增学习园地
     * 
     * @param finDocument,file学习园地
     * @return 结果
     */
    @Override
    public AjaxResult insertFinDocument(FinDocument finDocument, MultipartFile documentPath)
    {
        //  文件上传
        String fileName = documentPath.getOriginalFilename();
        String result = null;
        try {
            result = QiniuUtil.uoloapQiniu(documentPath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(result)){
            return  AjaxResult.error("上传失败");
        }
        finDocument.setDocumentPath(result);
        finDocument.setAddTime(new Date());
        finDocumentMapper.insertFinDocument(finDocument);
        return AjaxResult.success();
    }

    /**
     * 修改学习园地
     * 
     * @param finDocument 学习园地
     * @return 结果
     */
    @Override
    public int updateFinDocument(FinDocument finDocument)
    {
        return finDocumentMapper.updateFinDocument(finDocument);
    }

    /**
     * 批量删除学习园地
     * 
     * @param ids 需要删除的学习园地ID
     * @return 结果
     */
    @Override
    public int deleteFinDocumentByIds(Long[] ids)
    {
        return finDocumentMapper.deleteFinDocumentByIds(ids);
    }

    /**
     * 删除学习园地信息
     * 
     * @param id 学习园地ID
     * @return 结果
     */
    @Override
    public int deleteFinDocumentById(Long id)
    {
        return finDocumentMapper.deleteFinDocumentById(id);
    }
}
