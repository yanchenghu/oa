package com.ruoyi.finance.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.finance.domain.FinAncecontact;

/**
 * 财务最新跟踪Service接口
 * 
 * @author ych
 * @date 2021-02-02
 */
public interface IFinAncecontactService 
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
    public AjaxResult insertFinAncecontact(FinAncecontact finAncecontact, LoginUser loginUser) throws Exception;

    /**
     * 修改财务最新跟踪
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 结果
     */
    public int updateFinAncecontact(FinAncecontact finAncecontact);

    /**
     * 批量删除财务最新跟踪
     * 
     * @param contactIds 需要删除的财务最新跟踪ID
     * @return 结果
     */
    public int deleteFinAncecontactByIds(Integer[] contactIds);

    /**
     * 删除财务最新跟踪信息
     * 
     * @param contactId 财务最新跟踪ID
     * @return 结果
     */
    public int deleteFinAncecontactById(Integer contactId);

    /**
     * 查看历史跟进记录
     */
    AjaxResult selAncecontact(FinAncecontact finAncecontact);
}
