package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarServicepay;

/**
 * 服务费调整记录Mapper接口
 * 
 * @author ych
 * @date 2020-12-10
 */
public interface MarServicepayMapper 
{
    /**
     * 查询服务费调整记录
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 服务费调整记录
     */
    public MarServicepay selectMarServicepayById(Integer servicepayId);

    /**
     * 查询服务费调整记录列表
     * 
     * @param marServicepay 服务费调整记录
     * @return 服务费调整记录集合
     */
    public List<MarServicepay> selectMarServicepayList(MarServicepay marServicepay);

    /**
     * 新增服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    public int insertMarServicepay(MarServicepay marServicepay);

    /**
     * 修改服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    public int updateMarServicepay(MarServicepay marServicepay);

    /**
     * 删除服务费调整记录
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 结果
     */
    public int deleteMarServicepayById(Integer servicepayId);

    /**
     * 批量删除服务费调整记录
     * 
     * @param servicepayIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarServicepayByIds(Integer[] servicepayIds);
}
