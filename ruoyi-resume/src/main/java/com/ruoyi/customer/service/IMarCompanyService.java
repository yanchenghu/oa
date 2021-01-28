package com.ruoyi.customer.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.domain.MarContract;

/**
 * 合作公司Service接口
 * 
 * @author ruoyi
 * @date 2020-11-16
 */
public interface IMarCompanyService 
{
    /**
     * 查询合作公司
     * 
     * @param corpCode 合作公司ID
     * @return 合作公司
     */
    public AjaxResult selectMarCompanyById(String corpCode, LoginUser loginUser);

    /**
     * 查询合作公司列表
     * 
     * @param marCompany 合作公司
     * @return 合作公司集合
     */
    public List<MarCompany> selectMarCompanyList(MarCompany marCompany, LoginUser loginUser);

    /**
     * 新增合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    public AjaxResult insertMarCompany(MarCompany marCompany, LoginUser loginUser);

    /**
     * 修改合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    public AjaxResult updateMarCompany(MarCompany marCompany, LoginUser loginUser);

    /**
     * 批量删除合作公司
     * 
     * @param corpCodes 需要删除的合作公司ID
     * @return 结果
     */
    public int deleteMarCompanyByIds(String[] corpCodes);

    /**
     * 删除合作公司信息
     * 
     * @param corpCode 合作公司ID
     * @return 结果
     */
    public int deleteMarCompanyById(String corpCode);

     /**
     * 查询联系人信息
     */
    List<MarCompany> selectMarCompanyContact(MarCompany marCompany);


    /**
     * 查询合同信息
     */
    List<MarCompany> selectMarContractList(MarContract marContract);

    /**
     * 获取所有的合作公司
     * @return
     */
    List<MarCompany> selectMarCompanyAlllistcom();
}
