package com.ruoyi.resume.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.resume.DingUtil;
import com.ruoyi.conn.domain.ConDingtoken;
import com.ruoyi.conn.mapper.ConDingtokenMapper;
import com.ruoyi.demand.domain.UserInfo;
import com.ruoyi.tool.WorkDay;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerRobcustomerMapper;
import com.ruoyi.resume.domain.PerRobcustomer;
import com.ruoyi.resume.service.IPerRobcustomerService;

import javax.annotation.Resource;

/**
 * 抢占简历信息Service业务层处理
 * 
 * @author yan
 * @date 2020-10-30
 */
@Service
public class PerRobcustomerServiceImpl implements IPerRobcustomerService 
{
    @Autowired
    private PerRobcustomerMapper perRobcustomerMapper;
    @Autowired
    private ConDingtokenMapper conDingtokenMapper;
    @Resource
    private WorkDay workDay;
    /**
     * 查询抢占简历信息
     * 
     * @param seizeId 抢占简历信息ID
     * @return 抢占简历信息
     */
    @Override
    public PerRobcustomer selectPerRobcustomerById(String seizeId)
    {
        return perRobcustomerMapper.selectPerRobcustomerById(seizeId);
    }

    /**
     * 查询抢占简历信息列表
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 抢占简历信息
     */
    @Override
    public List<PerRobcustomer> selectPerRobcustomerList(PerRobcustomer perRobcustomer)
    {
        return perRobcustomerMapper.selectPerRobcustomerList(perRobcustomer);
    }

    /**
     * 新增抢占简历信息
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 结果
     */
    @Override
    public int insertPerRobcustomer(PerRobcustomer perRobcustomer)
    {
        return perRobcustomerMapper.insertPerRobcustomer(perRobcustomer);
    }

    /**
     * 修改抢占简历信息
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 结果
     */
    @Override
    public int updatePerRobcustomer(PerRobcustomer perRobcustomer)
    {
        return perRobcustomerMapper.updatePerRobcustomer(perRobcustomer);
    }

    /**
     * 批量删除抢占简历信息
     * 
     * @param seizeIds 需要删除的抢占简历信息ID
     * @return 结果
     */
    @Override
    public int deletePerRobcustomerByIds(String[] seizeIds)
    {
        return perRobcustomerMapper.deletePerRobcustomerByIds(seizeIds);
    }

    /**
     * 删除抢占简历信息信息
     * 
     * @param seizeId 抢占简历信息ID
     * @return 结果
     */
    @Override
    public int deletePerRobcustomerById(String seizeId)
    {
        return perRobcustomerMapper.deletePerRobcustomerById(seizeId);
    }
    /**
     * 定时任务
     * 查询全部抢占记录，监测是否过期，过期则释放
     * @param
     * @return
     */
    @Override
    public void initRobStatus() {
    //查询所有的过期抢占信息
    List<String>  list= perRobcustomerMapper.selectAllOverrob();
    if(list!=null && list.size()>0){
        perRobcustomerMapper.updatePerRobbylist(list);

    }

    }
    /**
     * 查看所有的我抢占的简历信息有没有简历原件
     * 5点提醒
     */
    @Override
    public void resumeOriginal() throws Exception {
        Integer ding_type=1;
        ConDingtoken  cotoken =conDingtokenMapper.selectConDingtokenByType(ding_type);
        if(cotoken==null){
            JSONObject jsonToken =  DingUtil.getAccessToken(DingUtil.TOKEN_URL);
            cotoken.setToken(jsonToken.getString("access_token"));
        }

        //获取所有抢占的人事
        List<UserInfo> liUs=perRobcustomerMapper.selectAllrobPeo();
        for(UserInfo sysUser:liUs){
            String addPeople=sysUser.getUserName();
            String dinguserid=sysUser.getDinguserid();
            List<String> liStri=perRobcustomerMapper.getAllresumenull(addPeople);
            if(liStri.size()>0){
                String customenamelist="";
                for(String customename:liStri){
                    customenamelist+=customename+",";
                }
                DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+dinguserid,
                        "您好，系统监控到您抢占的：("+customenamelist+")这些人没有录入简历原件，请抓紧录入");//罗梦莹
            }
        }
    }

    @Override
    public void updateDingtoken() throws Exception {
        Date now=new Date();
        ConDingtoken  conDingtoken=new ConDingtoken();
        JSONObject jsonToken =  DingUtil.getAccessToken(DingUtil.TOKEN_URL);
        conDingtoken.setToken(jsonToken.getString("access_token"));
        conDingtoken.setAddTime(now);
        conDingtoken.setExpireTime(workDay.getAfterHour(now,2));
        conDingtoken.setDingType(1);
        conDingtokenMapper.updateConDingByType(conDingtoken);
    }


}
