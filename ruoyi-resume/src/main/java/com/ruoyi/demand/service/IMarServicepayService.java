package com.ruoyi.demand.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.domain.MarServicepay;
import org.springframework.web.multipart.MultipartFile;

/**
 * 服务费调整记录Service接口
 * 
 * @author ych
 * @date 2020-12-10
 */
public interface IMarServicepayService 
{
    /**
     * 查询服务费调整记录
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 服务费调整记录
     */
    public MarServicepay selectMarServicepayById(Integer servicepayId);

    /**
     * 查询服务费调整记录列表
     * 
     * @param marServicepay 服务费调整记录
     * @return 服务费调整记录集合
     */
    public List<MarServicepay> selectMarServicepayList(MarServicepay marServicepay);

    /**
     * 新增服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    public AjaxResult insertMarServicepay(MarServicepay marServicepay);

    /**
     * 修改服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    public int updateMarServicepay(MarServicepay marServicepay);

    /**
     * 批量删除服务费调整记录
     * 
     * @param servicepayIds 需要删除的服务费调整记录ID
     * @return 结果
     */
    public int deleteMarServicepayByIds(Integer[] servicepayIds);

    /**
     * 删除服务费调整记录信息
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 结果
     */
    public int deleteMarServicepayById(Integer servicepayId);

    /**
     * 添加证件
     */
    AjaxResult addCertifi(Integer id, String marcusId, Integer photo, MultipartFile file);

    /**
     * 人员出项
     */
    int personnelItems(MarCustomerprojectpay marCustomerprojectpay);
}
