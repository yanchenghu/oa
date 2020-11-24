package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.domain.Yxcontact;
import com.ruoyi.customer.domain.Yxdemand;

import java.util.List;

/**
 * 营销录入公司Service接口
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public interface IYxdemandService 
{
    /**
     * 查询营销录入公司
     * 
     * @param entryId 营销录入公司ID
     * @return 营销录入公司
     */
    public AjaxResult selectYxdemandById(Integer entryId);

    /**
     * 查询营销录入公司列表
     * 
     * @param yxdemand 营销录入公司
     * @return 营销录入公司集合
     */
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand,LoginUser loginUser) throws Exception;

    /**
     * 新增营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    public AjaxResult insertYxdemand(Yxdemand yxdemand,LoginUser loginUser);

    /**
     * 修改营销录入公司
     * 
     * @param yxdemand 营销录入公司
     * @return 结果
     */
    public AjaxResult updateYxdemand(Yxdemand yxdemand,LoginUser loginUser);

    /**
     * 批量删除营销录入公司
     * 
     * @param entryIds 需要删除的营销录入公司ID
     * @return 结果
     */
    public int deleteYxdemandByIds(Integer[] entryIds);

    /**
     * 删除营销录入公司信息
     * 
     * @param entryId 营销录入公司ID
     * @return 结果
     */
    public int deleteYxdemandById(Integer entryId);

    /**
     * 录入营销录入公司信息
     *
     * @param yxcontact
     * @return 结果
     */
    AjaxResult insertYxcontact(Yxcontact yxcontact,LoginUser loginUser);
    /**
     * 客户移交
     */

    int Customertransfer(Yxdemand yxdemand, LoginUser loginUser);


    /**
     * 商务意向客户列表
     */
    List<Yxdemand> selectBusinessList(Yxdemand yxdemand, LoginUser loginUser);













    /**
     * 定时任务
     * 查询全部录入公司记录，监测是否过期，过期则释放
     */
    void markeRelease();
}
