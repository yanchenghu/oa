package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarDemandresumefollow;

/**
 * 简历绑定需求跟踪情况Mapper接口
 * 
 * @author ych
 * @date 2020-11-13
 */
public interface MarDemandresumefollowMapper 
{
    /**
     * 查询简历绑定需求跟踪情况
     * 
     * @param id 简历绑定需求跟踪情况ID
     * @return 简历绑定需求跟踪情况
     */
    public MarDemandresumefollow selectMarDemandresumefollowById(String id);

    /**
     * 查询简历绑定需求跟踪情况列表
     * 
     * @param marDemandresumefollow 简历绑定需求跟踪情况
     * @return 简历绑定需求跟踪情况集合
     */
    public List<MarDemandresumefollow> selectMarDemandresumefollowList(MarDemandresumefollow marDemandresumefollow);

    /**
     * 新增简历绑定需求跟踪情况
     * 
     * @param marDemandresumefollow 简历绑定需求跟踪情况
     * @return 结果
     */
    public int insertMarDemandresumefollow(MarDemandresumefollow marDemandresumefollow);

    /**
     * 修改简历绑定需求跟踪情况
     * 
     * @param marDemandresumefollow 简历绑定需求跟踪情况
     * @return 结果
     */
    public int updateMarDemandresumefollow(MarDemandresumefollow marDemandresumefollow);

    /**
     * 删除简历绑定需求跟踪情况
     * 
     * @param id 简历绑定需求跟踪情况ID
     * @return 结果
     */
    public int deleteMarDemandresumefollowById(String id);

    /**
     * 批量删除简历绑定需求跟踪情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarDemandresumefollowByIds(String[] ids);
}
