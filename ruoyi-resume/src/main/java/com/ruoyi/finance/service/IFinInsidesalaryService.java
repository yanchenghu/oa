package com.ruoyi.finance.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.finance.domain.FinInsidesalary;
import org.springframework.web.multipart.MultipartFile;

/**
 * 内部人员工资Service接口
 * 
 * @author ych
 * @date 2021-01-29
 */
public interface IFinInsidesalaryService 
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
     * 修改内部人员工资
     * 
     * @param finInsidesalary 内部人员工资
     * @return 结果
     */
    public int updateFinInsidesalary(FinInsidesalary finInsidesalary);

    /**
     * 批量删除内部人员工资
     * 
     * @param ids 需要删除的内部人员工资ID
     * @return 结果
     */
    public int deleteFinInsidesalaryByIds(Long[] ids);

    /**
     * 删除内部人员工资信息
     * 
     * @param id 内部人员工资ID
     * @return 结果
     */
    public int deleteFinInsidesalaryById(Long id);
    /**
     * 新增内部人员工资
     */
    AjaxResult addfinFinInsidesalary(MultipartFile file, Date months, LoginUser loginUser) throws IOException;
}
