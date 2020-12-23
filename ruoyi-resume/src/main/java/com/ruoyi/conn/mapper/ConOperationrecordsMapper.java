package com.ruoyi.conn.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.conn.domain.ConOperationrecords;

/**
 * 操作记录Mapper接口
 * 
 * @author ych
 * @date 2020-11-16
 */
public interface ConOperationrecordsMapper 
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
     * 删除操作记录
     * 
     * @param id 操作记录ID
     * @return 结果
     */
    public int deleteConOperationrecordsById(Long id);

    /**
     * 批量删除操作记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConOperationrecordsByIds(Long[] ids);

    List<ConOperationrecords> selectConOperatBymap(Map<String, Object> map);
}
