package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinExpatriatesalaryMapper;
import com.ruoyi.finance.domain.FinExpatriatesalary;
import com.ruoyi.finance.service.IFinExpatriatesalaryService;

/**
 * 外派人员工资Service业务层处理
 * 
 * @author ych
 * @date 2021-01-27
 */
@Service
public class FinExpatriatesalaryServiceImpl implements IFinExpatriatesalaryService 
{
    @Autowired
    private FinExpatriatesalaryMapper finExpatriatesalaryMapper;

    /**
     * 查询外派人员工资
     * 
     * @param id 外派人员工资ID
     * @return 外派人员工资
     */
    @Override
    public FinExpatriatesalary selectFinExpatriatesalaryById(Integer id)
    {
        return finExpatriatesalaryMapper.selectFinExpatriatesalaryById(id);
    }

    /**
     * 查询外派人员工资列表
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 外派人员工资
     */
    @Override
    public List<FinExpatriatesalary> selectFinExpatriatesalaryList(FinExpatriatesalary finExpatriatesalary)
    {
        return finExpatriatesalaryMapper.selectFinExpatriatesalaryList(finExpatriatesalary);
    }

    /**
     * 新增外派人员工资
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 结果
     */
    @Override
    public int insertFinExpatriatesalary(FinExpatriatesalary finExpatriatesalary)
    {
        return finExpatriatesalaryMapper.insertFinExpatriatesalary(finExpatriatesalary);
    }

    /**
     * 修改外派人员工资
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 结果
     */
    @Override
    public int updateFinExpatriatesalary(FinExpatriatesalary finExpatriatesalary)
    {
        return finExpatriatesalaryMapper.updateFinExpatriatesalary(finExpatriatesalary);
    }

    /**
     * 批量删除外派人员工资
     * 
     * @param ids 需要删除的外派人员工资ID
     * @return 结果
     */
    @Override
    public int deleteFinExpatriatesalaryByIds(Integer[] ids)
    {
        return finExpatriatesalaryMapper.deleteFinExpatriatesalaryByIds(ids);
    }

    /**
     * 删除外派人员工资信息
     * 
     * @param id 外派人员工资ID
     * @return 结果
     */
    @Override
    public int deleteFinExpatriatesalaryById(Integer id)
    {
        return finExpatriatesalaryMapper.deleteFinExpatriatesalaryById(id);
    }
}
