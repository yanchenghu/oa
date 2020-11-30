package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.MarContractfollow;

/**
 * 客户跟进联系记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-27
 */
public interface MarContractfollowMapper 
{
    /**
     * 查询客户跟进联系记录
     * 
     * @param contractId 客户跟进联系记录ID
     * @return 客户跟进联系记录
     */
    public MarContractfollow selectMarContractfollowById(Integer contractId);

    /**
     * 查询客户跟进联系记录列表
     * 
     * @param marContractfollow 客户跟进联系记录
     * @return 客户跟进联系记录集合
     */
    public List<MarContractfollow> selectMarContractfollowList(MarContractfollow marContractfollow);

    /**
     * 新增客户跟进联系记录
     * 
     * @param marContractfollow 客户跟进联系记录
     * @return 结果
     */
    public int insertMarContractfollow(MarContractfollow marContractfollow);

    /**
     * 修改客户跟进联系记录
     * 
     * @param marContractfollow 客户跟进联系记录
     * @return 结果
     */
    public int updateMarContractfollow(MarContractfollow marContractfollow);

    /**
     * 删除客户跟进联系记录
     * 
     * @param contractId 客户跟进联系记录ID
     * @return 结果
     */
    public int deleteMarContractfollowById(Integer contractId);

    /**
     * 批量删除客户跟进联系记录
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarContractfollowByIds(Integer[] contractIds);
}
