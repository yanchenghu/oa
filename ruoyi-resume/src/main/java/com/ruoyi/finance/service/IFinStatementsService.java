package com.ruoyi.finance.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.finance.domain.Business;
import com.ruoyi.finance.domain.FinStatements;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 新增服务费核算请求书
     */
    AjaxResult addFinStatements(MultipartFile file, String corpCode, String party, LoginUser loginUser) throws IOException;
    /**
     * 批量开票
     */
    AjaxResult batchBilling(String zm,MultipartFile file);
    /**
     * 批量付款
     */
    AjaxResult batchPayment(String zm);

    List<Business> selectFinStatementsLists(FinStatements finStatements);
}
