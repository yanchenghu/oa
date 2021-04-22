package com.ruoyi.finance.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.resume.DateUtils;
import com.ruoyi.finance.domain.Business;
import com.ruoyi.resume.domain.PerCustomerinfo;
import com.ruoyi.tool.ExcelRead;
import net.sf.jsqlparser.statement.Statements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinStatementsMapper;
import com.ruoyi.finance.domain.FinStatements;
import com.ruoyi.finance.service.IFinStatementsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 服务费核算请求书Service业务层处理
 * 
 * @author ych
 * @date 2021-01-21
 */
@Service
public class FinStatementsServiceImpl implements IFinStatementsService 
{
    @Autowired
    private FinStatementsMapper finStatementsMapper;

    /**
     * 查询服务费核算请求书
     * 
     * @param id 服务费核算请求书ID
     * @return 服务费核算请求书
     */
    @Override
    public FinStatements selectFinStatementsById(Integer id)
    {
        return finStatementsMapper.selectFinStatementsById(id);
    }

    /**
     * 查询服务费核算请求书列表
     * 
     * @param finStatements 服务费核算请求书
     * @return 服务费核算请求书
     */
    @Override
    public List<FinStatements> selectFinStatementsList(FinStatements finStatements)
    {
        return finStatementsMapper.selectFinStatementsList(finStatements);
    }


    @Override
    public int insertFinStatements(FinStatements finStatements)
    {
        return finStatementsMapper.insertFinStatements(finStatements);
    }

    /**
     * 修改服务费核算请求书
     * 
     * @param finStatements 服务费核算请求书
     * @return 结果
     */
    @Override
    public int updateFinStatements(FinStatements finStatements)
    {
        return finStatementsMapper.updateFinStatements(finStatements);
    }

    /**
     * 批量删除服务费核算请求书
     * 
     * @param ids 需要删除的服务费核算请求书ID
     * @return 结果
     */
    @Override
    public int deleteFinStatementsByIds(Integer[] ids)
    {
        return finStatementsMapper.deleteFinStatementsByIds(ids);
    }

    /**
     * 删除服务费核算请求书信息
     * 
     * @param id 服务费核算请求书ID
     * @return 结果
     */
    @Override
    public int deleteFinStatementsById(Integer id)
    {
        return finStatementsMapper.deleteFinStatementsById(id);
    }
    /**
     * 新增服务费核算请求书
     *
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult addFinStatements(MultipartFile file, String corpCode, String party, LoginUser loginUser) throws IOException {


            //读取Excel数据到List中
            List<ArrayList<String>> list = new ExcelRead().readExcel(file);
            //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始）
            List<FinStatements> listExcel = new ArrayList<FinStatements>();
            for(ArrayList<String> arr:list) {
                FinStatements excel  = new FinStatements();
                if (arr.get(0) != null && !arr.get(0).equals("")) {
                    excel.setCustomerName(arr.get(0));//每一行的第一个单元格
                } else {
                    return AjaxResult.error("上传失败，姓名为空！");
                }
                if(arr.get(1) == null || arr.get(1).equals("")){
                    return AjaxResult.error("上传失败，"+arr.get(0)+"时间为空！");
                }else if(arr.get(1).length() != 21){
                    return AjaxResult.error("上传失败，"+arr.get(0)+"时间字段格式不正确！");
                }else if(!arr.get(1).substring(10,11).equals("-")){
                    return AjaxResult.error("上传失败，"+arr.get(0)+"时间字段格式不正确！");
                }else {
                    excel.setTime(arr.get(1));
                    String startime=arr.get(1).substring(0, 4) + "-" + arr.get(1).substring(5, 7) + "-" + arr.get(1).substring(8, 10);
                    String endtime=arr.get(1).substring(11, 15) + "-" + arr.get(1).substring(16, 18) + "-" + arr.get(1).substring(19);
                    try {
                        excel.setStartTime(DateUtils.formatY_M_D2Date(startime,"yyyy-MM-dd"));
                        excel.setEndTime(DateUtils.formatY_M_D2Date(endtime,"yyyy-MM-dd"));
                    } catch (ParseException e) {
                        return AjaxResult.error("上传失败,时间字段格式不正确！");
                    }
                }
                String startime=arr.get(1).substring(0, 4) + "-" + arr.get(1).substring(5, 7) + "-" + arr.get(1).substring(8, 10);
                String endtime=arr.get(1).substring(11, 15) + "-" + arr.get(1).substring(16, 18) + "-" + arr.get(1).substring(19);
                FinStatements excssa  = new FinStatements();
                excssa.setCorpCode(corpCode);
                excssa.setParty(party);
                try {
                    excel.setStartTime(DateUtils.formatY_M_D2Date(startime,"yyyy-MM-dd"));
                    excel.setEndTime(DateUtils.formatY_M_D2Date(endtime,"yyyy-MM-dd"));
                } catch (ParseException e) {
                    return AjaxResult.error("上传失败,时间字段格式不正确！");
                }
                excel.setCustomerName( arr.get(0));

                List<FinStatements>  listFinStat=finStatementsMapper.selectFinStatementsList(excel);
                if(listFinStat.size() != 0) {
                    return AjaxResult.error("当前人"+arr.get(0)+"的当月的的结算单已经上传");
                }

                if(arr.get(2) != null && !arr.get(2).equals("")){
                    excel.setPrice(Double.parseDouble(arr.get(2)));
                }else{
                    return AjaxResult.error( "上传失败，"+arr.get(2)+"服务费字段格式不正确！");
                }

                if(arr.get(3) != null && !arr.get(3).equals("")){
                    excel.setStandard(Double.parseDouble(arr.get(3)));
                }else{
                    return AjaxResult.error( "上传失败，"+arr.get(3)+"标准工时字段格式不正确！");
                }

                if(arr.get(4) != null && !arr.get(4).equals("")){
                    excel.setAttenDance(Double.parseDouble(arr.get(4)));
                }else{
                    return AjaxResult.error( "上传失败，"+arr.get(4)+"出勤工时字段格式不正确！");

                }

                if(arr.get(5) != null && !arr.get(5).equals("")){
                    excel.setOverPay(Double.parseDouble(arr.get(5)));
                }else{
                    return AjaxResult.error( "上传失败，"+arr.get(5)+"加班费字段格式不正确！");

                }

                if(arr.get(6) != null && !arr.get(6).equals("")){
                    excel.setComBined(Double.parseDouble(arr.get(6)));
                }else{
                    return AjaxResult.error( "上传失败，"+arr.get(6)+"合计字段格式不正确！");
                }
                excel.setCorpCode(corpCode);
                excel.setParty(party);
                excel.setAddTime(new Date());
                excel.setAddPeople(loginUser.getUsername());
                FinStatements selectFinSt= finStatementsMapper.selectFinStatementsByNameCorp(excel);
                if(selectFinSt==null){
                    return AjaxResult.error( "上传失败，"+arr.get(0)+"当前人不在项！");
                }
                excel.setCustomerCode(selectFinSt.getCustomerCode());
                excel.setCustomerTel(selectFinSt.getCustomerTel());
                listExcel.add(excel);
            }
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            for(FinStatements finStat:listExcel){
                finStat.setExcelPicture(avatar);
            }
            int a= finStatementsMapper.insetlistExcel(listExcel);
            if(a>0){
                return AjaxResult.success( "上传成功");
            }else{
                return AjaxResult.error( "上传失败");
            }

    }
    /**
     * 批量开票
     */
    @Override
    @Transactional
    public AjaxResult batchBilling(String zm,MultipartFile file) {
        List<Integer> liId = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        String avatar="";
        if(file!=null){
            try {
                avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.error("图片上传错误，请联系管理员");
            }
        }
       for(Integer Id:liId){
           FinStatements finStatements = finStatementsMapper.selectFinStatementsById(Id);
           if(finStatements.getStatus()!=1){
            return AjaxResult.error("当前选中的状态有"+finStatements.getCustomerName()+ "已开票的，或已付款");
           }
           FinStatements finStat=new FinStatements();
           finStat.setStatus(2);
           finStat.setPicture(avatar);
           finStat.setUpdateDate(new Date());
           finStat.setId(Id);
           finStatementsMapper.updateFinStatements(finStat);
       }
        return AjaxResult.success("更改成功");
    }
    /**
     * 批量付款
     */
    @Override
    @Transactional
    public AjaxResult batchPayment(String zm) {
        List<Integer> liId = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        Date paymentTime= JSON.parseObject(zm).getDate("paymentTime");
        for(Integer Id:liId){
            FinStatements finStatements = finStatementsMapper.selectFinStatementsById(Id);
            if(finStatements.getStatus()!=2){
                return AjaxResult.error("当前选中的状态"+finStatements.getCustomerName()+"已开票的，或已付款");
            }
            FinStatements finStat=new FinStatements();
            finStat.setStatus(3);
            finStat.setId(Id);
            finStat.setPaymentTime(paymentTime);
            finStatementsMapper.updateFinStatements(finStat);
        }
        return AjaxResult.success("更改成功");
    }

    /**
     * 导出服务费
     */
    @Override
    public List<Business> selectFinStatementsLists(FinStatements finStatements) {

        List<Business> list = finStatementsMapper.selectFinStatementsLists(finStatements);

        for (Business s: list){
            String status = s.getStatus();
            String status1 = DictUtils.getDictLabel("serv_payment_status", status);
            s.setStatus(status1);
            Double price = s.getPrice();
            Double overPay = s.getOverPay();
            s.setSystemcomBined(price+overPay);
        }
        return list;
    }
}
