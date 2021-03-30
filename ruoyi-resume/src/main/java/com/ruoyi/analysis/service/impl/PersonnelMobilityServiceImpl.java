package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.PersonnelMobility;
import com.ruoyi.analysis.mapper.PersonnelMobilityMapper;
import com.ruoyi.analysis.service.IPersonnelMobilityService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.resume.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 人员流动分析Impl
 * @Author ych
 * @Date 2021/3/15
 */
@Service
public class PersonnelMobilityServiceImpl implements IPersonnelMobilityService {



    @Autowired
    private PersonnelMobilityMapper personnelMobilityMapper;
    @Override
    public AjaxResult getpersonnelmobilitylist(PersonnelMobility personnelMobility) {

        Map map=new HashMap();
        map.put("endTime",personnelMobility.getRecentYears());
        map.put("startTime",DateUtils.getNMonthfirstDateforDate(12,personnelMobility.getRecentYears()));

         //本年度出项人员
         List<Map> litoutPeople=personnelMobilityMapper.getLitoutPeople(map);
        //本年度入项人员
         List<Map> litentryPeople=personnelMobilityMapper.getLitentryPeople(map);
        //当前年收入与支出
        List<PersonnelMobility> liMan=new ArrayList<>();
        for (int i=11;i>=0;i--){
            Date monthsOfAge = DateUtils.getNMonthfirstDateforDate(i,personnelMobility.getRecentYears());
            PersonnelMobility personnel=new PersonnelMobility();
            personnel.setCustomMonth(DateUtils.formatY_M_D2String(monthsOfAge,DateUtils.FORMAT_Y_M));
            liMan.add(personnel);
        }
        for(PersonnelMobility personnelMo:liMan){
            String customMonth = personnelMo.getCustomMonth();
            for (Map mp:litoutPeople){
                String  ust = (String) mp.get("customMonth");
                if(customMonth.equals(ust)){
                    Long outPeople=(Long)mp.get("outPeople");
                    personnelMo.setOutPeople(outPeople);
                    double outgradation=0.00;
                    if(mp.get("gradation")!=null){
                        outgradation=(double)mp.get("gradation");
                    }
                    personnelMo.setOutPeopleProfit(outgradation);
                }
            }
            for(Map ma:litentryPeople){
                String  ust = (String) ma.get("customMonth");
                if(customMonth.equals(ust)){
                    Long entryPeople=(Long)ma.get("entryPeople");
                    personnelMo.setEntryPeople(entryPeople);
                    double entryPeopleProfit=0.00;
                    if(ma.get("gradation")!=null){
                         entryPeopleProfit=(double)ma.get("gradation");

                    }
                    personnelMo.setEntryPeopleProfit(entryPeopleProfit);
                }
            }
        }

        //去年12个月
        List<PersonnelMobility> lastyearliMan=new ArrayList<>();
        for (int i=23;i>=12;i--){
            Date monthsOfAge = DateUtils.getNMonthfirstDateforDate(i,personnelMobility.getRecentYears());
            PersonnelMobility personnel=new PersonnelMobility();
            personnel.setCustomMonth(DateUtils.formatY_M_D2String(monthsOfAge,DateUtils.FORMAT_Y_M));
            lastyearliMan.add(personnel);
        }
        map.put("endTime",DateUtils.getNMonthfirstDateforDate(12,personnelMobility.getRecentYears()));
        map.put("startTime",DateUtils.getNMonthfirstDateforDate(24,personnelMobility.getRecentYears()));
        //去年同期出项人员
        List<Map> lastyearoutPeople=personnelMobilityMapper.getLitoutPeople(map);
        //去年同期入项人员
        List<Map> lastyearentryPeople=personnelMobilityMapper.getLitentryPeople(map);
        for (PersonnelMobility personn:lastyearliMan){
            String customMonth = personn.getCustomMonth();
            for (Map mp:lastyearoutPeople){
                String  ust = (String) mp.get("customMonth");
                if(customMonth.equals(ust)){
                    Long outPeople=(Long)mp.get("outPeople");
                    personn.setLastyearOutPeople(outPeople);
                    double outgradation=0.00;
                    if(mp.get("gradation")!=null){
                         outgradation=(double)mp.get("gradation");
                    }
                    personn.setLastyearOutPeopleProfit(outgradation);
                }
            }
            for(Map ma:lastyearentryPeople){
                String  ust = (String) ma.get("customMonth");
                if(customMonth.equals(ust)){
                    Long entryPeople=(Long)ma.get("entryPeople");
                    personn.setLastyearEntryPeople(entryPeople);

                    double entryPeopleProfit=0.00;
                    if(ma.get("gradation")!=null){
                        entryPeopleProfit=(double)ma.get("gradation");
                    }
                    personn.setLastyearEntryPeopleProfit(entryPeopleProfit);
                }
            }
        }
         //当前年入项总人数
        double entryPeopleTotle=0.00;
        //当前年出项人数
        double outPeopleTotle=0.00;
         //当前年利润增减额度
        double gradationProfitTotle=0.00;
        for(PersonnelMobility personnelMob:liMan ){
            double outPeople = personnelMob.getOutPeople();
            double entryPeople = personnelMob.getEntryPeople();

            entryPeopleTotle+=entryPeople;
            outPeopleTotle+=outPeople;

            double outPeopleProfit = personnelMob.getOutPeopleProfit();
            double entryPeopleProfit = personnelMob.getEntryPeopleProfit();
            double gradationProfit=entryPeopleProfit-outPeopleProfit;
            gradationProfitTotle+=gradationProfit;

            personnelMob.setGradationProfit(gradationProfit);
            personnelMob.setGradation(entryPeople-outPeople);
        }
        //当前年人员增减
        double gradationTotle=entryPeopleTotle-outPeopleTotle;

        //去年入项总人数
        double lastyearentryPeopleTotle=0.00;
        //去年出项人数
        double lastyearoutPeopleTotle=0.00;
        //去年利润增减额度
        double lastyeargradationProfitTotle=0.00;
        for(PersonnelMobility person:lastyearliMan ){
            double lastyearOutPeople = person.getLastyearOutPeople();
            double lastyearEntryPeople = person.getLastyearEntryPeople();

            lastyearentryPeopleTotle+=lastyearEntryPeople;
            lastyearoutPeopleTotle+=lastyearOutPeople;

            double lastyearOutPeopleProfit = person.getLastyearOutPeopleProfit();
            double lastyearEntryPeopleProfit = person.getLastyearEntryPeopleProfit();

            lastyeargradationProfitTotle+=lastyearEntryPeopleProfit-lastyearOutPeopleProfit;
            person.setLastyearGradation(lastyearEntryPeople-lastyearOutPeople);
            person.setLastyearProfit(lastyearEntryPeopleProfit-lastyearOutPeopleProfit);
        }

        //去年人员增减
        double lastyeargradationTotle=lastyearentryPeopleTotle-lastyearoutPeopleTotle;

        Map totle=new HashMap();
        totle.put("entryPeopleTotle",entryPeopleTotle);
        totle.put("outPeopleTotle",outPeopleTotle);
        totle.put("gradationProfitTotle",gradationProfitTotle);
        totle.put("gradationTotle",gradationTotle);

        totle.put("lastyearentryPeopleTotle",lastyearentryPeopleTotle);
        totle.put("lastyearoutPeopleTotle",lastyearoutPeopleTotle);
        totle.put("lastyeargradationProfitTotle",lastyeargradationProfitTotle);
        totle.put("lastyeargradationTotle",lastyeargradationTotle);

        Map totlemap=new HashMap();
        totlemap.put("totle",totle);
        totlemap.put("liMan",liMan);
        totlemap.put("lastyearliMan",lastyearliMan);
        return AjaxResult.success(totlemap);
    }
}
