package com.ruoyi.demand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarDemandMapper;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;

/**
 * 需求Service业务层处理
 * 
 * @author ych
 * @date 2020-11-27
 */
@Service
public class MarDemandServiceImpl implements IMarDemandService 
{
    @Autowired
    private MarDemandMapper marDemandMapper;

    /**
     * 查询需求
     * 
     * @param id 需求ID
     * @return 需求
     */
    @Override
    public MarDemand selectMarDemandById(String id)
    {
        return marDemandMapper.selectMarDemandById(id);
    }

    /**
     * 查询需求列表
     * 
     * @param marDemand 需求
     * @return 需求
     */
    @Override
    public List<MarDemand> selectMarDemandList(MarDemand marDemand)
    {
        return marDemandMapper.selectMarDemandList(marDemand);
    }

    /**
     * 新增需求
     * 
     * @param marDemand 需求
     * @return 结果
     */
    @Override
    public int insertMarDemand(MarDemand marDemand)
    {
        return marDemandMapper.insertMarDemand(marDemand);
    }

    /**
     * 修改需求
     * 
     * @param marDemand 需求
     * @return 结果
     */
    @Override
    public int updateMarDemand(MarDemand marDemand)
    {
        return marDemandMapper.updateMarDemand(marDemand);
    }

    /**
     * 批量删除需求
     * 
     * @param ids 需要删除的需求ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandByIds(String[] ids)
    {
        return marDemandMapper.deleteMarDemandByIds(ids);
    }

    /**
     * 删除需求信息
     * 
     * @param id 需求ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandById(String id)
    {
        return marDemandMapper.deleteMarDemandById(id);
    }
}
