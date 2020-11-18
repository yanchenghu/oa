package com.ruoyi.demand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarDemandresumefollowMapper;
import com.ruoyi.demand.domain.MarDemandresumefollow;
import com.ruoyi.demand.service.IMarDemandresumefollowService;

/**
 * 简历绑定需求跟踪情况Service业务层处理
 * 
 * @author ych
 * @date 2020-11-13
 */
@Service
public class MarDemandresumefollowServiceImpl implements IMarDemandresumefollowService 
{
    @Autowired
    private MarDemandresumefollowMapper marDemandresumefollowMapper;

    /**
     * 查询简历绑定需求跟踪情况
     * 
     * @param id 简历绑定需求跟踪情况ID
     * @return 简历绑定需求跟踪情况
     */
    @Override
    public MarDemandresumefollow selectMarDemandresumefollowById(String id)
    {
        return marDemandresumefollowMapper.selectMarDemandresumefollowById(id);
    }

    /**
     * 查询简历绑定需求跟踪情况列表
     * 
     * @param marDemandresumefollow 简历绑定需求跟踪情况
     * @return 简历绑定需求跟踪情况
     */
    @Override
    public List<MarDemandresumefollow> selectMarDemandresumefollowList(MarDemandresumefollow marDemandresumefollow)
    {
        return marDemandresumefollowMapper.selectMarDemandresumefollowList(marDemandresumefollow);
    }

    /**
     * 新增简历绑定需求跟踪情况
     * 
     * @param marDemandresumefollow 简历绑定需求跟踪情况
     * @return 结果
     */
    @Override
    public int insertMarDemandresumefollow(MarDemandresumefollow marDemandresumefollow)
    {
        return marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
    }

    /**
     * 修改简历绑定需求跟踪情况
     * 
     * @param marDemandresumefollow 简历绑定需求跟踪情况
     * @return 结果
     */
    @Override
    public int updateMarDemandresumefollow(MarDemandresumefollow marDemandresumefollow)
    {
        return marDemandresumefollowMapper.updateMarDemandresumefollow(marDemandresumefollow);
    }

    /**
     * 批量删除简历绑定需求跟踪情况
     * 
     * @param ids 需要删除的简历绑定需求跟踪情况ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandresumefollowByIds(String[] ids)
    {
        return marDemandresumefollowMapper.deleteMarDemandresumefollowByIds(ids);
    }

    /**
     * 删除简历绑定需求跟踪情况信息
     * 
     * @param id 简历绑定需求跟踪情况ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandresumefollowById(String id)
    {
        return marDemandresumefollowMapper.deleteMarDemandresumefollowById(id);
    }
}
