package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.BusinessData;
import com.ruoyi.analysis.mapper.BusinessDataMapper;
import com.ruoyi.analysis.service.IBusinessDataService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.mapper.YxdemandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商务数据分析impl
 * @Author ych
 * @Date 2021/5/26
 */
@Service
public class BusinessDataServiceImpl implements IBusinessDataService {

    @Autowired
    private BusinessDataMapper businessDataMapper;
    @Autowired
    private YxdemandMapper yxdemandMapper;

    @Override
    public AjaxResult businessllist(BusinessData businessData) {

        //商务录入需求
        List<Map>  inputdemand=businessDataMapper.getinputdemandList(businessData);
        //绑定简历的
        List<Map>  bindingresume=businessDataMapper.getbindingResumeListes(businessData);
        //简历通过的？？
        businessData.setFollowStatus(3);
        List<Map>  resumepassedList=businessDataMapper.getinterviewPassedListes(businessData);
        //面试通过的
        businessData.setFollowStatus(5);
        List<Map>  interviewpassed=businessDataMapper.getinterviewPassedListes(businessData);
        //入项
        businessData.setFollowStatus(7);
        List<Map>  EntryList=businessDataMapper.getinterviewPassedListes(businessData);
        //出项人数？？
        List<Map>  OutList=businessDataMapper.getOutPeoList(businessData);

        List<BusinessData> allBusiness = businessDataMapper.getAllBusinessData();
        for(BusinessData busine:allBusiness){
            String nickName = busine.getNickName();
            //录入需求
            for (Map mp:inputdemand){
                if( mp.get("opertName")!=null){
                    String   opertName = (String) mp.get("opertName");
                    if(nickName.equals(opertName)){
                        if( mp.get("inputdataNum")!=null){
                            busine.setInputNum((Long) mp.get("inputdataNum"));
                        }
                    }
                }
            }


            //绑定简历
            for (Map mp:bindingresume){
                if( mp.get("opertName")!=null){
                    String   opertName = (String) mp.get("opertName");
                    if(nickName.equals(opertName)){
                        if( mp.get("bindingresume")!=null){
                            busine.setBindingNum((Long) mp.get("bindingresume"));
                        }
                    }
                }
            }
            //简历通过
            for (Map mp:resumepassedList){
                if( mp.get("opertName")!=null){
                    String   opertName = (String) mp.get("opertName");
                    if(nickName.equals(opertName)){
                        if( mp.get("interviewsNumber")!=null){
                            busine.setResumePassed((Long) mp.get("interviewsNumber"));
                        }
                    }
                }
            }
            //面试通过
            for (Map mp:interviewpassed){
                if( mp.get("opertName")!=null){
                    String   opertName = (String) mp.get("opertName");
                    if(nickName.equals(opertName)){
                        if( mp.get("interviewsNumber")!=null){
                            busine.setInterviewPassedNum((Long) mp.get("interviewsNumber"));
                        }
                    }
                }
            }
            //入项
            for (Map mp:EntryList){
                if( mp.get("opertName")!=null){
                    String   opertName = (String) mp.get("opertName");
                    if(nickName.equals(opertName)){
                        if( mp.get("interviewsNumber")!=null){
                            busine.setEntryPersonnelNum((Long) mp.get("interviewsNumber"));
                        }
                    }
                }
            }
            //出项
            for (Map mp:OutList){
                if( mp.get("opertName")!=null){
                    String   opertName = (String) mp.get("opertName");
                    if(nickName.equals(opertName)){
                        if( mp.get("outpeo")!=null){
                            busine.setOutPersonnelNum((Long) mp.get("outpeo"));
                        }
                    }
                }
            }

        }
        long a=0;
        for(BusinessData busine:allBusiness){
            Long outPersonnelNum = busine.getOutPersonnelNum();
            if(outPersonnelNum==null){
                busine.setOutPersonnelNum(a) ;
            }
        }

        return AjaxResult.success(allBusiness);
    }
}
