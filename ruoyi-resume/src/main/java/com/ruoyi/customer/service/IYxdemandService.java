package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.domain.MarCompany;
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
    public List<Yxdemand> selectYxdemandList(Yxdemand yxdemand,LoginUser loginUser) ;

    /**
     * 营销抢占
     *
     * @param  entryId loginUser
     * @return AjaxResult
     */
    AjaxResult rebMarByEnId(Integer entryId, LoginUser loginUser);

//    /**
//     * 新增营销录入公司
//     *
//     * @param yxdemand 营销录入公司
//     * @return 结果
//     */
//    public AjaxResult insertYxdemand(Yxdemand yxdemand,LoginUser loginUser);

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
     * 转化为合作客户
     */
    AjaxResult turnCustomers(MarCompany marCompany, LoginUser loginUser);


    /**
     * 定时任务
     * 查询全部录入公司记录，监测是否过期，过期则释放
     */
    void markeRelease();


    /**
     * 商务公海
     */
    List<Yxdemand> selectByDepartBus(Yxdemand yxdemand, LoginUser loginUser);
    /**
     * 营销公海
     */
    List<Yxdemand> selectByDepartMark(Yxdemand yxdemand, LoginUser loginUser);

    /**
     * 商务抢占功能营销
     */
    AjaxResult rebBusByEnId(Integer entryId, LoginUser loginUser);
    /**
     * 根据公司名字获取营销录入公司
     */
    String yxdemByName(String companyName);

    AjaxResult workresultlist(LoginUser loginUser);

    AjaxResult newIntention(Yxdemand yxdemand, LoginUser loginUser);
}
