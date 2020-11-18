package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.domain.Yxcontact;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.mapper.IntentionMapper;
import com.ruoyi.customer.mapper.YxcontactMapper;
import com.ruoyi.customer.mapper.YxdemandMapper;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.customer.service.IntentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 营销录入公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class IntentionServiceImpl implements IntentionService
{
    @Autowired
    private IntentionMapper intentionMapper;

    @Autowired
    private YxcontactMapper yxcontactMapper;


    /**
     * 查询营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 营销录入公司
     */
    @Override
    public AjaxResult selectYxdemandById(Integer entryId)
    {
        Yxdemand yxdemand= intentionMapper.selectYxdemandById(entryId);
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
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand,LoginUser loginUser)throws Exception
    {
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        Date updateDate = yxdemand.getUpdateDate();
//        Long day = dateDiff(sd.format(date),sd.format(updateDate));
//
//        String ss =  String.valueOf(day);
//        Integer sss = Integer.parseInt(ss);
//        yxdemand.setPersonnelInto(sss);

//        //当前时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date = sdf.format(new Date());
//        //昨天时间
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(new Date());
//        calendar.add(calendar.DATE,-1);
//        String date1= sdf.format(calendar.getTime());
//        //前天时间
//        calendar.setTime(new Date());
//        calendar.add(calendar.DATE,-2);
//        String date2= sdf.format(calendar.getTime());
////        System.out.println(date1);
////        System.out.println(date2);
//
//        if(a==1){
//            date = date;
//        }else if(a==2){
//            date = date1;
//        }else if(a==3){
//            date=date2;
//        }
        yxdemand.setRobPeopleId(loginUser.getUsername());
        return intentionMapper.selectYxdemandList(yxdemand);
    }


    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public long dateDiff(String startTime, String endTime) throws Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
        //获得两个时间的毫秒时间差异
        diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        long day = diff/nd;//计算差多少天
        return day ;
    }

    /**
     * 新增营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    @Override
    public int insertYxdemand(Yxdemand yxdemand,LoginUser loginUser)
    {
        yxdemand.setEntryPeopleId(loginUser.getUsername());
        yxdemand.setEntryPeople(loginUser.getUser().getNickName());
        yxdemand.setInsertTime(new Date());
        yxdemand.setRobPeople(loginUser.getUser().getNickName());
        yxdemand.setRobPeopleId(loginUser.getUsername());
        yxdemand.setRobTime(new Date());


        yxdemand.setUpdateDate(new Date());
        return intentionMapper.insertYxdemand(yxdemand);
    }

    /**
     * 修改营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    @Override
    public int updateYxdemand(Yxdemand yxdemand)
    {
        return intentionMapper.updateYxdemand(yxdemand);
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
        return intentionMapper.deleteYxdemandByIds(entryIds);
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
        return intentionMapper.deleteYxdemandById(entryId);
    }
}
