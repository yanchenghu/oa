package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarSign;

/**
 * 需求分配Mapper接口
 * 
 * @author ych
 * @date 2020-12-01
 */
public interface MarSignMapper 
{
    /**
     * 查询需求分配
     * 
     * @param id 需求分配ID
     * @return 需求分配
     */
    public MarSign selectMarSignById(Long id);

    /**
     * 查询需求分配列表
     * 
     * @param marSign 需求分配
     * @return 需求分配集合
     */
    public List<MarSign> selectMarSignList(MarSign marSign);

    /**
     * 新增需求分配
     * 
     * @param marSign 需求分配
     * @return 结果
     */
    public int insertMarSign(MarSign marSign);

    /**
     * 修改需求分配
     * 
     * @param marSign 需求分配
     * @return 结果
     */
    public int updateMarSign(MarSign marSign);

    /**
     * 删除需求分配
     * 
     * @param id 需求分配ID
     * @return 结果
     */
    public int deleteMarSignById(Long id);

    /**
     * 批量删除需求分配
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarSignByIds(Long[] ids);

    void insertMList(List<MarSign> list);

    /**
     * 根据需求ID删除
     * @return 结果
     */
    int deleteMarSignBy(String demandId);
}
