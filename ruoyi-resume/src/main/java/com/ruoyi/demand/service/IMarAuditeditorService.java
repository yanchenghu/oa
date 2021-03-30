package com.ruoyi.demand.service;

import java.util.List;
import java.util.Map;


import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.demand.domain.MarAuditeditor;
import org.springframework.web.multipart.MultipartFile;


/**
 * 审核编辑后的面试题Service接口
 * 
 * @author ych
 * @date 2021-03-02
 */
public interface IMarAuditeditorService 
{
    List<MarAuditeditor> selectAuditeditorList(MarAuditeditor marAuditeditorm);

    int insertMarAuditeditor(String zm, MultipartFile multipartFile, LoginUser loginUser);

    List<Map> selQuerydemand(String corpCode);
}
