package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.MarCompany;

/**
 * 合作公司Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-16
 */
public interface MarCompanyMapper 
{
    /**
     * 查询合作公司
     * 
     * @param corpCode 合作公司ID
     * @return 合作公司
     */
    public MarCompany selectMarCompanyById(String corpCode);

    /**
     * 查询合作公司列表
     * 
     * @param marCompany 合作公司
     * @return 合作公司集合
     */
    public List<MarCompany> selectMarCompanyList(MarCompany marCompany);

    /**
     * 新增合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    public int insertMarCompany(MarCompany marCompany);

    /**
     * 修改合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    public int updateMarCompany(MarCompany marCompany);

    /**
     * 删除合作公司
     * 
     * @param corpCode 合作公司ID
     * @return 结果
     */
    public int deleteMarCompanyById(String corpCode);

    /**
     * 批量删除合作公司
     * 
     * @param corpCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarCompanyByIds(String[] corpCodes);
}
