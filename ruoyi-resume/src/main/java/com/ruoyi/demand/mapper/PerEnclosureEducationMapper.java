package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.PerEnclosureEducation;

/**
 * 附件简历教育背景Mapper接口
 * 
 * @author ych
 * @date 2021-07-02
 */
public interface PerEnclosureEducationMapper 
{
    /**
     * 查询附件简历教育背景
     * 
     * @param id 附件简历教育背景ID
     * @return 附件简历教育背景
     */
    public PerEnclosureEducation selectPerEnclosureEducationById(Integer id);

    /**
     * 查询附件简历教育背景列表
     * 
     * @param perEnclosureEducation 附件简历教育背景
     * @return 附件简历教育背景集合
     */
    public List<PerEnclosureEducation> selectPerEnclosureEducationList(PerEnclosureEducation perEnclosureEducation);

    /**
     * 新增附件简历教育背景
     * 
     * @param perEnclosureEducation 附件简历教育背景
     * @return 结果
     */
    public int insertPerEnclosureEducation(PerEnclosureEducation perEnclosureEducation);

    /**
     * 修改附件简历教育背景
     * 
     * @param perEnclosureEducation 附件简历教育背景
     * @return 结果
     */
    public int updatePerEnclosureEducation(PerEnclosureEducation perEnclosureEducation);

    /**
     * 删除附件简历教育背景
     * 
     * @param id 附件简历教育背景ID
     * @return 结果
     */
    public int deletePerEnclosureEducationById(Integer id);

    /**
     * 批量删除附件简历教育背景
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePerEnclosureEducationByIds(Integer[] ids);
}
