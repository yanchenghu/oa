package com.ruoyi.finance.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.finance.domain.FinAncecontact;

/**
 * 财务最新跟踪Mapper接口
 * 
 * @author ych
 * @date 2021-02-02
 */
public interface FinAncecontactMapper 
{
    /**
     * 查询财务最新跟踪
     * 
     * @param contactId 财务最新跟踪ID
     * @return 财务最新跟踪
     */
    public FinAncecontact selectFinAncecontactById(Integer contactId);

    /**
     * 查询财务最新跟踪列表
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 财务最新跟踪集合
     */
    public List<FinAncecontact> selectFinAncecontactList(FinAncecontact finAncecontact);

    /**
     * 新增财务最新跟踪
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 结果
     */
    public int insertFinAncecontact(FinAncecontact finAncecontact);

    /**
     * 修改财务最新跟踪
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 结果
     */
    public int updateFinAncecontact(FinAncecontact finAncecontact);

    /**
     * 删除财务最新跟踪
     * 
     * @param contactId 财务最新跟踪ID
     * @return 结果
     */
    public int deleteFinAncecontactById(Integer contactId);

    /**
     * 批量删除财务最新跟踪
     * 
     * @param contactIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinAncecontactByIds(Integer[] contactIds);
    /**
     * 查看历史跟进记录
     */
    List<FinAncecontact>  selAncecontact(FinAncecontact finAncecontact);
}