package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.MarCompanyMapper;
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.service.IMarCompanyService;

/**
 * 合作公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-16
 */
@Service
public class MarCompanyServiceImpl implements IMarCompanyService 
{
    @Autowired
    private MarCompanyMapper marCompanyMapper;

    /**
     * 查询合作公司
     * 
     * @param corpCode 合作公司ID
     * @return 合作公司
     */
    @Override
    public MarCompany selectMarCompanyById(String corpCode)
    {
        return marCompanyMapper.selectMarCompanyById(corpCode);
    }

    /**
     * 查询合作公司列表
     * 
     * @param marCompany 合作公司
     * @return 合作公司
     */
    @Override
    public List<MarCompany> selectMarCompanyList(MarCompany marCompany)
    {
        return marCompanyMapper.selectMarCompanyList(marCompany);
    }

    /**
     * 新增合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    @Override
    public int insertMarCompany(MarCompany marCompany)
    {
        return marCompanyMapper.insertMarCompany(marCompany);
    }

    /**
     * 修改合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    @Override
    public int updateMarCompany(MarCompany marCompany)
    {
        return marCompanyMapper.updateMarCompany(marCompany);
    }

    /**
     * 批量删除合作公司
     * 
     * @param corpCodes 需要删除的合作公司ID
     * @return 结果
     */
    @Override
    public int deleteMarCompanyByIds(String[] corpCodes)
    {
        return marCompanyMapper.deleteMarCompanyByIds(corpCodes);
    }

    /**
     * 删除合作公司信息
     * 
     * @param corpCode 合作公司ID
     * @return 结果
     */
    @Override
    public int deleteMarCompanyById(String corpCode)
    {
        return marCompanyMapper.deleteMarCompanyById(corpCode);
    }
}
