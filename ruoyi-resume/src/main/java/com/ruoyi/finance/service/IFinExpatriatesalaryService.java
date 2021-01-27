package com.ruoyi.finance.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.finance.domain.FinExpatriatesalary;
import org.springframework.web.multipart.MultipartFile;

/**
 * 外派人员工资Service接口
 * 
 * @author ych
 * @date 2021-01-27
 */
public interface IFinExpatriatesalaryService 
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
     * 修改外派人员工资
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 结果
     */
    public int updateFinExpatriatesalary(FinExpatriatesalary finExpatriatesalary);

    /**
     * 批量删除外派人员工资
     * 
     * @param ids 需要删除的外派人员工资ID
     * @return 结果
     */
    public int deleteFinExpatriatesalaryByIds(Integer[] ids);

    /**
     * 删除外派人员工资信息
     * 
     * @param id 外派人员工资ID
     * @return 结果
     */
    public int deleteFinExpatriatesalaryById(Integer id);
    /**
     * 新增外派人员工资
     */
    AjaxResult addfinExpatriatesalary(MultipartFile file, Date months, LoginUser loginUser) throws IOException;
}
