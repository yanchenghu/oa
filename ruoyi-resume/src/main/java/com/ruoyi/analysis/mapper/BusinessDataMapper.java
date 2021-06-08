package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.BusinessData;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商务数据分析mapper层
 * @Author ych
 * @Date 2021/5/26
 */
public interface BusinessDataMapper {

    List<Map> getinputdemandList(BusinessData businessData);

    List<Map> getbindingResumeList(BusinessData businessData);

    List<Map> getinterviewPassedList(BusinessData businessData);

    List<BusinessData> getAllBusinessData();
}
