package com.ruoyi.demand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarServicepayMapper;
import com.ruoyi.demand.domain.MarServicepay;
import com.ruoyi.demand.service.IMarServicepayService;

/**
 * 服务费调整记录Service业务层处理
 * 
 * @author ych
 * @date 2020-12-10
 */
@Service
public class MarServicepayServiceImpl implements IMarServicepayService 
{
    @Autowired
    private MarServicepayMapper marServicepayMapper;

    /**
     * 查询服务费调整记录
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 服务费调整记录
     */
    @Override
    public MarServicepay selectMarServicepayById(Integer servicepayId)
    {
        return marServicepayMapper.selectMarServicepayById(servicepayId);
    }

    /**
     * 查询服务费调整记录列表
     * 
     * @param marServicepay 服务费调整记录
     * @return 服务费调整记录
     */
    @Override
    public List<MarServicepay> selectMarServicepayList(MarServicepay marServicepay)
    {
        return marServicepayMapper.selectMarServicepayList(marServicepay);
    }

    /**
     * 新增服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    @Override
    public int insertMarServicepay(MarServicepay marServicepay)
    {
        return marServicepayMapper.insertMarServicepay(marServicepay);
    }

    /**
     * 修改服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    @Override
    public int updateMarServicepay(MarServicepay marServicepay)
    {
        return marServicepayMapper.updateMarServicepay(marServicepay);
    }

    /**
     * 批量删除服务费调整记录
     * 
     * @param servicepayIds 需要删除的服务费调整记录ID
     * @return 结果
     */
    @Override
    public int deleteMarServicepayByIds(Integer[] servicepayIds)
    {
        return marServicepayMapper.deleteMarServicepayByIds(servicepayIds);
    }

    /**
     * 删除服务费调整记录信息
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 结果
     */
    @Override
    public int deleteMarServicepayById(Integer servicepayId)
    {
        return marServicepayMapper.deleteMarServicepayById(servicepayId);
    }
}
