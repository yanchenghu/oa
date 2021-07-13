package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarDemandRequirement;

/**
 * 需求岗位要求Mapper接口
 * 
 * @author ych
 * @date 2021-06-23
 */
public interface MarDemandRequirementMapper 
{
    /**
     * 查询需求岗位要求
     * 
     * @param id 需求岗位要求ID
     * @return 需求岗位要求
     */
    public MarDemandRequirement selectMarDemandRequirementById(Long id);

    /**
     * 查询需求岗位要求列表
     * 
     * @param marDemandRequirement 需求岗位要求
     * @return 需求岗位要求集合
     */
    public List<MarDemandRequirement> selectMarDemandRequirementList(MarDemandRequirement marDemandRequirement);

    /**
     * 新增需求岗位要求
     * 
     * @param marDemandRequirement 需求岗位要求
     * @return 结果
     */
    public int insertMarDemandRequirement(MarDemandRequirement marDemandRequirement);

    /**
     * 修改需求岗位要求
     * 
     * @param marDemandRequirement 需求岗位要求
     * @return 结果
     */
    public int updateMarDemandRequirement(MarDemandRequirement marDemandRequirement);

    /**
     * 删除需求岗位要求
     * 
     * @param id 需求岗位要求ID
     * @return 结果
     */
    public int deleteMarDemandRequirementById(Long id);

    /**
     * 批量删除需求岗位要求
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarDemandRequirementByIds(Long[] ids);

    void insertMListmarDema(List<MarDemandRequirement> listmarDema);
}
