package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinStatementsMapper;
import com.ruoyi.finance.domain.FinStatements;
import com.ruoyi.finance.service.IFinStatementsService;

/**
 * 服务费核算请求书Service业务层处理
 * 
 * @author ych
 * @date 2021-01-21
 */
@Service
public class FinStatementsServiceImpl implements IFinStatementsService 
{
    @Autowired
    private FinStatementsMapper finStatementsMapper;

    /**
     * 查询服务费核算请求书
     * 
     * @param id 服务费核算请求书ID
     * @return 服务费核算请求书
     */
    @Override
    public FinStatements selectFinStatementsById(Integer id)
    {
        return finStatementsMapper.selectFinStatementsById(id);
    }

    /**
     * 查询服务费核算请求书列表
     * 
     * @param finStatements 服务费核算请求书
     * @return 服务费核算请求书
     */
    @Override
    public List<FinStatements> selectFinStatementsList(FinStatements finStatements)
    {
        return finStatementsMapper.selectFinStatementsList(finStatements);
    }

    /**
     * 新增服务费核算请求书
     * 
     * @param finStatements 服务费核算请求书
     * @return 结果
     */
    @Override
    public int insertFinStatements(FinStatements finStatements)
    {
        return finStatementsMapper.insertFinStatements(finStatements);
    }

    /**
     * 修改服务费核算请求书
     * 
     * @param finStatements 服务费核算请求书
     * @return 结果
     */
    @Override
    public int updateFinStatements(FinStatements finStatements)
    {
        return finStatementsMapper.updateFinStatements(finStatements);
    }

    /**
     * 批量删除服务费核算请求书
     * 
     * @param ids 需要删除的服务费核算请求书ID
     * @return 结果
     */
    @Override
    public int deleteFinStatementsByIds(Integer[] ids)
    {
        return finStatementsMapper.deleteFinStatementsByIds(ids);
    }

    /**
     * 删除服务费核算请求书信息
     * 
     * @param id 服务费核算请求书ID
     * @return 结果
     */
    @Override
    public int deleteFinStatementsById(Integer id)
    {
        return finStatementsMapper.deleteFinStatementsById(id);
    }
}
