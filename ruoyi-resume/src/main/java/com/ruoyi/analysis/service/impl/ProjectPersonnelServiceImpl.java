package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.ProjectPersonnel;
import com.ruoyi.analysis.mapper.ProjectPersonnelMapper;
import com.ruoyi.analysis.service.IProjectPersonnelService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.resume.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 项目人员统计实现层
 * @Author ych
 * @Date 2021/4/22
 */
@Service
public class ProjectPersonnelServiceImpl implements IProjectPersonnelService {

    @Autowired
    private ProjectPersonnelMapper projectPersonnelMapper;

    @Override
    public AjaxResult getProjectpersonnellist(ProjectPersonnel projectPersonnel) {


        List<ProjectPersonnel> list=projectPersonnelMapper.getAllCorpName(projectPersonnel);
        //本月
        Date currentDate = projectPersonnel.getCurrentDate();
        List<ProjectPersonnel> currentDatelist=projectPersonnelMapper.getAcurrentDateList(projectPersonnel);
        //上个月
        Date nMonthlastDate = DateUtils.getNMonthlastDate(currentDate, 1);
        projectPersonnel.setCurrentDate(nMonthlastDate);
        List<ProjectPersonnel> nMonthlastlist=projectPersonnelMapper.getAcurrentDateList(projectPersonnel);
        //去年本月
        Date lastYearDate = DateUtils.getNMonthlastDate(currentDate, 12);
        projectPersonnel.setCurrentDate(lastYearDate);
        List<ProjectPersonnel> lastYearDatelist=projectPersonnelMapper.getAcurrentDateList(projectPersonnel);
        for(ProjectPersonnel projectPerso:list){
            String corp_code = projectPerso.getCorpCode();
             for(ProjectPersonnel currentDat:currentDatelist){
                 String corp_code1 = currentDat.getCorpCode();
                 if(corp_code.equals(corp_code1)){
                     projectPerso.setThismonthNum(currentDat.getThismonthNum());
                 }
             }
             for(ProjectPersonnel nMonthlast:nMonthlastlist){
                 String corp_code2 = nMonthlast.getCorpCode();
                 if(corp_code.equals(corp_code2)){
                     projectPerso.setLastmonthNum(nMonthlast.getThismonthNum());
                 }
             }
             for(ProjectPersonnel lastYearr:lastYearDatelist){
                 String corp_code3 = lastYearr.getCorpCode();
                 if(corp_code.equals(corp_code3)){
                     projectPerso.setLastyearmonthNum(lastYearr.getThismonthNum());
                 }
             }
        }
        for(int i=list.size()-1;i>0;i--){
            ProjectPersonnel projectPersonnel1 = list.get(i);
            double thismonthNum = projectPersonnel1.getThismonthNum();
            double lastmonthNum = projectPersonnel1.getLastmonthNum();
            double lastyearmonthNum = projectPersonnel1.getLastyearmonthNum();
            if(thismonthNum==0 && lastmonthNum==0  && lastyearmonthNum==0){
                list.remove(i);
            }
        }

        Map ma=new HashMap();
        ma.put("list",list);
        ma.put("currentDate",DateUtils.formatY_M_D2String(currentDate,"yyyy-MM"));
        ma.put("nMonthlastDate",DateUtils.formatY_M_D2String(nMonthlastDate,"yyyy-MM"));
        ma.put("lastYearDate",DateUtils.formatY_M_D2String(lastYearDate,"yyyy-MM"));
        return AjaxResult.success(ma);
    }
}
