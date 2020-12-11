package com.ruoyi.demand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarBorrowMapper;
import com.ruoyi.demand.domain.MarBorrow;
import com.ruoyi.demand.service.IMarBorrowService;

/**
 * 借用物品Service业务层处理
 * 
 * @author ych
 * @date 2020-12-10
 */
@Service
public class MarBorrowServiceImpl implements IMarBorrowService 
{
    @Autowired
    private MarBorrowMapper marBorrowMapper;

    /**
     * 查询借用物品
     * 
     * @param id 借用物品ID
     * @return 借用物品
     */
    @Override
    public MarBorrow selectMarBorrowById(Integer id)
    {
        return marBorrowMapper.selectMarBorrowById(id);
    }

    /**
     * 查询借用物品列表
     * 
     * @param marBorrow 借用物品
     * @return 借用物品
     */
    @Override
    public List<MarBorrow> selectMarBorrowList(MarBorrow marBorrow)
    {
        return marBorrowMapper.selectMarBorrowList(marBorrow);
    }

    /**
     * 新增借用物品
     * 
     * @param marBorrow 借用物品
     * @return 结果
     */
    @Override
    public int insertMarBorrow(MarBorrow marBorrow)
    {
        return marBorrowMapper.insertMarBorrow(marBorrow);
    }

    /**
     * 修改借用物品
     * 
     * @param marBorrow 借用物品
     * @return 结果
     */
    @Override
    public int updateMarBorrow(MarBorrow marBorrow)
    {
        return marBorrowMapper.updateMarBorrow(marBorrow);
    }

    /**
     * 批量删除借用物品
     * 
     * @param ids 需要删除的借用物品ID
     * @return 结果
     */
    @Override
    public int deleteMarBorrowByIds(Integer[] ids)
    {
        return marBorrowMapper.deleteMarBorrowByIds(ids);
    }

    /**
     * 删除借用物品信息
     * 
     * @param id 借用物品ID
     * @return 结果
     */
    @Override
    public int deleteMarBorrowById(Integer id)
    {
        return marBorrowMapper.deleteMarBorrowById(id);
    }
}
