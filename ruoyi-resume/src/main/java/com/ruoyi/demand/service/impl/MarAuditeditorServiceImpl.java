package com.ruoyi.demand.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarAuditeditorMapper;
import com.ruoyi.demand.domain.MarAuditeditor;
import com.ruoyi.demand.service.IMarAuditeditorService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 审核编辑后的面试题Service业务层处理
 * 
 * @author ych
 * @date 2021-03-02
 */
@Service
public class MarAuditeditorServiceImpl implements IMarAuditeditorService 
{
    @Autowired
    private MarAuditeditorMapper marAuditeditorMapper;


    @Override
    public List<MarAuditeditor> selectAuditeditorList(MarAuditeditor marAuditeditorm) {
        return marAuditeditorMapper.selectAuditeditorList(marAuditeditorm);
    }

    @Override
    public int insertMarAuditeditor(String zm, MultipartFile multipartFile,LoginUser loginUser) {
        String fsafsa="";
        try {
            fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MarAuditeditor marAuditeditor = JSON.parseObject(JSON.parseObject(zm).getString("marAuditeditor"), MarAuditeditor.class);
        marAuditeditor.setAuditedPath(fsafsa);
        marAuditeditor.setTopicUsername(loginUser.getUsername());
        marAuditeditor.setAddTime(new Date());
        return marAuditeditorMapper.insertMarAuditeditor(marAuditeditor);
    }

    @Override
    public List<Map> selQuerydemand(String corpCode) {
        return marAuditeditorMapper.selQuerydemand(corpCode);
    }
}
