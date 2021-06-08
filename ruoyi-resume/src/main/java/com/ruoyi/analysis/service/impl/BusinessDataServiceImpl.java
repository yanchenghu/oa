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
        List<Map>  bindingresume=businessDataMapper.getbindingResumeList(businessData);
        //面试通过的
        businessData.setFollowStatus(5);
        List<Map>  interviewpassed=businessDataMapper.getinterviewPassedList(businessData);
        //入项
        businessData.setFollowStatus(7);
        List<Map>  EntryList=businessDataMapper.getinterviewPassedList(businessData);

        List<BusinessData> allBusiness = businessDataMapper.getAllBusinessData();
        for(BusinessData busine:allBusiness){
            String nickName = busine.getNickName();

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


        }

        return AjaxResult.success(allBusiness);
    }
}
