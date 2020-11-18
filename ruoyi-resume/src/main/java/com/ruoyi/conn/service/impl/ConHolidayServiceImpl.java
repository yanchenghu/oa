package com.ruoyi.conn.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.conn.mapper.ConHolidayMapper;
import com.ruoyi.conn.domain.ConHoliday;
import com.ruoyi.conn.service.IConHolidayService;

/**
 * resumeService业务层处理
 * 
 * @author ych
 * @date 2020-11-11
 */
@Service
public class ConHolidayServiceImpl implements IConHolidayService 
{
    @Autowired
    private ConHolidayMapper conHolidayMapper;

    /**
     * 查询resume
     * 
     * @param id resumeID
     * @return resume
     */
    @Override
    public ConHoliday selectConHolidayById(Long id)
    {
        return conHolidayMapper.selectConHolidayById(id);
    }

    /**
     * 查询resume列表
     * 
     * @param conHoliday resume
     * @return resume
     */
    @Override
    public List<ConHoliday> selectConHolidayList(ConHoliday conHoliday)
    {
        return conHolidayMapper.selectConHolidayList(conHoliday);
    }

    /**
     * 新增resume
     * 
     * @param conHoliday resume
     * @return 结果
     */
    @Override
    public int insertConHoliday(ConHoliday conHoliday)
    {
        return conHolidayMapper.insertConHoliday(conHoliday);
    }

    /**
     * 修改resume
     * 
     * @param conHoliday resume
     * @return 结果
     */
    @Override
    public int updateConHoliday(ConHoliday conHoliday)
    {
        return conHolidayMapper.updateConHoliday(conHoliday);
    }

    /**
     * 批量删除resume
     * 
     * @param ids 需要删除的resumeID
     * @return 结果
     */
    @Override
    public int deleteConHolidayByIds(Long[] ids)
    {
        return conHolidayMapper.deleteConHolidayByIds(ids);
    }

    /**
     * 删除resume信息
     * 
     * @param id resumeID
     * @return 结果
     */
    @Override
    public int deleteConHolidayById(Long id)
    {
        return conHolidayMapper.deleteConHolidayById(id);
    }

    @Override
    public List<Date> queryHolidayList(Date baseda, int monthcount, int holtype) {
        Map<String,Object> comap = new HashMap<String,Object>();
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -monthcount);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        cal_1.set(Calendar.HOUR_OF_DAY,0);
        cal_1.set(Calendar.MINUTE,0);
        cal_1.set(Calendar.SECOND,0);

        comap.put("startdate", cal_1.getTime());
        comap.put("enddate", baseda);
        comap.put("holtype", holtype);

        return conHolidayMapper.queryHolidayList(comap);
    }
}
