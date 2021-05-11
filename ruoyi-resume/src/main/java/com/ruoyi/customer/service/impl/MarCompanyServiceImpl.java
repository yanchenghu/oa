package com.ruoyi.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.resume.DingUtil;
import com.ruoyi.conn.domain.ConDingtoken;
import com.ruoyi.conn.mapper.ConDingtokenMapper;
import com.ruoyi.customer.domain.*;
import com.ruoyi.customer.mapper.*;
import com.ruoyi.demand.domain.MarAuditeditor;
import com.ruoyi.demand.mapper.MarAuditeditorMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.service.IMarCompanyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合作公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-16
 */
@Service
public class MarCompanyServiceImpl implements IMarCompanyService 
{
    @Autowired
    private MarCompanyMapper marCompanyMapper;

    @Autowired
    private MarContractfollowMapper marContractfollowMapper;

    @Autowired
    private MarContractMapper marContractMapper;
    @Autowired
    private MarAuditeditorMapper marAuditeditorMapper;
    @Autowired
    private MarCompanyContactsMapper marCompanyContactsMapper;
    @Autowired
    private ConDingtokenMapper conDingtokenMapper;
    @Autowired
    private MarCompanyChangerecordMapper marCompanyChangerecordMapper;

    /**
     * 查询合作公司
     * 
     * @param corpCode 合作公司ID
     * @return 合作公司
     */
    @Override
    public AjaxResult selectMarCompanyById(String corpCode,LoginUser loginUser)
    {
        MarCompany marCompany = marCompanyMapper.selectMarCompanyById(corpCode);
        MarContract marContract = new MarContract();
        marContract.setCorpCode(corpCode);
        List<MarContract> marContracts = marContractMapper.selectMarContractList(marContract);
        MarContractfollow marContractfollow = new MarContractfollow();
        marContractfollow.setCorpCode(marCompany.getCorpCode());
        List<MarContractfollow> mar = marContractfollowMapper.selectMarContractfollowList(marContractfollow);
        //面试题
        MarAuditeditor marAuditeditor=new MarAuditeditor();
        marAuditeditor.setCorpCode(corpCode);
        List<MarAuditeditor> listAuditeditors = marAuditeditorMapper.selectMarAuditeditorList(marAuditeditor);

        //联系人
        MarCompanyContacts marCompanyContacts=new MarCompanyContacts();
        marCompanyContacts.setCorpCode(corpCode);
        List<MarCompanyContacts> listMarCompanyC=marCompanyContactsMapper.selectMarCompanyContactsList(marCompanyContacts);


        HashMap hashmap = new HashMap();
        hashmap.put("marContracts",marContracts);
        hashmap.put("marCompany",marCompany);
        hashmap.put("mar",mar);
        hashmap.put("listAuditeditors",listAuditeditors);
        hashmap.put("listMarCompanyC",listMarCompanyC);
        return AjaxResult.success("hashmap",hashmap);
    }

    /**
     * 查询合作公司列表
     * 
     * @param marCompany 合作公司
     * @return 合作公司
     */
    @Override
    public List<MarCompany> selectMarCompanyList(MarCompany marCompany, LoginUser loginUser)
    {
        if(loginUser!=null){
            marCompany.setTransformingPeople(loginUser.getUsername());
        }

        return marCompanyMapper.selectMarCompanyList(marCompany);
    }

    /**
     * 新增合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    @Override
    public AjaxResult insertMarCompany(MarCompany marCompany,LoginUser loginUser)
    {
        MarCompany mar = marCompanyMapper.selectMarCompanyByName(marCompany.getCorpName());
        if(mar!=null){
            return AjaxResult.error("当前公司已存在");
        }

        marCompany.setEntryPeople(loginUser.getUser().getNickName());
        marCompany.setTransformingPeople(loginUser.getUsername());
        marCompany.setAddTime(new Date());
        marCompanyMapper.insertMarCompany(marCompany);
        MarContractfollow marContractfollow = new MarContractfollow();
        marContractfollow.setNickName(loginUser.getUser().getNickName());
        marContractfollow.setContactTime(new Date());
        marContractfollow.setCorpCode(marCompany.getCorpCode());
        marContractfollow.setContactDetail("添加合作公司");
        marContractfollow.setStatus(1);
        marContractfollowMapper.insertMarContractfollow(marContractfollow);

        return AjaxResult.success("添加成功");
    }

    /**
     * 修改合作公司
     * 
     * @param marCompany 合作公司
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateMarCompany(MarCompany marCompany,LoginUser loginUser) throws Exception {
       
        ConDingtoken cotoken =conDingtokenMapper.selectConDingtokenByType(1);
        if(cotoken==null){
            JSONObject jsonToken =  DingUtil.getAccessToken(DingUtil.TOKEN_URL);
            cotoken.setToken(jsonToken.getString("access_token"));
        }
        String DingId="01195548941584";
        Map map=marCompanyMapper.getAllBoos();
        if(map!=null){
            DingId= (String) map.get("dinguserid");
        }
        MarCompany mar = marCompanyMapper.selectMarCompanyById(marCompany.getCorpCode());
        String corpName = mar.getCorpName();
        String contactPhone = mar.getContactPhone();
        MarCompanyChangerecord marCompanyChangerecord=new MarCompanyChangerecord();
        marCompanyChangerecord.setCorpCode(marCompany.getCorpCode());
        marCompanyChangerecord.setAddTime(new Date());
        if(!marCompany.getCorpName().equals(corpName)){
            marCompanyChangerecord.setChangeType(1);
            marCompanyChangerecord.setChangeContent("将："+corpName+"，名称改为："+marCompany.getCorpName());
            marCompanyChangerecordMapper.insertMarCompanyChangerecord(marCompanyChangerecord);
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+DingId,
                    "您好，系统监测到"+loginUser.getUser().getNickName()+"将："+corpName+"，名称改为："+marCompany.getCorpName());
        }

        if(!marCompany.getContactPhone().equals(contactPhone)){
            marCompanyChangerecord.setChangeType(2);
            marCompanyChangerecord.setChangeContent("将："+corpName+"的电话，"+contactPhone+"改为："+marCompany.getContactPhone());
            marCompanyChangerecordMapper.insertMarCompanyChangerecord(marCompanyChangerecord);
            DingUtil.sendMessage(DingUtil.sendMessage_URL+"?access_token="+cotoken.getToken()+"&agent_id="+DingUtil.agent_id+"&userid_list="+DingId,
                    "您好，系统监测到"+loginUser.getUser().getNickName()+"将："+corpName+"的电话，"+contactPhone+"改为："+marCompany.getContactPhone());
        }
        Integer a = mar.getCompanySituation();
        Integer b = mar.getCustomerLevel();
        Integer c = mar.getPaybackPeriod();

        if(a!=marCompany.getCompanySituation()){
            Integer a1 = marCompany.getCompanySituation();
            String  aa= "";
            if(a1==0){
                aa = "外包公司";
            } else if(a1==1){
                aa ="甲方公司";
            }else if(a1 ==2){
                aa ="不确定";
            }

            MarContractfollow marContractfollow = new MarContractfollow();
            marContractfollow.setNickName(loginUser.getUser().getNickName());
            marContractfollow.setContactTime(new Date());
            marContractfollow.setCorpCode(marCompany.getCorpCode());
            marContractfollow.setContactDetail("将公司性质改为"+aa);
            marContractfollow.setStatus(3);
            marContractfollowMapper.insertMarContractfollow(marContractfollow);
        }

        if(b!=marCompany.getCustomerLevel()){
            Integer b1 = marCompany.getCustomerLevel();
            String  bb= "";
            if(b1==0){
                bb = "A(核心客户)";
            } else if(b1==1){
                bb ="B(重点客户)";
            }else if(b1 ==2){
                bb ="C(普通客户)";
            }else if(b1 ==3){
                bb = "D(拉黑客户)";
            }

            MarContractfollow marContractfollow = new MarContractfollow();
            marContractfollow.setNickName(loginUser.getUser().getNickName());
            marContractfollow.setContactTime(new Date());
            marContractfollow.setCorpCode(marCompany.getCorpCode());
            marContractfollow.setContactDetail("将客户级别改为"+bb);
            marContractfollow.setStatus(3);
            marContractfollowMapper.insertMarContractfollow(marContractfollow);
        }

        if(c!=marCompany.getPaybackPeriod()){
            Integer c1 = marCompany.getPaybackPeriod();
            String  cc= "";
            if(c1==0){
                cc = "N+3";
            } else if(c1==1){
                cc ="N+5";
            }else if(c1 ==2){
                cc ="N+7";
            }else if(c1 ==3){
                cc = "月付";
            }else if(c1 == 4){
                cc ="季付";
            }else if(c1 == 5){
                cc ="年付";
            }else if(c1 == 6){
                cc ="次次付";
            }else if(c1 == 7){
                cc ="隔月付";
            }else if(c1 == 8){
                cc ="每两个月结算一次";
            }else if(c1 == 9){
                cc ="第六个月付前三个月费用";
            }
            MarContractfollow marContractfollow = new MarContractfollow();
            marContractfollow.setNickName(loginUser.getUser().getNickName());
            marContractfollow.setContactTime(new Date());
            marContractfollow.setCorpCode(marCompany.getCorpCode());
            marContractfollow.setContactDetail("将回款周期改为"+cc);
            marContractfollow.setStatus(3);
            marContractfollowMapper.insertMarContractfollow(marContractfollow);
        }
        marCompanyMapper.updateMarCompany(marCompany);

        return AjaxResult.success("修改成功");
    }

    /**
     * 批量删除合作公司
     * 
     * @param corpCodes 需要删除的合作公司ID
     * @return 结果
     */
    @Override
    public int deleteMarCompanyByIds(String[] corpCodes)
    {
        return marCompanyMapper.deleteMarCompanyByIds(corpCodes);
    }

    /**
     * 删除合作公司信息
     * 
     * @param corpCode 合作公司ID
     * @return 结果
     */
    @Override
    public int deleteMarCompanyById(String corpCode)
    {
        return marCompanyMapper.deleteMarCompanyById(corpCode);
    }


    /**
     * 查询联系人信息
     */
    @Override
    public List<MarCompany> selectMarCompanyContact(MarCompany marCompany) {
        return marCompanyMapper.selectMarCompanyContact(marCompany);
    }

    /**
     * 查询合同信息
     */
    @Override
    public List<MarCompany> selectMarContractList(MarContract marContract) {

        return marCompanyMapper.selectMarContractList(marContract);
    }

    @Override
    public List<MarCompany> selectMarCompanyAlllistcom() {
        MarCompany marCompany=new MarCompany();
        return marCompanyMapper.selectMarCompanyList(marCompany);

    }

    @Override
    public String selcheckingcompany(String corpCode, String corpName) {
        if(StringUtils.isEmpty(corpCode)||StringUtils.isEmpty(corpName)){
            return "系统错误，请联系管理员";
        }
        MarCompany marCompany1 = marCompanyMapper.selectMarCompanyById(corpCode);
        if(marCompany1!=null){
            String sads=marCompany1.getCorpName();
            if(corpName.equals(sads)){
                return "修改名称与原名称相同";
            }

        }

        MarCompany marCompany=new MarCompany();
        marCompany.setCorpCode(corpCode);
        marCompany.setCorpName(corpName);
        List<MarCompany> lisy=marCompanyMapper.selcheckingcompany(marCompany);
        if(lisy.size()>0){
            return "当前公司名称已存在";
        }else{
            return "当前公司名称可以修改";
        }

    }

    @Override
    public String getcompanyName(String companyName) {
        if(StringUtils.isEmpty(companyName)){
            return "请输入公司名称";
        }
        MarCompany mar = marCompanyMapper.selectMarCompanyByName(companyName);
        if(mar!=null){
            return "当前公司已存在";
        }
        return "当前公司不存在";
    }
}
