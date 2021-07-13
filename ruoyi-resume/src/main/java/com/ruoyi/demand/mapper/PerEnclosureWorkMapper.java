package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.PerEnclosureWork;

/**
 * 附件简历工作经历Mapper接口
 * 
 * @author ych
 * @date 2021-07-02
 */
public interface PerEnclosureWorkMapper 
{
    /**
     * 查询附件简历工作经历
     * 
     * @param id 附件简历工作经历ID
     * @return 附件简历工作经历
     */
    public PerEnclosureWork selectPerEnclosureWorkById(Integer id);

    /**
     * 查询附件简历工作经历列表
     * 
     * @param perEnclosureWork 附件简历工作经历
     * @return 附件简历工作经历集合
     */
    public List<PerEnclosureWork> selectPerEnclosureWorkList(PerEnclosureWork perEnclosureWork);

    /**
     * 新增附件简历工作经历
     * 
     * @param perEnclosureWork 附件简历工作经历
     * @return 结果
     */
    public int insertPerEnclosureWork(PerEnclosureWork perEnclosureWork);

    /**
     * 修改附件简历工作经历
     * 
     * @param perEnclosureWork 附件简历工作经历
     * @return 结果
     */
    public int updatePerEnclosureWork(PerEnclosureWork perEnclosureWork);

    /**
     * 删除附件简历工作经历
     * 
     * @param id 附件简历工作经历ID
     * @return 结果
     */
    public int deletePerEnclosureWorkById(Integer id);

    /**
     * 批量删除附件简历工作经历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePerEnclosureWorkByIds(Integer[] ids);
}
