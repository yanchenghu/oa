package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.finance.mapper.FinMonitoringcompaniesMapper;
import com.ruoyi.finance.domain.FinMonitoringcompanies;
import com.ruoyi.finance.service.IFinMonitoringcompaniesService;

/**
 * 重点监测公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
@Service
public class FinMonitoringcompaniesServiceImpl implements IFinMonitoringcompaniesService 
{
    @Autowired
    private FinMonitoringcompaniesMapper finMonitoringcompaniesMapper;

    /**
     * 查询重点监测公司
     * 
     * @param id 重点监测公司ID
     * @return 重点监测公司
     */
    @Override
    public FinMonitoringcompanies selectFinMonitoringcompaniesById(Integer id)
    {
        return finMonitoringcompaniesMapper.selectFinMonitoringcompaniesById(id);
    }

    /**
     * 查询重点监测公司列表
     * 
     * @param finMonitoringcompanies 重点监测公司
     * @return 重点监测公司
     */
    @Override
    public List<FinMonitoringcompanies> selectFinMonitoringcompaniesList(FinMonitoringcompanies finMonitoringcompanies)
    {
        return finMonitoringcompaniesMapper.selectFinMonitoringcompaniesList(finMonitoringcompanies);
    }

    /**
     * 新增重点监测公司
     * 
     * @param finMonitoringcompanies 重点监测公司
     * @return 结果
     */
    @Override
    public int insertFinMonitoringcompanies(FinMonitoringcompanies finMonitoringcompanies)
    {
        FinMonitoringcompanies sdad=new FinMonitoringcompanies();
        sdad.setCorpCode( finMonitoringcompanies.getCorpCode());
        List<FinMonitoringcompanies> finMonitoringcompanies1 = finMonitoringcompaniesMapper.selectFinMonitoringcompaniesList(sdad);
        if(finMonitoringcompanies1.size()>0){
            return 0;
        }
        return finMonitoringcompaniesMapper.insertFinMonitoringcompanies(finMonitoringcompanies);
    }

    /**
     * 修改重点监测公司
     * 
     * @param finMonitoringcompanies 重点监测公司
     * @return 结果
     */
    @Override
    public int updateFinMonitoringcompanies(FinMonitoringcompanies finMonitoringcompanies)
    {
        return finMonitoringcompaniesMapper.updateFinMonitoringcompanies(finMonitoringcompanies);
    }

    /**
     * 批量删除重点监测公司
     * 
     * @param ids 需要删除的重点监测公司ID
     * @return 结果
     */
    @Override
    public int deleteFinMonitoringcompaniesByIds(Integer[] ids)
    {
        return finMonitoringcompaniesMapper.deleteFinMonitoringcompaniesByIds(ids);
    }

    /**
     * 删除重点监测公司信息
     * 
     * @param id 重点监测公司ID
     * @return 结果
     */
    @Override
    public int deleteFinMonitoringcompaniesById(Integer id)
    {
        return finMonitoringcompaniesMapper.deleteFinMonitoringcompaniesById(id);
    }
}
