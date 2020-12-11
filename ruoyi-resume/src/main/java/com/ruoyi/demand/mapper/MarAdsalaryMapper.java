package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarAdsalary;

/**
 * 调整工资记录Mapper接口
 * 
 * @author ych
 * @date 2020-12-10
 */
public interface MarAdsalaryMapper 
{
    /**
     * 查询调整工资记录
     * 
     * @param salaryId 调整工资记录ID
     * @return 调整工资记录
     */
    public MarAdsalary selectMarAdsalaryById(Integer salaryId);

    /**
     * 查询调整工资记录列表
     * 
     * @param marAdsalary 调整工资记录
     * @return 调整工资记录集合
     */
    public List<MarAdsalary> selectMarAdsalaryList(MarAdsalary marAdsalary);

    /**
     * 新增调整工资记录
     * 
     * @param marAdsalary 调整工资记录
     * @return 结果
     */
    public int insertMarAdsalary(MarAdsalary marAdsalary);

    /**
     * 修改调整工资记录
     * 
     * @param marAdsalary 调整工资记录
     * @return 结果
     */
    public int updateMarAdsalary(MarAdsalary marAdsalary);

    /**
     * 删除调整工资记录
     * 
     * @param salaryId 调整工资记录ID
     * @return 结果
     */
    public int deleteMarAdsalaryById(Integer salaryId);

    /**
     * 批量删除调整工资记录
     * 
     * @param salaryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarAdsalaryByIds(Integer[] salaryIds);
}
