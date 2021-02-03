package com.ruoyi.finance.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.finance.domain.FinAncecompany;
import com.ruoyi.finance.domain.FinAncecontact;

/**
 * 添加合作公司Mapper接口
 * 
 * @author ych
 * @date 2021-02-02
 */
public interface FinAncecompanyMapper 
{
    /**
     * 查询添加合作公司
     * 
     * @param id 添加合作公司ID
     * @return 添加合作公司
     */
    public FinAncecompany selectFinAncecompanyById(Integer id);

    /**
     * 查询添加合作公司列表
     * 
     * @param finAncecompany 添加合作公司
     * @return 添加合作公司集合
     */
    public List<FinAncecompany> selectFinAncecompanyList(FinAncecompany finAncecompany);

    /**
     * 新增添加合作公司
     * 
     * @param finAncecompany 添加合作公司
     * @return 结果
     */
    public int insertFinAncecompany(FinAncecompany finAncecompany);

    /**
     * 修改添加合作公司
     * 
     * @param finAncecompany 添加合作公司
     * @return 结果
     */
    public int updateFinAncecompany(FinAncecompany finAncecompany);

    /**
     * 删除添加合作公司
     * 
     * @param id 添加合作公司ID
     * @return 结果
     */
    public int deleteFinAncecompanyById(Integer id);

    /**
     * 批量删除添加合作公司
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinAncecompanyByIds(Integer[] ids);

    List<FinAncecontact> selectFinAncecontactList(FinAncecompany finAncecompany);


    FinAncecontact selectFinAncecontactById(Integer id);


    int deleteFinAncecontactById(String corpCode);
}
