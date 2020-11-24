package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.Yxdemand;

import java.util.List;

/**
 * 营销录入公司Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public interface HighseasMapper
{
    /**
     * 查询营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 营销录入公司
     */
    public Yxdemand selectYxdemandById(Integer entryId);

    /**
     * 查询营销录入公司列表
     * 
     * @param yxdemand 营销录入公司
     * @return 营销录入公司集合
     */
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand);

    /**
     * 新增营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    public int insertYxdemand(Yxdemand yxdemand);

    /**
     * 修改营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    public int updateYxdemand(Yxdemand yxdemand);

    /**
     * 删除营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 结果
     */
    public int deleteYxdemandById(Integer entryId);

    /**
     * 批量删除营销录入公司
     * 
     * @param entryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxdemandByIds(Integer[] entryIds);
}
