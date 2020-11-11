package com.ruoyi.resume.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.resume.ResumeParserUtil;

import com.ruoyi.common.utils.resume.SerialNumber;
import com.ruoyi.resume.domain.*;
import com.ruoyi.resume.mapper.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.service.IPerCustomerinfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


/**
 * 简历Service业务层处理
 *
 * @author yan
 * @date 2020-10-28
 */
@Service

public class PerCustomerinfoServiceImpl implements IPerCustomerinfoService
{
    @Value("${ruoyi.profile}")
    private String profile;// (路径)
    @Autowired
    private PerCustomerinfoMapper perCustomerinfoMapper;
    @Autowired
    private PerRobcustomerMapper perRobcustomerMapper;
    @Autowired
    private PerEducationMapper perEducationMapper;
    @Autowired
    private PerWorkMapper perWorkMapper;
    @Autowired
    private PerProjectMapper perProjectMapper;
    @Autowired
    private ConOperationrecordsMapper conOperationrecordsMapper;
    @Autowired
    private PerCuscontactMapper perCuscontactMapper;




    /**
     * 查询简历
     *
     * @param customerCode 简历ID
     * @return 简历
     */
    @Override
    public AjaxResult selectPerCustomerinfoById(String customerCode)
    {
        PerCustomerinfo  perCustomerinfo= perCustomerinfoMapper.selectPerCustomerinfoById(customerCode);
        PerEducation pereducation=new PerEducation();
        pereducation.setCustomerCode(customerCode);
        List<PerEducation>  perEducList =perEducationMapper.selectPerEducationList(pereducation);
        PerWork perwork=new PerWork();
        perwork.setCustomerCode(customerCode);
        List<PerWork>  PerWorList= perWorkMapper.selectPerWorkList(perwork);
        PerProject perproject=new PerProject();
        perproject.setCustomerCode(customerCode);
        List<PerProject>  PerProjList= perProjectMapper.selectPerProjectList(perproject);
        //入项信息
        List<Map> mapList=perCustomerinfoMapper.selectIntoItem(customerCode);

        //简历跟踪状态
        PerCuscontact percuscontact=new PerCuscontact();
        percuscontact.setContactCustomercode(customerCode);
        List<PerCuscontact>  perCuscontactList=  perCuscontactMapper.selectPerCuscontactList(percuscontact);

        HashMap hashmap=new HashMap();
        hashmap.put("perCustomerinfo",perCustomerinfo);
        hashmap.put("perEducList",perEducList);
        hashmap.put("PerWorList",PerWorList);
        hashmap.put("PerProjList",PerProjList);
        hashmap.put("mapList",mapList);
        hashmap.put("perCuscontactList",perCuscontactList);
        return AjaxResult.success("hashmap",hashmap);
    }

    /**
     * 查询简历列表
     *
     * @param perCustomerinfo 简历
     * @return 简历
     */
    @Override
    public List<PerCustomerinfo> selectPerCustomerinfoList(PerCustomerinfo perCustomerinfo)
    {
        return perCustomerinfoMapper.selectPerCustomerinfoList(perCustomerinfo);
    }

    /**
     * 新增简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    @Override
    public int insertPerCustomerinfo(PerCustomerinfo perCustomerinfo)
    {
        return perCustomerinfoMapper.insertPerCustomerinfo(perCustomerinfo);
    }

    /**
     * 修改简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    @Override
    public int updatePerCustomerinfo(PerCustomerinfo perCustomerinfo)
    {
        perCustomerinfo.setUpdateTime(DateUtils.getNowDate());
        return perCustomerinfoMapper.updatePerCustomerinfo(perCustomerinfo);
    }

    /**
     * 批量删除简历
     *
     * @param customerCodes 需要删除的简历ID
     * @return 结果
     */
    @Override
    public int deletePerCustomerinfoByIds(String[] customerCodes)
    {
        return perCustomerinfoMapper.deletePerCustomerinfoByIds(customerCodes);
    }

    /**
     * 删除简历信息
     *
     * @param customerCode 简历ID
     * @return 结果
     */
    @Override
    public int deletePerCustomerinfoById(String customerCode)
    {
        return perCustomerinfoMapper.deletePerCustomerinfoById(customerCode);
    }

    //    @Transactional(rollbackFor = Exception.class)
    @Override
    @Transactional
    public AjaxResult goAnalysisResume(MultipartFile  file, Integer resumeDirection1, LoginUser loginUser) throws Exception {

        if(file == null){
            return AjaxResult.error("上传失败，文件为空");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        String fsafsa=avatar.replace("/profile","");
        File f =new File(profile+fsafsa);
        PerCustomerinfo perCustomerinfo=new PerCustomerinfo();
        JSONObject  analyticalResults =  ResumeParserUtil.resumeParser(ResumeParserUtil.URL,f);
        JSONObject contact_info   = analyticalResults.getJSONObject("parsing_result").getJSONObject("contact_info");
             String phone_number = contact_info.getString("phone_number");
             if(phone_number.equals("")){
                return AjaxResult.error("该简历无联系方式，请查证");
             }
            perCustomerinfo.setCustomerTel(phone_number);
            PerRobcustomer perrobcus = perRobcustomerMapper.selectByphone(phone_number);
            if(perrobcus!=null){
                return AjaxResult.error("客户信息已被"+perrobcus.getAddName()+"抢占");
//                return AjaxResult.error("客户信息已被"+perrobcus.getAddName()+"抢占,还未录入!");
            }
            int count = perCustomerinfoMapper.getSameCustomerCount(perCustomerinfo);
            if(count>0){
                return AjaxResult.error("客户已存在，请查证");
            }
            //教育经历
           String education_experience = analyticalResults.getJSONObject("parsing_result").getJSONArray("education_experience").toString(4);
            List<GenEducationExperience> education_experienceListArr = new ArrayList<GenEducationExperience>();
            if(!"[]".equals(education_experience)){
                education_experienceListArr = JSON.parseArray(education_experience, GenEducationExperience.class);
            }
            if(education_experienceListArr.size()<=0){
                return AjaxResult.error("简历无教育经历");
            }
            //工作经历
            String  work_experience = analyticalResults.getJSONObject("parsing_result").getJSONArray("work_experience").toString(4);
            List<GenWorkExperience> work_experienceListArr = new ArrayList<GenWorkExperience>();
            if(!"[]".equals(work_experience)){
                work_experienceListArr = JSON.parseArray(work_experience, GenWorkExperience.class);
            }
            if(work_experienceListArr.size()<=0){
                return AjaxResult.error("简历无工作经历");
            }
            //项目经验
            String  project_experience = analyticalResults.getJSONObject("parsing_result").getJSONArray("project_experience").toString(4);
            List<GenProjectExperience> project_experienceListArr = new ArrayList<GenProjectExperience>();
            if(!"[]".equals(project_experience)){
                project_experienceListArr = JSON.parseArray(project_experience, GenProjectExperience.class);
            }
            if(project_experienceListArr.size()<=0){

                return AjaxResult.error("简历无项目经验");

            }
            //基本信息解析
            JSONObject basic_info = analyticalResults.getJSONObject("parsing_result").getJSONObject("basic_info");
            String s = basic_info.getString("date_of_birth");
            if(!s.equals("")){
                String[] split = s.split("-");
                if(split.length == 1){
                    s=s+"-01-01";
                }
                if(split.length == 2){
                    s=s+"-01";
                }
            }
            perCustomerinfo.setCustomerCode(SerialNumber.createSerial("cstm", 5));//个人编号生成
            if(!basic_info.getString("name").equals("")){
                perCustomerinfo.setCustomerName(basic_info.getString("name"));//姓名
            }
            if(basic_info.getString("gender").equals("男")){//性别
                perCustomerinfo.setCustomerSex(0);
            }else if(basic_info.getString("gender").equals("女")){
                perCustomerinfo.setCustomerSex(1);
            }
            //处理简历方向
            perCustomerinfo.setResumeDirection(resumeDirection1);
            //如果入职时间为空
            if(!basic_info.getString("work_start_year").equals("")){
                perCustomerinfo.setEntryTime(sdf.parse(basic_info.getString("work_start_year")+"-07-01"));
            }
            if(!basic_info.getString("major").equals("")){
                perCustomerinfo.setCustomerSpecialities(basic_info.getString("major"));
            }
            //期望薪资
            if(!basic_info.getString("desired_salary").equals("") && !basic_info.getString("desired_salary").equals("面议")){
               perCustomerinfo.setExpectationSalary((basic_info.getString("desired_salary")));
            }
            if(!contact_info.getString("email").equals("")){
                perCustomerinfo.setEmail(contact_info.getString("email"));
            }
            if(!contact_info.getString("phone_number").equals("")){
                perCustomerinfo.setCustomerTel(contact_info.getString("phone_number"));
            }
            if(!s.equals("")){
                perCustomerinfo.setCustomerBirth(sdf.parse(s));
            }
            if(!basic_info.getString("school_name").equals("")){
                perCustomerinfo.setCustomerUniversity(basic_info.getString("school_name"));
            }
            perCustomerinfo.setAddTime(new Date());
            perCustomerinfo.setOpertCode(loginUser.getUser().getUserName());
            perCustomerinfo.setOpertName(loginUser.getUser().getNickName());
            perCustomerinfo.setResumePath(avatar);
            //插入简历基本信息
            perCustomerinfoMapper.insertPerCustomerinfo(perCustomerinfo);
            PerRobcustomer perrobcustomer=new PerRobcustomer();
            perrobcustomer.setSeizeId(UUID.randomUUID().toString());
            perrobcustomer.setCustomerTel(phone_number);
            perrobcustomer.setCustomerName(perCustomerinfo.getCustomerName());
            perrobcustomer.setAddTime(new Date());
            perrobcustomer.setAddPeople(loginUser.getUsername());
            perrobcustomer.setAddName(loginUser.getUser().getNickName());
            perrobcustomer.setStatus(0);
            //插入简历抢占信息
            perRobcustomerMapper.insertPerRobcustomer(perrobcustomer);
            PerProject cp = null;
            List<PerProject> proListArr = new ArrayList<PerProject>();
            for(GenProjectExperience arr:project_experienceListArr){
                cp= new PerProject();
                if(!arr.getProject_name().equals("")){
                    cp.setTechnologyType(arr.getProject_name());
                }
                if(!arr.getStart_time_year().equals("") && !arr.getStart_time_month().equals("")){
                    cp.setJoinprojectTime(sdf.parse(arr.getStart_time_year()+'-'+arr.getStart_time_month()+"-01"));
                }
                if(!arr.getEnd_time_year().equals("") && !arr.getEnd_time_month().equals("")){
                    cp.setQuitprojectTime(sdf.parse(arr.getEnd_time_year()+'-'+arr.getEnd_time_month()+"-01"));
                }
                if(!arr.getDescription().equals("")){
                    String zhi="";
                    String dsfsa="";
                    String description= arr.getDescription();
                    if(description.contains("\n")){
                        String dsada=  StringUtils.substringAfter(description, "\n");
                        if(dsada.contains("\n")){
                            zhi = StringUtils.substringBefore(dsada, "\n");
                            dsfsa=StringUtils.substringAfter(dsada, "\n");
                        }
                    }
                    cp.setProjectName(zhi);
                    cp.setDuty(dsfsa);
                }
                cp.setCustomerCode(perCustomerinfo.getCustomerCode());
//                perProjectMapper.insertPerProject(cp);
                proListArr.add(cp);


            }
            //简历项目经验
            perProjectMapper.insertList(proListArr);

            PerEducation ed = null;
            List<PerEducation> edulistArr = new ArrayList<PerEducation>();
            for(GenEducationExperience arr:education_experienceListArr){
                ed= new PerEducation();
                if(arr.getStill_active() != null){
                    ed.setStillActive(arr.getStill_active());
                }
                if(!arr.getStart_time_year().equals("") && !arr.getStart_time_month().equals("")){
                    ed.setStartTime(sdf.parse(arr.getStart_time_year()+'-'+arr.getStart_time_month()+"-01 00:00:00"));
                }
                if(!arr.getEnd_time_year().equals("") && !arr.getEnd_time_month().equals("")){
                    ed.setEndTime(sdf.parse(arr.getEnd_time_year()+'-'+arr.getEnd_time_month()+"-01 00:00:00"));
                }
                if(!arr.getSchool_name().equals("")){
                    ed.setSchoolName(arr.getSchool_name());
                }
                if(!arr.getSchool_level().equals("")){
                    ed.setSchoolLevel(arr.getSchool_level());
                }
                if(!arr.getStudy_model().equals("")){
                    ed.setStudyModel(arr.getStudy_model());
                }
                if(!arr.getLocation().equals("")){
                    ed.setLocation(arr.getLocation());
                }
                if(!arr.getDegree().equals("")){
                    ed.setDegree(arr.getDegree());
                }
                if(!arr.getMajor().equals("")){
                    ed.setMajor(arr.getMajor());
                }
                if(!arr.getGPA().equals("")){
                    ed.setGpa(arr.getGPA());
                }
                if(!arr.getRanking().equals("")){
                    ed.setRanking(arr.getRanking());
                }
                if(!arr.getCourses().equals("")){
                    ed.setCourses(arr.getCourses());
                }
                if(!arr.getDepartment().equals("")){
                    ed.setDepartment(arr.getDepartment());
                }
                if(!arr.getSchool_rank().equals("")){
                    ed.setSchoolRank(arr.getSchool_rank());
                }
                ed.setCustomerCode(perCustomerinfo.getCustomerCode());
                ed.setAddpeople(loginUser.getUser().getUserName());
                ed.setAddtime(new Date());
                edulistArr.add(ed);
            }
            //简历教育经验
            perEducationMapper.insertList(edulistArr);

            PerWork cw = null;
            List<PerWork> worklistArr = new ArrayList<PerWork>();
            for(GenWorkExperience arr:work_experienceListArr){
                cw = new PerWork();
                if(!arr.getStart_time_year().equals("") && !arr.getStart_time_month().equals("")){
                    cw.setStarttime(sdf.parse(arr.getStart_time_year()+'-'+arr.getStart_time_month()+"-01 00:00:00"));
                }
                if(!arr.getEnd_time_year().equals("") && !arr.getEnd_time_month().equals("")){
                    cw.setEndTime(sdf.parse(arr.getEnd_time_year()+'-'+arr.getEnd_time_month()+"-01 00:00:00"));
                }
                if(arr.getStill_active() != null){
                    cw.setStillActive(arr.getStill_active());
                }
                if(!arr.getCompany_name().equals("")){
                    cw.setCompanyName(arr.getCompany_name());
                }
                if(!arr.getDepartment().equals("")){
                    cw.setDepartment(arr.getDepartment());
                }
                if(!arr.getLocation().equals("")){
                    cw.setLocation(arr.getLocation());
                }
                if(!arr.getJob_function().equals("")){
                    cw.setJobFunction(arr.getJob_function());
                }
                if(!arr.getCompany_size().equals("")){
                    cw.setCompanySize(arr.getCompany_size());
                }
                if(!arr.getCompany_type().equals("")){
                    cw.setCompanyType(arr.getCompany_type());
                }
                if(!arr.getSalary().equals("")){
                    cw.setSalary(arr.getSalary());
                }
                if(!arr.getJob_title().equals("")){
                    cw.setJobTitle(arr.getJob_title());
                }
                cw.setCustomerCode(perCustomerinfo.getCustomerCode());
                cw.setAddpeople(loginUser.getUser().getUserName());
                cw.setAddtime(new Date());
                worklistArr.add(cw);
            }
            //简历工作经验
            perWorkMapper.insertList(worklistArr);

            //添加操作记录
            ConOperationrecords record = new ConOperationrecords();
            record.setType("1");
            record.setDatetime(new Date());
            record.setRemark("录入简历-"+perCustomerinfo.getCustomerName());
            record.setUserName(loginUser.getUser().getUserName());
            conOperationrecordsMapper.insertConOperationrecords(record);
            PerCuscontact   percuscontact=new   PerCuscontact();
            String serrialno = SerialNumber.createSerial("shzq", 6);
            percuscontact.setContacDatecode(serrialno);
            percuscontact.setContactTime(new Date());
            percuscontact.setContactUsercode(loginUser.getUser().getUserName());
            percuscontact.setContactCustomercode(perCustomerinfo.getCustomerCode());
            percuscontact.setEntryProbability(0);
            percuscontact.setUpdateStatic(5);
            percuscontact.setMemo("录入人员信息");
            perCuscontactMapper.insertPerCuscontact(percuscontact);
        HashMap dsa=new HashMap();
        dsa.put("perCustomerinfo",perCustomerinfo);
        dsa.put("work_experienceListArr",worklistArr);
        dsa.put("education_experienceListArr",edulistArr);
        dsa.put("project_experienceListArr",proListArr);
        return AjaxResult.success("map",  dsa);
    }

    @Override
    public List<PerCustomerinfo> selectlistbyNametel(PerCustomerinfo perCustomerinfo) {
        return perCustomerinfoMapper.selectlistbyNametel(perCustomerinfo);

    }

    @Override
    public AjaxResult fileUpload(MultipartFile file)  {
        try {
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            return AjaxResult.success("avatar",  avatar);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传失败");
        }

    }


}