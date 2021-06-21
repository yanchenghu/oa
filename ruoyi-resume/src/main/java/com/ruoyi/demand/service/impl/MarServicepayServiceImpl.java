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
import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.domain.MarServicepay;
import com.ruoyi.demand.mapper.*;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.resume.mapper.PerCustomerinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private MarDemandresumeMapper marDemandresumeMapper;

    @Autowired
    private MarCustomerprojectpayMapper marCustomerprojectpayMapper;
    @Autowired
    private MarCertificatesMapper marCertificatesMapper;
    @Autowired
    private PerCustomerinfoMapper perCustomerinfoMapper;

    @Autowired
    private MarDemandresumefollowMapper marDemandresumefollowMapper;



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

    @Override
    public AjaxResult accessItems(MarCustomerprojectpay marCustomerprojectpay) {

        MarCustomerprojectpay marCustom=new  MarCustomerprojectpay();
        if(StringUtils.isNotEmpty( marCustomerprojectpay.getId())){
            marCustom= marCustomerprojectpayMapper.selectMarCustomerprojectpayById(marCustomerprojectpay.getId());
            if(marCustom.getDemandId().equals(marCustomerprojectpay.getDemandId())){
                return AjaxResult.error("转入转出为同一个需求，请核实后重新操作");
            }
            MarDemandresume marDemandresume=new MarDemandresume();
            //查看当前人是否绑郭该需求，如果绑定过删除(特殊情况当前人在那个项目也入项目)
            marDemandresume.setDemandId(marCustomerprojectpay.getDemandId());
            marDemandresume.setCustomerCode(marCustom.getCustomerCode());
            List<MarDemandresume> marDemes = marDemandresumeMapper.selectMarDemandresumeList(marDemandresume);
            if(marDemes.size()>0){
                MarDemandresume marDemaume1 = marDemes.get(0);
                MarDemandresume marDemandresume1 = marDemandresumeMapper.SelectStatusBydemandIdcustomerCode(marDemaume1);
                if (marDemandresume1.getDownloadStatus()==7){
                    return AjaxResult.error("当前人已经入过该项目，不能重复入项");
                }else{
                    marDemandresumeMapper.deleteMarDemandresumeById(marDemandresume1.getId());
                }
            }

            marDemandresume.setDemandId(marCustom.getDemandId());
            List<MarDemandresume> marDemandresumes = marDemandresumeMapper.selectMarDemandresumeList(marDemandresume);
            if(marDemandresumes.size()>0){
                MarDemandresume marDeme1 = marDemandresumes.get(0);
                marDeme1.setDemandId(marCustomerprojectpay.getDemandId());
                marDemandresumeMapper.updateMarDemandresume(marDeme1);
                marCustomerprojectpayMapper.updateMarCustomerprojectpay(marCustomerprojectpay);

            }else{
                return AjaxResult.error("该简历未按照正常流程绑定，请联系管理员后在操作");

            }

        }
        return AjaxResult.success("转出入成功");
    }
}
