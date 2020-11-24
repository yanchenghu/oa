package com.ruoyi.resume.mapper;

import java.util.List;
import com.ruoyi.resume.domain.PerProject;

/**
 * 简历工作经验Mapper接口
 * 
 * @author ych
 * @date 2020-11-06
 */
public interface PerProjectMapper 
{
    /**
     * 查询简历工作经验
     * 
     * @param custproId 简历工作经验ID
     * @return 简历工作经验
     */
    public PerProject selectPerProjectById(String custproId);

    /**
     * 查询简历工作经验列表
     * 
     * @param perProject 简历工作经验
     * @return 简历工作经验集合
     */
    public List<PerProject> selectPerProjectList(PerProject perProject);

    /**
     * 新增简历工作经验
     * 
     * @param perProject 简历工作经验
     * @return 结果
     */
    public int insertPerProject(PerProject perProject);

    /**
     * 修改简历工作经验
     * 
     * @param perProject 简历工作经验
     * @return 结果
     */
    public int updatePerProject(PerProject perProject);

    /**
     * 删除简历工作经验
     * 
     * @param custproId 简历工作经验ID
     * @return 结果
     */
    public int deletePerProjectById(Integer custproId);

    /**
     * 批量删除简历工作经验
     * 
     * @param custproIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePerProjectByIds(String[] custproIds);

    int insertList(List<PerProject> proListArr);
}
