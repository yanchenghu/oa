package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.Yxdemand;

import java.util.List;
import java.util.Map;

/**
 * 营销录入公司Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public interface YxdemandMapper 
{



    /**
     * 查询营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 营销录入公司
     */
    public Yxdemand selectYxdemandById(Integer entryId);

    /**
     * 查询营销录入公司列表
     * 
     * @param yxdemand 营销录入公司
     * @return 营销录入公司集合
     */
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand);

    /**
     * 新增营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    public int insertYxdemand(Yxdemand yxdemand);

    /**
     * 修改营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    public int updateYxdemand(Yxdemand yxdemand);

    /**
     * 删除营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 结果
     */
    public int deleteYxdemandById(Integer entryId);

    /**
     * 批量删除营销录入公司
     * 
     * @param entryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxdemandByIds(Integer[] entryIds);
    /**
     * 根据名字查询Yxdemand
     *
     */
    Yxdemand selectYxdemandByName(String companyName);

    /**
     * 定时任务
     * 查询全部录入公司记录，监测是否过期，过期则释放
     */
    List<Yxdemand> selrobYxdelist();

    /**
     * 商务公海
     */
    List<Yxdemand> selectByDepartBus();
    /**
     * 营销公海
     */
    List<Yxdemand> selectByDepartMark();
}
