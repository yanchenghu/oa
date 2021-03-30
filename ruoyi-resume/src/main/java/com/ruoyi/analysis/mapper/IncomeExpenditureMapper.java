package com.ruoyi.analysis.mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 收入与支出mapper层
 * @Author ych
 * @Date 2021/3/17
 */
public interface IncomeExpenditureMapper {

    List<Map> getListIncome(Map map);

    List<Map> getListExpenditure(Map map);

    List<Map> getlistinsideSalary(Map map);

    List<Map> getlistotherexpensesSalary(Map map);
}
