package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.customer.domain.Yxcontact;
import com.ruoyi.customer.mapper.YxcontactMapper;
import com.ruoyi.customer.service.IYxcontactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 跟进联系内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-09
 */
@Service
public class YxcontactServiceImpl implements IYxcontactService
{
    @Autowired
    private YxcontactMapper yxcontactMapper;

    /**
     * 查询跟进联系内容
     * 
     * @param contactId 跟进联系内容ID
     * @return 跟进联系内容
     */
    @Override
    public Yxcontact selectYxcontactById(Long contactId)
    {
        return yxcontactMapper.selectYxcontactById(contactId);
    }

    /**
     * 查询跟进联系内容列表
     * 
     * @param yxcontact 跟进联系内容
     * @return 跟进联系内容
     */
    @Override
    public List<Yxcontact> selectYxcontactList(Yxcontact yxcontact)
    {
        return yxcontactMapper.selectYxcontactList(yxcontact);
    }

    /**
     * 新增跟进联系内容
     * 
     * @param yxcontact 跟进联系内容
     * @return 结果
     */
    @Override
    public int insertYxcontact(Yxcontact yxcontact)
    {

        return yxcontactMapper.insertYxcontact(yxcontact);
    }

    /**
     * 修改跟进联系内容
     * 
     * @param yxcontact 跟进联系内容
     * @return 结果
     */
    @Override
    public int updateYxcontact(Yxcontact yxcontact)
    {
        return yxcontactMapper.updateYxcontact(yxcontact);
    }

    /**
     * 批量删除跟进联系内容
     * 
     * @param contactIds 需要删除的跟进联系内容ID
     * @return 结果
     */
    @Override
    public int deleteYxcontactByIds(Long[] contactIds)
    {
        return yxcontactMapper.deleteYxcontactByIds(contactIds);
    }

    /**
     * 删除跟进联系内容信息
     * 
     * @param contactId 跟进联系内容ID
     * @return 结果
     */
    @Override
    public int deleteYxcontactById(Long contactId)
    {
        return yxcontactMapper.deleteYxcontactById(contactId);
    }
}
