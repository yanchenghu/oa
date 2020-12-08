package com.ruoyi.demand.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarCustomerprojectpayMapper;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.service.IMarCustomerprojectpayService;

/**
 * 入项Service业务层处理
 * 
 * @author ych
 * @date 2020-12-07
 */
@Service
public class MarCustomerprojectpayServiceImpl implements IMarCustomerprojectpayService 
{
    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;

    /**
     * 查询入项
     * 
     * @param id 入项ID
     * @return 入项
     */
    @Override
    public MarCustomerprojectpay selectMarCustomerprojectpayById(String id)
    {
        return marCustomerprojectpayMapper.selectMarCustomerprojectpayById(id);
    }

    /**
     * 查询入项列表
     * 
     * @param marCustomerprojectpay 入项
     * @return 入项
     */
    @Override
    public List<MarCustomerprojectpay> selectMarCustomerprojectpayList(MarCustomerprojectpay marCustomerprojectpay)
    {
        return marCustomerprojectpayMapper.selectMarCustomerprojectpayList(marCustomerprojectpay);
    }

    /**
     * 新增入项
     * 
     * @param marCustomerprojectpay 入项
     * @return 结果
     */
    @Override
    public int insertMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay)
    {
        return marCustomerprojectpayMapper.insertMarCustomerprojectpay(marCustomerprojectpay);
    }

    /**
     * 修改入项
     * 
     * @param marCustomerprojectpay 入项
     * @return 结果
     */
    @Override
    public int updateMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay)
    {
        return marCustomerprojectpayMapper.updateMarCustomerprojectpay(marCustomerprojectpay);
    }

    /**
     * 批量删除入项
     * 
     * @param ids 需要删除的入项ID
     * @return 结果
     */
    @Override
    public int deleteMarCustomerprojectpayByIds(String[] ids)
    {
        return marCustomerprojectpayMapper.deleteMarCustomerprojectpayByIds(ids);
    }

    /**
     * 删除入项信息
     * 
     * @param id 入项ID
     * @return 结果
     */
    @Override
    public int deleteMarCustomerprojectpayById(String id)
    {
        return marCustomerprojectpayMapper.deleteMarCustomerprojectpayById(id);
    }
    /**
     * 获取轮播入项信息
     */
    @Override
    public AjaxResult rotation() {
        List<Map> sa=  marCustomerprojectpayMapper.selRotation();
        return AjaxResult.success(sa);
    }
}
