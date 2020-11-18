package com.ruoyi.conn.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.conn.domain.ConHoliday;

/**
 * resumeService接口
 * 
 * @author ych
 * @date 2020-11-11
 */
public interface IConHolidayService 
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
     * 批量删除resume
     * 
     * @param ids 需要删除的resumeID
     * @return 结果
     */
    public int deleteConHolidayByIds(Long[] ids);

    /**
     * 删除resume信息
     * 
     * @param id resumeID
     * @return 结果
     */
    public int deleteConHolidayById(Long id);

    /**
     * 获取日期list
     *
     * @param date i b
     * @return 结果
     */
    List<Date> queryHolidayList(Date date, int i, int b);
}
