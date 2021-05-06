package com.ruoyi.demand.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.domain.MarWaitingentry;

/**
 * 待入项Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-31
 */
public interface MarWaitingentryMapper 
{
    /**
     * 查询待入项
     * 
     * @param id 待入项ID
     * @return 待入项
     */
    public MarWaitingentry selectMarWaitingentryById(Integer id);

    /**
     * 查询待入项列表
     * 
     * @param marWaitingentry 待入项
     * @return 待入项集合
     */
    public List<MarWaitingentry> selectMarWaitingentryList(MarWaitingentry marWaitingentry);

    /**
     * 新增待入项
     * 
     * @param marWaitingentry 待入项
     * @return 结果
     */
    public int insertMarWaitingentry(MarWaitingentry marWaitingentry);

    /**
     * 修改待入项
     * 
     * @param marWaitingentry 待入项
     * @return 结果
     */
    public int updateMarWaitingentry(MarWaitingentry marWaitingentry);

    /**
     * 删除待入项
     * 
     * @param id 待入项ID
     * @return 结果
     */
    public int deleteMarWaitingentryById(Long id);

    /**
     * 批量删除待入项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarWaitingentryByIds(Long[] ids);
    /**
     * 修改待入项
     *
     * @param 根据需求Id和个人编号 待入项
     * @return 结果
     */
    int updateMarWaitingentryby(MarWaitingentry marWaiting);

    List<Map> getMarWaitingentr(String username);

    int deleteMarWaitingentryByDemandandCode(MarDemandresume marDemandresume);
}
