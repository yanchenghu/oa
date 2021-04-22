package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarAuditedtorDemand;

/**
 * 面试题绑定需求Mapper接口
 * 
 * @author ych
 * @date 2021-03-03
 */
public interface MarAuditedtorDemandMapper 
{
    /**
     * 查询面试题绑定需求
     * 
     * @param demandTopic 面试题绑定需求ID
     * @return 面试题绑定需求
     */
    public MarAuditedtorDemand selectMarAuditedtorDemandById(Integer demandTopic);

    /**
     * 查询面试题绑定需求列表
     * 
     * @param marAuditedtorDemand 面试题绑定需求
     * @return 需求面试题绑定集合
     */
    public List<MarAuditedtorDemand> selectMarAuditedtorDemandList(MarAuditedtorDemand marAuditedtorDemand);

    /**
     * 新增面试题绑定需求
     * 
     * @param marAuditedtorDemand 面试题绑定需求
     * @return 结果
     */
    public int insertMarAuditedtorDemand(MarAuditedtorDemand marAuditedtorDemand);

    /**
     * 修改面试题绑定需求
     * 
     * @param marAuditedtorDemand 面试题绑定需求
     * @return 结果
     */
    public int updateMarAuditedtorDemand(MarAuditedtorDemand marAuditedtorDemand);

    /**
     * 删除面试题绑定需求
     * 
     * @param demandTopic 面试题绑定需求ID
     * @return 结果
     */
    public int deleteMarAuditedtorDemandById(Integer demandTopic);

    /**
     * 批量删除面试题绑定需求
     * 
     * @param demandTopics 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarAuditedtorDemandByIds(Integer[] demandTopics);

    int deleteMarAuditedtorDemandByAuditedId(String demandId);

    Integer selectMarAuditedtorDemandByDemandId(String demandId);


}
