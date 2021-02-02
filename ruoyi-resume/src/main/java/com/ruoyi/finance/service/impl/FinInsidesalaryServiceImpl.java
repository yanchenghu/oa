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
import com.ruoyi.finance.mapper.FinInsidesalaryMapper;
import com.ruoyi.finance.domain.FinInsidesalary;
import com.ruoyi.finance.service.IFinInsidesalaryService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 内部人员工资Service业务层处理
 * 
 * @author ych
 * @date 2021-01-29
 */
@Service
public class FinInsidesalaryServiceImpl implements IFinInsidesalaryService
{
    @Autowired
    private FinInsidesalaryMapper finInsidesalaryMapper;

    /**
     * 查询内部人员工资
     * 
     * @param id 内部人员工资ID
     * @return 内部人员工资
     */
    @Override
    public FinInsidesalary selectFinInsidesalaryById(Long id)
    {
        return finInsidesalaryMapper.selectFinInsidesalaryById(id);
    }

    /**
     * 查询内部人员工资列表
     * 
     * @param finInsidesalary 内部人员工资
     * @return 内部人员工资
     */
    @Override
    public List<FinInsidesalary> selectFinInsidesalaryList(FinInsidesalary finInsidesalary)
    {
        return finInsidesalaryMapper.selectFinInsidesalaryList(finInsidesalary);
    }



    /**
     * 修改内部人员工资
     * 
     * @param finInsidesalary 内部人员工资
     * @return 结果
     */
    @Override
    public int updateFinInsidesalary(FinInsidesalary finInsidesalary)
    {
        return finInsidesalaryMapper.updateFinInsidesalary(finInsidesalary);
    }

    /**
     * 批量删除内部人员工资
     * 
     * @param ids 需要删除的内部人员工资ID
     * @return 结果
     */
    @Override
    public int deleteFinInsidesalaryByIds(Long[] ids)
    {
        return finInsidesalaryMapper.deleteFinInsidesalaryByIds(ids);
    }

    /**
     * 删除内部人员工资信息
     * 
     * @param id 内部人员工资ID
     * @return 结果
     */
    @Override
    public int deleteFinInsidesalaryById(Long id)
    {
        return finInsidesalaryMapper.deleteFinInsidesalaryById(id);
    }
    /**
     * 新增内部人员工资
     */
    @Override
    public AjaxResult addfinFinInsidesalary(MultipartFile file, Date months, LoginUser loginUser)throws IOException {
        if(file==null && months==null){
            return AjaxResult.error("上传失败,文件或者日期为空！");
        }
        //读取Excel数据到List中
        List<ArrayList<String>> list = new ExcelRead().readExcel(file);
        List<FinInsidesalary> listFinExpat=new ArrayList<>();
        for(ArrayList<String> arr:list) {
            FinInsidesalary finInside=new FinInsidesalary();
            if (arr.get(0) != null && !arr.get(0).equals("")) {
                finInside.setName(arr.get(0));//每一行的第一个单元格
            } else {
                return AjaxResult.error("上传失败,文件中有姓名为空，请核对后重新上传！");
            }
            finInside.setSideMonths(months);
            List<FinInsidesalary> finExpatriries = finInsidesalaryMapper.selectFinInsidesalaryList(finInside);
            if(finExpatriries.size() != 0) {
                return AjaxResult.error("当前人"+arr.get(0)+"的当月的的结算单已经上传");
            }

            if (arr.get(1) != null && !arr.get(1).equals("")) {
                finInside.setLeaveDate(arr.get(1));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的请假天数为空！");
            }
            if (arr.get(2) != null && !arr.get(2).equals("")) {
                finInside.setRealAttence(arr.get(2));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的实际工资为空！");
            }
            if (arr.get(3) != null && !arr.get(3).equals("")) {
                finInside.setActualAttendance(arr.get(3));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的实际出勤/天为空！");
            }
            if (arr.get(4) != null && !arr.get(4).equals("")) {
                finInside.setStatutoryAttendance(arr.get(4));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的法定出勤为空！");
            }
            if (arr.get(5) != null && !arr.get(5).equals("")) {
                finInside.setActualIncome(arr.get(5));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的入项实际工资为空！");
            }
            if (arr.get(6) != null && !arr.get(6).equals("")) {
                finInside.setTaxes(arr.get(6));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的税金为空！");
            }
            if (arr.get(7) != null && !arr.get(7).equals("")) {
                finInside.setTotalManagement(arr.get(7));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的全勤奖为空！");
            }
            if (arr.get(8) != null && !arr.get(8).equals("")) {
                finInside.setInsurance(arr.get(8));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的保险为空！");
            }
            if (arr.get(9) != null && !arr.get(9).equals("")) {
                finInside.setCommission(arr.get(9));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的提成为空！");
            }
            if (arr.get(10) != null && !arr.get(10).equals("")) {
                finInside.setRealSalary(arr.get(10));
            } else {
                return AjaxResult.error("上传失败"+arr.get(0)+"的实发工资为空！");
            }

            listFinExpat.add(finInside);
        }
        String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        for (FinInsidesalary finExpatriatesala:listFinExpat){
            finExpatriatesala.setAddDate(new Date());
            finExpatriatesala.setAddPeople(loginUser.getUsername());
            finExpatriatesala.setExcelPath(avatar);
        }
        int a =finInsidesalaryMapper.insertFinInsidesalarylist(listFinExpat);
        if (a > 1) {
            return AjaxResult.success("上传成功");
        }
        return AjaxResult.error("上传失败");
    }
}
