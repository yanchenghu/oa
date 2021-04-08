package com.ruoyi.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.domain.MarContract;
import com.ruoyi.customer.domain.MarContractfollow;
import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.mapper.MarContractMapper;
import com.ruoyi.customer.mapper.MarContractfollowMapper;
import com.ruoyi.demand.domain.MarAuditeditor;
import com.ruoyi.demand.mapper.MarAuditeditorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.MarCompanyMapper;
import com.ruoyi.customer.domain.MarCompany;
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

        HashMap hashmap = new HashMap();
        hashmap.put("marContracts",marContracts);
        hashmap.put("marCompany",marCompany);
        hashmap.put("mar",mar);
        hashmap.put("listAuditeditors",listAuditeditors);
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
    public AjaxResult updateMarCompany(MarCompany marCompany,LoginUser loginUser)
    {
        MarCompany maw = marCompanyMapper.selectMarCompanyByName(marCompany.getCorpName());
        if(!marCompany.getCorpCode().equals(maw.getCorpCode())){
            return AjaxResult.error("当前公司已存在");
        }

        MarCompany mar = marCompanyMapper.selectMarCompanyById(marCompany.getCorpCode());
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
}
