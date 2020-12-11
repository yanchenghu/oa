package com.ruoyi.demand.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.demand.domain.*;
import com.ruoyi.demand.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.service.IMarCustomerprojectpayService;

/**
 * 入项Service业务层处理
 * 
 * @author ych
 * @date 2020-12-07
 */
@Service
public class MarCustomerprojectpayServiceImpl implements IMarCustomerprojectpayService 
{
    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;
    @Autowired
    private MarBorrowMapper marBorrowMapper;
    @Autowired
    private MarCertificatesMapper marCertificatesMapper;
    @Autowired
    private MarAdsalaryMapper marAdsalaryMapper;
    @Autowired
    private MarServicepayMapper marServicepayMapper;





    /**
     * 查询入项
     * 
     * @param id 入项ID
     * @return 入项
     */
    @Override
    public MarCustomerprojectpay selectMarCustomerprojectpayById(String id)
    {
        return marCustomerprojectpayMapper.selectMarCustomerprojectpayById(id);
    }

    /**
     * 查询入项列表
     * 
     * @param marCustomerprojectpay 入项
     * @return 入项
     */
    @Override
    public List<MarCustomerprojectpay> selectMarCustomerprojectpayList(MarCustomerprojectpay marCustomerprojectpay)
    {
        return marCustomerprojectpayMapper.selectMarCustomerprojectpayList(marCustomerprojectpay);
    }

    /**
     * 新增入项
     * 
     * @param marCustomerprojectpay 入项
     * @return 结果
     */
    @Override
    public AjaxResult insertMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay)
    {
         marCustomerprojectpayMapper.insertMarCustomerprojectpay(marCustomerprojectpay);
         return AjaxResult.success("人员添加成功");
    }

    /**
     * 修改入项
     * 
     * @param marCustomerprojectpay 入项
     * @return 结果
     */
    @Override
    public AjaxResult updateMarCustomerprojectpay(MarCustomerprojectpay marCustomerprojectpay)
    {
         marCustomerprojectpayMapper.updateMarCustomerprojectpay(marCustomerprojectpay);
         return AjaxResult.success("修改成功");
    }

    /**
     * 批量删除入项
     * 
     * @param ids 需要删除的入项ID
     * @return 结果
     */
    @Override
    public int deleteMarCustomerprojectpayByIds(String[] ids)
    {
        return marCustomerprojectpayMapper.deleteMarCustomerprojectpayByIds(ids);
    }

    /**
     * 删除入项信息
     * 
     * @param id 入项ID
     * @return 结果
     */
    @Override
    public int deleteMarCustomerprojectpayById(String id)
    {
        return marCustomerprojectpayMapper.deleteMarCustomerprojectpayById(id);
    }
    /**
     * 获取轮播入项信息
     */
    @Override
    public AjaxResult rotation() {
        List<Map> sa=  marCustomerprojectpayMapper.selRotation();
        return AjaxResult.success(sa);
    }
    /**
     * 人员入项信息列表
     */
    @Override
    public List<MarCustomePerinfo> selectentrylistList(MarCustomerprojectpay marCustomerprojectpay) {

        return marCustomerprojectpayMapper.selectentrylistList(marCustomerprojectpay);
    }
    /**
     * 人员入项配置信息
     */
    @Override
    public AjaxResult entryinfor(String id) {
        Map map=new HashMap();
        MarCustomerprojectpay marprojectpay=marCustomerprojectpayMapper.selectMarCustomerprojectpayById(id);
        if(marprojectpay==null){
            return AjaxResult.error("入项人为空，请联系管理员");
        }
        Map marCustomePerinfo=marCustomerprojectpayMapper.selectMarCustomePerinfoById(id);
        map.put("MarCustomePerinfo",marCustomePerinfo);

        MarCertificates marCertificates=new MarCertificates();
        marCertificates.setMarcusId(id);
        List<MarCertificates> marCertificates1 = marCertificatesMapper.selectMarCertificatesList(marCertificates);
        map.put("marCertificates1",marCertificates1);

        MarCustomerprojectpay marpr=new MarCustomerprojectpay();
        marpr.setCustomerCode(marprojectpay.getCustomerCode());
        List<MarCustomerprojectpay> Listmarcustomerp = marCustomerprojectpayMapper.selectMarCustomerprojectpayList(marpr);
        map.put("Listmarcustomerp",Listmarcustomerp);

        MarBorrow marBorrow=new MarBorrow();
        marBorrow.setMarcusId(id);
        List<MarBorrow> marBorrows = marBorrowMapper.selectMarBorrowList(marBorrow);
        map.put("marBorrows",marBorrows);

        MarAdsalary marAdsalary=new MarAdsalary();
        marAdsalary.setMarcusId(id);
        List<MarAdsalary> marAdsalaries = marAdsalaryMapper.selectMarAdsalaryList(marAdsalary);
        map.put("marAdsalaries",marAdsalaries);

        MarServicepay marServicepay=new MarServicepay();
        marServicepay.setMarcusId(id);
        List<MarServicepay> marServicepays = marServicepayMapper.selectMarServicepayList(marServicepay);
        map.put("marServicepays",marServicepays);


        return AjaxResult.success(map);
    }
}
