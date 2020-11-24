package com.ruoyi.conn.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.conn.domain.ConHoliday;

/**
 * resumeMapper接口
 * 
 * @author ych
 * @date 2020-11-11
 */
public interface ConHolidayMapper 
{
    /**
     * 查询resume
     * 
     * @param id resumeID
     * @return resume
     */
    public ConHoliday selectConHolidayById(Long id);

    /**
     * 查询resume列表
     * 
     * @param conHoliday resume
     * @return resume集合
     */
    public List<ConHoliday> selectConHolidayList(ConHoliday conHoliday);

    /**
     * 新增resume
     * 
     * @param conHoliday resume
     * @return 结果
     */
    public int insertConHoliday(ConHoliday conHoliday);

    /**
     * 修改resume
     * 
     * @param conHoliday resume
     * @return 结果
     */
    public int updateConHoliday(ConHoliday conHoliday);

    /**
     * 删除resume
     * 
     * @param id resumeID
     * @return 结果
     */
    public int deleteConHolidayById(Long id);

    /**
     * 批量删除resume
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConHolidayByIds(Long[] ids);

    List<Date> queryHolidayList(Map<String, Object> comap);
}
