package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.FinStatements;

/**
 * 服务费核算请求书Service接口
 * 
 * @author ych
 * @date 2021-01-21
 */
public interface IFinStatementsService 
{
    /**
     * 查询服务费核算请求书
     * 
     * @param id 服务费核算请求书ID
     * @return 服务费核算请求书
     */
    public FinStatements selectFinStatementsById(Integer id);

    /**
     * 查询服务费核算请求书列表
     * 
     * @param finStatements 服务费核算请求书
     * @return 服务费核算请求书集合
     */
    public List<FinStatements> selectFinStatementsList(FinStatements finStatements);

    /**
     * 新增服务费核算请求书
     * 
     * @param finStatements 服务费核算请求书
     * @return 结果
     */
    public int insertFinStatements(FinStatements finStatements);

    /**
     * 修改服务费核算请求书
     * 
     * @param finStatements 服务费核算请求书
     * @return 结果
     */
    public int updateFinStatements(FinStatements finStatements);

    /**
     * 批量删除服务费核算请求书
     * 
     * @param ids 需要删除的服务费核算请求书ID
     * @return 结果
     */
    public int deleteFinStatementsByIds(Integer[] ids);

    /**
     * 删除服务费核算请求书信息
     * 
     * @param id 服务费核算请求书ID
     * @return 结果
     */
    public int deleteFinStatementsById(Integer id);
}
