package com.ruoyi.demand.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.demand.domain.MarBorrow;

/**
 * 借用物品Mapper接口
 * 
 * @author ych
 * @date 2020-12-10
 */
public interface MarBorrowMapper 
{
    /**
     * 查询借用物品
     * 
     * @param id 借用物品ID
     * @return 借用物品
     */
    public MarBorrow selectMarBorrowById(Integer id);

    /**
     * 查询借用物品列表
     * 
     * @param marBorrow 借用物品
     * @return 借用物品集合
     */
    public List<MarBorrow> selectMarBorrowList(MarBorrow marBorrow);

    /**
     * 新增借用物品
     * 
     * @param marBorrow 借用物品
     * @return 结果
     */
    public int insertMarBorrow(MarBorrow marBorrow);

    /**
     * 修改借用物品
     * 
     * @param marBorrow 借用物品
     * @return 结果
     */
    public int updateMarBorrow(MarBorrow marBorrow);

    /**
     * 删除借用物品
     * 
     * @param id 借用物品ID
     * @return 结果
     */
    public int deleteMarBorrowById(Integer id);

    /**
     * 批量删除借用物品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarBorrowByIds(Integer[] ids);

    /**
     * 归还
     *
     */

}
