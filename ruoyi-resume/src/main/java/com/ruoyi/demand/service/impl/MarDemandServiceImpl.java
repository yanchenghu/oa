package com.ruoyi.demand.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.resume.DateUtils;
import com.ruoyi.common.utils.resume.DingDingJiQi;
import com.ruoyi.common.utils.resume.DingUtil;
import com.ruoyi.common.utils.resume.SerialNumber;
import com.ruoyi.conn.domain.ConDingtoken;
import com.ruoyi.conn.domain.ConOperationrecords;
import com.ruoyi.conn.mapper.ConDingtokenMapper;
import com.ruoyi.conn.mapper.ConOperationrecordsMapper;
import com.ruoyi.customer.domain.MarContract;
import com.ruoyi.customer.mapper.MarContractMapper;
import com.ruoyi.customer.mapper.YxdemandMapper;
import com.ruoyi.demand.domain.*;
import com.ruoyi.demand.mapper.*;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.mapper.PerCustomerinfoMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.tool.WordUtils;
import com.ruoyi.tool.WorkDay;
import com.taobao.api.ApiException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.service.IMarDemandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 需求Service业务层处理
 * 
 * @author ych
 * @date 2020-11-27
 */
@Service
public class MarDemandServiceImpl implements IMarDemandService
{
    @Value("${ruoyi.template}")
    private String template;// 模板(路径)

    @Autowired
    private MarDemandMapper marDemandMapper;

    @Autowired
    private MarDemandresumeMapper marDemandresumeMapper;
    @Autowired
    private MarDemandresumefollowMapper marDemandresumefollowMapper;
    @Autowired
    private MarSignMapper marSignMapper;
    @Autowired
    private ConOperationrecordsMapper conOperationrecordsMapper;
    @Autowired
    private PerCustomerinfoMapper perCustomerinfoMapper;

    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;
    @Autowired
    private ConDingtokenMapper conDingtokenMapper;

    @Autowired
    private MarWaitingentryMapper marWaitingentryMapper;
    @Autowired
    private WorkDay workDay;
    @Autowired
    private MarContractMapper marContractMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private YxdemandMapper yxdemandMapper;
    @Autowired
    private  MarWaitinginterviewMapper marWaitinginterviewMapper;

    @Autowired
    private MarDemandRequirementMapper marDemandRequirementMapper;
    @Autowired
    private MarDemandresumeRequirementMapper marDemandresumeRequirementMapper;
    @Autowired
    private PerEnclosureCustomerinfoMapper perEnclosureCustomerinfoMapper;
    @Autowired
    private  PerEnclosureEducationMapper perEnclosureEducationMapper;
    @Autowired
    private PerEnclosureProjectMapper perEnclosureProjectMapper;
    @Autowired
    private   PerEnclosureSkillsMapper perEnclosureSkillsMapper;
    @Autowired
    private PerEnclosureWorkMapper perEnclosureWorkMapper;


    /**
     * 查询需求
     * @param
     * @return 需求
     */
    @Override
    public Map selectMarDemandById(String demandId)
    {
        Map map=new HashMap();
        MarSign marSign=new MarSign();
        marSign.setDemandId(demandId);
        List<MarSign> Signlis=marSignMapper.selectMarSignList(marSign);
        MarDemand marDeman=marDemandMapper.selectMarDemandById(demandId);
        MarDemandRequirement marDemandRequirement=new MarDemandRequirement();
        marDemandRequirement.setDemandId(demandId);
        List<MarDemandRequirement> marDemandRequirements = marDemandRequirementMapper.selectMarDemandRequirementList(marDemandRequirement);
        map.put("Signlis",Signlis);
        map.put("marDeman",marDeman);
        map.put("marDemandRequirements",marDemandRequirements);
        return map;
    }

    @Override
    public Map copydemand(String demandId) {
        Map map=new HashMap();
        MarSign marSign=new MarSign();
        marSign.setDemandId(demandId);
        List<MarSign> Signlis=marSignMapper.selectMarSignList(marSign);
        MarDemand marDeman=marDemandMapper.selectMarDemandById(demandId);
        MarDemandRequirement marDemandRequirement=new MarDemandRequirement();
        marDemandRequirement.setDemandId(demandId);
        List<MarDemandRequirement> marDemandRequirements = marDemandRequirementMapper.selectMarDemandRequirementList(marDemandRequirement);
        for (MarDemandRequirement marDemandR:marDemandRequirements){
            marDemandR.setId(null);
            marDemandR.setDemandId("");
        }
        map.put("Signlis",Signlis);
        map.put("marDeman",marDeman);
        map.put("marDemandRequirements",marDemandRequirements);
        return map;
    }

    /**
     * 查询需求列表
     * 
     * @param marDemand 需求
     * @return 需求
     */
    @Override
    public List<MarDemand> selectMarDemandList(MarDemand marDemand, LoginUser loginUser)
    {

        marDemand.setOperationuser(loginUser.getUsername());
        List<MarDemand>  list=marDemandMapper.selectMarDemandList(marDemand);
        for (MarDemand marDe:list){
        //查询当前需求绑定了多少简历
        MarDemandresume marDema=new MarDemandresume();
        marDema.setDemandId(marDe.getDemandId());
        String  DemandId= marDe.getDemandId();
        List<MarDemandresume>  li=marDemandresumeMapper.selectMarDemandresumeUpperBydemandId(DemandId);
        marDe.setIfLook(li.size());
        //查询当前需求面试通过多少
        marDema.setDownloadStatus(5);
        List<MarDemandresume>  lifoll=marDemandresumefollowMapper.selectMarDemandList(marDema);
        marDe.setChsiFlag(lifoll.size());
        //查询当前需求入项
        marDema.setDownloadStatus(7);
        List<MarDemandresume>  lif=marDemandresumefollowMapper.selectMarDemandList(marDema);
        marDe.setCoopnature(lif.size());
        //岗位要求
        MarDemandRequirement marDemandRequirement=new MarDemandRequirement();
        marDemandRequirement.setDemandId(marDe.getDemandId());
        List<MarDemandRequirement> marDemandRequirements = marDemandRequirementMapper.selectMarDemandRequirementList(marDemandRequirement);
        marDe.setMarDemandRequirementList(marDemandRequirements);

        }
        return list;
    }

    /**
     * 新增需求
     * 
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertMarDemand(String zm, MultipartFile demandPic, LoginUser loginUser)
    {
        String fsafsa="";
        if(demandPic!=null){
            try {
                fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), demandPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        List<Integer> liStr = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        MarDemand marDemand = JSON.parseObject(JSON.parseObject(zm).getString("marDemand"), MarDemand.class);
        //岗位要求
        List<MarDemandRequirement> ListmarDema = JSON.parseArray(JSON.parseObject(zm).getString("marDemandRequirement"), MarDemandRequirement.class);

        MarDemand sd=marDemandMapper.selectMarDemandByName(marDemand.getProjectName());
        if(sd!=null){
            return AjaxResult.error("當前需求已存在");
        }
        String demandId=SerialNumber.createSerial("demd-", 5);
        List<MarSign> work=new ArrayList<MarSign>();
        for( Integer sk:liStr){
            MarSign marSi=new MarSign();
            marSi.setDemandId(demandId);
            marSi.setDeptId(sk);
            marSi.setAddTime(new Date());
            work.add(marSi);
        }
        if(work.size()>0){
            marSignMapper.insertMList(work);
        }else {
            return AjaxResult.error("没有选择下包商");
        }
        for (MarDemandRequirement marDemandRequirement:ListmarDema ){
            marDemandRequirement.setDemandId(demandId);
            marDemandRequirement.setAddTime(new Date());

        }
        if(ListmarDema.size()>0){
            marDemandRequirementMapper.insertMListmarDema(ListmarDema);
        }else {
         return AjaxResult.error("岗位要求为空");
        }




        marDemand.setDemandPic(fsafsa);
        marDemand.setDemandId(demandId);
        marDemand.setOperationuser(loginUser.getUsername());
        marDemand.setOperUsername(loginUser.getUser().getNickName());
        marDemand.setAddTime(new Date());
        marDemandMapper.insertMarDemand(marDemand);

        return AjaxResult.success("添加需求成功");
    }

    /**
     * 修改需求
     * 
     * @param zm 需求
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateMarDemand(String zm, MultipartFile demandPic, LoginUser loginUser)
    {
        List<Integer> liStr = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        MarDemand marDemand = JSON.parseObject(JSON.parseObject(zm).getString("marDemand"), MarDemand.class);
        //岗位要求
        List<MarDemandRequirement> ListmarDema = JSON.parseArray(JSON.parseObject(zm).getString("marDemandRequirement"), MarDemandRequirement.class);
        String demandId=marDemand.getDemandId();

        if(liStr.size()<=0){
            return AjaxResult.error("没有选择下包商");
        }
        MarDemand sd=marDemandMapper.selectMarDemandByName(marDemand.getProjectName());
        if(sd!=null){
            if(!sd.getDemandId().equals(marDemand.getDemandId())){
                return AjaxResult.error("當前需求已存在");
            }
        }
        //如果图片不为空的话，则重新上传图片
        String fsafsa="";
        if(demandPic != null){
            try {
                fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), demandPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        marDemand.setDemandPic(fsafsa);
        int b=marSignMapper.deleteMarSignBy(demandId);
        if(b>0){
            List<MarSign> work=new ArrayList<MarSign>();
            for( Integer sk:liStr){
                MarSign marSi=new MarSign();
                marSi.setDemandId(demandId);
                marSi.setDeptId(sk);
                marSi.setAddTime(new Date());
                work.add(marSi);
            }
                marSignMapper.insertMList(work);
        }
        int a=marDemandMapper.updateMarDemand(marDemand);
        if (a==1){
            return AjaxResult.success("修改成功");
        }else{
            return AjaxResult.error("修改失敗");
        }


    }

    /**
     * 批量删除需求
     * 
     * @param ids 需要删除的需求ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandByIds(String[] ids)
    {
        return marDemandMapper.deleteMarDemandByIds(ids);
    }

    /**
     * 删除需求信息
     * 
     * @param id 需求ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandById(String id)
    {
        return marDemandMapper.deleteMarDemandById(id);
    }

    /**
     * 需求查重
     */
    @Override
    public int selDemandDuplicate(String projectName,String demandId) {
        MarDemand sd = marDemandMapper.selectMarDemandByName(projectName);
        if(StringUtils.isEmpty(demandId)){
            if (sd != null) {
                return 2;
            }else{
                return 1;
            }
        }else{
            if (sd != null) {
                if (!sd.getDemandId().equals(demandId)) {
                    return 2;
                }else{
                     return 1;
                }
            }else{
                return 1;
            }

        }


    }
    /**
     * 需求关闭
     */
    @Override
    @Transactional
    public AjaxResult demandClosure(String demandId, LoginUser loginUser) {
        MarDemand marDema= marDemandMapper.selectMarDemandById(demandId);
        if (marDema!=null){
            if(!marDema.getOperationuser().equals(loginUser.getUsername())){
                return AjaxResult.error("你不是该需求的持有人，不可以关闭需求");
            }
        }else{
            return AjaxResult.error("该需求不存在");
        }
        if(marDema.getState()==1){
            return AjaxResult.error("该需求已经在关闭状态了，不需要关闭");
        }
        //需求下面是否存在未处理的
        List<String> liStr=marDemandMapper.seluntreatedResume(demandId);
        if(null != liStr && !liStr.isEmpty()){
            return AjaxResult.error("当前需求下的"+liStr.toString()+"还未完成操作，不可关闭");

        }
        MarDemand marDe=new MarDemand();
        marDe.setDemandId(demandId);
        marDe.setState(1);
        marDe.setCloseDate(new Date());
        int as=marDemandMapper.updateMarDemand(marDe);
        if(as!=1){
            return AjaxResult.error("关闭失败");

        }
        return AjaxResult.success("关闭成功");
    }
    /**
     * 需求开启
     */
    @Override
    public AjaxResult demandOpen(String demandId, LoginUser loginUser) {
        MarDemand marDema= marDemandMapper.selectMarDemandById(demandId);
        if (marDema!=null){
            if(!marDema.getOperationuser().equals(loginUser.getUsername())){
                return AjaxResult.error("你不是该需求的持有人，不可以关闭需求");
            }
        }else{
            return AjaxResult.error("该需求不存在");
        }
        if(marDema.getState()==0){
            return AjaxResult.error("该需求已经在开启状态了，不需要开启");
        }


        MarDemand marDe=new MarDemand();
        marDe.setDemandId(demandId);
        marDe.setState(0);
        int as=marDemandMapper.updateMarDemand(marDe);
        if(as!=1){
            return AjaxResult.error("开启失败");

        }
        return AjaxResult.success("开启成功");

    }
    /**
     * 需求绑定表的查询
     */
    @Override
    public List<MarDemand> selectMarDemandbindingList(MarDemand marDemand, LoginUser loginUser) {
        Map map=new HashMap();
        map.put("deptId",loginUser.getUser().getDeptId());
        map.put("projectName",marDemand.getProjectName());
        map.put("technologyDirection",marDemand.getTechnologyDirection());
        map.put("demandYears",marDemand.getDemandYears());
        map.put("projectLocation",marDemand.getProjectLocation());
        map.put("importantLevel",marDemand.getImportantLevel());
        map.put("corpCode",marDemand.getCorpCode());
        List<MarDemand> list= marDemandMapper.selectMarDemandbindingList(map);

        for (MarDemand marDe:list){
            MarDemandresume marDema=new MarDemandresume();
            //查询当前需求绑定了多少简历
            String  DemandId= marDe.getDemandId();
            List<MarDemandresume>  li=marDemandresumeMapper.selectMarDemandresumeUpperBydemandId(DemandId);
            marDe.setIfLook(li.size());
            //查询当前需求面试通过多少
            marDema.setDownloadStatus(5);
            List<MarDemandresume>  lifoll=marDemandresumefollowMapper.selectMarDemandList(marDema);
            marDe.setChsiFlag(lifoll.size());
            //查询当前需求入项
            marDema.setDownloadStatus(7);
            List<MarDemandresume>  lif=marDemandresumefollowMapper.selectMarDemandList(marDema);
            marDe.setCoopnature(lif.size());
            //岗位要求
            MarDemandRequirement marDemandRequirement=new MarDemandRequirement();
            marDemandRequirement.setDemandId(marDe.getDemandId());
            List<MarDemandRequirement> marDemandRequirements = marDemandRequirementMapper.selectMarDemandRequirementList(marDemandRequirement);
            marDe.setMarDemandRequirementList(marDemandRequirements);

        }
        return list;
    }
    /**
     * 简历绑定需求
     */
    @Override
    @Transactional
    public AjaxResult resumeBingDemand(LoginUser loginUser,String zm) {
        String customerCode = JSON.parseObject(zm).getString("customerCode");
        String demandId = JSON.parseObject(zm).getString("demandId");
        if(StringUtils.isEmpty(customerCode)||StringUtils.isEmpty(demandId)){
            return AjaxResult.error("绑定简历ID为空，或者需求ID为空，请联系管理员");
        }
        //岗位要求
        List<MarDemandresumeRequirement> listresumeRequirement = JSON.parseArray(JSON.parseObject(zm).getString("marDemandresumeRequirement"), MarDemandresumeRequirement.class);
        //基本信息
        PerEnclosureCustomerinfo perEnclosureCustomerinfo = JSON.parseObject(JSON.parseObject(zm).getString("perEnclosureCustomerinfo"), PerEnclosureCustomerinfo.class);
        if(perEnclosureCustomerinfo==null){
            return AjaxResult.error("基本信息为空,请联系管理员");

        }
        perEnclosureCustomerinfo.setCustomerCode(customerCode);
        //工作经验
        List<PerEnclosureWork> perEnclosureWorkList = JSON.parseArray(JSON.parseObject(zm).getString("perEnclosureWorkList"), PerEnclosureWork.class);
        if(perEnclosureWorkList.size()<1){
            return AjaxResult.error("工作经验最少一个");
        }
        //项目经验
        List<PerEnclosureProject> perEnclosureProjectList = JSON.parseArray(JSON.parseObject(zm).getString("perEnclosureProject"), PerEnclosureProject.class);
        if(perEnclosureProjectList.size()<2){
            return AjaxResult.error("项目经验最少两个");
        }
        //教育背景
        List<PerEnclosureEducation> perEnclosureEducationList = JSON.parseArray(JSON.parseObject(zm).getString("perEnclosureEducation"), PerEnclosureEducation.class);
        if(perEnclosureEducationList.size()<1){
            return AjaxResult.error("专业技能最少一个");
        }
        //专业技能
        List<PerEnclosureSkills> perEnclosureSkillsList = JSON.parseArray(JSON.parseObject(zm).getString("perEnclosureSkills"), PerEnclosureSkills.class);
       if(perEnclosureSkillsList.size()<2){
             return AjaxResult.error("专业技能最少两个");
       }

        MarDemand marDemand = marDemandMapper.selectMarDemandById(demandId);
        PerCustomerinfo perCustomerinfo = perCustomerinfoMapper.selectPerCustomerinfoById(customerCode);
        // 保存简历模板
        Map ma =new HashMap();
        ma.put("customerName",perCustomerinfo.getCustomerName());
        ma.put("workYear",perEnclosureCustomerinfo.getWorkYear());
        ma.put("professionId",perEnclosureCustomerinfo.getProfessionId());
        ma.put("customerAge",perEnclosureCustomerinfo.getCustomerAge());
        ma.put("selfEvaluation",perEnclosureCustomerinfo.getSelfEvaluation());
        ma.put("customerSex",perEnclosureCustomerinfo.getCustomerSex()>0?"女":"男");
        ma.put("education", DictUtils.getDictLabel("per_customerinfo_education",perEnclosureCustomerinfo.getEducation().toString()));

        ma.put("workList",perEnclosureWorkList);
        ma.put("projectList",perEnclosureProjectList);
        ma.put("educationList",perEnclosureEducationList);
        ma.put("skillsList",perEnclosureSkillsList);
        String downLoadName = perCustomerinfo.getCustomerName() +"_"+ marDemand.getProjectName() + ".doc";  /*默认为word格式*/
        String templateName = "个人基本.xml";
        String downloadWordByTemplate="";
        try {
            downloadWordByTemplate = WordUtils.downloadWordByTemplate(ma, template, templateName, downLoadName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isEmpty(downloadWordByTemplate)){
            return AjaxResult.error("附件简历保存异常，请联系管理员");
        }
        //查询是否有简历附件,如果没有附件覆盖简历原件
        if(StringUtils.isEmpty(perCustomerinfo.getResumePath())){
            perCustomerinfo.setResumePath(downloadWordByTemplate);
            perCustomerinfoMapper.updatePerCustomerinfo(perCustomerinfo);
        }
        PerEnclosureCustomerinfo perEnclo = perEnclosureCustomerinfoMapper.selectPerEnclosureCustomerinfoById(customerCode);
        if (perEnclo!= null) {
            perEnclo.setCustomerCode(customerCode);
            perEnclosureCustomerinfoMapper.updatePerEnclosureCustomerinfo(perEnclo);
        }else{
            perEnclosureCustomerinfoMapper.insertPerEnclosureCustomerinfo(perEnclosureCustomerinfo);
            for(PerEnclosureWork perEnclosureWork:perEnclosureWorkList){
                perEnclosureWork.setCustomerCode(customerCode);
                perEnclosureWorkMapper.insertPerEnclosureWork(perEnclosureWork);
            }
            for(PerEnclosureProject perEnclosureProject:perEnclosureProjectList){
                perEnclosureProject.setCustomerCode(customerCode);
                perEnclosureProjectMapper.insertPerEnclosureProject(perEnclosureProject);
            }
            for(PerEnclosureEducation perEnclosureEducation:perEnclosureEducationList){
                perEnclosureEducation.setCustomerCode(customerCode);
                perEnclosureEducationMapper.insertPerEnclosureEducation(perEnclosureEducation);
            }

            for(PerEnclosureSkills perEnclosureSkills:perEnclosureSkillsList){
                perEnclosureSkills.setCustomerCode(customerCode);
                perEnclosureSkillsMapper.insertPerEnclosureSkills(perEnclosureSkills);
            }

        }


        Map map=new HashMap();
        map.put("demandId",demandId);


        Date date=new Date();
        String demandresumeId=UUID.randomUUID().toString();
        MarDemandresume marde=new MarDemandresume();
        marde.setId(demandresumeId);
        marde.setDemandId(demandId);
        marde.setCustomerCode(customerCode);
        marde.setBindTime(date);
        marde.setBindPeople(loginUser.getUsername());
        marde.setNewfollowtime(date);
        marde.setTrackzPeoname(loginUser.getUser().getNickName());
        marde.setResumeEnclosurepath(downloadWordByTemplate);
        marDemandresumeMapper.insertMarDemandresume(marde);
        //添加简历跟进记录
            MarDemandresumefollow marDemFoll=new MarDemandresumefollow();
            marDemFoll.setDemandresumeId(demandresumeId);
            marDemFoll.setTrackingPeople(loginUser.getUsername());
            marDemFoll.setTrackingtime(date);
            marDemFoll.setFollowStatus(1);
            marDemFoll.setFollowDetail("简历绑定");
         marDemandresumefollowMapper.insertMarDemandresumefollow(marDemFoll);
        //添加岗位需求
        for(MarDemandresumeRequirement marDemandresumeRequirement:listresumeRequirement){
            marDemandresumeRequirement.setDemandresumeId(demandresumeId);
            marDemandresumeRequirementMapper.insertMarDemandresumeRequirement(marDemandresumeRequirement);
        }
             //添加操作记录
            ConOperationrecords conOperationrec=new ConOperationrecords();
            conOperationrec.setType(4);
            conOperationrec.setDateTime(date);
            conOperationrec.setUserName(loginUser.getUsername());
            conOperationrec.setRemark("简历绑定");
        conOperationrecordsMapper.insertConOperationrecords(conOperationrec);

        ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
        if(cotoken==null){
            cotoken=new ConDingtoken();
            try {
                JSONObject jsonToken = DingUtil.getAccessToken(DingUtil.TOKEN_URL);
                cotoken.setToken( jsonToken.getString("access_token"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        SysUser sysUser = sysUserMapper.selectUserByUserName(marDemand.getOperationuser());
        String dinguserid = sysUser.getDinguserid();//用户钉钉
        try {
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+dinguserid,
                    loginUser.getUser().getNickName()+"将 "+perCustomerinfo.getCustomerName()+" 绑定到你的需求"+marDemand.getProjectName()+" 请及时跟进");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success("绑定成功");

    }
    /**
     * 需求已绑定简历列表
     */
    @Override
    public  List<Map>  demandDetailsList( String demandId ,String customerName) {
        Map map=new HashMap();
        map.put("demandId",demandId);
        map.put("customerName",customerName);
        List<Map>  list=marDemandresumefollowMapper.selectDemandDetailsList(map);
        MarDemandresumeRequirement marDemandresumeRequirement=new MarDemandresumeRequirement();
        for(Map ma:list){
           String id= (String) ma.get("id");
            marDemandresumeRequirement.setDemandresumeId(id);
            List<MarDemandresumeRequirement> t1 = marDemandresumeRequirementMapper.selectMarDemandresumeRequirementList(marDemandresumeRequirement);
            ma.put("postlist",t1);
        }
        return list;
    }
    /**
     *需求已绑定简历跟踪
     */
    @Override
    @Transactional
    public AjaxResult demandResumeTrack(MarDemandresumefollow marDemandresumefollow, LoginUser loginUser)  {

          if(StringUtils.isEmpty(marDemandresumefollow.getDemandresumeId())){
            return AjaxResult.error("id为空,不可跟踪，请联系管理员");
        }
          Date date=new Date();
          MarDemandresume marDemandres=new MarDemandresume();
          marDemandres.setId(marDemandresumefollow.getDemandresumeId());
          marDemandres.setNewfollowtime(date);
            int a=marDemandresumeMapper.updateMarDemandresume(marDemandres);
            if(marDemandresumefollow.getFollowStatus()==3) {

         //添加待面试信息并且钉钉提醒
         MarDemandresume marDemandresume = marDemandresumeMapper.selectMarDemandresumeById(marDemandresumefollow.getDemandresumeId());
         MarWaitinginterview  marWaitingi=new MarWaitinginterview();
         marWaitingi.setNickName(marDemandresume.getTrackzPeoname());
         marWaitingi.setUserName(marDemandresume.getBindPeople());
         marWaitingi.setCustomerCode(marDemandresume.getCustomerCode());
         marWaitingi.setDemandId(marDemandresume.getDemandId());
         marWaitingi.setBeginTime(DateUtils.strToTime(marDemandresumefollow.getBeginTime(),DateUtils.FORMAT_Y_M_D_H_M_S)        );
         marWaitingi.setEndTime(DateUtils.strToTime(marDemandresumefollow.getEndTime(),DateUtils.FORMAT_Y_M_D_H_M_S));
         marWaitingi.setInterviewMode(marDemandresumefollow.getRemark1());
         marWaitingi.setInterviewLocation(marDemandresumefollow.getInterviewLocation());
         marWaitingi.setInterviewContact(marDemandresumefollow.getInterviewContact());
         marWaitingi.setBeCareful(marDemandresumefollow.getBeCareful());
         marWaitingi.setInsertTime(new Date());
         marWaitinginterviewMapper.insertMarWaitinginterview(marWaitingi);
        ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
        if(cotoken==null){
            cotoken=new ConDingtoken();

            try {
                JSONObject jsonToken = DingUtil.getAccessToken(DingUtil.TOKEN_URL);
                cotoken.setToken(jsonToken.getString("access_token"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SysUser sysUser = sysUserMapper.selectUserByUserName(marDemandresume.getBindPeople());
        String dinguserid = sysUser.getDinguserid();//用户钉钉
        MarDemand marDemand = marDemandMapper.selectMarDemandById(marDemandresume.getDemandId());
        PerCustomerinfo perCustomerinfo = perCustomerinfoMapper.selectPerCustomerinfoById(marDemandresume.getCustomerCode());
        try {
            String beCareful = marDemandresumefollow.getBeCareful();
            if(StringUtils.isEmpty(beCareful)){
                beCareful="无";
            }

            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+dinguserid,
                    "你绑定"+marDemand.getProjectName()+"项目下的："+perCustomerinfo.getCustomerName()+"简历通过，确定为："+marDemandresumefollow.getRemark1()+"，请及时沟通选定具体面试时间，面试时间范围为："+marDemandresumefollow.getBeginTime()+"至"+
                            marDemandresumefollow.getEndTime()+"并填入OA平台，注意事项："+beCareful);

//       System.out.println("你绑定"+marDemand.getProjectName()+"项目下的："+perCustomerinfo.getCustomerName()+"简历通过，确定为："+marDemandresumefollow.getRemark1()+"，请及时沟通选定具体面试时间，面试时间范围为："+marDemandresumefollow.getBeginTime()+"至"+
//              marDemandresumefollow.getEndTime()+"并填入OA平台，注意事项："+beCareful);
        } catch (Exception e) {
            e.printStackTrace();
        }

        }else if(marDemandresumefollow.getFollowStatus()==5){ //添加待入项信息
              MarDemandresume marDemandresume = marDemandresumeMapper.selectMarDemandresumeById(marDemandresumefollow.getDemandresumeId());
              MarWaitingentry  marWaiting=new MarWaitingentry();
              marWaiting.setDemandId(marDemandresume.getDemandId());
              marWaiting.setCustomerCode(marDemandresume.getCustomerCode());
              List<MarWaitingentry> marWaitingentries = marWaitingentryMapper.selectMarWaitingentryList(marWaiting);
              marWaiting.setInterviewTime(date);
              marWaiting.setStayTime(marDemandresumefollow.getTrackingtime());
              marWaiting.setNickName(marDemandresume.getTrackzPeoname());
              marWaiting.setInsertTime(new Date());
              if(marWaitingentries.size()>0){
                  marWaitingentryMapper.updateMarWaitingentryby(marWaiting);
              }else{
                  marWaitingentryMapper.insertMarWaitingentry(marWaiting);
              }
          }
          if(a==1){
              marDemandresumefollow.setTrackingtime(date);
              marDemandresumefollow.setTrackingPeople(loginUser.getUsername());
              marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
              return AjaxResult.success("跟踪成功");
          }else {
              return AjaxResult.error("跟踪失败");
          }
    }
    /**
     *根据绑定简历获取入项信息的基本
     */
    @Override
    public AjaxResult getInputInformation(String id) {
       Map map=marDemandresumeMapper.getInputInformation(id);
       if(map==null){
           return AjaxResult.success("未获取入项信息基本信息，请联系管理员");
       }
       return AjaxResult.success(map);
    }
    /**
     *人员安排入项
     */
    @Override
    @Transactional
    public AjaxResult entryPersonnel(MarCustomerprojectpay marCustomerprojectpay, SysUser user,LoginUser loginUser) {
        MarCustomerprojectpay marCustompay=marCustomerprojectpayMapper.isInitem(marCustomerprojectpay.getCustomerCode());
       if(marCustompay!=null){
           return AjaxResult.error("当前人已经在项，不可重复入项");
       }
        //更改跟踪状态
        if(StringUtils.isEmpty(marCustomerprojectpay.getId())){
            return AjaxResult.error("id为空,不可跟踪，请联系管理员");
        }
        Date date=new Date();
        MarDemandresume marDemandres=new MarDemandresume();
        marDemandres.setId(marCustomerprojectpay.getId());
        marDemandres.setNewfollowtime(date);
        MarDemandresumefollow marDemandresumefollow=new MarDemandresumefollow();
        int a =marDemandresumeMapper.updateMarDemandresume(marDemandres);
        if(a!=1){
            return AjaxResult.error("入项失败，请联系管理员");
        }
        marDemandresumefollow.setDemandresumeId(marCustomerprojectpay.getId());
        marDemandresumefollow.setFollowStatus(7);
        marDemandresumefollow.setTrackingtime(date);
        marDemandresumefollow.setTrackingPeople(loginUser.getUsername());
        marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);


        marCustomerprojectpay.setId(UUID.randomUUID().toString());
        marCustomerprojectpay.setOperTime(new Date());
        marCustomerprojectpayMapper.insertMarCustomerprojectpay(marCustomerprojectpay);


        PerCustomerinfo perCustomeri=perCustomerinfoMapper.selectPerCustomerinfoById(marCustomerprojectpay.getCustomerCode());
        //入项成功更改个人简历信息
        ConOperationrecords operationrecords=new ConOperationrecords();
        operationrecords.setType(5);
        operationrecords.setDateTime(new Date());
        operationrecords.setUserName(marCustomerprojectpay.getOpercode());
        operationrecords.setRemark("入项-"+perCustomeri.getCustomerName());
        conOperationrecordsMapper.insertConOperationrecords(operationrecords);
        if (perCustomeri.getJoinStatus()>2){
            perCustomeri.setJoinStatus(4);
        }else{
            perCustomeri.setJoinStatus(2);
        }
        perCustomerinfoMapper.updatePerCustomerinfo(perCustomeri);
        ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(2);
        //钉钉提醒
        if(cotoken!=null){
            try {
                DingDingJiQi.DingDingd("喜报！！！恭喜，"+user.getNickName()+"成功入项一名人员",cotoken.getToken());
            } catch (ApiException e) {
                return AjaxResult.error("入项失败，钉钉发送失败,请联系管理员");
            }
        }


        return AjaxResult.success("入项成功");
    }
    /**
     *批量简历、面试、通过
     */
    @Override
    public AjaxResult batchOperation(String zm, LoginUser loginUser) {
        List<String> listDemand = JSON.parseArray(JSON.parseObject(zm).getString("list"), String.class);
        Integer type=JSON.parseObject(zm).getInteger("type");
        String followDetail=JSON.parseObject(zm).getString("followDetail");
        String Remark1=JSON.parseObject(zm).getString("remark1");
        String beCareful=JSON.parseObject(zm).getString("beCareful");

        String interviewLocation=JSON.parseObject(zm).getString("interviewLocation");
        String interviewContact=JSON.parseObject(zm).getString("interviewContact");
        Date stayTime= JSON.parseObject(zm).getDate("trackingtime");


        if(type==null){
            return AjaxResult.error("传入类型为空");
        }
        if(listDemand.size()<=0){
            return AjaxResult.error("没有选中绑定简历");
        }

        for (String Id:listDemand){
            Date date=new Date();
            MarDemandresume marDemandres=new MarDemandresume();
            marDemandres.setId(Id);
            marDemandres.setNewfollowtime(date);
            int a=marDemandresumeMapper.updateMarDemandresume(marDemandres);

            if(type==3) {
                Date beginTime= JSON.parseObject(zm).getDate("beginTime");
                Date endTime= JSON.parseObject(zm).getDate("endTime");
                //添加待面试信息并且钉钉提醒
                MarDemandresume marDemandresume = marDemandresumeMapper.selectMarDemandresumeById(Id);
                MarWaitinginterview  marWaitingi=new MarWaitinginterview();
                marWaitingi.setNickName(marDemandresume.getTrackzPeoname());
                marWaitingi.setUserName(marDemandresume.getBindPeople());
                marWaitingi.setCustomerCode(marDemandresume.getCustomerCode());
                marWaitingi.setDemandId(marDemandresume.getDemandId());
                marWaitingi.setBeginTime(beginTime);
                marWaitingi.setEndTime(endTime);
                marWaitingi.setInterviewMode(Remark1);
                marWaitingi.setInterviewLocation(interviewLocation);
                marWaitingi.setInterviewContact(interviewContact);
                marWaitingi.setBeCareful(beCareful);
                marWaitingi.setInsertTime(new Date());
                marWaitinginterviewMapper.insertMarWaitinginterview(marWaitingi);
                ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
                if(cotoken==null){
                    cotoken=new ConDingtoken();

                    try {
                        JSONObject jsonToken = DingUtil.getAccessToken(DingUtil.TOKEN_URL);
                        cotoken.setToken(jsonToken.getString("access_token"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                SysUser sysUser = sysUserMapper.selectUserByUserName(marDemandresume.getBindPeople());
                String dinguserid = sysUser.getDinguserid();//用户钉钉
                MarDemand marDemand = marDemandMapper.selectMarDemandById(marDemandresume.getDemandId());
                PerCustomerinfo perCustomerinfo = perCustomerinfoMapper.selectPerCustomerinfoById(marDemandresume.getCustomerCode());
                try {
                    if(StringUtils.isEmpty(beCareful)){
                        beCareful="无";
                    }

                    DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+dinguserid,
                            "你绑定"+marDemand.getProjectName()+"项目下的："+perCustomerinfo.getCustomerName()+"简历通过，确定为："+Remark1+"，请及时沟通选定具体面试时间，面试时间范围为："+DateUtils.formatY_M_D2String(beginTime,DateUtils.FORMAT_Y_M_D_H_M_S)+"至"+
                                    DateUtils.formatY_M_D2String(endTime,DateUtils.FORMAT_Y_M_D_H_M_S)+"并填入OA平台,注意事项："+beCareful);
//                    System.out.println("你绑定"+marDemand.getProjectName()+"项目下的："+perCustomerinfo.getCustomerName()+"简历通过，确定为："+Remark1+"，请及时沟通选定具体面试时间，面试时间范围为："+DateUtils.formatY_M_D2String(beginTime,DateUtils.FORMAT_Y_M_D_H_M_S)+"至"+
//                            DateUtils.formatY_M_D2String(endTime,DateUtils.FORMAT_Y_M_D_H_M_S)+"并填入OA平台,注意事项："+beCareful);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(type==5){//添加待入项信息
                MarDemandresume marDemandresume = marDemandresumeMapper.selectMarDemandresumeById(Id);
                MarWaitingentry  marWaiting=new MarWaitingentry();
                marWaiting.setDemandId(marDemandresume.getDemandId());
                marWaiting.setCustomerCode(marDemandresume.getCustomerCode());
                marWaiting.setInterviewTime(date);
                marWaiting.setStayTime(stayTime);
                marWaiting.setNickName(marDemandresume.getTrackzPeoname());
                marWaiting.setInsertTime(new Date());
                marWaitingentryMapper.insertMarWaitingentry(marWaiting);

            }
            if(a==1){
                MarDemandresumefollow marDemandresumefollow=new MarDemandresumefollow();
                marDemandresumefollow.setDemandresumeId(Id);
                marDemandresumefollow.setTrackingtime(date);
                marDemandresumefollow.setFollowStatus(type);
                marDemandresumefollow.setFollowDetail(followDetail);
                marDemandresumefollow.setTrackingPeople(loginUser.getUsername());
                marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
            }else {
                return AjaxResult.error("跟踪失败");
            }

        }
        return AjaxResult.success("批量跟踪成功");
    }
    /**
     *批量重置简历绑定状态
     */
    @Override
    @Transactional
    public AjaxResult batchResumeStatus(String zm, LoginUser loginUser) {
        List<String> listDemand = JSON.parseArray(JSON.parseObject(zm).getString("list"), String.class);
        if(listDemand.size()>0){
            String s = listDemand.get(0);
            MarDemandresume marDemandresume = marDemandresumeMapper.selectMarDemandresumeById(s);
            MarDemand marDemand = marDemandMapper.selectMarDemandById(marDemandresume.getDemandId());
            if(marDemand.getState()!=0){
                return AjaxResult.error("当前需求已经关闭，请开启后在进行重置");
            }
        }

        Date date=new Date();
        for (String Id:listDemand){
            //删除跟踪状态
            marDemandresumefollowMapper.deleteMarDemandresumefollowByDemand_id(Id);
            MarDemandresume marDemandresume = marDemandresumeMapper.selectMarDemandresumeById(Id);
            marDemandresume.setNewfollowtime(date);
            marDemandresumeMapper.updateMarDemandresume(marDemandresume);
            MarDemandresumefollow marDemandresumefoll=new MarDemandresumefollow();
            marDemandresumefoll.setDemandresumeId(marDemandresume.getId());
            marDemandresumefoll.setTrackingtime(date);
            marDemandresumefoll.setFollowStatus(1);
            marDemandresumefoll.setTrackingPeople(marDemandresume.getBindPeople());
            marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefoll);
            //删除待入项信息，和待面试信息
            marWaitingentryMapper.deleteMarWaitingentryByDemandandCode(marDemandresume);
            marWaitinginterviewMapper.deleteMarWaitingeinterviewByDemandandCode(marDemandresume);
        }
        return AjaxResult.success("重置简历绑定状态成功");
    }

    @Override
    @Transactional
    public void timingDemandClosure() {
        //查询所有开发的需求
        MarDemand marDemand=new MarDemand();
        marDemand.setState(0);
        List<MarDemand> listm=marDemandMapper.selectMarDemandList(marDemand);
        for(int i=listm.size()-1;i>0;i--){

            MarDemand demand = listm.get(i);

            String demandId = demand.getDemandId();

            MarDemandresume marDemandresume=new MarDemandresume();
            marDemandresume.setDemandId(demandId);
            marDemandresume.setBindTime(workDay.getBeforeWorkDay(new Date(),7));
            List<MarDemandresume> list=marDemandresumeMapper.selectByDemandIdBinTime(marDemandresume);
            if(list.size()>0){
                List<MarDemandresume>  li=marDemandresumeMapper.selectMarDemandresumebyDemand(demandId);
                 for(MarDemandresume marDemandres:li){
                     MarDemandresume marDeman=new MarDemandresume();
                     Integer downloadStatus = marDemandres.getDownloadStatus();
                     Date date=new Date();
                     marDeman.setNewfollowtime(date);
                     marDeman.setId(marDemandres.getId());
                     MarDemandresumefollow marDemandresumefollow=new MarDemandresumefollow();
                     marDemandresumefollow.setDemandresumeId(marDemandres.getId());
                     marDemandresumefollow.setTrackingtime(date);
                     marDemandresumefollow.setTrackingPeople(marDemandres.getBindPeople());
                     if(downloadStatus==1){
                         marDemandresumefollow.setFollowStatus(4);
                     }else if(downloadStatus==3){
                         marDemandresumefollow.setFollowStatus(6);
                     }else if(downloadStatus==5){
                         marDemandresumefollow.setFollowStatus(8);
                         marDemandresumefollow.setFollowDetail("需求关闭，系统默认当前人未入项");
                     }
                     marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
                     marDemandresumeMapper.updateMarDemandresume(marDeman);
                 }
                MarDemand dsad=new MarDemand();
                dsad.setDemandId(demandId);
                dsad.setState(1);
                dsad.setCloseDate(new Date());
                marDemandMapper.updateMarDemand(dsad);
            }
        }
    }

    @Override
    public void bindingResume() {
        MarDemandresume marDemandresume=new MarDemandresume();
        marDemandresume.setNewfollowtime(workDay.getBeforeWorkDay(new Date(),5));
        List<MarDemandresume>  li=marDemandresumeMapper.selectByDemandNewfollowtime(marDemandresume);
        for(MarDemandresume marDemandres:li){
            Integer downloadStatus = marDemandres.getDownloadStatus();
            Date date=new Date();
            marDemandres.setNewfollowtime(date);
            MarDemandresumefollow marDemandresumefollow=new MarDemandresumefollow();
            marDemandresumefollow.setDemandresumeId(marDemandres.getId());
            marDemandresumefollow.setTrackingtime(date);
            marDemandresumefollow.setTrackingPeople(marDemandres.getBindPeople());
            if(downloadStatus==1){
                marDemandresumefollow.setFollowStatus(4);
            }else if(downloadStatus==3){
                marDemandresumefollow.setFollowStatus(6);
            }else if(downloadStatus==5){
                marDemandresumefollow.setFollowStatus(8);
                marDemandresumefollow.setFollowDetail("需求关闭，系统默认当前人未入项");
            }
            marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
            marDemandresumeMapper.updateMarDemandresume(marDemandres);
        }
    }
    //查询所有开放需求的公司监测有没有合同没有，提醒录入合同
    @Override
    public void demandCooperationCompany() throws Exception {

        List<Map> allBusiness = yxdemandMapper.getAllBusiness();
        for (Map map:allBusiness) {
            String userName = (String) map.get("user_name");
            String dsadas="";
            List<MarDemand> listm = marDemandMapper.selectdemandCooperationCompany(userName);
            for (MarDemand marDemand : listm) {
                String corpCode = marDemand.getCorpCode();
                String project_name = marDemand.getProjectName();//用户id
                MarContract marContract=new MarContract();
                marContract.setCorpCode(corpCode);
                List<MarContract> marContracts = marContractMapper.selectMarContractList(marContract);
                if(marContracts.size()==0){
                    dsadas+=project_name+",";
                }
            }
            SysUser sysUser = sysUserMapper.selectUserByUserName(userName);
            String dinguserid = sysUser.getDinguserid();//用户钉钉
            ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
            if(StringUtils.isNotEmpty(dsadas)){
                if(cotoken==null){
                    cotoken=new ConDingtoken();
                    JSONObject jsonToken =  DingUtil.getAccessToken(DingUtil.TOKEN_URL);
                    cotoken.setToken(jsonToken.getString("access_token"));
                }
                try {
//                    System.out.println("系统监控到您的这些合作公司："+dsadas+"，还没有合同，请及时录入");
                    DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+dinguserid,
                            "您好，系统监控到您的这些合作公司："+dsadas+"，还没有合同，请及时录入");
                } catch (ApiException e) {
                }
            }

        }


    }
    // 待入项定时器处理
    @Override
    public void itemsIncluded() {




    }

    @Override
    public List<MarDemand> getAllMarDemandList(MarDemand marDemand) {
        List<MarDemand>  list=marDemandMapper.selectMarDemandList(marDemand);
        for (MarDemand marDe:list){
            //查询当前需求绑定了多少简历
            MarDemandresume marDema=new MarDemandresume();
            marDema.setDemandId(marDe.getDemandId());
            List<MarDemandresume>  li= marDemandresumeMapper.selectMarDemandresumeList(marDema);
            marDe.setIfLook(li.size());
            //查询当前需求面试通过多少
            marDema.setDownloadStatus(5);
            List<MarDemandresume>  lifoll=marDemandresumefollowMapper.selectMarDemandList(marDema);
            marDe.setChsiFlag(lifoll.size());
            //查询当前需求入项
            marDema.setDownloadStatus(7);
            List<MarDemandresume>  lif=marDemandresumefollowMapper.selectMarDemandList(marDema);
            marDe.setCoopnature(lif.size());
            MarDemandRequirement marDemandRequirement=new MarDemandRequirement();
            marDemandRequirement.setDemandId(marDe.getDemandId());
            List<MarDemandRequirement> marDemandRequirements = marDemandRequirementMapper.selectMarDemandRequirementList(marDemandRequirement);
            marDe.setMarDemandRequirementList(marDemandRequirements);
        }
        return list;
    }

    @Override
    public AjaxResult bindingUpper(String demandId) {
         if(StringUtils.isEmpty(demandId)){
             return AjaxResult.error("需求Id为空，请联系管理员");
         }
        MarDemand marDemand = marDemandMapper.selectMarDemandById(demandId);
        Integer targetNumber = marDemand.getTargetNumber();
        List<MarDemandresume> marDemandresumes = marDemandresumeMapper.selectMarDemandresumeUpperBydemandId(demandId);
        if(marDemandresumes.size()>=targetNumber){
            return AjaxResult.error("当前需求绑定，已达到目标人数，不可绑定");
        }
        return AjaxResult.success("当前需求绑定，未达到目标人数，可绑定");

    }
    /**
     * 人事处理简历面试不通过，不入项
     */
    @Override
    public AjaxResult noInterviewEntry(String demandId, String customerCode,Integer followStatus,String followDetail) {
         if(StringUtils.isEmpty(demandId)){
             return AjaxResult.error("需求ID为空，请联系管理员");
         }
        if(StringUtils.isEmpty(customerCode)){
            return AjaxResult.error("简历ID为空，请联系管理员");
        }
        if(followStatus==null){
            return AjaxResult.error("状态为空，请联系管理员");
        }

        MarDemandresume marDemandresume=new MarDemandresume();
        marDemandresume.setDemandId(demandId);
        marDemandresume.setCustomerCode(customerCode);
        MarDemandresume marDemandresumes = marDemandresumeMapper.SelectStatusBydemandIdcustomerCode(marDemandresume);
        if(marDemandresumes==null){
            return AjaxResult.error("查不到当前人的绑定信息");
        }
        Integer downloadStatus = marDemandresumes.getDownloadStatus();
        Date date=new Date();
        if(followStatus==6){
            if(downloadStatus!=3){
                return AjaxResult.error("当前人的状态已更改，请刷新后重置");
            } else{
            marDemandresumes.setNewfollowtime(date);
            marDemandresumes.setDownloadStatus(null);
            marDemandresumeMapper.updateMarDemandresume(marDemandresumes);
            MarDemandresumefollow marDemandresumefollow=new MarDemandresumefollow();
            marDemandresumefollow.setTrackingPeople(marDemandresumes.getBindPeople());
            marDemandresumefollow.setDemandresumeId(marDemandresumes.getId());
            marDemandresumefollow.setTrackingtime(date);
            marDemandresumefollow.setFollowStatus(followStatus);
            marDemandresumefollow.setFollowDetail(followDetail);
            marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
            return AjaxResult.success("当前人的简历状态，改为面试失败成功");
            }
        }else if(followStatus==8){
            if(downloadStatus!=5){
                return AjaxResult.error("当前人的状态已更改，请刷新后重置");
            } else{
                marDemandresumes.setNewfollowtime(date);
                marDemandresumes.setDownloadStatus(null);
                marDemandresumeMapper.updateMarDemandresume(marDemandresumes);
                MarDemandresumefollow marDemandresumefollow=new MarDemandresumefollow();
                marDemandresumefollow.setTrackingPeople(marDemandresumes.getBindPeople());
                marDemandresumefollow.setDemandresumeId(marDemandresumes.getId());
                marDemandresumefollow.setTrackingtime(date);
                marDemandresumefollow.setFollowStatus(followStatus);
                marDemandresumefollow.setFollowDetail(followDetail);
                marDemandresumefollowMapper.insertMarDemandresumefollow(marDemandresumefollow);
                return AjaxResult.success("当前人的简历状态，改为不入项成功");
            }
        }else{
            return AjaxResult.success("当前人的简历状态不符合要求，请刷新后重置");
        }


    }

    @Override
    public String queryresumesalary(String customerCode) {
        //查询是否有简历附件
        PerCustomerinfo perCustomerinfo = perCustomerinfoMapper.selectPerCustomerinfoById(customerCode);
        Date customerUniversityTime = perCustomerinfo.getCustomerUniversityTime();
        String expectationSalary = perCustomerinfo.getExpectationSalary();
        if(customerUniversityTime==null){
            if(StringUtils.isEmpty(expectationSalary)){
                return  perCustomerinfo.getCustomerName()+"的期望薪资、毕业时间为空，绑定失败";
            }
            return  perCustomerinfo.getCustomerName()+"的毕业时间为空，绑定失败";
        }
        if(StringUtils.isEmpty(expectationSalary)){
            if(customerUniversityTime==null){
                return  perCustomerinfo.getCustomerName()+"的期望薪资、毕业时间为空，绑定失败";
            }
            return  perCustomerinfo.getCustomerName()+"的期望薪资为空，绑定失败";
        }
        return "可以绑定";
    }

    @Override
    public List<MarDemand> selectaccordingDemand(MarDemand marDemand) {
        return marDemandMapper.selectMarDemandList(marDemand);
    }

    @Override
    public int insertdemandAnalysis(MarDemandRequirement marDemandRequirement) {
        marDemandRequirement.setAddTime(new Date());
        return  marDemandRequirementMapper.insertMarDemandRequirement(marDemandRequirement);
    }

    @Override
    public int deleteMarDemandRequirementById(Long id) {
        marDemandresumeRequirementMapper.delbyRequirements_id(id);

        return marDemandRequirementMapper.deleteMarDemandRequirementById(id);
    }

    @Override
    public AjaxResult demandIDAcquisition(String demandId, LoginUser loginUser) {

        Map ma=new HashMap();
        ma.put("bindStarTime",DateUtils.getDayBefore(new Date(),10));
        ma.put("bindEndTime",new Date());
        ma.put("bindPeople",loginUser.getUsername());
        //查询近十天的简历绑定数量
        String format="0";
        int marDeman = marDemandresumeMapper.selectBindingMarDemandresumeCont(ma);
        int maran = marDemandresumeMapper.selectpassMarDemandresumeCont(ma);
        if(marDeman!=0){
        //查询近十天的简历绑定数量的通过率
         DecimalFormat df=new DecimalFormat(".00");
         format=df.format((float)maran / marDeman*100);
        }
        MarDemandRequirement marDemandRequirement=new MarDemandRequirement();
        marDemandRequirement.setDemandId(demandId);
        List<MarDemandRequirement> marDemandRequirements = marDemandRequirementMapper.selectMarDemandRequirementList(marDemandRequirement);
        Map map=new HashMap();
        map.put("marDemandRequirements",marDemandRequirements);
        map.put("format",format);
        return AjaxResult.success(map);
    }

    @Override
    public int updateMarDemandRequirement(MarDemandRequirement marDemandRequirement) {
        return marDemandRequirementMapper.updateMarDemandRequirement(marDemandRequirement);
    }

    @Override
    public AjaxResult isResumeTemplate(String customerCode) {
    if(StringUtils.isEmpty(customerCode)){
        return AjaxResult.error( "简历ID为空，请联系管理员");
    }
    Map map=new HashMap();
        List<PerEnclosureEducation> perEnclosureEducationList=new ArrayList<>();
        List<PerEnclosureProject> perEnclosureProjectList=new ArrayList<>();
        List<PerEnclosureSkills> perEnclosureSkillsList=new ArrayList<>();
        List<PerEnclosureWork> perEnclosureWorkList=new ArrayList<>();
        PerEnclosureCustomerinfo perEnclosureCustomerinfo = perEnclosureCustomerinfoMapper.selectPerEnclosureCustomerinfoById(customerCode);
    if(perEnclosureCustomerinfo==null){
        PerCustomerinfo perCustomerinfo = perCustomerinfoMapper.selectPerCustomerinfoById(customerCode);
        perCustomerinfo.setProfessionId("");
        map.put("perCustomerinfo",perCustomerinfo);
        map.put("educationList",perEnclosureEducationList);
        map.put("projectList",perEnclosureProjectList);
        map.put("skillsList",perEnclosureSkillsList);
        map.put("eWorkList",perEnclosureWorkList);
        return AjaxResult.success(map);
    }
        //查重(查询当前简历是否在该需求上绑定过)
        map.put("customerCode",customerCode);
        Map me=marDemandresumeMapper.selectMarDemandresumeByCodeID(map);
        if(me!=null && me.get("customer_name")!=null){
            return AjaxResult.error( me.get("customer_name").toString()+"已经被绑定，绑定失败");
        }
         perEnclosureEducationList = perEnclosureEducationMapper.selectPerEnclosureEducationList(new PerEnclosureEducation(customerCode));
         perEnclosureProjectList = perEnclosureProjectMapper.selectPerEnclosureProjectList(new PerEnclosureProject(customerCode));
        perEnclosureSkillsList = perEnclosureSkillsMapper.selectPerEnclosureSkillsList(new PerEnclosureSkills(customerCode));
         perEnclosureWorkList = perEnclosureWorkMapper.selectPerEnclosureWorkList(new PerEnclosureWork(customerCode));
        map.put("perCustomerinfo",perEnclosureCustomerinfo);
        map.put("educationList",perEnclosureEducationList);
        map.put("projectList",perEnclosureProjectList);
        map.put("skillsList",perEnclosureSkillsList);
        map.put("eWorkList",perEnclosureWorkList);
     return AjaxResult.success(map);
    }


}
