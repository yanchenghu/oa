package com.ruoyi.demand.service;

import java.util.List;
import com.ruoyi.demand.domain.MarDemandresume;

/**
 * 需求绑定简历Service接口
 * 
 * @author ych
 * @date 2020-11-13
 */
public interface IMarDemandresumeService 
{
    /**
     * 查询需求绑定简历
     * 
     * @param id 需求绑定简历ID
     * @return 需求绑定简历
     */
    public MarDemandresume selectMarDemandresumeById(String id);

    /**
     * 查询需求绑定简历列表
     * 
     * @param marDemandresume 需求绑定简历
     * @return 需求绑定简历集合
     */
    public List<MarDemandresume> selectMarDemandresumeList(MarDemandresume marDemandresume);

    /**
     * 新增需求绑定简历
     * 
     * @param marDemandresume 需求绑定简历
     * @return 结果
     */
    public int insertMarDemandresume(MarDemandresume marDemandresume);

    /**
     * 修改需求绑定简历
     * 
     * @param marDemandresume 需求绑定简历
     * @return 结果
     */
    public int updateMarDemandresume(MarDemandresume marDemandresume);

    /**
     * 批量删除需求绑定简历
     * 
     * @param ids 需要删除的需求绑定简历ID
     * @return 结果
     */
    public int deleteMarDemandresumeByIds(String[] ids);

    /**
     * 删除需求绑定简历信息
     * 
     * @param id 需求绑定简历ID
     * @return 结果
     */
    public int deleteMarDemandresumeById(String id);
}
