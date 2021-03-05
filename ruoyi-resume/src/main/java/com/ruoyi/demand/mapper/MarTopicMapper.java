package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarTopic;

/**
 * 面试题Mapper接口
 * 
 * @author ych
 * @date 2021-03-02
 */
public interface MarTopicMapper 
{
    /**
     * 查询面试题
     * 
     * @param id 面试题ID
     * @return 面试题
     */
    public MarTopic selectMarTopicById(Integer id);

    /**
     * 查询面试题列表
     * 
     * @param marTopic 面试题
     * @return 面试题集合
     */
    public List<MarTopic> selectMarTopicList(MarTopic marTopic);

    /**
     * 新增面试题
     * 
     * @param marTopic 面试题
     * @return 结果
     */
    public int insertMarTopic(MarTopic marTopic);

    /**
     * 修改面试题
     * 
     * @param marTopic 面试题
     * @return 结果
     */
    public int updateMarTopic(MarTopic marTopic);

    /**
     * 删除面试题
     * 
     * @param id 面试题ID
     * @return 结果
     */
    public int deleteMarTopicById(Integer id);

    /**
     * 批量删除面试题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarTopicByIds(Integer[] ids);

    List<MarTopic> selectextopicList(MarTopic topic);
}
