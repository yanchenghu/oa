package com.ruoyi.resume.service;

import java.util.List;
import com.ruoyi.resume.domain.PerCuscontact;

/**
 * 简历跟踪Service接口
 * 
 * @author ych
 * @date 2020-11-10
 */
public interface IPerCuscontactService 
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
     * 批量删除简历跟踪
     * 
     * @param contacDatecodes 需要删除的简历跟踪ID
     * @return 结果
     */
    public int deletePerCuscontactByIds(String[] contacDatecodes);

    /**
     * 删除简历跟踪信息
     * 
     * @param contacDatecode 简历跟踪ID
     * @return 结果
     */
    public int deletePerCuscontactById(String contacDatecode);
}
