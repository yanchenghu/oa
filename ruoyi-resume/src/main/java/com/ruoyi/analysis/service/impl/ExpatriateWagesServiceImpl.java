package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.domain.ExpatriateWages;
import com.ruoyi.analysis.mapper.ExpatriateWagesMapper;
import com.ruoyi.analysis.service.IExpatriateWagesService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.mapper.MarCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 外派人员工资统计实现层
 * @Author ych
 * @Date 2021/2/22
 */
@Service
public class ExpatriateWagesServiceImpl implements IExpatriateWagesService {

    @Autowired
    private ExpatriateWagesMapper expatriateWagesMapper;


    @Override
    public List<ExpatriateWages> selectExpatriateWagelist(ExpatriateWages expatriateWage) {
        List<ExpatriateWages> listexpatr =expatriateWagesMapper.selectExpatriateWagelist(expatriateWage);
        for (ExpatriateWages expatriateWages: listexpatr){
            String customerCode = expatriateWages.getCustomerCode();
            String corpCode = expatriateWages.getCorpCode();
            if(!customerCode.equals("")||!corpCode.equals("")){
                ExpatriateWages expatriat= expatriateWagesMapper.getCumulativenum(expatriateWages);
                if(expatriat!=null){
                    expatriateWages.setCumulativeServicepay(expatriat.getCumulativeServicepay());
                    expatriateWages.setCumulativeSalary(expatriat.getCumulativeSalary());
                    expatriateWages.setLastcollecMoth(expatriat.getLastcollecMoth());
                }
            }
        }
        return listexpatr;
    }

    @Override
    public List<Map> selectComlist(LoginUser loginUser,ExpatriateWages expatriateWages) {
        Map map=new HashMap();
        map.put("transformingPeople",loginUser.getUsername());
        map.put("corpName",expatriateWages.getCorpName());
        List<Map> lit=expatriateWagesMapper.selectComlist(map);
        return lit;
    }
}
