package com.ruoyi.resume.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.resume.domain.PerCustomerinfo;

/**
 * 简历Mapper接口
 *
 * @author yan
 * @date 2020-10-28
 */
public interface PerCustomerinfoMapper
{
    /**
     * 查询简历
     *
     * @param customerCode 简历ID
     * @return 简历
     */
    public PerCustomerinfo selectPerCustomerinfoById(String customerCode);

    /**
     * 查询简历列表
     *
     * @param perCustomerinfo 简历
     * @return 简历集合
     */
    public List<PerCustomerinfo> selectPerCustomerinfoList(PerCustomerinfo perCustomerinfo);

    /**
     * 新增简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    public int insertPerCustomerinfo(PerCustomerinfo perCustomerinfo);

    /**
     * 修改简历
     *
     * @param perCustomerinfo 简历
     * @return 结果
     */
    public int updatePerCustomerinfo(PerCustomerinfo perCustomerinfo);

    /**
     * 删除简历
     *
     * @param customerCode 简历ID
     * @return 结果
     */
    public int deletePerCustomerinfoById(String customerCode);

    /**
     * 批量删除简历
     *
     * @param customerCodes 需要删除的数据ID
     * @return 结果
     */
    public int deletePerCustomerinfoByIds(String[] customerCodes);

    /**
     * 根据电话号码查询当前人是否存在
     *
     * @param perCustomerinfo
     * @return 结果
     */
    int getSameCustomerCount(PerCustomerinfo perCustomerinfo);

    /**
     * 根据电话号码查询姓名查询简历信息
     *
     * @param perCustomerinfo
     * @return 结果
     */
    List<PerCustomerinfo> selectlistbyNametel(PerCustomerinfo perCustomerinfo);

    /**
     * 根据个人信息查询入项信息
     *
     * @param customerCode
     * @return 结果
     */
    List<Map> selectIntoItem(String customerCode);

    /**
     * 获取我抢占的简历
     */
    List<Map> myRobresume(String username);

    /**
     * 查询我跟踪的简历 列表
     */
    List<Map> selectmytrackresumeList(PerCustomerinfo perCustomerinfo);

    /**
     * 智能人岗匹配简历信息列表
     */
    List<Map> selectPeopostlist(PerCustomerinfo perCustomerinfo);
}
