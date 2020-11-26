package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.customer.service.YxrobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.YxrobMapper;
import com.ruoyi.customer.domain.Yxrob;

/**
 * 抢占记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-26
 */
@Service
public class YxrobServiceImpl implements YxrobService
{
    @Autowired
    private YxrobMapper yxrobMapper;

    /**
     * 查询抢占记录
     * 
     * @param robId 抢占记录ID
     * @return 抢占记录
     */
//    @Override
//    public Yxrob selectYxrobById(Integer robId)
//    {
//        return yxrobMapper.selectYxrobById(robId);
//    }

    /**
     * 查询抢占记录列表
     * 
     * @param yxrob 抢占记录
     * @return 抢占记录
     */
    @Override
    public List<Yxrob> selectYxrobList(Yxrob yxrob)
    {
        return yxrobMapper.selectYxrobList(yxrob);
    }

    @Override
    public String selectYxrobById(Integer robId) {
        return null;
    }

    /**
     * 新增抢占记录
     * 
     * @param yxrob 抢占记录
     * @return 结果
     */
    @Override
    public int insertYxrob(Yxrob yxrob)
    {
        return yxrobMapper.insertYxrob(yxrob);
    }

    /**
     * 修改抢占记录
     * 
     * @param yxrob 抢占记录
     * @return 结果
     */
    @Override
    public int updateYxrob(Yxrob yxrob)
    {
        return yxrobMapper.updateYxrob(yxrob);
    }

    /**
     * 批量删除抢占记录
     * 
     * @param robIds 需要删除的抢占记录ID
     * @return 结果
     */
    @Override
    public int deleteYxrobByIds(Integer[] robIds)
    {
        return yxrobMapper.deleteYxrobByIds(robIds);
    }

    /**
     * 删除抢占记录信息
     * 
     * @param robId 抢占记录ID
     * @return 结果
     */
//    @Override
//    public int deleteYxrobById(Integer robId)
//    {
//        return yxrobMapper.deleteYxrobById(robId);
//    }
}
