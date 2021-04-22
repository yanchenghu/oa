package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.MarCompanyContactsMapper;
import com.ruoyi.customer.domain.MarCompanyContacts;
import com.ruoyi.customer.service.IMarCompanyContactsService;

/**
 * 合作公司联系管理Service业务层处理
 * 
 * @author ych
 * @date 2021-04-15
 */
@Service
public class MarCompanyContactsServiceImpl implements IMarCompanyContactsService 
{
    @Autowired
    private MarCompanyContactsMapper marCompanyContactsMapper;

    /**
     * 查询合作公司联系管理
     * 
     * @param id 合作公司联系管理ID
     * @return 合作公司联系管理
     */
    @Override
    public MarCompanyContacts selectMarCompanyContactsById(Integer id)
    {
        return marCompanyContactsMapper.selectMarCompanyContactsById(id);
    }

    /**
     * 查询合作公司联系管理列表
     * 
     * @param marCompanyContacts 合作公司联系管理
     * @return 合作公司联系管理
     */
    @Override
    public List<MarCompanyContacts> selectMarCompanyContactsList(MarCompanyContacts marCompanyContacts)
    {
        return marCompanyContactsMapper.selectMarCompanyContactsList(marCompanyContacts);
    }

    /**
     * 新增合作公司联系管理
     * 
     * @param marCompanyContacts 合作公司联系管理
     * @return 结果
     */
    @Override
    public int insertMarCompanyContacts(MarCompanyContacts marCompanyContacts)
    {
        return marCompanyContactsMapper.insertMarCompanyContacts(marCompanyContacts);
    }

    /**
     * 修改合作公司联系管理
     * 
     * @param marCompanyContacts 合作公司联系管理
     * @return 结果
     */
    @Override
    public int updateMarCompanyContacts(MarCompanyContacts marCompanyContacts)
    {
        return marCompanyContactsMapper.updateMarCompanyContacts(marCompanyContacts);
    }

    /**
     * 批量删除合作公司联系管理
     * 
     * @param ids 需要删除的合作公司联系管理ID
     * @return 结果
     */
    @Override
    public int deleteMarCompanyContactsByIds(Integer[] ids)
    {
        return marCompanyContactsMapper.deleteMarCompanyContactsByIds(ids);
    }

    /**
     * 删除合作公司联系管理信息
     * 
     * @param id 合作公司联系管理ID
     * @return 结果
     */
    @Override
    public int deleteMarCompanyContactsById(Integer id)
    {
        return marCompanyContactsMapper.deleteMarCompanyContactsById(id);
    }
}
