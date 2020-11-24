package com.ruoyi.resume.mapper;

import java.util.List;
import com.ruoyi.resume.domain.PerRobcustomer;

/**
 * 抢占简历信息Mapper接口
 * 
 * @author yan
 * @date 2020-10-30
 */
public interface PerRobcustomerMapper 
{
    /**
     * 查询抢占简历信息
     * 
     * @param seizeId 抢占简历信息ID
     * @return 抢占简历信息
     */
    public PerRobcustomer selectPerRobcustomerById(String seizeId);

    /**
     * 查询抢占简历信息列表
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 抢占简历信息集合
     */
    public List<PerRobcustomer> selectPerRobcustomerList(PerRobcustomer perRobcustomer);

    /**
     * 新增抢占简历信息
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 结果
     */
    public int insertPerRobcustomer(PerRobcustomer perRobcustomer);

    /**
     * 修改抢占简历信息
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 结果
     */
    public int updatePerRobcustomer(PerRobcustomer perRobcustomer);

    /**
     * 删除抢占简历信息
     * 
     * @param seizeId 抢占简历信息ID
     * @return 结果
     */
    public int deletePerRobcustomerById(String seizeId);

    /**
     * 批量删除抢占简历信息
     * 
     * @param seizeIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePerRobcustomerByIds(String[] seizeIds);
    /**
     * 根据电话号码查询当前人是否被抢占
     *
     * @param phone_number
     * @return 结果PerRobcustomer
     */
    PerRobcustomer selectByphone(String phone_number);

    /**
     * 根据个人编号查询当前人是否被抢占
     *
     * @param customerCode
     * @return 结果PerRobcustomer
     */
    PerRobcustomer selectByCustomerCode(String customerCode);

    List<String> selectAllOverrob();

    void updatePerRobbylist(List<String> list);
}
