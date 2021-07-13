package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarDemandresumeRequirement;

/**
 * 简历 绑定岗位Mapper接口
 * 
 * @author ych
 * @date 2021-06-23
 */
public interface MarDemandresumeRequirementMapper 
{
    /**
     * 查询简历 绑定岗位
     * 
     * @param id 简历 绑定岗位ID
     * @return 简历 绑定岗位
     */
    public MarDemandresumeRequirement selectMarDemandresumeRequirementById(Long id);

    /**
     * 查询简历 绑定岗位列表
     * 
     * @param marDemandresumeRequirement 简历 绑定岗位
     * @return 简历 绑定岗位集合
     */
    public List<MarDemandresumeRequirement> selectMarDemandresumeRequirementList(MarDemandresumeRequirement marDemandresumeRequirement);

    /**
     * 新增简历 绑定岗位
     * 
     * @param marDemandresumeRequirement 简历 绑定岗位
     * @return 结果
     */
    public int insertMarDemandresumeRequirement(MarDemandresumeRequirement marDemandresumeRequirement);

    /**
     * 修改简历 绑定岗位
     * 
     * @param marDemandresumeRequirement 简历 绑定岗位
     * @return 结果
     */
    public int updateMarDemandresumeRequirement(MarDemandresumeRequirement marDemandresumeRequirement);

    /**
     * 删除简历 绑定岗位
     * 
     * @param id 简历 绑定岗位ID
     * @return 结果
     */
    public int deleteMarDemandresumeRequirementById(Long id);

    /**
     * 批量删除简历 绑定岗位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarDemandresumeRequirementByIds(Long[] ids);

    void delbyRequirements_id(Long id);
}
