package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.ExpatriateWages;

import java.util.List;
import java.util.Map;

/**
 * @Description: 外派人员Mapper层
 * @Author ych
 * @Date 2021/2/22
 */
public interface ExpatriateWagesMapper {


    List<ExpatriateWages> selectExpatriateWagelist(ExpatriateWages expatriateWage);


    ExpatriateWages getCumulativenum(ExpatriateWages expatriateWages);

    List<Map> selectComlist(Map map);
}
