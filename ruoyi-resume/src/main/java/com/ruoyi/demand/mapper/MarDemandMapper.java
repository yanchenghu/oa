package com.ruoyi.demand.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarDemand;

/**
 * 需求Mapper接口
 * 
 * @author ych
 * @date 2020-12-01
 */
public interface MarDemandMapper 
{
    /**
     * 查询需求
     * 
     * @param demandId 需求ID
     * @return 需求
     */
    public MarDemand selectMarDemandById(String demandId);

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
     * 删除需求
     * 
     * @param demandId 需求ID
     * @return 结果
     */
    public int deleteMarDemandById(String demandId);

    /**
     * 批量删除需求
     * 
     * @param demandIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarDemandByIds(String[] demandIds);
    /**
     * 需求根据名字查重
     */

    MarDemand selectMarDemandByName(String projectName);
    /*
    需求下面是否存在未处理的
     */
    List<String> seluntreatedResume(String demandId);
    /**
     * 需求绑定表的查询
     */
    List<MarDemand> selectMarDemandbindingList(Map map);
}
