package com.ruoyi.analysis.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.analysis.domain.PersonnelData;
import com.ruoyi.analysis.domain.PersonnelDataDetails;
import com.ruoyi.analysis.mapper.PersonnelDataMapper;
import com.ruoyi.analysis.service.IPersonnelDataService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                if(map.get("nickName")!=null){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("bindingNum")!=null){
                            personnelDataDetails.setBindingNum((Long) mp.get("bindingNum"));
                        }
                    }
                }
            }
            for(Map mp:resumePassedList){
                if(mp.get("nickName")!=null){
                    String nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName1)){
                        if(mp.get("resumePassedNum")!=null){
                            personnelDataDetails.setResumePassedNum((Long) mp.get("resumePassedNum"));
                        }
                    }
                }
            }
            for(Map mp:interviewPassedList){
                if(mp.get("nickName")!=null){
                    String nickName1 =(String) mp.get(nickName);
                    if(nickName.equals(nickName1)){
                        if(mp.get("interviewPassedNum")!=null){
                            personnelDataDetails.setInterviewPassedNum((Long) mp.get("interviewPassedNum"));
                        }
                    }
                }
            }
            for(Map mp:entryPersonnelList){
                if(mp.get("nickName")!=null){
                    String  nickName1 =(String) mp.get("nickName");
                    if(nickName.equals(nickName)){
                        if(mp.get("entryPersonnelNum")!=null){
                            personnelDataDetails.setEntryPersonnelNum((Long) mp.get("entryPersonnelNum"));
                        }
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
        List<PersonnelDataDetails> dataDetailsList= personnelDataMapper.getDataDetailsList();
        for (PersonnelDataDetails personnelDataDetails:dataDetailsList){
            String addTime = String.valueOf(personnelDataDetails.getAddTime());
            String nickName = personnelDataDetails.getNickName();
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
                if(mp.get("addTime")!=null){
                    String addTime1 =(String) mp.get("addTime");
                    if(addTime.equals(addTime1)){
                        if(mp.get("entryPersonneNum")!=null){
                            personnelDataDetails.setBindingNum((Long) mp.get("entryPersonneNum"));
                        }
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

    @Override
    public List<Map> userlist() {
        List<Map> list = personnelDataMapper.userlist();
        return list;
    }


}
