package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.Management;
import com.ruoyi.analysis.mapper.ManagementMapper;
import com.ruoyi.analysis.service.IManagementService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.resume.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Description: 经营分析实现层
 * @Author ych
 * @Date 2021/3/9
 */
@Service
public class ManagementServiceImpl implements IManagementService {


    @Autowired
    private ManagementMapper managementMapper;

    @Override
    public AjaxResult managementlist(Management management) {
        Map map=new HashMap();
        map.put("endTime",management.getRecentYears());
        map.put("startTime",DateUtils.getNMonthfirstDateforDate(12,management.getRecentYears()));
        map.put("samePeriodTime",DateUtils.getNMonthfirstDateforDate(24,management.getRecentYears()));
        if(management.getSalesQuota()==1){
            map.put("plasticTime","payment_time");//实际回款
            map.put("actualExpenditureTime","add_date");//实际支出
        }else{
            map.put("plasticTime","start_time");//销售额回款
            map.put("actualExpenditureTime","months");//销售额支出
        }
        //按照销售额度或者实际额度  计算请求书（收入）
        List<Map> litmap=managementMapper.getPaymentCollection(map);
        //按照销售额度或者实际额度  计算请求书（支出）
        List<Map> litexpenditure=managementMapper.getExpenditureAmount(map);
        //当前年收入与支出
        List<Management> liMan=new ArrayList<>();
        for (int i=11;i>=0;i--){
            Date monthsOfAge = DateUtils.getNMonthfirstDateforDate(i,management.getRecentYears());
            Management managem=new Management();
            managem.setCustomMonth(DateUtils.formatY_M_D2String(monthsOfAge,DateUtils.FORMAT_Y_M));
            liMan.add(managem);
        }
        for(Management mana:liMan){
            String customMonth = mana.getCustomMonth();
            //循环本年的收入
            for(Map ma:litmap){
                String paymentCollectionTime = (String) ma.get("paymentCollectionTime");
                if(customMonth.equals(paymentCollectionTime)){
                    Double  totlePayment = (Double) ma.get("totlePayment");
                    mana.setIncome(totlePayment);
                }
            }
            //循环本年的支出
            for (Map maex:litexpenditure){
                String expenditureTime = (String) maex.get("expenditureTime");
                if(customMonth.equals(expenditureTime)){
                    Double  expenditureAmount = (Double) maex.get("expenditureAmount");
                    mana.setExpenditure(expenditureAmount);
                }
            }
        }
        //去年收入与支出
        List<Management> lastyearMan=new ArrayList<>();
        for (int i=23;i>=12;i--){
            Date monthsOfAge = DateUtils.getNMonthfirstDateforDate(i,management.getRecentYears());
            Management managem=new Management();
            managem.setCustomMonth(DateUtils.formatY_M_D2String(monthsOfAge,DateUtils.FORMAT_Y_M));
            lastyearMan.add(managem);
        }
        //同比去年按照销售额度/或者实际额度 计算去年（收入）
        List<Map> lastyearincomeMap=managementMapper.getlastyeartPaymentCollection(map);
        //同比去年按照销售额度/或者实际额度 计算请求书（支出）
        List<Map> lastyearexpenditure=managementMapper.getlastyearExpenditureAmount(map);
        for(Management mana:lastyearMan){
            String customMonth = mana.getCustomMonth();
            //循环本年的收入
            for(Map ma:lastyearincomeMap){
                String paymentCollectionTime = (String) ma.get("paymentCollectionTime");
                if(customMonth.equals(paymentCollectionTime)){
                    Double  totlePayment = (Double) ma.get("totlePayment");
                    mana.setOnIncomeData(totlePayment);
                }
            }
            //循环本年的支出
            for (Map maex:lastyearexpenditure){
                String expenditureTime = (String) maex.get("expenditureTime");
                if(customMonth.equals(expenditureTime)){
                    Double  expenditureAmount = (Double) maex.get("expenditureAmount");
                    mana.setOnExpenditureData(expenditureAmount);
                }
            }
        }
        double totalincome=0.00;//总收入
        double totalexpenditure=0.00;//总支出
        for (Management mana:liMan){
            Double income =0.00;
            Double expenditure=0.00;
            if(mana.getIncome()!=null){
                income=mana.getIncome();
            }else{
                mana.setIncome(0.00);
            }
            if(mana.getExpenditure()!=null) {
                expenditure = mana.getExpenditure();
            }else{
                mana.setExpenditure(0.00);
            }
            mana.setProfit(income-expenditure);
            totalincome+=income;
            totalexpenditure+=expenditure;
        }
        double totalProfit=totalincome-totalexpenditure;//总利润
        double lastyearTotalincome=0.00;//总收入
        double lastyearTotalexpenditure=0.00;//总支出
        //去年利润数据 onProfitData
        for (Management mana:lastyearMan){
            Double onIncomeData =0.00;
            Double onExpenditureData=0.00;
            if(mana.getOnIncomeData()!=null){
                onIncomeData=mana.getOnIncomeData();
            }else{
                mana.setOnIncomeData(0.00);
            }
            if(mana.getOnExpenditureData()!=null) {
                onExpenditureData = mana.getOnExpenditureData();
            }else{
                mana.setOnExpenditureData(0.00);
            }
            mana.setOnProfitData(onIncomeData-onExpenditureData);
            lastyearTotalincome+=onIncomeData;
            lastyearTotalexpenditure+=onExpenditureData;
        }
        double lastyeartotalProfit=lastyearTotalincome-lastyearTotalexpenditure;//去年总利润
        Map totle=new HashMap();
        totle.put("totalincome",totalincome);
        totle.put("totalexpenditure",totalexpenditure);
        totle.put("totalProfit",totalProfit);
        totle.put("lastyearTotalincome",lastyearTotalincome);
        totle.put("lastyearTotalexpenditure",lastyearTotalexpenditure);
        totle.put("lastyeartotalProfit",lastyeartotalProfit);
        Map totlemap=new HashMap();
        totlemap.put("totle",totle);
        totlemap.put("liMan",liMan);
        totlemap.put("lastyearMan",lastyearMan);
        return AjaxResult.success(totlemap);
    }
}
