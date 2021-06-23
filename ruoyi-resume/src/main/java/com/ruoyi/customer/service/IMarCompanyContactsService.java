package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.MarCompanyContacts;

/**
 * 合作公司联系管理Service接口
 * 
 * @author ych
 * @date 2021-04-15
 */
public interface IMarCompanyContactsService 
{
    /**
     * 查询合作公司联系管理
     * 
     * @param id 合作公司联系管理ID
     * @return 合作公司联系管理
     */
    public MarCompanyContacts selectMarCompanyContactsById(Integer id);

    /**
     * 查询合作公司联系管理列表
     * 
     * @param marCompanyContacts 合作公司联系管理
     * @return 合作公司联系管理集合
     */
    public List<MarCompanyContacts> selectMarCompanyContactsList(MarCompanyContacts marCompanyContacts);

    /**
     * 新增合作公司联系管理
     * 
     * @param marCompanyContacts 合作公司联系管理
     * @return 结果
     */
    public int insertMarCompanyContacts(MarCompanyContacts marCompanyContacts);

    /**
     * 修改合作公司联系管理
     * 
     * @param marCompanyContacts 合作公司联系管理
     * @return 结果
     */
    public int updateMarCompanyContacts(MarCompanyContacts marCompanyContacts);

    /**
     * 批量删除合作公司联系管理
     * 
     * @param ids 需要删除的合作公司联系管理ID
     * @return 结果
     */
    public int deleteMarCompanyContactsByIds(Integer[] ids);

    /**
     * 删除合作公司联系管理信息
     * 
     * @param id 合作公司联系管理ID
     * @return 结果
     */
    public int deleteMarCompanyContactsById(Integer id);
}