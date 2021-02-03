package com.ruoyi.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.MarCompany;
import com.ruoyi.customer.mapper.MarCompanyMapper;
import com.ruoyi.finance.domain.FinAncecontact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinAncecompanyMapper;
import com.ruoyi.finance.domain.FinAncecompany;
import com.ruoyi.finance.service.IFinAncecompanyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 添加合作公司Service业务层处理
 * 
 * @author ych
 * @date 2021-02-02
 */
@Service
public class FinAncecompanyServiceImpl implements IFinAncecompanyService 
{
    @Autowired
    private FinAncecompanyMapper finAncecompanyMapper;
    @Autowired
    private MarCompanyMapper marCompanyMapper;

    /**
     * 查询添加合作公司
     * 
     * @param id 添加合作公司ID
     * @return 添加合作公司
     */
    @Override
    public FinAncecontact selectFinAncecontactById(Integer id)
    {
        return finAncecompanyMapper.selectFinAncecontactById(id);
    }

    /**
     * 查询添加合作公司列表
     * 
     * @param finAncecompany 添加合作公司
     * @return 添加合作公司
     */
    @Override
    public List<FinAncecompany> selectFinAncecompanyList(FinAncecompany finAncecompany)
    {
        return finAncecompanyMapper.selectFinAncecompanyList(finAncecompany);
    }

    /**
     * 新增添加合作公司
     * 
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertFinAncecompany(String zm)
    {
        List<String> licorp = JSON.parseArray(JSON.parseObject(zm).getString("corpCodelist"), String.class);
        if (licorp.size()<=0){
            return AjaxResult.error("未选中添加公司，请重新选择");
        }
        for (String corp:licorp){
            MarCompany marCompany = marCompanyMapper.selectMarCompanyById(corp);
            FinAncecompany finAncecompany=new FinAncecompany();
            finAncecompany.setCorpCode(corp);
            finAncecompany.setAddTime(new Date());
            List<FinAncecompany> list=finAncecompanyMapper.selectFinAncecompanyList(finAncecompany);
            if(list.size()>0){
                return AjaxResult.error("添加失败，当前月已经添加过:"+marCompany.getCorpName());
            }
            finAncecompany.setCorpName(marCompany.getCorpName());
            finAncecompanyMapper.insertFinAncecompany(finAncecompany);
        }
            return AjaxResult.success();

    }

    /**
     * 修改添加合作公司
     * 
     * @param finAncecompany 添加合作公司
     * @return 结果
     */
    @Override
    public int updateFinAncecompany(FinAncecompany finAncecompany)
    {
        return finAncecompanyMapper.updateFinAncecompany(finAncecompany);
    }

    /**
     * 批量删除添加合作公司
     * 
     * @param ids 需要删除的添加合作公司ID
     * @return 结果
     */
    @Override
    public int deleteFinAncecompanyByIds(Integer[] ids)
    {
        return finAncecompanyMapper.deleteFinAncecompanyByIds(ids);
    }

    /**
     * 删除添加合作公司信息
     * 
     * @param id 添加合作公司ID
     * @return 结果
     */
    @Override
    public int deleteFinAncecompanyById(Integer id)
    {
        FinAncecompany finAncecompany = finAncecompanyMapper.selectFinAncecompanyById(id);
        if(finAncecompany!=null){
            finAncecompanyMapper.deleteFinAncecontactById(finAncecompany.getCorpCode());
        }
        return finAncecompanyMapper.deleteFinAncecompanyById(id);
    }
    @Override
    public List<FinAncecontact> selectFinAncecontactList(FinAncecompany finAncecompany) {

        return finAncecompanyMapper.selectFinAncecontactList(finAncecompany);

    }
}
