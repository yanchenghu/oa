package com.ruoyi.demand.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.resume.DateUtils;
import com.ruoyi.common.utils.resume.DingUtil;
import com.ruoyi.conn.domain.ConDingtoken;
import com.ruoyi.conn.mapper.ConDingtokenMapper;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.mapper.MarDemandMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarWaitinginterviewMapper;
import com.ruoyi.demand.domain.MarWaitinginterview;
import com.ruoyi.demand.service.IMarWaitinginterviewService;

/**
 * 待面试Service业务层处理
 * 
 * @author ych
 * @date 2021-04-29
 */
@Service
public class MarWaitinginterviewServiceImpl implements IMarWaitinginterviewService 
{
    @Autowired
    private MarWaitinginterviewMapper marWaitinginterviewMapper;
    @Autowired
    private MarDemandMapper marDemandMapper;
    @Autowired
    private ConDingtokenMapper conDingtokenMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询待面试
     * 
     * @param id 待面试ID
     * @return 待面试
     */
    @Override
    public MarWaitinginterview selectMarWaitinginterviewById(Integer id)
    {
        return marWaitinginterviewMapper.selectMarWaitinginterviewById(id);
    }

    /**
     * 查询待面试列表
     * 
     * @param marWaitinginterview 待面试
     * @return 待面试
     */
    @Override
    public List<MarWaitinginterview> selectMarWaitinginterviewList(MarWaitinginterview marWaitinginterview)
    {
        return marWaitinginterviewMapper.selectMarWaitinginterviewList(marWaitinginterview);
    }

    @Override
    public List<Map> selectMarWaitinginterviewMap(String username) {
        return marWaitinginterviewMapper.selectMarWaitinginterviewMap(username);
    }

    @Override
    public int updateMarWaitinginterview(MarWaitinginterview marWaitinginterview) {
        String demandId = marWaitinginterview.getDemandId();
        MarDemand marDemand = marDemandMapper.selectMarDemandById(demandId);
        ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
        if(cotoken==null){
            JSONObject jsonToken = null;
            try {
                jsonToken = DingUtil.getAccessToken(DingUtil.TOKEN_URL);
                cotoken.setToken(jsonToken.getString("access_token"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SysUser sysUser = sysUserMapper.selectUserByUserName(marDemand.getOperationuser());
        String dinguserid = sysUser.getDinguserid();//用户钉钉
        try {
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+dinguserid,
                    "你"+marDemand.getProjectName()+"项目下的："+marWaitinginterview.getCustomerName()+"已确定面试时间为，"+ DateUtils.formatY_M_D2String(marWaitinginterview.getInterviewTime(),DateUtils.FORMAT_Y_M_D_H_M_S));
//       System.out.println("你好，你"+marDemand.getProjectName()+"项目下的："+marWaitinginterview.getCustomerName()+"已确定面试时间为，"+ DateUtils.formatY_M_D2String(marWaitinginterview.getInterviewTime(),DateUtils.FORMAT_Y_M_D_H_M_S));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marWaitinginterviewMapper.updateMarWaitinginByDemandCode(marWaitinginterview);
    }


}
