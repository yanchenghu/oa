package com.ruoyi.expenditure.service;

import java.text.ParseException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.expenditure.domain.Expenditure;
import com.ruoyi.expenditure.domain.FinOtherexpenses;

/**
 * 每月公司其他住处费用Service接口
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
public interface IFinOtherexpensesService 
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
    public AjaxResult insertFinOtherexpenses(FinOtherexpenses finOtherexpenses);

    /**
     * 修改每月公司其他住处费用
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 结果
     */
    public int updateFinOtherexpenses(FinOtherexpenses finOtherexpenses);

    /**
     * 批量删除每月公司其他住处费用
     * 
     * @param ids 需要删除的每月公司其他住处费用ID
     * @return 结果
     */
    public int deleteFinOtherexpensesByIds(Long[] ids);

    /**
     * 删除每月公司其他住处费用信息
     * 
     * @param id 每月公司其他住处费用ID
     * @return 结果
     */
    public int deleteFinOtherexpensesById(Long id);

    List<Expenditure> selectFinOtherexpensesListt(FinOtherexpenses finOtherexpenses);
}
