package com.ruoyi.demand.service;

import java.util.List;
import com.ruoyi.demand.domain.MarDemand;

/**
 * 需求Service接口
 * 
 * @author ych
 * @date 2020-11-27
 */
public interface IMarDemandService 
{
    /**
     * 查询需求
     * 
     * @param id 需求ID
     * @return 需求
     */
    public MarDemand selectMarDemandById(String id);

    /**
     * 查询需求列表
     * 
     * @param marDemand 需求
     * @return 需求集合
     */
    public List<MarDemand> selectMarDemandList(MarDemand marDemand);

    /**
     * 新增需求
     * 
     * @param marDemand 需求
     * @return 结果
     */
    public int insertMarDemand(MarDemand marDemand);

    /**
     * 修改需求
     * 
     * @param marDemand 需求
     * @return 结果
     */
    public int updateMarDemand(MarDemand marDemand);

    /**
     * 批量删除需求
     * 
     * @param ids 需要删除的需求ID
     * @return 结果
     */
    public int deleteMarDemandByIds(String[] ids);

    /**
     * 删除需求信息
     * 
     * @param id 需求ID
     * @return 结果
     */
    public int deleteMarDemandById(String id);
}
