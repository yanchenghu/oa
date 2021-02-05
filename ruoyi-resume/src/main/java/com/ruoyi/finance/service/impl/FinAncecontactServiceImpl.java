package com.ruoyi.finance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.resume.DingUtil;
import com.ruoyi.conn.domain.ConDingtoken;
import com.ruoyi.conn.mapper.ConDingtokenMapper;
import com.ruoyi.finance.domain.FinAncecompany;
import com.ruoyi.finance.mapper.FinAncecompanyMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinAncecontactMapper;
import com.ruoyi.finance.domain.FinAncecontact;
import com.ruoyi.finance.service.IFinAncecontactService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 财务最新跟踪Service业务层处理
 * 
 * @author ych
 * @date 2021-02-02
 */
@Service
public class FinAncecontactServiceImpl implements IFinAncecontactService 
{
    @Autowired
    private FinAncecontactMapper finAncecontactMapper;
    @Autowired
    private FinAncecompanyMapper finAncecompanyMapper;
    @Autowired
    private ConDingtokenMapper conDingtokenMapper;

    /**
     * 查询财务最新跟踪
     * 
     * @param contactId 财务最新跟踪ID
     * @return 财务最新跟踪
     */
    @Override
    public FinAncecontact selectFinAncecontactById(Integer contactId)
    {
        return finAncecontactMapper.selectFinAncecontactById(contactId);
    }

    /**
     * 查询财务最新跟踪列表
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 财务最新跟踪
     */
    @Override
    public List<FinAncecontact> selectFinAncecontactList(FinAncecontact finAncecontact)
    {
        return finAncecontactMapper.selectFinAncecontactList(finAncecontact);
    }

    /**
     * 新增财务最新跟踪
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertFinAncecontact(FinAncecontact finAncecontact, LoginUser loginUser) throws Exception {

        ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
        if(cotoken==null){
            JSONObject jsonToken =  DingUtil.getAccessToken(DingUtil.TOKEN_URL);
            cotoken.setToken(jsonToken.getString("access_token"));
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

        Date nowdate=new Date();
        FinAncecontact finAncecontact1 = finAncecontactMapper.selectFinAncecontactById(finAncecontact.getContactId());
        if (finAncecontact1!=null){
            int i = new Double(finAncecontact1.getManMonth()).intValue();
            int x = new Double(finAncecontact.getManMonth()).intValue();
            Date collectionTime = finAncecontact.getCollectionTime();
            Date collectionTime1 = finAncecontact1.getCollectionTime();
            if( i==x  &&
            finAncecontact.getActualMonth().equals(finAncecontact1.getActualMonth())   && fmt.format(collectionTime).equals(fmt.format(collectionTime1))
            && finAncecontact.getActualMoney().equals(finAncecontact1.getActualMoney()) && finAncecontact.getReceivedPayment().equals(finAncecontact1.getReceivedPayment())
            && finAncecontact.getCollectionStatus()==finAncecontact1.getCollectionStatus()  && finAncecontact.getRemarks().equals(finAncecontact1.getRemarks())
            && finAncecontact.getLastmonthWages().equals(finAncecontact1.getLastmonthWages()) && finAncecontact.getContactSituation().equals(finAncecontact1.getContactSituation())){
             return AjaxResult.success("修改成功");
            }

            if(!finAncecontact.getContactSituation().equals(finAncecontact1.getContactSituation())){
                DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+"01195548941584",
                        finAncecontact.getCorpName()+"的最新沟通情况："+finAncecontact.getContactSituation());
            }
            if( finAncecontact.getReceivedPayment().equals(finAncecontact1.getReceivedPayment())){
                DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+"01195548941584",
                        finAncecontact.getCorpName().toString()+",应回款金额为"+finAncecontact.getActualMoney()+"元"+"已回款了金额为："+finAncecontact.getReceivedPayment()+"元。"+"备注："+finAncecontact.getPaymentTime());
                DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+"055019496438124425",
                        finAncecontact.getCorpName().toString()+"已回款了金额为："+finAncecontact.getReceivedPayment()+"元。"+"请抓紧把这部分人"+finAncecontact.getActualMonth()+"的请求书录入系统");//ccq钉钉提醒去录入请求书
            }
        }else {
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+"01195548941584",
                    finAncecontact.getCorpName()+"的最新沟通情况："+finAncecontact.getContactSituation());
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+"01195548941584",
                    finAncecontact.getCorpName().toString()+",应回款金额为"+finAncecontact.getActualMoney()+"元"+"已回款了金额为："+finAncecontact.getReceivedPayment()+"元。"+"备注："+finAncecontact.getPaymentTime());
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+"055019496438124425",
                    finAncecontact.getCorpName().toString()+"已回款了金额为："+finAncecontact.getReceivedPayment()+"元。"+"请抓紧把这部分人"+finAncecontact.getActualMonth()+"的请求书录入系统");//ccq钉钉提醒去录入请求书
        }
        FinAncecompany finAncecompa=new FinAncecompany();
        finAncecompa.setCorpCode(finAncecontact.getCorpCode());
        finAncecompa.setAddTime(nowdate);
        List<FinAncecompany> finAncecompanies = finAncecompanyMapper.selectFinAncecompanyList(finAncecompa);
        if(finAncecompanies.size()>0){
            FinAncecompany finAncecompany = finAncecompanies.get(0);
            finAncecompany.setAddTime(nowdate);
            finAncecompanyMapper.updateFinAncecompany(finAncecompany);
        }else {
            return AjaxResult.error("操作异常，请联系管理员");
        }
        finAncecontact.setAddTime(nowdate);
        finAncecontactMapper.insertFinAncecontact(finAncecontact);




        return AjaxResult.success("操作成功");
    }

    /**
     * 修改财务最新跟踪
     * 
     * @param finAncecontact 财务最新跟踪
     * @return 结果
     */
    @Override
    public int updateFinAncecontact(FinAncecontact finAncecontact)
    {
        return finAncecontactMapper.updateFinAncecontact(finAncecontact);
    }

    /**
     * 批量删除财务最新跟踪
     * 
     * @param contactIds 需要删除的财务最新跟踪ID
     * @return 结果
     */
    @Override
    public int deleteFinAncecontactByIds(Integer[] contactIds)
    {
        return finAncecontactMapper.deleteFinAncecontactByIds(contactIds);
    }

    /**
     * 删除财务最新跟踪信息
     * 
     * @param contactId 财务最新跟踪ID
     * @return 结果
     */
    @Override
    public int deleteFinAncecontactById(Integer contactId)
    {
        return finAncecontactMapper.deleteFinAncecontactById(contactId);
    }
    /**
     * 查看历史跟进记录
     */
    @Override
    public AjaxResult selAncecontact(FinAncecontact finAncecontact) {
        return AjaxResult.success(finAncecontactMapper.selAncecontact(finAncecontact));
    }
}
