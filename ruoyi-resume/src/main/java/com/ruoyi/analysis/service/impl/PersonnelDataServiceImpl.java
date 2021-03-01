package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.domain.PersonnelDataDetails;
import com.ruoyi.analysis.mapper.PersonnelDataMapper;
import com.ruoyi.analysis.service.IPersonnelDataService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 人事数据分析的实现层
 * @Author ych
 * @Date 2021/2/25
 */
@Service
public class PersonnelDataServiceImpl implements IPersonnelDataService {


    @Autowired
    private PersonnelDataMapper personnelDataMapper;
    @Override
    public AjaxResult personnellist(PersonnelData personnelData) {
        Map map=new HashMap();
        List<Map> InputList= personnelDataMapper.getInputdataList(personnelData);
        List<Map> trackList= personnelDataMapper.getTrackList(personnelData);
        List<Map> bindingList= personnelDataMapper.getBindingList(personnelData);
        List<Map> resumePassedList= personnelDataMapper.getResumePassedList(personnelData);
        List<Map> interviewPassedList= personnelDataMapper.getInterviewPassedList(personnelData);
        List<Map> entryPersonnelList= personnelDataMapper.getEntryPersonnelList(personnelData);
        List<Map> inputProfitList= personnelDataMapper.getInputProfitList(personnelData);
        //  数据详情
        List<PersonnelDataDetails> dataDetailsList= personnelDataMapper.getDataDetailsList();
        for (PersonnelDataDetails personnelDataDetails:dataDetailsList){

        }


        map.put("InputList",InputList);
        map.put("trackList",trackList);
        map.put("bindingList",bindingList);
        map.put("resumePassedList",resumePassedList);
        map.put("interviewPassedList",interviewPassedList);
        map.put("entryPersonnelList",entryPersonnelList);
        map.put("inputProfitList",inputProfitList);
        map.put("dataDetailsList",dataDetailsList);
        return AjaxResult.success(map);
    }
}
