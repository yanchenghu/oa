package com.ruoyi.customer.service.impl;

import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.mapper.YxdemandMapper;
import com.ruoyi.customer.service.IYxdemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 营销录入公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class YxdemandServiceImpl implements IYxdemandService
{
    @Autowired
    private YxdemandMapper yxdemandMapper;

    /**
     * 查询营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 营销录入公司
     */
    @Override
    public Yxdemand selectYxdemandById(Integer entryId)
    {
        return yxdemandMapper.selectYxdemandById(entryId);
    }

    /**
     * 查询营销录入公司列表
     * 
     * @param yxdemand 营销录入公司
     * @return 营销录入公司
     */
    @Override
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand)
    {
        return yxdemandMapper.selectYxdemandList(yxdemand);
    }

    /**
     * 新增营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    @Override
    public int insertYxdemand(Yxdemand yxdemand)
    {
        return yxdemandMapper.insertYxdemand(yxdemand);
    }

    /**
     * 修改营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    @Override
    public int updateYxdemand(Yxdemand yxdemand)
    {
        return yxdemandMapper.updateYxdemand(yxdemand);
    }

    /**
     * 批量删除营销录入公司
     * 
     * @param entryIds 需要删除的营销录入公司ID
     * @return 结果
     */
    @Override
    public int deleteYxdemandByIds(Integer[] entryIds)
    {
        return yxdemandMapper.deleteYxdemandByIds(entryIds);
    }

    /**
     * 删除营销录入公司信息
     * 
     * @param entryId 营销录入公司ID
     * @return 结果
     */
    @Override
    public int deleteYxdemandById(Integer entryId)
    {
        return yxdemandMapper.deleteYxdemandById(entryId);
    }
}
