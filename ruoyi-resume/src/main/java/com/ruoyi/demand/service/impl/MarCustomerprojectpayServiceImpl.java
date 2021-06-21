package com.ruoyi.demand.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.resume.DateUtils;
import com.ruoyi.demand.domain.*;
import com.ruoyi.demand.mapper.*;
import com.ruoyi.entrycontract.domain.MarEntrycontract;
import com.ruoyi.entrycontract.mapper.MarEntrycontractMapper;
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

    @Autowired
    private MarEntrycontractMapper marEntrycontractMapper;



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
    public List<MarCustomePerinfo> selectentrylistList(MarCustomerprojectpay marCustomerprojectpay, LoginUser loginUser) {
        List<SysRole> roles = loginUser.getUser().getRoles();
        List<String> rol=new ArrayList<>();
        for (SysRole sysRo:roles){
            rol.add(sysRo.getRoleKey());
        }
        //老板 boss，总商务权限 businessextra ，财务finance ,行政 administration
        if(rol.contains("Boss") || rol.contains("businessextra")|| rol.contains("finance")  || rol.contains("administration")|| rol.contains("admin")){
            return marCustomerprojectpayMapper.selectentrylistList(marCustomerprojectpay);
        }
        marCustomerprojectpay.setEntryAssistant(loginUser.getUsername());
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
        if(marCertificates1.size()>0){
            map.put("marCertificates1",marCertificates1.get(0));
        }else{
            map.put("marCertificates1",marCertificates);
        }

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

        MarEntrycontract marEntrycontract=new MarEntrycontract();
        marEntrycontract.setMarcusId(id);
        List<MarEntrycontract> Listcontrr=marEntrycontractMapper.selectMarEntrycontractList(marEntrycontract);
        map.put("Listcontrr",Listcontrr);
        return AjaxResult.success(map);
    }
    /**
     * 人员流动信息列表
     */

    @Override
    public List<PersonnelEssentialinfor> selectpersonnelTurnoverList(MarCustomerprojectpay marCustomerprojectpay,LoginUser loginUser) {

        List<SysRole> roles = loginUser.getUser().getRoles();
        List<String> rol=new ArrayList<>();
        for (SysRole sysRo:roles){
            rol.add(sysRo.getRoleKey());
        }
        Map map=new HashMap();
        map.put("startTime",marCustomerprojectpay.getStartTime());
        map.put("endTime",marCustomerprojectpay.getEndTime());
        map.put("status",marCustomerprojectpay.getSettledCycle());
        List<PersonnelEssentialinfor>  list=new ArrayList<>();
        //老板 boss，总商务权限 businessextra ，财务finance ,行政 administration
        if(rol.contains("Boss") || rol.contains("businessextra")|| rol.contains("finance")  || rol.contains("administration")|| rol.contains("admin")){
            list=marCustomerprojectpayMapper.selectpersonnelTurnoverList(map);
        }else{
             map.put("operationuser",loginUser.getUsername());
             list=marCustomerprojectpayMapper.selectpersonnelTurnoverList(map);
        }
        BigDecimal b1 = new BigDecimal("100");
        for(PersonnelEssentialinfor personnelEsse:list){
            BigDecimal salary = personnelEsse.getSalary();
            BigDecimal servicePay = personnelEsse.getServicePay();
            BigDecimal divide =  (servicePay.subtract(salary)).divide(salary,2, RoundingMode.HALF_UP);
            personnelEsse.setProfitMargin(divide.multiply(b1));
        }
        return list;
    }
    /*入项人员信息*/
    @Override
    public Map entryDetail(MarCustomerprojectpay marCustomerprojectpay,LoginUser loginUser) {
        List<SysRole> roles = loginUser.getUser().getRoles();
        List<String> rol=new ArrayList<>();
        for (SysRole sysRo:roles){
            rol.add(sysRo.getRoleKey());
        }
        // 目前在项 查询当前在项人数 、当月净成本、 当月净服务费 、 、当月净利润     净利润率
        Map nowItem=marCustomerprojectpayMapper.inItemNowCount();
        Integer settledCycle = marCustomerprojectpay.getSettledCycle();

        Map map=new HashMap();
        map.put("startTime",marCustomerprojectpay.getStartTime());
        map.put("endTime",marCustomerprojectpay.getEndTime());
        map.put("status",marCustomerprojectpay.getSettledCycle());
        // 总成本   服务    总利润   总利润率
        Map totalItem=new HashMap();
        //出项
        List<Map>  digressilist =new ArrayList<>();
        //老板 boss，总商务权限 businessextra ，财务finance ,行政 administration
        if(rol.contains("Boss") || rol.contains("businessextra")|| rol.contains("finance")  || rol.contains("administration")|| rol.contains("admin")){
             totalItem=marCustomerprojectpayMapper.entryPeopleCount(map);
             digressilist =marCustomerprojectpayMapper.outItemlist(map);
        }else{
            map.put("operationuser",loginUser.getUsername());
            totalItem=marCustomerprojectpayMapper.entryPeopleCount(map);
            digressilist =marCustomerprojectpayMapper.outItemlist(map);
        }
        double   entryServicePay= (double )totalItem.get("entryServicePay");
        double   entrySalary= (double )totalItem.get("entrySalary");

        int a=digressilist.size();
        double digreSalary = 0.0;
        double digreServicepay=0.0;
        for (Map map1:digressilist){
             double   salary= (double ) map1.get("salary");
             double   service_pay= (double ) map1.get("service_pay");

            digreSalary+=salary;
            digreServicepay+=service_pay;
        }
        if(settledCycle==null){
            nowItem.put("sumServicePay",entryServicePay-digreServicepay);
            nowItem.put("sumSalary",entrySalary-digreSalary);
        }else{
            nowItem.put("sumServicePay",entryServicePay);
            nowItem.put("sumSalary",entrySalary);
        }
        Map digression=new HashMap();
        digression.put("digreCost",digreSalary);
        digression.put("digreService",digreServicepay);
        digression.put("digreprofit",(digreServicepay-digreSalary));
        digression.put("digrePeopleNum",a);
        Map maps=new HashMap();
        maps.put("totalItem",totalItem);
        maps.put("nowItem",nowItem);
        maps.put("digression",digression);
        return maps;
    }

    @Override
    public List<Map> outItemlist(MarCustomerprojectpay marCustomerprojectpay,LoginUser loginUser) {
        Map map=new HashMap();
        map.put("startTime",marCustomerprojectpay.getStartTime());
        map.put("endTime",marCustomerprojectpay.getEndTime());
        List<SysRole> roles = loginUser.getUser().getRoles();
        List<String> rol=new ArrayList<>();
        for (SysRole sysRo:roles){
            rol.add(sysRo.getRoleKey());
        }
        if(rol.contains("Boss") || rol.contains("businessextra")|| rol.contains("finance")  || rol.contains("administration")|| rol.contains("admin")){
            return marCustomerprojectpayMapper.outItemlist(map);
        }else{
            map.put("operationuser",loginUser.getUsername());
            return  marCustomerprojectpayMapper.outItemlist(map);
        }
    }
    /**
     * 检查人员借用物品是否有归还
     */
    @Override
    public int isReturnborrow(String id) {
       List<MarBorrow> listMar= marBorrowMapper.isReturnborrow(id);
       if(listMar.size()>0){
           return 1;
       }
       return 2;

    }

    /**
     * 导出人员出项列表
     */
    @Override
    public List outItemlists(MarCustomerprojectpay marCustomerprojectpay) {
        Map map=new HashMap();
        map.put("startTime",marCustomerprojectpay.getStartTime());
        map.put("endTime",marCustomerprojectpay.getEndTime());
        List<ExportItemList> list = marCustomerprojectpayMapper.outItemlists(map);
        BigDecimal b1 = new BigDecimal("100");
        if(list!=null && list.size()>0) {
            for (ExportItemList map1 : list) {
                String QuitProreason = map1.getQuitProreason();
                String outof_project_cause = DictUtils.getDictLabel("outof_project_cause", QuitProreason);
                map1.setQuitProreason(outof_project_cause);
                BigDecimal salary = map1.getSalary();
                BigDecimal servicePay = map1.getServicePay();
                BigDecimal profit = map1.getProfit();
                BigDecimal divide = (servicePay.subtract(salary)).divide(salary, 2, RoundingMode.HALF_UP);
                map1.setProfitMargin(divide.multiply(b1)+"%");
                if (StringUtils.isEmpty(map1.getBorrowSth())) {
                    map1.setBorrowSth("已还");
                } else {
                    map1.setBorrowSth("未还");
                }
            }
        }
        return list;

    }

    /**
     * 导出人员出入项列表
     */
    @Override
    public List<Entry> selectentrylistLists(MarCustomerprojectpay marCustomerprojectpay) {

        List<Entry> list = marCustomerprojectpayMapper.selectentrylistLists(marCustomerprojectpay);
        return list;
    }
}
