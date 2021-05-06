package com.ruoyi.demand.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarWaitinginterview;

/**
 * 待面试Service接口
 * 
 * @author ych
 * @date 2021-04-29
 */
public interface IMarWaitinginterviewService 
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


    List<Map> selectMarWaitinginterviewMap(String username);

    int updateMarWaitinginterview(MarWaitinginterview marWaitinginterview);
}
