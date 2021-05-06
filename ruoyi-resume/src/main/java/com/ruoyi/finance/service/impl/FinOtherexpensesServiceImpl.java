package com.ruoyi.finance.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.finance.domain.Expenditure;
import com.ruoyi.finance.service.IFinOtherexpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinOtherexpensesMapper;
import com.ruoyi.finance.domain.FinOtherexpenses;

/**
 * 每月公司其他住处费用Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
@Service
public class FinOtherexpensesServiceImpl implements IFinOtherexpensesService
{
    @Autowired
    private FinOtherexpensesMapper finOtherexpensesMapper;

    /**
     * 查询每月公司其他住处费用
     * 
     * @param id 每月公司其他住处费用ID
     * @return 每月公司其他住处费用
     */
    @Override
    public FinOtherexpenses selectFinOtherexpensesById(Long id)
    {
        return finOtherexpensesMapper.selectFinOtherexpensesById(id);
    }

    /**
     * 查询每月公司其他住处费用列表
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 每月公司其他住处费用
     */
    @Override
    public List<FinOtherexpenses> selectFinOtherexpensesList(FinOtherexpenses finOtherexpenses)
    {
        return finOtherexpensesMapper.selectFinOtherexpensesList(finOtherexpenses);
    }

    /**
     * 新增每月公司其他住处费用
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 结果
     */
    @Override
    public AjaxResult insertFinOtherexpenses(FinOtherexpenses finOtherexpenses)  {

        int i = finOtherexpensesMapper.selectFinOtherexpensesLists(finOtherexpenses);
        if(i>0){
             return AjaxResult.error("当月信息已经存在，不用在添加了，请去修改");
        }

        int i1 = finOtherexpensesMapper.insertFinOtherexpenses(finOtherexpenses);
        if(i1>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error("系统操作错误，请联系管理员");

        }



    }




    /**
     * 修改每月公司其他住处费用
     * 
     * @param finOtherexpenses 每月公司其他住处费用
     * @return 结果
     */
    @Override
    public int updateFinOtherexpenses(FinOtherexpenses finOtherexpenses)
    {
        finOtherexpenses.setUpdateTime(DateUtils.getNowDate());
        return finOtherexpensesMapper.updateFinOtherexpenses(finOtherexpenses);
    }

    /**
     * 批量删除每月公司其他住处费用
     * 
     * @param ids 需要删除的每月公司其他住处费用ID
     * @return 结果
     */
    @Override
    public int deleteFinOtherexpensesByIds(Long[] ids)
    {
        return finOtherexpensesMapper.deleteFinOtherexpensesByIds(ids);
    }

    /**
     * 删除每月公司其他住处费用信息
     * 
     * @param id 每月公司其他住处费用ID
     * @return 结果
     */
    @Override
    public int deleteFinOtherexpensesById(Long id)
    {
        return finOtherexpensesMapper.deleteFinOtherexpensesById(id);
    }

    @Override
    public List<Expenditure> selectFinOtherexpensesListt(FinOtherexpenses finOtherexpenses) {
        List<Expenditure> list = finOtherexpensesMapper.selectFinOtherexpensesListt(finOtherexpenses);
        return list;
    }


}
