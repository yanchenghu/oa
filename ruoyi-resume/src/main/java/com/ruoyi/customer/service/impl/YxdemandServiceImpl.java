package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.resume.DateUtils;
import com.ruoyi.common.utils.resume.SerialNumber;
import com.ruoyi.conn.domain.ConOperationrecords;
import com.ruoyi.conn.mapper.ConOperationrecordsMapper;
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.domain.Yxcontact;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.domain.Yxrob;
import com.ruoyi.customer.mapper.MarCompanyMapper;
import com.ruoyi.customer.mapper.YxcontactMapper;
import com.ruoyi.customer.mapper.YxdemandMapper;
import com.ruoyi.customer.mapper.YxrobMapper;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.domain.PerRobcustomer;
import com.ruoyi.tool.WorkDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 营销录入公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class YxdemandServiceImpl implements IYxdemandService
{
    @Autowired
    private YxdemandMapper yxdemandMapper;

    @Autowired
    private YxcontactMapper yxcontactMapper;

    @Autowired
    private WorkDay workDay;

    @Autowired
    private MarCompanyMapper marCompanyMapper;



    @Autowired
    private YxrobMapper yxrobMapper;
    @Autowired
    private ConOperationrecordsMapper conOperationrecordsMapper;




    /**
     * 查询营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 营销录入公司
     */
    @Override
    public AjaxResult selectYxdemandById(Integer entryId)
    {
        Yxdemand yxdemand= yxdemandMapper.selectYxdemandById(entryId);
        Yxcontact yxcontact=new Yxcontact();
        yxcontact.setEntryId(entryId);
        List<Yxcontact> listYxcon=yxcontactMapper.selectYxcontactList(yxcontact);
        HashMap hashmap=new HashMap();
        hashmap.put("Yxdemand",yxdemand);
        hashmap.put("listYxcon",listYxcon);
        return AjaxResult.success("hashmap",hashmap);
    }



    /**
     * 查询营销录入公司列表
     * 
     * @param yxdemand 营销录入公司
     * @return 营销录入公司
     */
    @Override
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand,LoginUser loginUser)
    {
        //当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        //昨天时间
        String zuotian=sdf.format(DateUtils.getDayBefore(new Date(),1));
        //前天时间
        String qiantian=sdf.format(DateUtils.getDayBefore(new Date(),2));
        yxdemand.setRobPeopleId(loginUser.getUsername());
        List<Yxdemand> list=yxdemandMapper.selectYxdemandList(yxdemand);
        for (Yxdemand yxd:list){
            String dangqia=sdf.format(yxd.getInsertTime());
           if(dangqia.equals(date)){
               yxd.setQq("3");
           }else if(zuotian.equals(dangqia)){
               yxd.setQq("2");
           }else if(qiantian.equals(dangqia)){
               yxd.setQq("1");
           }
        }
        return  list;
    }
    /**
     * 营销抢占
     *
     * @param  entryId loginUser
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult rebMarByEnId(Integer entryId, LoginUser loginUser) {
        Yxdemand yxdemand=yxdemandMapper.selectYxdemandById(entryId);
        String robPeopleId=yxdemand.getRobPeopleId();
        if(StringUtils.isNotEmpty(robPeopleId)){
            return AjaxResult.error("当前人已被"+yxdemand.getRobPeople()+"抢占");
        }
        Yxdemand yxdema=new Yxdemand();
        yxdema.setEntryId(entryId);
        yxdema.setRobPeopleId(loginUser.getUsername());
        yxdema.setRobPeople(loginUser.getUser().getNickName());
        yxdema.setRobTime(new Date());
        yxdema.setUpdateDate(new Date());
        yxdemandMapper.updateYxdemand(yxdema);
        Yxrob yxrob=new Yxrob();
        yxrob.setEntryId(entryId);
        yxrob.setRobUsercode(loginUser.getUsername());
        yxrob.setRobName(loginUser.getUser().getNickName());
        yxrob.setRobTime(new Date());
        yxrobMapper.insertPerRobPeople(yxrob);
        return AjaxResult.success("抢占成功");
    }


    /**
     * 新增营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertYxdemand(Yxdemand yxdemand,LoginUser loginUser)
    {
        Yxdemand yxde=yxdemandMapper.selectYxdemandByName(yxdemand.getCompanyName());
        if(yxde!=null){
            return AjaxResult.error("当前公司已经存在");
        }
        yxdemand.setEntryPeopleId(loginUser.getUsername());
        yxdemand.setEntryPeople(loginUser.getUser().getNickName());
        yxdemand.setInsertTime(new Date());
        yxdemand.setRobPeople(loginUser.getUser().getNickName());
        yxdemand.setRobPeopleId(loginUser.getUsername());
        yxdemand.setRobTime(new Date());
        yxdemand.setUpdateDate(new Date());
        yxdemandMapper.insertYxdemand(yxdemand);
        Yxcontact yxcontact=new Yxcontact();
        yxcontact.setNickName(loginUser.getUser().getNickName());
        yxcontact.setContactTime(new Date());
        yxcontact.setEntryId(yxdemand.getEntryId());
        yxcontact.setContactDetail(yxdemand.getContactInformation());
        yxcontact.setStatus(1);
        yxcontactMapper.insertYxcontact(yxcontact);
        return AjaxResult.success("添加成功");

    }

    /**
     * 修改营销录入公司
     * 
     * @param yxdemand
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateYxdemand(Yxdemand yxdemand,LoginUser loginUser)
    {
        Yxdemand yxde=yxdemandMapper.selectYxdemandById(yxdemand.getEntryId());
        Integer a=yxde.getIsFollowSubmit();
        Integer c=yxde.getIsBusiness();

        if(a!=yxdemand.getIsFollowSubmit()){
            Integer b=yxdemand.getIsFollowSubmit();
            String abc="";
            if(b==1){
                abc="无意向";
            }else if (b==0){
                abc="暂未回复";
            }else if (b==2){
                abc="需跟进";
            }else if (b==3){
                abc="无效联系";
            }else if (b==4){
                abc="意向客户";
            }
            Yxcontact yxcontact=new Yxcontact();
            yxcontact.setNickName(loginUser.getUser().getNickName());
            yxcontact.setContactTime(new Date());
            yxcontact.setEntryId(yxdemand.getEntryId());
            yxcontact.setContactDetail("将线索状态改为"+abc);
            yxcontact.setStatus(2);
            yxcontactMapper.insertYxcontact(yxcontact);
        }
        Integer f=yxdemand.getIsBusiness();
        if(c!=null&&f!=null){
           if(c!=f){
               String abc="";
               if(f==0){
                   abc="无意向";
               }else if (f==1){
                   abc="需跟进";
               }else if(f==2){
                   abc="成为客户";
               }else if(f==3){
                   abc="无效联系";
               }
               Yxcontact yxcontact=new Yxcontact();
               yxcontact.setNickName(loginUser.getUser().getNickName());
               yxcontact.setContactTime(new Date());
               yxcontact.setEntryId(yxdemand.getEntryId());
               yxcontact.setContactDetail("将线索状态改为"+abc);
               yxcontact.setStatus(2);
               yxcontactMapper.insertYxcontact(yxcontact);
           }
        }
        yxdemandMapper.updateYxdemand(yxdemand);
        return AjaxResult.success("修改成功");
    }

    /**
     * 批量删除营销录入公司
     * 
     * @param entryIds 需要删除的营销录入公司ID
     * @return 结果
     */
    @Override
    public int deleteYxdemandByIds(Integer[] entryIds)
    {
        return yxdemandMapper.deleteYxdemandByIds(entryIds);
    }

    /**
     * 删除营销录入公司信息
     * 
     * @param entryId 营销录入公司ID
     * @return 结果
     */
    @Override
    public int deleteYxdemandById(Integer entryId)
    {
        return yxdemandMapper.deleteYxdemandById(entryId);
    }

    @Override
    @Transactional
    public AjaxResult insertYxcontact(Yxcontact yxcontact,LoginUser loginUser) {
        Integer entryId= yxcontact.getEntryId();
        if(entryId==null){
            return AjaxResult.error("无主键,发布失败");
        }
        String contact_detail=yxcontact.getContactDetail();
        //查询最新的一次更新情况
        Yxcontact yxco=yxcontactMapper.selNewdetailByentryId(entryId);
        if(yxco!=null){
            String cong=yxco.getContactDetail();
            if(contact_detail.equals(cong)){
                return AjaxResult.error("与上次发布内容相同,发布失败");
            }
        }
        Date date=new Date();
        Yxdemand yxdemand=new Yxdemand();
        yxdemand.setEntryId(yxcontact.getEntryId());
        yxdemand.setContactInformation(yxcontact.getContactDetail());
        yxdemand.setUpdateDate(date);
        yxdemandMapper.updateYxdemand(yxdemand);
        yxcontact.setNickName(loginUser.getUser().getNickName());
        yxcontact.setContactTime(date);
        yxcontact.setStatus(3);
        yxcontactMapper.insertYxcontact(yxcontact);
        return AjaxResult.success("发布成功");
    }
    /**
     * 客户移交
     */
    @Override
    public int Customertransfer(Yxdemand yxdemand, LoginUser loginUser) {
        yxdemand.setIsAccept(1);
        yxdemand.setSubmitTime(new Date());
        yxdemand.setIsBusiness(1);
        return yxdemandMapper.updateYxdemand(yxdemand);
    }
    /**
     * 商务意向客户列表
     */
    @Override
    public List<Yxdemand> selectBusinessList(Yxdemand yxdemand, LoginUser loginUser) {
        //当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        //昨天时间
        String zuotian=sdf.format(DateUtils.getDayBefore(new Date(),1));
        //前天时间
        String qiantian=sdf.format(DateUtils.getDayBefore(new Date(),2));
        yxdemand.setBusinessId(loginUser.getUsername());
        List<Yxdemand> list=yxdemandMapper.selectYxdemandList(yxdemand);
        for (Yxdemand yxd:list){
            String dangqia=sdf.format(yxd.getInsertTime());
            if(dangqia.equals(date)){
                yxd.setQq("3");
            }else if(zuotian.equals(dangqia)){
                yxd.setQq("2");
            }else if(qiantian.equals(dangqia)){
                yxd.setQq("1");
            }
        }
        return list;
    }
    /**
     * 转化为合作客户
     */
    @Override
    @Transactional
    public AjaxResult turnCustomers(MarCompany marCom, LoginUser loginUser) {
        MarCompany mar=marCompanyMapper.selectMarCompanyByName(marCom.getCorpName());
        if(mar!=null){
            return AjaxResult.error("当前项目已经转化为合作公司");
        }
        Yxdemand yxdemand=yxdemandMapper.selectYxdemandByName(marCom.getCorpName());
        if(yxdemand==null){
            return AjaxResult.error("需求不存在");
        }else{//5
           Integer a= yxdemand.getIsBusiness();
           if(a==5){
               return AjaxResult.error("当前需求已经转化为合作客户");
           } else{
               yxdemand.setIsBusiness(4);
               yxdemandMapper.updateYxdemand(yxdemand);
           }
        }
        marCom.setCorpCode(SerialNumber.createSerial("hzgs", 6));
        marCom.setTransformingPeople(loginUser.getUsername());
        marCom.setAddTime(new Date());
        marCompanyMapper.insertMarCompany(marCom);

        return AjaxResult.success("转化成功");
    }


    /**
     * 定时任务
     * 查询全部录入公司记录，监测是否过期，过期则释放
     */
    @Override
    public void markeRelease() {
        List<Yxdemand> lis =yxdemandMapper.selrobYxdelist();
        Date date= workDay.getBeforeWorkDay(new Date(),2);
        for (Yxdemand yxdem:lis){
           Integer isAaccept=yxdem.getIsAccept();
           if(isAaccept==0){//营销的
//               //昨天时间
//               String zuotian=sdf.format(workDay.getBeforeWorkDay(new Date(),1));
//               //前天时间
//               String qiantian=sdf.format(workDay.getBeforeWorkDay(new Date(),2));
               Date libdate=yxdem.getUpdateDate();
               if(libdate.before(date)){
                   Yxdemand yxd=new Yxdemand();
                   yxd.setEntryId(yxdem.getEntryId());
                   yxd.setRobPeopleId("");
                   yxd.setRobPeople("");
                   yxdemandMapper.updateYxdemand(yxd);
               }
           }else{//商务的
               Date libdate=yxdem.getUpdateDate();
               Integer sa=yxdem.getIsBusiness();
               if(libdate.before(date)){
                   if(null ==sa||sa!=2||sa!=4){
                       Yxdemand yxd=new Yxdemand();
                       yxd.setEntryId(yxdem.getEntryId());
                       yxd.setBusinessId("");
                       yxd.setBusinessPeople("");
                       yxdemandMapper.updateYxdemand(yxd);
                   }

               }
           }
        }
    }

    /**
     * 商务公海
     */
    @Override
    public List<Yxdemand> selectByDepartBus(Yxdemand yxdemand, LoginUser loginUser) {
        return yxdemandMapper.selectByDepartBus(yxdemand);
    }

    @Override
    public List<Yxdemand> selectByDepartMark(Yxdemand yxdemand, LoginUser loginUser) {
        return yxdemandMapper.selectByDepartMark(yxdemand);
    }
    /**
     * 商务抢占功能营销
     */
    @Override
    @Transactional
    public AjaxResult rebBusByEnId(Integer entryId, LoginUser loginUser) {
        Yxdemand yxdemand=yxdemandMapper.selectYxdemandById(entryId);
        String businessId=yxdemand.getBusinessId();
        if(StringUtils.isNotEmpty(businessId)){
            return AjaxResult.error("当前人已被"+yxdemand.getBusinessPeople()+"抢占");
        }
        Yxdemand yxdema=new Yxdemand();
        yxdema.setEntryId(entryId);
        yxdema.setBusinessId(loginUser.getUsername());
        yxdema.setBusinessPeople(loginUser.getUser().getNickName());
        yxdema.setSubmitTime(new Date());
        yxdema.setUpdateDate(new Date());
        yxdemandMapper.updateYxdemand(yxdema);
        Yxrob yxrob=new Yxrob();
        yxrob.setEntryId(entryId);
        yxrob.setRobUsercode(loginUser.getUsername());
        yxrob.setRobName(loginUser.getUser().getNickName());
        yxrob.setRobTime(new Date());
        yxrobMapper.insertPerRobPeople(yxrob);
        return AjaxResult.success("抢占成功");
    }

    @Override
    public int yxdemByName(String companyName) {
        Yxdemand yxdemand=yxdemandMapper.selectYxdemandByName(companyName);
        int a=1;
        if(yxdemand!=null){
            a=2;
        }
        return a;

    }

    @Override
    public AjaxResult workresultlist(LoginUser loginUser) {
        Map<String,Object> map = new HashMap<String,Object>();
        Date dat1 = workDay.getBeforeWorkDay(new Date(),1);
        Date dat2 = workDay.getBeforeWorkDay(new Date(),2);
        map.put("date1", dat1);
        map.put("date2", dat2);
        map.put("usercode", loginUser.getUsername());

        List<ConOperationrecords>  ListCon=conOperationrecordsMapper.selectConOperatBymap(map);

        return AjaxResult.success(ListCon);
    }

    @Override
    public AjaxResult newIntention(Yxdemand yxdemand, LoginUser loginUser) {




        yxdemand.setIsAccept(1);
        yxdemand.setSubmitTime(new Date());
        yxdemand.setIsBusiness(1);
        yxdemand.setInsertTime(new Date());
        yxdemand.setRobPeople(loginUser.getUser().getNickName());
        yxdemand.setRobPeopleId(loginUser.getUsername());
        yxdemand.setRobTime(new Date());
        yxdemand.setEntryPeopleId(loginUser.getUsername());
        yxdemand.setEntryPeople(loginUser.getUser().getNickName());
        yxdemand.setBusinessId(loginUser.getUsername());
        yxdemand.setBusinessPeople(loginUser.getUser().getNickName());
        yxdemand.setUpdateDate(new Date());
        yxdemandMapper.insertYxdemand(yxdemand);
        if(StringUtils.isNotEmpty(yxdemand.getContactInformation())){
            Yxcontact  yxcontact=new Yxcontact();
            yxcontact.setNickName(loginUser.getUser().getNickName());
            yxcontact.setContactTime(new Date());
            yxcontact.setEntryId(yxdemand.getEntryId());
            yxcontact.setContactDetail(yxdemand.getContactInformation());
            yxcontact.setStatus(1);
            yxcontactMapper.insertYxcontact(yxcontact);
        }
        return AjaxResult.success("新建意向客户成功");
    }


}
