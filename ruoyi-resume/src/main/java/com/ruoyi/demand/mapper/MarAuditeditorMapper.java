package com.ruoyi.demand.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarAuditeditor;

/**
 * 审核编辑后的面试题Mapper接口
 * 
 * @author ych
 * @date 2021-03-02
 */
public interface MarAuditeditorMapper 
{
    /**
     * 查询审核编辑后的面试题
     * 
     * @param auditedId 审核编辑后的面试题ID
     * @return 审核编辑后的面试题
     */
    public MarAuditeditor selectMarAuditeditorById(Integer auditedId);

    /**
     * 查询审核编辑后的面试题列表
     * 
     * @param marAuditeditor 审核编辑后的面试题
     * @return 审核编辑后的面试题集合
     */
    public List<MarAuditeditor> selectMarAuditeditorList(MarAuditeditor marAuditeditor);

    /**
     * 新增审核编辑后的面试题
     * 
     * @param marAuditeditor 审核编辑后的面试题
     * @return 结果
     */
    public int insertMarAuditeditor(MarAuditeditor marAuditeditor);

    /**
     * 修改审核编辑后的面试题
     * 
     * @param marAuditeditor 审核编辑后的面试题
     * @return 结果
     */
    public int updateMarAuditeditor(MarAuditeditor marAuditeditor);

    /**
     * 删除审核编辑后的面试题
     * 
     * @param auditedId 审核编辑后的面试题ID
     * @return 结果
     */
    public int deleteMarAuditeditorById(Integer auditedId);

    /**
     * 批量删除审核编辑后的面试题
     * 
     * @param auditedIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarAuditeditorByIds(Integer[] auditedIds);

    List<MarAuditeditor> selectAuditeditorList(MarAuditeditor marAuditeditorm);

    List<Map> selQuerydemand(String corpCode);
}
