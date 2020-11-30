package com.ruoyi.customer.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.MarContract;

/**
 * 合同Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-27
 */
public interface MarContractMapper 
{
    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    public MarContract selectMarContractById(Integer id);

    /**
     * 查询合同列表
     * 
     * @param marContract 合同
     * @return 合同集合
     */
    public List<MarContract> selectMarContractList(MarContract marContract);

    /**
     * 新增合同
     * 
     * @param marContract 合同
     * @return 结果
     */
    public int insertMarContract(MarContract marContract);

    /**
     * 修改合同
     * 
     * @param marContract 合同
     * @return 结果
     */
    public int updateMarContract(MarContract marContract);

    /**
     * 删除合同
     * 
     * @param id 合同ID
     * @return 结果
     */
    public int deleteMarContractById(Integer id);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarContractByIds(Integer[] ids);
}
