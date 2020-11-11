package com.ruoyi.resume.service;

import java.util.List;
import com.ruoyi.resume.domain.ConOperationrecords;

/**
 * 操作记录Service接口
 * 
 * @author ych
 * @date 2020-11-09
 */
public interface IConOperationrecordsService 
{
    /**
     * 查询操作记录
     * 
     * @param id 操作记录ID
     * @return 操作记录
     */
    public ConOperationrecords selectConOperationrecordsById(Long id);

    /**
     * 查询操作记录列表
     * 
     * @param conOperationrecords 操作记录
     * @return 操作记录集合
     */
    public List<ConOperationrecords> selectConOperationrecordsList(ConOperationrecords conOperationrecords);

    /**
     * 新增操作记录
     * 
     * @param conOperationrecords 操作记录
     * @return 结果
     */
    public int insertConOperationrecords(ConOperationrecords conOperationrecords);

    /**
     * 修改操作记录
     * 
     * @param conOperationrecords 操作记录
     * @return 结果
     */
    public int updateConOperationrecords(ConOperationrecords conOperationrecords);

    /**
     * 批量删除操作记录
     * 
     * @param ids 需要删除的操作记录ID
     * @return 结果
     */
    public int deleteConOperationrecordsByIds(Long[] ids);

    /**
     * 删除操作记录信息
     * 
     * @param id 操作记录ID
     * @return 结果
     */
    public int deleteConOperationrecordsById(Long id);
}
