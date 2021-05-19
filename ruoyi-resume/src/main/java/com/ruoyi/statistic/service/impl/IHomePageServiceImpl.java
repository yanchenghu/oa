package com.ruoyi.statistic.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.resume.DateUtils;
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
import com.ruoyi.statistic.domain.BeInterviewed;
import com.ruoyi.statistic.domain.ItemsIncluded;
import com.ruoyi.statistic.service.IHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    /**
     * 首页人事数据展示
     */
    @Override
    @Transactional
    public AjaxResult datadisplay(LoginUser loginUser) {
        Map map=new HashMap();
        map.put("AddPeople",loginUser.getUsername());
        PerCustomerinfo perCustomerinfo=new PerCustomerinfo();
        perCustomerinfo.setOpertCode(loginUser.getUsername());
        int x=0;
        //获取本月录入
        List<PerCustomerinfo> EnterInfo = perCustomerinfoMapper.selectPerCustomerinfoByMonth(perCustomerinfo);


        //获取抢占跟踪
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
        //本月面试排行榜
        List<Map> InterviewEntry=marCustomerprojectpayMapper.getInterviewEntry();

        //获取分配给我的项目
        Map mapss=new HashMap();
        mapss.put("deptId",loginUser.getUser().getDeptId());
        //获取分配给我的项目的目标数绑定数
        List<MarDemand> list= marDemandMapper.selectMarDemandbindingList(mapss);
        for (MarDemand marDe:list){
            //查询当前需求绑定了多少简历
            String  DemandId= marDe.getDemandId();
            List<MarDemandresume>  li=marDemandresumeMapper.selectMarDemandresumeUpperBydemandId(DemandId);
            marDe.setIfLook(li.size());
        }

        List<MarEntryInfo> listMarEntry= marDemandMapper.selectMarDemanddsaList(mapss);
        mapss.put("bindPeople",loginUser.getUsername());
        for(int i=listMarEntry.size()-1;i>=0;i--) {
            MarEntryInfo marDe= listMarEntry.get(i);
            mapss.put("demandId",marDe.getDemandId());
            List<BasicInfo>  li= marDemandMapper.selectMarDem(mapss);
            marDe.setBasicInfo(li);
            if(li.size()==0){
                listMarEntry.remove(i);
            }
        }
        Map maps=new HashMap();
        maps.put("list",list);
        maps.put("entryrobnnum",ListperRob.size());
        maps.put("bingdinnum",ListMarbing.size());
        maps.put("resumeadopt",resumeadopt.size());
        maps.put("interviewadopt",interviewadopt.size());
        maps.put("entryPeople",entryPeople.size());
        maps.put("RankingEntry",RankingEntry);
        maps.put("InterviewEntry",InterviewEntry);
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
            List<BeInterviewed> resumeadopt=marDemandresumeMapper.selectMarDemandbyBeInterviewed(map);
            return AjaxResult.success(resumeadopt);
        }else if(followStatus==4){
            //获取简历面试通过
            map.put("followStatus",5);
            List<ItemsIncluded> interviewadopt=marDemandresumeMapper.selectMarDemandresumeItemsIncluded(map);
            return AjaxResult.success(interviewadopt);
        }else if(followStatus==5){
            //获取简历入项
            map.put("followStatus",7);
            List<Map> entryPeople=marDemandresumeMapper.selectMarDemandresumedataDisplay(map);
            return AjaxResult.success(entryPeople);
        }
        return AjaxResult.error("获取失败");

    }


    /**
     * 首页商务数据展示
     */
    @Override
    public AjaxResult businessData(LoginUser loginUser) {
        //录入需求
        MarDemand marDemand=new MarDemand();
        marDemand.setOperationuser(loginUser.getUsername());
        marDemand.setState(0);
        List<MarDemand> litmarD= marDemandMapper.selectMarDemandList(marDemand);

        Map map=new HashMap();
        map.put("userName",loginUser.getUsername());
        map.put("followStatus",1);
        map.put("lastMonth", DateUtils.getlastMonthfirstDate());
        //简历绑定
        List<Map> litmap=marDemandresumeMapper.selBinMardandPeople(map);
//        int lastmap=marDemandresumeMapper.selectlastmapBylastMonth(map);

        //简历通过
        map.put("followStatus",3);
        List<Map> litinfo=marDemandresumeMapper.selBinMardandPeople(map);
//        int lastinfo=marDemandresumeMapper.selectlastmapBylastMonth(map);
        //面试通过
        map.put("followStatus",5);
        List<Map> litview=marDemandresumeMapper.selBinMardandPeople(map);
//        int lastview=marDemandresumeMapper.selectlastmapBylastMonth(map);
        //面试入项
        map.put("followStatus",7);
        List<Map> litentry=marDemandresumeMapper.selBinMardandPeople(map);
//        int lastlitentry=marDemandresumeMapper.selectlastmapBylastMonth(map);
        //出项数据
        List<Map> litout=marDemandresumeMapper.selectlitoutPeople(map);
        int lastlitout=marDemandresumeMapper.selectlitoutNum(map);

        //本月入项排行榜
        List<Map> RankingEntry=marCustomerprojectpayMapper.getRankingEntry();
        //本月面试排行榜
        List<Map> InterviewEntry=marCustomerprojectpayMapper.getInterviewEntry();
        //我的需求
        List<MarDemand> marDWorLit= marDemandMapper.selectmarDWorLitList(map);

        Map maps=new HashMap();

        maps.put("litmarD",litmarD.size());
        maps.put("litmap",litmap.size());
        maps.put("litinfo",litinfo.size());
        maps.put("litview",litview.size());
        maps.put("litentry",litentry.size());
        maps.put("marDWorLit",marDWorLit);
        maps.put("RankingEntry",RankingEntry);
        maps.put("InterviewEntry",InterviewEntry);
        maps.put("litout",litout.size());
        maps.put("lastlitout",lastlitout);

        return AjaxResult.success(maps);
    }

    @Override
    public AjaxResult businessdetailsplay(LoginUser loginUser, Integer followStatus) {
        Map map=new HashMap();
        map.put("userName",loginUser.getUsername());
        if(followStatus==0){
            MarDemand marDemand=new MarDemand();
            marDemand.setState(0);
            marDemand.setOperationuser(loginUser.getUsername());
            List<MarDemand> litmarD= marDemandMapper.selectMarDemandList(marDemand);
            return AjaxResult.success(litmarD);
        }else if(followStatus==1){
            map.put("followStatus",1);
            List<Map> litmap=marDemandresumeMapper.selBinMardandPeople(map);
            return AjaxResult.success(litmap);
        }else if(followStatus==3){
            //简历通过
            map.put("followStatus",3);
            List<BeInterviewed> litinfo=marDemandresumeMapper.selBinCode(map);
            return AjaxResult.success(litinfo);
        }else if(followStatus==5){
            //面试通过
            map.put("followStatus",5);
            List<Map> litview=marDemandresumeMapper.selBinMardandPeople(map);
            return AjaxResult.success(litview);
        }else if(followStatus==7) {
            //面试入项
            map.put("followStatus", 7);
            List<Map> litentry = marDemandresumeMapper.selBinMardandPeople(map);
            return AjaxResult.success(litentry);
        }else if(followStatus==8) {
            //出项
            List<Map> listOut = marDemandresumeMapper.sellistOutbyuserName(map);
            return AjaxResult.success(listOut);
        }
        return AjaxResult.error("获取失败");
    }

    @Override
    public AjaxResult partjoblist(LoginUser loginUser) {
        Map map=new HashMap();
        map.put("AddPeople",loginUser.getUsername());
        PerCustomerinfo perCustomerinfo=new PerCustomerinfo();
        perCustomerinfo.setOpertCode(loginUser.getUsername());
        int x=0;
        //获取本月录入
        List<PerCustomerinfo> EnterInfo = perCustomerinfoMapper.selectPerCustomerinfoByMonth(perCustomerinfo);


        //获取抢占跟踪
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

        //获取分配给我的项目
        Map mapss=new HashMap();
        mapss.put("deptId",loginUser.getUser().getDeptId());
        List<MarEntryInfo> listMarEntry= marDemandMapper.selectMarDemanddsaList(mapss);
        mapss.put("bindPeople",loginUser.getUsername());
        for(int i=listMarEntry.size()-1;i>=0;i--) {
            MarEntryInfo marDe= listMarEntry.get(i);
            mapss.put("demandId",marDe.getDemandId());
            List<BasicInfo>  li= marDemandMapper.selectMarDem(mapss);
            marDe.setBasicInfo(li);
            if(li.size()==0){
                listMarEntry.remove(i);
            }
        }
        Map maps=new HashMap();
        maps.put("entryrobnnum",ListperRob.size());
        maps.put("bingdinnum",ListMarbing.size());
        maps.put("resumeadopt",resumeadopt.size());
        maps.put("interviewadopt",interviewadopt.size());
        maps.put("entryPeople",entryPeople.size());

        maps.put("ListperRob",ListperRob);
        maps.put("listMarEntry",listMarEntry);
        maps.put("EnterInfosize",EnterInfo.size());

        return AjaxResult.success(maps);
    }
}
