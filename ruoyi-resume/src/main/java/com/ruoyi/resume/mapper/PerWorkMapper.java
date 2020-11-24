package com.ruoyi.resume.mapper;

import java.util.List;
import com.ruoyi.resume.domain.PerWork;

/**
 * 简历录入工作经历Mapper接口
 * 
 * @author ych
 * @date 2020-10-30
 */
public interface PerWorkMapper 
{
    /**
     * 查询简历录入工作经历
     * 
     * @param id 简历录入工作经历ID
     * @return 简历录入工作经历
     */
    public PerWork selectPerWorkById(Long id);

    /**
     * 查询简历录入工作经历列表
     * 
     * @param perWork 简历录入工作经历
     * @return 简历录入工作经历集合
     */
    public List<PerWork> selectPerWorkList(PerWork perWork);

    /**
     * 新增简历录入工作经历
     * 
     * @param perWork 简历录入工作经历
     * @return 结果
     */
    public int insertPerWork(PerWork perWork);

    /**
     * 修改简历录入工作经历
     * 
     * @param perWork 简历录入工作经历
     * @return 结果
     */
    public int updatePerWork(PerWork perWork);

    /**
     * 删除简历录入工作经历
     * 
     * @param id 简历录入工作经历ID
     * @return 结果
     */
    public int deletePerWorkById(Integer id);

    /**
     * 批量删除简历录入工作经历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePerWorkByIds(Integer[] ids);

    int insertList(List<PerWork> worklistArr);
}
