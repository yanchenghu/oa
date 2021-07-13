package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.PerEnclosureCustomerinfo;

/**
 * 附件简历基本信息Mapper接口
 * 
 * @author ych
 * @date 2021-07-02
 */
public interface PerEnclosureCustomerinfoMapper 
{
    /**
     * 查询附件简历基本信息
     * 
     * @param customerCode 附件简历基本信息ID
     * @return 附件简历基本信息
     */
    public PerEnclosureCustomerinfo selectPerEnclosureCustomerinfoById(String customerCode);

    /**
     * 查询附件简历基本信息列表
     * 
     * @param perEnclosureCustomerinfo 附件简历基本信息
     * @return 附件简历基本信息集合
     */
    public List<PerEnclosureCustomerinfo> selectPerEnclosureCustomerinfoList(PerEnclosureCustomerinfo perEnclosureCustomerinfo);

    /**
     * 新增附件简历基本信息
     * 
     * @param perEnclosureCustomerinfo 附件简历基本信息
     * @return 结果
     */
    public int insertPerEnclosureCustomerinfo(PerEnclosureCustomerinfo perEnclosureCustomerinfo);

    /**
     * 修改附件简历基本信息
     * 
     * @param perEnclosureCustomerinfo 附件简历基本信息
     * @return 结果
     */
    public int updatePerEnclosureCustomerinfo(PerEnclosureCustomerinfo perEnclosureCustomerinfo);

    /**
     * 删除附件简历基本信息
     * 
     * @param customerCode 附件简历基本信息ID
     * @return 结果
     */
    public int deletePerEnclosureCustomerinfoById(String customerCode);

    /**
     * 批量删除附件简历基本信息
     * 
     * @param customerCodes 需要删除的数据ID
     * @return 结果
     */
    public int deletePerEnclosureCustomerinfoByIds(String[] customerCodes);
}
