package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.domain.PersonnelDataDetails;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Description: 人事数据分析mapper
 * @Author ych
 * @Date 2021/2/25
 */
public interface PersonnelDataMapper {

    List<Map> getInputdataList(PersonnelData personnelData);

    List<Map> getTrackList(PersonnelData personnelData);

    List<Map> getBindingList(PersonnelData personnelData);

    List<Map> getResumePassedList(PersonnelData personnelData);

    List<Map> getInterviewPassedList(PersonnelData personnelData);

    List<Map> getEntryPersonnelList(PersonnelData personnelData);

    List<Map> getInputProfitList(PersonnelData personnelData);

    //
    List<PersonnelDataDetails> getDataDetailsList();

    List<Map> getInputdataLists(PersonnelData personnelData);

    List<Map> getTrackLists(PersonnelData PersonnelData);

    List<Map> getBindingLists(PersonnelData PersonnelData);

    List<Map> getResumePassedLists(PersonnelData PersonnelData);

    List<Map> getInterviewPassedLists(PersonnelData PersonnelData);

    List<Map> getEntryPersonnelLists(PersonnelData PersonnelData);

    List<Map> getInputProfitLists(PersonnelData PersonnelData);

    List<Map> userlist();
}
