package com.ruoyi.demand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarSignMapper;
import com.ruoyi.demand.domain.MarSign;
import com.ruoyi.demand.service.IMarSignService;

/**
 * 需求分配Service业务层处理
 * 
 * @author ych
 * @date 2020-12-01
 */
@Service
public class MarSignServiceImpl implements IMarSignService 
{
    @Autowired
    private MarSignMapper marSignMapper;

    /**
     * 查询需求分配
     * 
     * @param id 需求分配ID
     * @return 需求分配
     */
    @Override
    public MarSign selectMarSignById(Long id)
    {
        return marSignMapper.selectMarSignById(id);
    }

    /**
     * 查询需求分配列表
     * 
     * @param marSign 需求分配
     * @return 需求分配
     */
    @Override
    public List<MarSign> selectMarSignList(MarSign marSign)
    {
        return marSignMapper.selectMarSignList(marSign);
    }

    /**
     * 新增需求分配
     * 
     * @param marSign 需求分配
     * @return 结果
     */
    @Override
    public int insertMarSign(MarSign marSign)
    {
        return marSignMapper.insertMarSign(marSign);
    }

    /**
     * 修改需求分配
     * 
     * @param marSign 需求分配
     * @return 结果
     */
    @Override
    public int updateMarSign(MarSign marSign)
    {
        return marSignMapper.updateMarSign(marSign);
    }

    /**
     * 批量删除需求分配
     * 
     * @param ids 需要删除的需求分配ID
     * @return 结果
     */
    @Override
    public int deleteMarSignByIds(Long[] ids)
    {
        return marSignMapper.deleteMarSignByIds(ids);
    }

    /**
     * 删除需求分配信息
     * 
     * @param id 需求分配ID
     * @return 结果
     */
    @Override
    public int deleteMarSignById(Long id)
    {
        return marSignMapper.deleteMarSignById(id);
    }
}
