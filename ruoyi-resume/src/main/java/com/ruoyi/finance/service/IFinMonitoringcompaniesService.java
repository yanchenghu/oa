package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.FinMonitoringcompanies;

/**
 * 重点监测公司Service接口
 * 
 * @author ruoyi
 * @date 2021-02-02
 */
public interface IFinMonitoringcompaniesService 
{
    /**
     * 查询重点监测公司
     * 
     * @param id 重点监测公司ID
     * @return 重点监测公司
     */
    public FinMonitoringcompanies selectFinMonitoringcompaniesById(Integer id);

    /**
     * 查询重点监测公司列表
     * 
     * @param finMonitoringcompanies 重点监测公司
     * @return 重点监测公司集合
     */
    public List<FinMonitoringcompanies> selectFinMonitoringcompaniesList(FinMonitoringcompanies finMonitoringcompanies);

    /**
     * 新增重点监测公司
     * 
     * @param finMonitoringcompanies 重点监测公司
     * @return 结果
     */
    public int insertFinMonitoringcompanies(FinMonitoringcompanies finMonitoringcompanies);

    /**
     * 修改重点监测公司
     * 
     * @param finMonitoringcompanies 重点监测公司
     * @return 结果
     */
    public int updateFinMonitoringcompanies(FinMonitoringcompanies finMonitoringcompanies);

    /**
     * 批量删除重点监测公司
     * 
     * @param ids 需要删除的重点监测公司ID
     * @return 结果
     */
    public int deleteFinMonitoringcompaniesByIds(Integer[] ids);

    /**
     * 删除重点监测公司信息
     * 
     * @param id 重点监测公司ID
     * @return 结果
     */
    public int deleteFinMonitoringcompaniesById(Integer id);
}
