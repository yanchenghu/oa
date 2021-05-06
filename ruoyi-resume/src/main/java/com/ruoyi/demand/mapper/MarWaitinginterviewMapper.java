package com.ruoyi.demand.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.domain.MarWaitinginterview;

/**
 * 待面试Mapper接口
 * 
 * @author ych
 * @date 2021-04-29
 */
public interface MarWaitinginterviewMapper 
{
    /**
     * 查询待面试
     * 
     * @param id 待面试ID
     * @return 待面试
     */
    public MarWaitinginterview selectMarWaitinginterviewById(Integer id);

    /**
     * 查询待面试列表
     * 
     * @param marWaitinginterview 待面试
     * @return 待面试集合
     */
    public List<MarWaitinginterview> selectMarWaitinginterviewList(MarWaitinginterview marWaitinginterview);

    /**
     * 新增待面试
     * 
     * @param marWaitinginterview 待面试
     * @return 结果
     */
    public int insertMarWaitinginterview(MarWaitinginterview marWaitinginterview);

    /**
     * 修改待面试
     * 
     * @param marWaitinginterview 待面试
     * @return 结果
     */
    public int updateMarWaitinginterview(MarWaitinginterview marWaitinginterview);

    /**
     * 删除待面试
     * 
     * @param id 待面试ID
     * @return 结果
     */
    public int deleteMarWaitinginterviewById(Integer id);

    /**
     * 批量删除待面试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarWaitinginterviewByIds(Integer[] ids);

    int deleteMarWaitingeinterviewByDemandandCode(MarDemandresume marDemandresume);

    List<Map> selectMarWaitinginterviewMap(String username);

    int updateMarWaitinginByDemandCode(MarWaitinginterview marWaitinginterview);
}
