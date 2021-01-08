package com.ruoyi.statistic.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.demand.domain.BasicInfo;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.domain.MarEntryInfo;
import com.ruoyi.demand.mapper.MarCustomerprojectpayMapper;
import com.ruoyi.demand.mapper.MarDemandMapper;
import com.ruoyi.demand.mapper.MarDemandresumeMapper;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.domain.PerRobcustomer;
import com.ruoyi.resume.mapper.PerCustomerinfoMapper;
import com.ruoyi.resume.mapper.PerRobcustomerMapper;
import com.ruoyi.statistic.service.IHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IHomePageServiceImpl implements IHomePageService {


    @Autowired
    private PerRobcustomerMapper perRobcustomerMapper;
    @Autowired
    private MarDemandresumeMapper marDemandresumeMapper;
    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;
    @Autowired
    private MarDemandMapper marDemandMapper;
    @Autowired
    private PerCustomerinfoMapper perCustomerinfoMapper;




    @Override
    @Transactional
    public AjaxResult datadisplay(LoginUser loginUser) {
        Map map=new HashMap();
        map.put("AddPeople",loginUser.getUsername());
        PerCustomerinfo perCustomerinfo=new PerCustomerinfo();
        perCustomerinfo.setOpertCode(loginUser.getUsername());
        perCustomerinfo.setAddTime(new Date());
        List<PerCustomerinfo> EnterInfo = perCustomerinfoMapper.selectPerCustomerinfoByMonth(perCustomerinfo);

        List<PerRobcustomer> ListperRob = perRobcustomerMapper.selectPerRobdatadisplayList(map);
        //获取简历绑定人数
        map.put("followStatus",1);
        List<Map> ListMarbing=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
        //获取简历通过
        map.put("followStatus",3);
        List<Map> resumeadopt=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
        //获取简历面试通过
        map.put("followStatus",5);
        List<Map> interviewadopt=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
        //获取简历入项
        map.put("followStatus",7);
        List<Map> entryPeople=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
        //本月入项排行榜
        List<Map> RankingEntry=marCustomerprojectpayMapper.getRankingEntry();
        //获取分配给我的项目
        Map mapss=new HashMap();
        mapss.put("deptId",loginUser.getUser().getDeptId());
        List<MarEntryInfo> listMarEntry= marDemandMapper.selectMarDemanddsaList(mapss);
        mapss.put("bindPeople",loginUser.getUsername());
        for (MarEntryInfo marDe:listMarEntry){
            mapss.put("demandId",marDe.getDemandId());
            List<BasicInfo>  li= marDemandMapper.selectMarDem(mapss);
            marDe.setBasicInfo(li);
        }
        Map maps=new HashMap();
        maps.put("entryrobnnum",ListperRob.size());
        maps.put("bingdinnum",ListMarbing.size());
        maps.put("resumeadopt",resumeadopt.size());
        maps.put("interviewadopt",interviewadopt.size());
        maps.put("entryPeople",entryPeople.size());
        maps.put("RankingEntry",RankingEntry);
        maps.put("ListperRob",ListperRob);
        maps.put("listMarEntry",listMarEntry);
        maps.put("EnterInfosize",EnterInfo.size());
        return AjaxResult.success(maps);
    }

    @Override
    public AjaxResult viewdetailsplay(LoginUser loginUser, Integer followStatus) {
        Map map=new HashMap();
        map.put("AddPeople",loginUser.getUsername());
        if(followStatus==0){
            PerCustomerinfo perCustomerinfo=new PerCustomerinfo();
            perCustomerinfo.setOpertCode(loginUser.getUsername());
            perCustomerinfo.setAddTime(new Date());
            List<PerCustomerinfo> ListperRob = perCustomerinfoMapper.selectPerCustomerinfoByMonth(perCustomerinfo);
            return AjaxResult.success(ListperRob);
        }else if(followStatus==1){
            List<PerRobcustomer> ListperRob = perRobcustomerMapper.selectPerRobdatadisplayList(map);
            return AjaxResult.success(ListperRob);
        }else if(followStatus==2){
            //获取简历绑定人数
            map.put("followStatus",1);
            List<Map> ListMarbing=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
            return AjaxResult.success(ListMarbing);
        }else if(followStatus==3){
            //获取简历通过
            map.put("followStatus",3);
            List<Map> resumeadopt=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
            return AjaxResult.success(resumeadopt);
        }else if(followStatus==4){
            //获取简历面试通过
            map.put("followStatus",5);
            List<Map> interviewadopt=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
            return AjaxResult.success(interviewadopt);
        }else if(followStatus==5){
            //获取简历入项
            map.put("followStatus",7);
            List<Map> entryPeople=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
            return AjaxResult.success(entryPeople);
        }
        return AjaxResult.error("获取失败");

    }
}