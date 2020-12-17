package com.ruoyi.demand.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.demand.domain.MarCustomePerinfo;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.domain.PersonnelEssentialinfor;


/**
 * 入项Service接口
 * 
 * @author ych
 * @date 2020-12-07
 */
public interface IMarCustomerprojectpayService 
{
    /**
     * 查询入项
     * 
     * @param id 入项ID
     * @return 入项
     */
    public MarCustomerprojectpay selectMarCustomerprojectpayById(String id);

    /**
     * 查询入项列表
     * 
     * @param marCustomerprojectpay 入项
     * @return 入项集合
     */
    public List<MarCustomerprojectpay> selectMarCustomerprojectpayList(MarCustomerprojectpay marCustomerprojectpay);

    /**
     * 新增入项
     * 
     * @param marCustomerprojectpay 入项
     * @return 结果
     */
    public AjaxResult insertMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay);

    /**
     * 修改入项
     * 
     * @param marCustomerprojectpay 入项
     * @return 结果
     */
    public AjaxResult updateMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay);

    /**
     * 批量删除入项
     * 
     * @param ids 需要删除的入项ID
     * @return 结果
     */
    public int deleteMarCustomerprojectpayByIds(String[] ids);

    /**
     * 删除入项信息
     * 
     * @param id 入项ID
     * @return 结果
     */
    public int deleteMarCustomerprojectpayById(String id);
    /**
     * 获取轮播入项信息
     */
    AjaxResult rotation();
    /**
     * 人员入项信息列表
     */
    List<MarCustomePerinfo> selectentrylistList(MarCustomerprojectpay marCustomerprojectpay);
    /**
     * 人员入项配置信息
     */
    AjaxResult entryinfor(String id);


    /**
     * 人员流动信息列表
     */
    List<PersonnelEssentialinfor> selectpersonnelTurnoverList(MarCustomerprojectpay marCustomerprojectpay);
    /*入项人员信息*/
    Map entryDetail(MarCustomerprojectpay marCustomerprojectpay) ;

    List<Map> outItemlist(MarCustomerprojectpay marCustomerprojectpay);
}
