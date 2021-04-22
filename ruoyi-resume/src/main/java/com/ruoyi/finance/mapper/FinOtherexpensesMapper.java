package com.ruoyi.finance.mapper;

import java.util.List;

import com.ruoyi.finance.domain.Expenditure;
import com.ruoyi.finance.domain.FinOtherexpenses;

/**
 * 每月公司其他住处费用Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
public interface FinOtherexpensesMapper 
{
    /**
     * 查询每月公司其他住处费用
     * 
     * @param id 每月公司其他住处费用ID
     * @return 每月公司其他住处费用
     */
    public FinOtherexpenses selectFinOtherexpensesById(Long id);

    /**
     * 查询每月公司其他住处费用列表
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 每月公司其他住处费用集合
     */
    public List<FinOtherexpenses> selectFinOtherexpensesList(FinOtherexpenses finOtherexpenses);

    /**
     * 新增每月公司其他住处费用
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 结果
     */
    public int insertFinOtherexpenses(FinOtherexpenses finOtherexpenses);

    /**
     * 修改每月公司其他住处费用
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 结果
     */
    public int updateFinOtherexpenses(FinOtherexpenses finOtherexpenses);

    /**
     * 删除每月公司其他住处费用
     * 
     * @param id 每月公司其他住处费用ID
     * @return 结果
     */
    public int deleteFinOtherexpensesById(Long id);

    /**
     * 批量删除每月公司其他住处费用
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinOtherexpensesByIds(Long[] ids);


    int selectFinOtherexpensesLists(FinOtherexpenses finOtherexpenses);

    List<Expenditure> selectFinOtherexpensesListt(FinOtherexpenses finOtherexpenses);
}