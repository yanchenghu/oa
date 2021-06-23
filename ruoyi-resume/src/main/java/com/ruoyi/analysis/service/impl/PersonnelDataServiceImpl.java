package com.ruoyi.analysis.service.impl;


import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.domain.PersonnelDataDetails;
import com.ruoyi.analysis.mapper.PersonnelDataMapper;
import com.ruoyi.analysis.service.IPersonnelDataService;
import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.resume.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

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
    public AjaxResult personnellist(PersonnelData personnelData, LoginUser loginUser) {

        //判断是否有人事组长的权限
        List<SysRole> roles = loginUser.getUser().getRoles();
        boolean personnelteamleader = roles.stream().anyMatch(e -> e.getRoleKey().equals("personnelteamleader"));
        if(personnelteamleader){
            personnelData.setDeptId(loginUser.getUser().getDeptId());
        }
        Map map=new HashMap();
        List<Map> InputList= personnelDataMapper.getInputdataList(personnelData);
        List<Map> trackList= personnelDataMapper.getTrackList(personnelData);
        List<Map> bindingList= personnelDataMapper.getBindingList(personnelData);
        List<Map> resumePassedList= personnelDataMapper.getResumePassedList(personnelData);
        List<Map> interviewPassedList= personnelDataMapper.getInterviewPassedList(personnelData);
        List<Map> entryPersonnelList= personnelDataMapper.getEntryPersonnelList(personnelData);

        List<Map> inputProfitList= personnelDataMapper.getInputProfitList(personnelData);
        //  数据详情
        List<PersonnelDataDetails> dataDetailsList= personnelDataMapper.getDataDetailsList(personnelData);

        for (PersonnelDataDetails personnelDataDetails:dataDetailsList){
            String nickName = personnelDataDetails.getNickName();

            for (Map mp:InputList){
                if( mp.get("opertName")!=null){
                  String   opertName = (String) mp.get("opertName");
                  if(nickName.equals(opertName)){
                      if( mp.get("inputdataNum")!=null){
                          personnelDataDetails.setInputNum((Long) mp.get("inputdataNum"));
                      }
                  }
                }
            }
            for(Map mp:trackList){
                if(mp.get("nickName")!=null){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("trackNum")!=null){
                            personnelDataDetails.setTrackNum((Long) mp.get("trackNum"));
                        }
                    }
                }
            }
            for(Map mp:bindingList){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("bindingNum")!=null){
                            personnelDataDetails.setBindingNum((Long) mp.get("bindingNum"));
                        }
                    }
            }
            for(Map mp:resumePassedList){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("resumePassedNum")!=null){
                            personnelDataDetails.setResumePassedNum((Long) mp.get("resumePassedNum"));
                        }
                    }

            }
            for(Map mp:interviewPassedList){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("interviewPassedNum")!=null){
                            personnelDataDetails.setInterviewPassedNum((Long) mp.get("interviewPassedNum"));
                        }
                    }
            }
            for(Map mp:entryPersonnelList){

                    String  nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("entryPersonneNum")!=null){
                            personnelDataDetails.setEntryPersonnelNum((Long) mp.get("entryPersonneNum"));
                        }
                    }

            }
            for(Map mp:inputProfitList){
                if(mp.get("nickName")!=null){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        personnelDataDetails.setPeopleNum(((Long)mp.get("peopleNum")).doubleValue());
                        Double  costNum = (Double) mp.get("costNum");
                        personnelDataDetails.setCostNum(costNum);
                        Double  serviceNum = (Double) mp.get("serviceNum");
                        personnelDataDetails.setServiceNum(serviceNum);
                    }
                }
            }

        }
        if(!personnelteamleader){
            map.put("inputProfitList",inputProfitList);
        }
        map.put("InputList",InputList);
        map.put("trackList",trackList);
        map.put("bindingList",bindingList);
        map.put("resumePassedList",resumePassedList);
        map.put("interviewPassedList",interviewPassedList);
        map.put("entryPersonnelList",entryPersonnelList);
        map.put("dataDetailsList",dataDetailsList);
        return AjaxResult.success(map);
    }

    @Override
    public AjaxResult everydaylist(PersonnelData personnelData) {
        Map map=new HashMap();
        List<Map> InputList= personnelDataMapper.getInputdataLists(personnelData);
        List<Map> trackList= personnelDataMapper.getTrackLists(personnelData);
        List<Map> bindingList= personnelDataMapper.getBindingLists(personnelData);
        List<Map> resumePassedList= personnelDataMapper.getResumePassedLists(personnelData);
        List<Map> interviewPassedList= personnelDataMapper.getInterviewPassedLists(personnelData);
        List<Map> entryPersonnelList= personnelDataMapper.getEntryPersonnelLists(personnelData);
        List<Map> inputProfitList= personnelDataMapper.getInputProfitLists(personnelData);
        //  数据详情
        Date strdate = new Date();
        Date enddate = new Date();
        try {
            strdate = DateUtils.formatY_M_D2Date(personnelData.getStartTime(), "yyyy-MM-dd");
            enddate =DateUtils.formatY_M_D2Date(personnelData.getEndTime(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Date> betweenDates = DateUtils.getBetweenDates(strdate, enddate);
        List<PersonnelDataDetails> listpersDetails=new ArrayList<>();
        for (Date date:betweenDates){
            PersonnelDataDetails personnelDat=new PersonnelDataDetails();
            personnelDat.setAddTime(date);
            listpersDetails.add(personnelDat);
        }
        for (PersonnelDataDetails personnelDataDetails:listpersDetails){
            String addTime = DateUtils.formatY_M_D2String(personnelDataDetails.getAddTime(),"yyyy-MM-dd");
            for(Map mp:InputList){
                if(mp.get("addTime")!=null){
                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("inputdataNum")!=null){
                            personnelDataDetails.setInputNum((Long) mp.get("inputdataNum"));
                        }
                    }
                }
            }
            for(Map mp:trackList){
                if(mp.get("addTime")!=null){
                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("trackNum")!=null){
                            personnelDataDetails.setTrackNum((Long) mp.get("trackNum"));
                        }
                    }
                }
            }
            for(Map mp:bindingList){
                if(mp.get("addTime")!=null){
                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("bindingNum")!=null){
                            personnelDataDetails.setBindingNum((Long) mp.get("bindingNum"));
                        }
                    }
                }
            }
            for(Map mp:resumePassedList){
                if(mp.get("addTime")!=null){
                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("resumePassedNum")!=null){
                            personnelDataDetails.setResumePassedNum((Long) mp.get("resumePassedNum"));
                        }
                    }
                }
            }
            for(Map mp:interviewPassedList){
                if(mp.get("addTime")!=null){
                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("interviewPassedNum")!=null){
                            personnelDataDetails.setInterviewPassedNum((Long) mp.get("interviewPassedNum"));
                        }
                    }
                }
            }
            for(Map mp:entryPersonnelList){

                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("entryPersonneNum")!=null){
                            personnelDataDetails.setEntryPersonnelNum((Long) mp.get("entryPersonneNum"));
                        }
                    }

            }
        }


        map.put("InputList",InputList);
        map.put("trackList",trackList);
        map.put("bindingList",bindingList);
        map.put("resumePassedList",resumePassedList);
        map.put("interviewPassedList",interviewPassedList);
        map.put("entryPersonnelList",entryPersonnelList);
        map.put("inputProfitList",inputProfitList);
        map.put("dataDetailsList",listpersDetails);
        return AjaxResult.success(map);
    }

    @Override
    public List<Map> userlist() {
        List<Map> list = personnelDataMapper.userlist();
        return list;
    }


}
