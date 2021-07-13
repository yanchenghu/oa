package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.PerEnclosureProject;

/**
 * 附件简历项目经验Mapper接口
 * 
 * @author ych
 * @date 2021-07-02
 */
public interface PerEnclosureProjectMapper 
{
    /**
     * 查询附件简历项目经验
     * 
     * @param custproId 附件简历项目经验ID
     * @return 附件简历项目经验
     */
    public PerEnclosureProject selectPerEnclosureProjectById(Integer custproId);

    /**
     * 查询附件简历项目经验列表
     * 
     * @param perEnclosureProject 附件简历项目经验
     * @return 附件简历项目经验集合
     */
    public List<PerEnclosureProject> selectPerEnclosureProjectList(PerEnclosureProject perEnclosureProject);

    /**
     * 新增附件简历项目经验
     * 
     * @param perEnclosureProject 附件简历项目经验
     * @return 结果
     */
    public int insertPerEnclosureProject(PerEnclosureProject perEnclosureProject);

    /**
     * 修改附件简历项目经验
     * 
     * @param perEnclosureProject 附件简历项目经验
     * @return 结果
     */
    public int updatePerEnclosureProject(PerEnclosureProject perEnclosureProject);

    /**
     * 删除附件简历项目经验
     * 
     * @param custproId 附件简历项目经验ID
     * @return 结果
     */
    public int deletePerEnclosureProjectById(Integer custproId);

    /**
     * 批量删除附件简历项目经验
     * 
     * @param custproIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePerEnclosureProjectByIds(Integer[] custproIds);
}
