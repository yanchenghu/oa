package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.MarContractfollowMapper;
import com.ruoyi.customer.domain.MarContractfollow;
import com.ruoyi.customer.service.IMarContractfollowService;

/**
 * 客户跟进联系记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-27
 */
@Service
public class MarContractfollowServiceImpl implements IMarContractfollowService 
{
    @Autowired
    private MarContractfollowMapper marContractfollowMapper;

    /**
     * 查询客户跟进联系记录
     * 
     * @param contractId 客户跟进联系记录ID
     * @return 客户跟进联系记录
     */
    @Override
    public MarContractfollow selectMarContractfollowById(Integer contractId)
    {
        return marContractfollowMapper.selectMarContractfollowById(contractId);
    }

    /**
     * 查询客户跟进联系记录列表
     * 
     * @param marContractfollow 客户跟进联系记录
     * @return 客户跟进联系记录
     */
    @Override
    public List<MarContractfollow> selectMarContractfollowList(MarContractfollow marContractfollow)
    {
        return marContractfollowMapper.selectMarContractfollowList(marContractfollow);
    }

    /**
     * 新增客户跟进联系记录
     * 
     * @param marContractfollow 客户跟进联系记录
     * @return 结果
     */
    @Override
    public int insertMarContractfollow(MarContractfollow marContractfollow)
    {
        return marContractfollowMapper.insertMarContractfollow(marContractfollow);
    }

    /**
     * 修改客户跟进联系记录
     * 
     * @param marContractfollow 客户跟进联系记录
     * @return 结果
     */
    @Override
    public int updateMarContractfollow(MarContractfollow marContractfollow)
    {
        return marContractfollowMapper.updateMarContractfollow(marContractfollow);
    }

    /**
     * 批量删除客户跟进联系记录
     * 
     * @param contractIds 需要删除的客户跟进联系记录ID
     * @return 结果
     */
    @Override
    public int deleteMarContractfollowByIds(Integer[] contractIds)
    {
        return marContractfollowMapper.deleteMarContractfollowByIds(contractIds);
    }

    /**
     * 删除客户跟进联系记录信息
     * 
     * @param contractId 客户跟进联系记录ID
     * @return 结果
     */
    @Override
    public int deleteMarContractfollowById(Integer contractId)
    {
        return marContractfollowMapper.deleteMarContractfollowById(contractId);
    }
}
