package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.Yxcontact;

import java.util.List;

/**
 * 跟进联系内容Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-09
 */
public interface YxcontactMapper 
{
    /**
     * 查询跟进联系内容
     * 
     * @param contactId 跟进联系内容ID
     * @return 跟进联系内容
     */
    public Yxcontact selectYxcontactById(Long contactId);

    /**
     * 查询跟进联系内容列表
     * 
     * @param yxcontact 跟进联系内容
     * @return 跟进联系内容集合
     */
    public List<Yxcontact> selectYxcontactList(Yxcontact yxcontact);

    /**
     * 新增跟进联系内容
     * 
     * @param yxcontact 跟进联系内容
     * @return 结果
     */
    public int insertYxcontact(Yxcontact yxcontact);

    /**
     * 修改跟进联系内容
     * 
     * @param yxcontact 跟进联系内容
     * @return 结果
     */
    public int updateYxcontact(Yxcontact yxcontact);

    /**
     * 删除跟进联系内容
     * 
     * @param contactId 跟进联系内容ID
     * @return 结果
     */
    public int deleteYxcontactById(Long contactId);

    /**
     * 批量删除跟进联系内容
     * 
     * @param contactIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxcontactByIds(Long[] contactIds);


    Yxcontact selNewdetailByentryId(Integer entryId);
}
