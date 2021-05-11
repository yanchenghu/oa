package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.MarCompanyChangerecord;

/**
 * 合作公司更改记录Mapper接口
 * 
 * @author ych
 * @date 2021-04-21
 */
public interface MarCompanyChangerecordMapper 
{
    /**
     * 查询合作公司更改记录
     * 
     * @param id 合作公司更改记录ID
     * @return 合作公司更改记录
     */
    public MarCompanyChangerecord selectMarCompanyChangerecordById(Integer id);

    /**
     * 查询合作公司更改记录列表
     * 
     * @param marCompanyChangerecord 合作公司更改记录
     * @return 合作公司更改记录集合
     */
    public List<MarCompanyChangerecord> selectMarCompanyChangerecordList(MarCompanyChangerecord marCompanyChangerecord);

    /**
     * 新增合作公司更改记录
     * 
     * @param marCompanyChangerecord 合作公司更改记录
     * @return 结果
     */
    public int insertMarCompanyChangerecord(MarCompanyChangerecord marCompanyChangerecord);

    /**
     * 修改合作公司更改记录
     * 
     * @param marCompanyChangerecord 合作公司更改记录
     * @return 结果
     */
    public int updateMarCompanyChangerecord(MarCompanyChangerecord marCompanyChangerecord);

    /**
     * 删除合作公司更改记录
     * 
     * @param id 合作公司更改记录ID
     * @return 结果
     */
    public int deleteMarCompanyChangerecordById(Integer id);

    /**
     * 批量删除合作公司更改记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarCompanyChangerecordByIds(Integer[] ids);
}
