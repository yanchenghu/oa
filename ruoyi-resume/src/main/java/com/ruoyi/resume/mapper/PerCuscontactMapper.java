package com.ruoyi.resume.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.resume.domain.PerCuscontact;

/**
 * 简历跟踪Mapper接口
 * 
 * @author ych
 * @date 2020-11-10
 */
public interface PerCuscontactMapper 
{
    /**
     * 查询简历跟踪
     * 
     * @param contacDatecode 简历跟踪ID
     * @return 简历跟踪
     */
    public PerCuscontact selectPerCuscontactById(String contacDatecode);

    /**
     * 查询简历跟踪列表
     * 
     * @param perCuscontact 简历跟踪
     * @return 简历跟踪集合
     */
    public List<PerCuscontact> selectPerCuscontactList(PerCuscontact perCuscontact);

    /**
     * 新增简历跟踪
     * 
     * @param perCuscontact 简历跟踪
     * @return 结果
     */
    public int insertPerCuscontact(PerCuscontact perCuscontact);

    /**
     * 修改简历跟踪
     * 
     * @param perCuscontact 简历跟踪
     * @return 结果
     */
    public int updatePerCuscontact(PerCuscontact perCuscontact);

    /**
     * 删除简历跟踪
     * 
     * @param contacDatecode 简历跟踪ID
     * @return 结果
     */
    public int deletePerCuscontactById(String contacDatecode);

    /**
     * 批量删除简历跟踪
     * 
     * @param contacDatecodes 需要删除的数据ID
     * @return 结果
     */
    public int deletePerCuscontactByIds(String[] contacDatecodes);

    /**
     * 根据简历编号查询跟总信息
     *
     * @param customerCode
     * @return 结果
     */
    List<Map> selectBycustomerCode(String customerCode);

}
