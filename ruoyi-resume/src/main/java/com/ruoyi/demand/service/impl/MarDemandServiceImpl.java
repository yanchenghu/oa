package com.ruoyi.demand.service.impl;

import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.resume.DingDingJiQi;
import com.ruoyi.common.utils.resume.SerialNumber;
import com.ruoyi.conn.domain.ConOperationrecords;
import com.ruoyi.conn.mapper.ConOperationrecordsMapper;
import com.ruoyi.demand.domain.*;
import com.ruoyi.demand.mapper.*;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.mapper.PerCustomerinfoMapper;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
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
        map.put("Signlis",Signlis);
        map.put("marDeman",marDeman);
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
        MarDemandresume marDema=new MarDemandresume();
        for (MarDemand marDe:list){
        //查询当前需求绑定了多少简历
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
        try {
            fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), demandPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> liStr = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        MarDemand marDemand = JSON.parseObject(JSON.parseObject(zm).getString("marDemand"), MarDemand.class);

        MarDemand sd=marDemandMapper.selectMarDemandByName(marDemand.getProjectName());
        if(sd!=null){
            return AjaxResult.error("當前需求已存在");
        }
        String demandId=SerialNumber.createSerial("demd-", 5);
        marDemand.setDemandPic(fsafsa);
        marDemand.setDemandId(demandId);
        marDemand.setOperationuser(loginUser.getUsername());
        marDemand.setOperUsername(loginUser.getUser().getNickName());
        marDemand.setAddTime(new Date());
        marDemandMapper.insertMarDemand(marDemand);
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
        String demandId=marDemand.getDemandId();

        MarDemand sd=marDemandMapper.selectMarDemandByName(marDemand.getProjectName());
        if(sd!=null){
            if(!sd.getDemandId().equals(marDemand.getDemandId())){
                return AjaxResult.error("當前需求已存在");
            }
        }
        //如果图片不为空的话，则重新上传图片
        if(demandPic != null){
            String fsafsa="";
            try {
                fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), demandPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marDemand.setDemandPic(fsafsa);
        }
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
            if(work.size()>0){
                marSignMapper.insertMList(work);
            }else {
                return AjaxResult.error("没有选择下包商");
            }
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
    public int selDemandDuplicate(String projectName) {
       MarDemand sd=marDemandMapper.selectMarDemandByName(projectName);
        if(sd!=null){
            return 2;
        }
        return 1;
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
        List<MarDemand> list= marDemandMapper.selectMarDemandbindingList(map);
        MarDemandresume marDema=new MarDemandresume();
        for (MarDemand marDe:list){
            //查询当前需求绑定了多少简历
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
        }
        return list;
    }
    /**
     * 简历绑定需求
     */
    @Override
    @Transactional
    public AjaxResult resumeBingDemand(LoginUser loginUser,String zm) {
        List<String> liStr = JSON.parseArray(JSON.parseObject(zm).getString("list"), String.class);
        String demandId = JSON.parseObject(zm).getString("demandId");
        Map map=new HashMap();
        map.put("demandId",demandId);
        if(liStr.size()>0){
            for (String customerCode:liStr){
                //查询是否有简历地址
                PerCustomerinfo perCustomerinfo = perCustomerinfoMapper.selectPerCustomerinfoById(customerCode);
                if(StringUtils.isEmpty(perCustomerinfo.getResumePath())){
                    return AjaxResult.error( perCustomerinfo.getCustomerName()+"没有简历附件，绑定失败，请上传后在绑定");
                }
                //查重
                map.put("customerCode",customerCode);
                Map me=marDemandresumeMapper.selectMarDemandresumeByCodeID(map);
                if(me!=null && me.get("customer_name")!=null){
                    return AjaxResult.error( me.get("customer_name").toString()+"已经被绑定，绑定失败");
                }
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
                marDemandresumeMapper.insertMarDemandresume(marde);
                //添加简历跟进记录
                    MarDemandresumefollow marDemFoll=new MarDemandresumefollow();
                    marDemFoll.setDemandresumeId(demandresumeId);
                    marDemFoll.setTrackingPeople(loginUser.getUsername());
                    marDemFoll.setTrackingtime(date);
                    marDemFoll.setFollowStatus(1);
                    marDemFoll.setFollowDetail("简历绑定");
                 marDemandresumefollowMapper.insertMarDemandresumefollow(marDemFoll);
                     //添加操作记录
                    ConOperationrecords conOperationrec=new ConOperationrecords();
                    conOperationrec.setType(4);
                    conOperationrec.setDatetime(date);
                    conOperationrec.setUserName(loginUser.getUsername());
                    conOperationrec.setRemark("简历绑定");
                conOperationrecordsMapper.insertConOperationrecords(conOperationrec);

            }
        }else{
            return AjaxResult.error("绑定简历为空");
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
        return list;
    }
    /**
     *需求已绑定简历跟踪
     */
    @Override
    public AjaxResult demandResumeTrack(MarDemandresumefollow marDemandresumefollow, LoginUser loginUser) {

          if(StringUtils.isEmpty(marDemandresumefollow.getDemandresumeId())){
              return AjaxResult.error("id为空,不可跟踪，请联系管理员");
          }
          Date date=new Date();
          MarDemandresume marDemandres=new MarDemandresume();
          marDemandres.setId(marDemandresumefollow.getDemandresumeId());
          marDemandres.setNewfollowtime(date);

          int a=marDemandresumeMapper.updateMarDemandresume(marDemandres);
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
       if(map.isEmpty()){
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
        ConOperationrecords operationrecords=new ConOperationrecords();
        operationrecords.setType(5);
        operationrecords.setDatetime(new Date());
        operationrecords.setUserName(marCustomerprojectpay.getOpercode());
        conOperationrecordsMapper.insertConOperationrecords(operationrecords);
        //钉钉提醒
        try {
            DingDingJiQi.DingDingd("我是机器人，测试消息勿回，，，喜报！！！恭喜，"+user.getNickName()+"成功入项一名人员");
        } catch (ApiException e) {
            return AjaxResult.error("入项失败，钉钉发送失败,请联系管理员");
        }
        //钉钉个人提醒？？？




        PerCustomerinfo perCustomeri=perCustomerinfoMapper.selectPerCustomerinfoById(marCustomerprojectpay.getCustomerCode());
        //入项成功更改个人简历信息
        PerCustomerinfo perCustomer=new PerCustomerinfo();
        perCustomer.setCustomerCode(marCustomerprojectpay.getCustomerCode());
        if (perCustomer.getJoinStatus()>2){
            perCustomer.setJoinStatus(4);
        }else{
            perCustomer.setJoinStatus(2);
        }
        perCustomerinfoMapper.updatePerCustomerinfo(perCustomer);
        return AjaxResult.success("入项成功");
    }


}