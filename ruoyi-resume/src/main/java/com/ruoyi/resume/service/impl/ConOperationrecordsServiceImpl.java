package com.ruoyi.resume.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.ConOperationrecordsMapper;
import com.ruoyi.resume.domain.ConOperationrecords;
import com.ruoyi.resume.service.IConOperationrecordsService;

/**
 * 操作记录Service业务层处理
 * 
 * @author ych
 * @date 2020-11-09
 */
@Service
public class ConOperationrecordsServiceImpl implements IConOperationrecordsService 
{
    @Autowired
    private ConOperationrecordsMapper conOperationrecordsMapper;

    /**
     * 查询操作记录
     * 
     * @param id 操作记录ID
     * @return 操作记录
     */
    @Override
    public ConOperationrecords selectConOperationrecordsById(Long id)
    {
        return conOperationrecordsMapper.selectConOperationrecordsById(id);
    }

    /**
     * 查询操作记录列表
     * 
     * @param conOperationrecords 操作记录
     * @return 操作记录
     */
    @Override
    public List<ConOperationrecords> selectConOperationrecordsList(ConOperationrecords conOperationrecords)
    {
        return conOperationrecordsMapper.selectConOperationrecordsList(conOperationrecords);
    }

    /**
     * 新增操作记录
     * 
     * @param conOperationrecords 操作记录
     * @return 结果
     */
    @Override
    public int insertConOperationrecords(ConOperationrecords conOperationrecords)
    {
        return conOperationrecordsMapper.insertConOperationrecords(conOperationrecords);
    }

    /**
     * 修改操作记录
     * 
     * @param conOperationrecords 操作记录
     * @return 结果
     */
    @Override
    public int updateConOperationrecords(ConOperationrecords conOperationrecords)
    {
        return conOperationrecordsMapper.updateConOperationrecords(conOperationrecords);
    }

    /**
     * 批量删除操作记录
     * 
     * @param ids 需要删除的操作记录ID
     * @return 结果
     */
    @Override
    public int deleteConOperationrecordsByIds(Long[] ids)
    {
        return conOperationrecordsMapper.deleteConOperationrecordsByIds(ids);
    }

    /**
     * 删除操作记录信息
     * 
     * @param id 操作记录ID
     * @return 结果
     */
    @Override
    public int deleteConOperationrecordsById(Long id)
    {
        return conOperationrecordsMapper.deleteConOperationrecordsById(id);
    }
}
