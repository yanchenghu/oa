package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.FinExpatriatesalary;

/**
 * 外派人员工资Mapper接口
 * 
 * @author ych
 * @date 2021-01-27
 */
public interface FinExpatriatesalaryMapper 
{
    /**
     * 查询外派人员工资
     * 
     * @param id 外派人员工资ID
     * @return 外派人员工资
     */
    public FinExpatriatesalary selectFinExpatriatesalaryById(Integer id);

    /**
     * 查询外派人员工资列表
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 外派人员工资集合
     */
    public List<FinExpatriatesalary> selectFinExpatriatesalaryList(FinExpatriatesalary finExpatriatesalary);

    /**
     * 新增外派人员工资
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 结果
     */
    public int insertFinExpatriatesalary(FinExpatriatesalary finExpatriatesalary);

    /**
     * 修改外派人员工资
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 结果
     */
    public int updateFinExpatriatesalary(FinExpatriatesalary finExpatriatesalary);

    /**
     * 删除外派人员工资
     * 
     * @param id 外派人员工资ID
     * @return 结果
     */
    public int deleteFinExpatriatesalaryById(Integer id);

    /**
     * 批量删除外派人员工资
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinExpatriatesalaryByIds(Integer[] ids);
}
