package com.ruoyi.finance.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.tool.ExcelRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinExpatriatesalaryMapper;
import com.ruoyi.finance.domain.FinExpatriatesalary;
import com.ruoyi.finance.service.IFinExpatriatesalaryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 外派人员工资Service业务层处理
 * 
 * @author ych
 * @date 2021-01-27
 */
@Service
public class FinExpatriatesalaryServiceImpl implements IFinExpatriatesalaryService 
{
    @Autowired
    private FinExpatriatesalaryMapper finExpatriatesalaryMapper;

    /**
     * 查询外派人员工资
     * 
     * @param id 外派人员工资ID
     * @return 外派人员工资
     */
    @Override
    public FinExpatriatesalary selectFinExpatriatesalaryById(Integer id)
    {
        return finExpatriatesalaryMapper.selectFinExpatriatesalaryById(id);
    }

    /**
     * 查询外派人员工资列表
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 外派人员工资
     */
    @Override
    public List<FinExpatriatesalary> selectFinExpatriatesalaryList(FinExpatriatesalary finExpatriatesalary)
    {
        return finExpatriatesalaryMapper.selectFinExpatriatesalaryList(finExpatriatesalary);
    }


    /**
     * 修改外派人员工资
     * 
     * @param finExpatriatesalary 外派人员工资
     * @return 结果
     */
    @Override
    public int updateFinExpatriatesalary(FinExpatriatesalary finExpatriatesalary)
    {
        return finExpatriatesalaryMapper.updateFinExpatriatesalary(finExpatriatesalary);
    }

    /**
     * 批量删除外派人员工资
     * 
     * @param ids 需要删除的外派人员工资ID
     * @return 结果
     */
    @Override
    public int deleteFinExpatriatesalaryByIds(Integer[] ids)
    {
        return finExpatriatesalaryMapper.deleteFinExpatriatesalaryByIds(ids);
    }

    /**
     * 删除外派人员工资信息
     * 
     * @param id 外派人员工资ID
     * @return 结果
     */
    @Override
    public int deleteFinExpatriatesalaryById(Integer id)
    {
        return finExpatriatesalaryMapper.deleteFinExpatriatesalaryById(id);
    }
    /**
     * 新增外派人员工资
     */
    @Override
    @Transactional
    public AjaxResult addfinExpatriatesalary(MultipartFile file, Date months, LoginUser loginUser) throws IOException {
          if(file==null && months==null){
              return AjaxResult.error("上传失败,文件或者日期为空！");
          }
        //读取Excel数据到List中
        List<ArrayList<String>> list = new ExcelRead().readExcel(file);
        List<FinExpatriatesalary> listFinExpat=new ArrayList<>();
        for(ArrayList<String> arr:list) {
            FinExpatriatesalary finExpatriate=new FinExpatriatesalary();
            if (arr.get(0) != null && !arr.get(0).equals("")) {
                finExpatriate.setCustomerName(arr.get(0));//每一行的第一个单元格
            } else {
                return AjaxResult.error("上传失败,文件中有姓名为空，请核对后重新上传！");
            }
            if (arr.get(1) != null && !arr.get(1).equals("")) {
                finExpatriate.setBasicSalary(arr.get(1));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的综合工资为空！");
            }
            if (arr.get(2) != null && !arr.get(2).equals("")) {
                finExpatriate.setOverAttence(arr.get(2));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的满勤/天为空！");
            }
            if (arr.get(3) != null && !arr.get(3).equals("")) {
                finExpatriate.setRealAttence(arr.get(3));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的实际出勤/天为空！");
            }
            if (arr.get(4) != null && !arr.get(4).equals("")) {
                finExpatriate.setProjectSalary(arr.get(4));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的应发工资为空！");
            }
            if (arr.get(5) != null && !arr.get(5).equals("")) {
                finExpatriate.setBuzhuSalary(arr.get(5));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的补助为空！");
            }
            if (arr.get(6) != null && !arr.get(6).equals("")) {
                finExpatriate.setBaoxianSalary(arr.get(6));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的补助为空！");
            }
            if (arr.get(7) != null && !arr.get(7).equals("")) {
                finExpatriate.setBaoxianSalary(arr.get(7));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的实发金额为空！");
            }
            if (arr.get(8) != null && !arr.get(8).equals("")) {
                finExpatriate.setBaoxianSalary(arr.get(8));
                FinExpatriatesalary dsf=finExpatriatesalaryMapper.selectFinExpatriatesalaryBytel(finExpatriate);
                if(dsf==null){
                    return AjaxResult.error("上传失败"+arr.get(0)+"未入项，请核对消息！");
                }
                finExpatriate.setCustomerCode(dsf.getCustomerCode());

            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的电话为空！");
            }
            listFinExpat.add(finExpatriate);
        }
        String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
          for (FinExpatriatesalary finExpatriatesala:listFinExpat){
              finExpatriatesala.setMonths(months);
              finExpatriatesala.setAddDate(new Date());
              finExpatriatesala.setAddPeople(loginUser.getUsername());
              finExpatriatesala.setExcelPath(avatar);
          }

        int a =finExpatriatesalaryMapper.insertFinExpatriatesalarylist(listFinExpat);
        if (a > 1) {
            return AjaxResult.success("上传成功");
        }
        return AjaxResult.error("上传失败");

    }
}
