package com.ruoyi.demand.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.statistic.domain.BeInterviewed;

/**
 * 需求绑定简历Mapper接口
 * 
 * @author ych
 * @date 2020-11-13
 */
public interface MarDemandresumeMapper 
{
    /**
     * 查询需求绑定简历
     * 
     * @param id 需求绑定简历ID
     * @return 需求绑定简历
     */
    public MarDemandresume selectMarDemandresumeById(String id);

    /**
     * 查询需求绑定简历列表
     * 
     * @param marDemandresume 需求绑定简历
     * @return 需求绑定简历集合
     */
    public List<MarDemandresume> selectMarDemandresumeList(MarDemandresume marDemandresume);

    /**
     * 新增需求绑定简历
     * 
     * @param marDemandresume 需求绑定简历
     * @return 结果
     */
    public int insertMarDemandresume(MarDemandresume marDemandresume);

    /**
     * 修改需求绑定简历
     * 
     * @param marDemandresume 需求绑定简历
     * @return 结果
     */
    public int updateMarDemandresume(MarDemandresume marDemandresume);

    /**
     * 删除需求绑定简历
     * 
     * @param id 需求绑定简历ID
     * @return 结果
     */
    public int deleteMarDemandresumeById(String id);

    /**
     * 批量删除需求绑定简历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarDemandresumeByIds(String[] ids);

    /**
     * 根据简历编号，和需求ID查询当前人是否被绑定
     *
     * @param
     * @return 结果
     */
    Map selectMarDemandresumeByCodeID( Map map);

    /**
     *根据绑定简历获取入项信息的基本
     */
    Map getInputInformation(String id);
    //获取简历绑定人数
    List<Map> selectMarDemandresumedataDisplay(Map map);

    List<Map> selBinMardandPeople(Map map);

    int selectfirstMarbingBystatus(Map map);

//    int selectlastmapBylastMonth(Map map);

    List<Map> selectlitoutPeople(Map map);

    int selectlitoutNum(Map map);

    List<Map> sellistOutbyuserName(Map map);

    List<MarDemandresume> selectByDemandIdBinTime(MarDemandresume marDemandresume);

    List selectMarDemandresumebyDemand(String demandId);

    List<MarDemandresume> selectByDemandNewfollowtime(MarDemandresume marDemandresume);


    List<BeInterviewed> selBinCode(Map map);

    List<BeInterviewed> selectMarDemandbyBeInterviewed(Map map);
}
