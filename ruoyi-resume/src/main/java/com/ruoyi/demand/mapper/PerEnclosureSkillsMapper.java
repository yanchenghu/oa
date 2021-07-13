package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.PerEnclosureSkills;

/**
 * 专业技能Mapper接口
 * 
 * @author ych
 * @date 2021-07-02
 */
public interface PerEnclosureSkillsMapper 
{
    /**
     * 查询专业技能
     * 
     * @param id 专业技能ID
     * @return 专业技能
     */
    public PerEnclosureSkills selectPerEnclosureSkillsById(Integer id);

    /**
     * 查询专业技能列表
     * 
     * @param perEnclosureSkills 专业技能
     * @return 专业技能集合
     */
    public List<PerEnclosureSkills> selectPerEnclosureSkillsList(PerEnclosureSkills perEnclosureSkills);

    /**
     * 新增专业技能
     * 
     * @param perEnclosureSkills 专业技能
     * @return 结果
     */
    public int insertPerEnclosureSkills(PerEnclosureSkills perEnclosureSkills);

    /**
     * 修改专业技能
     * 
     * @param perEnclosureSkills 专业技能
     * @return 结果
     */
    public int updatePerEnclosureSkills(PerEnclosureSkills perEnclosureSkills);

    /**
     * 删除专业技能
     * 
     * @param id 专业技能ID
     * @return 结果
     */
    public int deletePerEnclosureSkillsById(Integer id);

    /**
     * 批量删除专业技能
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePerEnclosureSkillsByIds(Integer[] ids);
}
