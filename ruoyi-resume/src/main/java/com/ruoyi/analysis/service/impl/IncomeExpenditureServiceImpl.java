package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.IncomeExpenditure;
import com.ruoyi.analysis.mapper.IncomeExpenditureMapper;
import com.ruoyi.analysis.service.IIncomeExpenditureService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.resume.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 收入与支出实现层Impl
 * @Author ych
 * @Date 2021/3/17
 */
@Service
public class IncomeExpenditureServiceImpl implements IIncomeExpenditureService {

    @Autowired
    private IncomeExpenditureMapper incomeExpenditureMapper;
    @Override
    public AjaxResult getIncomeexpenditureList(IncomeExpenditure incomeExpenditure) {

        Map map=new HashMap();
        map.put("endTime",incomeExpenditure.getRecentYears());
        map.put("startTime",DateUtils.getNMonthfirstDateforDate(12,incomeExpenditure.getRecentYears()));
        // 请求书收入
        List<Map> listIncome=incomeExpenditureMapper.getListIncome(map);
        // 外人员工资
        List<Map> listExpenditure=incomeExpenditureMapper.getListExpenditure(map);
        //内部人员工资
        List<Map> listinsideSalary=incomeExpenditureMapper.getlistinsideSalary(map);
        //其他支出
        List<Map> listotherexpensesSalary=incomeExpenditureMapper.getlistotherexpensesSalary(map);

        List<IncomeExpenditure> liMan=new ArrayList<>();
        for (int i=11;i>=0;i--){
            Date monthsOfAge = DateUtils.getNMonthfirstDateforDate(i,incomeExpenditure.getRecentYears());
            IncomeExpenditure incomeExpe=new IncomeExpenditure();
            incomeExpe.setCustomMonth(DateUtils.formatY_M_D2String(monthsOfAge,DateUtils.FORMAT_Y_M));
            liMan.add(incomeExpe);
        }
        for (IncomeExpenditure incomeExp:liMan){
            String customMonth = incomeExp.getCustomMonth();
            for(Map ma:listIncome){
                String paymentCollectionTime= (String) ma.get("paymentCollectionTime");
                if(customMonth.equals(paymentCollectionTime)){
                    double income= 0.00;
                    if(ma.get("totlePayment")!=null){
                        income= (double) ma.get("totlePayment");

                    }
                    incomeExp.setIncome(income);
                }
            }
            for (Map maExp:listExpenditure){
                String expenditureTime= (String) maExp.get("expenditureTime");
                if(customMonth.equals(expenditureTime)){
                    double ExpatriateSalary= 0.0 ;
                    if(maExp.get("expenditureAmount")!=null){
                        ExpatriateSalary=(double)maExp.get("expenditureAmount");
                    }
                    incomeExp.setExpatriateSalary(ExpatriateSalary);
                }
            }
            for (Map maInsid:listinsideSalary){
                String addDate= (String) maInsid.get("addDate");
                if(customMonth.equals(addDate)){
                    double realSalaryTotle= 0.00;
                    if(maInsid.get("realSalaryTotle")!=null){
                         realSalaryTotle= (double) maInsid.get("realSalaryTotle");

                    }
                    incomeExp.setInsideSalary(realSalaryTotle);
                }
            }
            for (Map maOtherex:listotherexpensesSalary){
                String inserTime= (String) maOtherex.get("inserTime");
                if(customMonth.equals(inserTime)){
                    double mergeMoneyTotle=0.00;
                    if(maOtherex.get("mergeMoneyTotle")!=null){
                         mergeMoneyTotle= (double) maOtherex.get("mergeMoneyTotle");
                    }
                    incomeExp.setOtherExpenses(mergeMoneyTotle);
                }
            }
        }
        double incomeTotal=0.00;//总收入

        double expatriateSalaryTotal=0.00;//总外派人员工资
        double insideSalaryTotal=0.00;//总内部人员工资
        double otherExpensesTotal=0.00;//总其他支出
        double profitTotal=0.00;//总盈利

        for (IncomeExpenditure incomeExp:liMan){
            double income = incomeExp.getIncome();

            double expatriateSalary = incomeExp.getExpatriateSalary();
            double insideSalary = incomeExp.getInsideSalary();
            double otherExpenses = incomeExp.getOtherExpenses();
            double profit = income - ( expatriateSalary + insideSalary + otherExpenses);
            incomeExp.setProfit(profit);
            incomeTotal+=income;

            expatriateSalaryTotal+=expatriateSalary;
            insideSalaryTotal+=insideSalary;
            otherExpensesTotal+=otherExpenses;
            profitTotal+=profit;
        }
        double expenditureTotal=0.00;//总支出
        expenditureTotal=expatriateSalaryTotal+insideSalaryTotal+otherExpensesTotal;


        Map maTotle=new HashMap();
        maTotle.put("incomeTotal",incomeTotal);
        maTotle.put("expenditureTotal",expenditureTotal);
        maTotle.put("expatriateSalaryTotal",expatriateSalaryTotal);
        maTotle.put("insideSalaryTotal",insideSalaryTotal);
        maTotle.put("otherExpensesTotal",otherExpensesTotal);
        maTotle.put("profitTotal",profitTotal);

        Map ma=new HashMap();
        ma.put("liMan",liMan);
        ma.put("maTotle",maTotle);
     return AjaxResult.success(ma);
    }
}
