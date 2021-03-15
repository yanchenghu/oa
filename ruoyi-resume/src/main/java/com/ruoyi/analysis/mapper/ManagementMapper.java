package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.Management;

import java.util.List;
import java.util.Map;

/**
 * @Description: 经营分析的mapper
 * @Author ych
 * @Date 2021/3/10
 */
public interface ManagementMapper {


    List<Map> getPaymentCollection(Map map);

    List<Map> getExpenditureAmount(Map map);

    List<Map> getlastyeartPaymentCollection(Map map);

    List<Map> getlastyearExpenditureAmount(Map map);
}
