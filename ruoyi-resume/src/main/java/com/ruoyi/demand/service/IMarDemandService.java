package com.ruoyi.demand.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.domain.MarDemandresumefollow;
import org.springframework.web.multipart.MultipartFile;

/**
 * 需求Service接口
 * 
 * @author ych
 * @date 2020-11-27
 */
public interface IMarDemandService 
{
    /**
     * 查询需求
     * 
     * @param
     * @return 需求
     */
    public Map selectMarDemandById(String demandId);

    /**
     * 查询需求列表
     * 
     * @param marDemand 需求
     * @return 需求集合
     */
    public List<MarDemand> selectMarDemandList(MarDemand marDemand,LoginUser loginUser);

    /**
     * 新增需求
     * 
     * @param
     * @return 结果
     */
    public AjaxResult insertMarDemand(String zm, MultipartFile demandPic,LoginUser loginUser);

    /**
     * 修改需求
     * 
     * @param zm demandPic loginUser
     * @return 结果
     */
    AjaxResult updateMarDemand(String zm, MultipartFile demandPic, LoginUser loginUser);


    /**
     * 批量删除需求
     * 
     * @param ids 需要删除的需求ID
     * @return 结果
     */
    public int deleteMarDemandByIds(String[] ids);

    /**
     * 删除需求信息
     * 
     * @param id 需求ID
     * @return 结果
     */
    public int deleteMarDemandById(String id);

    /**
     * 需求查重
     */
    int selDemandDuplicate(String projectName);

    /**
     * 需求关闭
     */
    AjaxResult demandClosure(String demandId,LoginUser loginUser);
    /**
     * 需求开启
     */
    AjaxResult demandOpen(String demandId, LoginUser loginUser);
    /**
     * 需求绑定表的查询
     */
    List<MarDemand> selectMarDemandbindingList(MarDemand marDemand, LoginUser loginUser);

    /**
     * 简历绑定需求
     */
    AjaxResult resumeBingDemand(LoginUser loginUser,String zm);

    /**
     * 需求已绑定简历列表
     */
    List<Map> demandDetailsList(String demandId ,String customerName);

    /**
     *需求已绑定简历跟踪
     */
    AjaxResult demandResumeTrack(MarDemandresumefollow marDemandresumefollow, LoginUser loginUser);
}
