package com.ruoyi.analysis.mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 人员流动分析mapper
 * @Author ych
 * @Date 2021/3/15
 */


public interface PersonnelMobilityMapper {

    List<Map> getLitoutPeople(Map map);

    List<Map> getLitentryPeople(Map map);


}
