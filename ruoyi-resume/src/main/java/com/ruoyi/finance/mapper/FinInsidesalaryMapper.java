package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.FinInsidesalary;

/**
 * 内部人员工资Mapper接口
 * 
 * @author ych
 * @date 2021-01-29
 */
public interface FinInsidesalaryMapper 
{
    /**
     * 查询内部人员工资
     * 
     * @param id 内部人员工资ID
     * @return 内部人员工资
     */
    public FinInsidesalary selectFinInsidesalaryById(Long id);

    /**
     * 查询内部人员工资列表
     * 
     * @param finInsidesalary 内部人员工资
     * @return 内部人员工资集合
     */
    public List<FinInsidesalary> selectFinInsidesalaryList(FinInsidesalary finInsidesalary);

    /**
     * 新增内部人员工资
     * 
     * @param finInsidesalary 内部人员工资
     * @return 结果
     */
    public int insertFinInsidesalary(FinInsidesalary finInsidesalary);

    /**
     * 修改内部人员工资
     * 
     * @param finInsidesalary 内部人员工资
     * @return 结果
     */
    public int updateFinInsidesalary(FinInsidesalary finInsidesalary);

    /**
     * 删除内部人员工资
     * 
     * @param id 内部人员工资ID
     * @return 结果
     */
    public int deleteFinInsidesalaryById(Long id);

    /**
     * 批量删除内部人员工资
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinInsidesalaryByIds(Long[] ids);

    int insertFinInsidesalarylist(List<FinInsidesalary> listFinExpat);
}
