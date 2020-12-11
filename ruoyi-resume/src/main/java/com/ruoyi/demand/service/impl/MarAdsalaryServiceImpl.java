package com.ruoyi.demand.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.demand.domain.MarAdsalary;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.mapper.MarCustomerprojectpayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarAdsalaryMapper;
import com.ruoyi.demand.service.IMarAdsalaryService;

/**
 * 调整工资记录Service业务层处理
 * 
 * @author ych
 * @date 2020-12-10
 */
@Service
public class MarAdsalaryServiceImpl implements IMarAdsalaryService 
{
    @Autowired
    private MarAdsalaryMapper marAdsalaryMapper;

    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;

    /**
     * 查询调整工资记录
     * 
     * @param salaryId 调整工资记录ID
     * @return 调整工资记录
     */
    @Override
    public MarAdsalary selectMarAdsalaryById(Integer salaryId)
    {
        return marAdsalaryMapper.selectMarAdsalaryById(salaryId);
    }

    /**
     * 查询调整工资记录列表
     * 
     * @param marAdsalary 调整工资记录
     * @return 调整工资记录
     */
    @Override
    public List<MarAdsalary> selectMarAdsalaryList(MarAdsalary marAdsalary)
    {
        return marAdsalaryMapper.selectMarAdsalaryList(marAdsalary);
    }

    /**
     * 新增调整工资记录
     * 
     * @param marAdsalary 调整工资记录
     * @return 结果
     */
    @Override
    public AjaxResult insertMarAdsalary(MarAdsalary marAdsalary)
    {
        MarCustomerprojectpay marCustomerprojectpay = marCustomerprojectpayMapper.selectMarCustomerprojectpayById(marAdsalary.getMarcusId());
        marCustomerprojectpay.setSalary(marAdsalary.getAfterSalary());
        marAdsalary.setCustomerCode(marCustomerprojectpay.getCustomerCode());
        marAdsalary.setCorpName(marCustomerprojectpay.getCorpName());
        marAdsalary.setDemandId(marCustomerprojectpay.getDemandId());
        marAdsalary.setProjectName(marCustomerprojectpay.getProjectName());
        marAdsalary.setSyqstartTime(marCustomerprojectpay.getSyqstartTime());
        marAdsalary.setAddTime(new Date());
        marCustomerprojectpayMapper.updateMarCustomerprojectpay(marCustomerprojectpay);
        marAdsalaryMapper.insertMarAdsalary(marAdsalary);
         return AjaxResult.success("工资调整成功");
    }

    /**
     * 修改调整工资记录
     * 
     * @param marAdsalary 调整工资记录
     * @return 结果
     */
    @Override
    public int updateMarAdsalary(MarAdsalary marAdsalary)
    {
        return marAdsalaryMapper.updateMarAdsalary(marAdsalary);
    }

    /**
     * 批量删除调整工资记录
     * 
     * @param salaryIds 需要删除的调整工资记录ID
     * @return 结果
     */
    @Override
    public int deleteMarAdsalaryByIds(Integer[] salaryIds)
    {
        return marAdsalaryMapper.deleteMarAdsalaryByIds(salaryIds);
    }

    /**
     * 删除调整工资记录信息
     * 
     * @param salaryId 调整工资记录ID
     * @return 结果
     */
    @Override
    public int deleteMarAdsalaryById(Integer salaryId)
    {
        return marAdsalaryMapper.deleteMarAdsalaryById(salaryId);
    }



}
