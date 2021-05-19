package com.ruoyi.demand.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.demand.domain.MarCertificates;
import com.ruoyi.demand.domain.MarCustomerprojectpay;
import com.ruoyi.demand.domain.MarServicepay;
import com.ruoyi.demand.mapper.MarCertificatesMapper;
import com.ruoyi.demand.mapper.MarCustomerprojectpayMapper;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.mapper.PerCustomerinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarServicepayMapper;
import com.ruoyi.demand.service.IMarServicepayService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 服务费调整记录Service业务层处理
 * 
 * @author ych
 * @date 2020-12-10
 */
@Service
public class MarServicepayServiceImpl implements IMarServicepayService 
{
    @Autowired
    private MarServicepayMapper marServicepayMapper;

    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;
    @Autowired
    private MarCertificatesMapper marCertificatesMapper;
    @Autowired
    private PerCustomerinfoMapper perCustomerinfoMapper;



    /**
     * 查询服务费调整记录
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 服务费调整记录
     */
    @Override
    public MarServicepay selectMarServicepayById(Integer servicepayId)
    {
        return marServicepayMapper.selectMarServicepayById(servicepayId);
    }

    /**
     * 查询服务费调整记录列表
     * 
     * @param marServicepay 服务费调整记录
     * @return 服务费调整记录
     */
    @Override
    public List<MarServicepay> selectMarServicepayList(MarServicepay marServicepay)
    {
        return marServicepayMapper.selectMarServicepayList(marServicepay);
    }

    /**
     * 新增服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    @Override
    public AjaxResult insertMarServicepay(MarServicepay marServicepay)
    {
        MarCustomerprojectpay marCustomerprojectpay = marCustomerprojectpayMapper.selectMarCustomerprojectpayById(marServicepay.getMarcusId());
        marCustomerprojectpay.setServicePay(marServicepay.getAfterServicepay());
        marServicepay.setCustomerCode(marCustomerprojectpay.getCustomerCode());
        marServicepay.setCorpName(marCustomerprojectpay.getCorpName());
        marServicepay.setDemandId(marCustomerprojectpay.getDemandId());
        marServicepay.setProjectName(marCustomerprojectpay.getProjectName());
        marServicepay.setSyqstartTime(marCustomerprojectpay.getSyqstartTime());
        marServicepay.setAddTime(new Date());
        marServicepayMapper.insertMarServicepay(marServicepay);
//        marCustomerprojectpayMapper.updateMarCustomerprojectpay(marCustomerprojectpay);
         return AjaxResult.success("服务费调整成功");
    }

    /**
     * 修改服务费调整记录
     * 
     * @param marServicepay 服务费调整记录
     * @return 结果
     */
    @Override
    public int updateMarServicepay(MarServicepay marServicepay)
    {
        return marServicepayMapper.updateMarServicepay(marServicepay);
    }

    /**
     * 批量删除服务费调整记录
     * 
     * @param servicepayIds 需要删除的服务费调整记录ID
     * @return 结果
     */
    @Override
    public int deleteMarServicepayByIds(Integer[] servicepayIds)
    {
        return marServicepayMapper.deleteMarServicepayByIds(servicepayIds);
    }

    /**
     * 删除服务费调整记录信息
     * 
     * @param servicepayId 服务费调整记录ID
     * @return 结果
     */
    @Override
    public int deleteMarServicepayById(Integer servicepayId)
    {
        return marServicepayMapper.deleteMarServicepayById(servicepayId);
    }
    /**
     * 添加证件
     */

    @Override
    public AjaxResult addCertifi(Integer id, String marcusId, Integer photo, MultipartFile file) {

        String avatar="";
        try {
             avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传失败");
        }
        MarCertificates marCertificates=new MarCertificates();
        if(photo==1){
            marCertificates.setIdcardPositive(avatar);
        }else if(photo==2){
            marCertificates.setIdcardReverse(avatar);
        }else if(photo==3){
            marCertificates.setDiploma(avatar);
        }else if(photo==4){
            marCertificates.setAcademic(avatar);
        }else if(photo==5){
            marCertificates.setConfidentialityAgreement(avatar);
        }else if(photo==6){
            marCertificates.setServiceContract(avatar);
        }else if(photo==7){
            marCertificates.setConfidentialityAgreementlo(avatar);
        }
       if(id==null){
           MarCustomerprojectpay marCustomerprojectpay = marCustomerprojectpayMapper.selectMarCustomerprojectpayById(marcusId);
           marCertificates.setMarcusId(marcusId);
           marCertificates.setCustomerCode(marCustomerprojectpay.getCustomerCode());
           marCertificates.setAddTime(new Date());
           marCertificatesMapper.insertMarCertificates(marCertificates);
       }else{
           marCertificates.setId(id);
           marCertificatesMapper.updateMarCertificates(marCertificates);
       }
        return AjaxResult.success("文件上传成功");


    }

    @Override
    @Transactional
    public AjaxResult personnelItems(MarCustomerprojectpay marCustomerprojectpay) {
        MarCustomerprojectpay marCustomerprojectpay1 = marCustomerprojectpayMapper.selectMarCustomerprojectpayById(marCustomerprojectpay.getId());
        String customerCode=marCustomerprojectpay1.getCustomerCode();
        if ( StringUtils.isEmpty(customerCode)) {
            return  AjaxResult.error("出项人编号为空，请检查后再出项") ;
        }
        PerCustomerinfo perCustomerinf =new PerCustomerinfo();
        perCustomerinf.setCustomerCode(customerCode);
        perCustomerinf.setJoinStatus(3);
        perCustomerinfoMapper.updatePerCustomerinfo(perCustomerinf);
        int a= marCustomerprojectpayMapper.updateMarCustomerprojectpay(marCustomerprojectpay);
        if (a==1){
            return  AjaxResult.success("出项成功") ;
        }
        return  AjaxResult.error("出项失败") ;
    }
}
