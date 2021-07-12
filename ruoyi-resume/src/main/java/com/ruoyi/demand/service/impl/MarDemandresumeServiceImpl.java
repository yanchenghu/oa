package com.ruoyi.demand.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.demand.domain.MybindingResume;
import com.ruoyi.resume.domain.PerCustomerinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarDemandresumeMapper;
import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.service.IMarDemandresumeService;

/**
 * 需求绑定简历Service业务层处理
 * 
 * @author ych
 * @date 2020-11-13
 */
@Service
public class MarDemandresumeServiceImpl implements IMarDemandresumeService 
{
    @Autowired
    private MarDemandresumeMapper marDemandresumeMapper;

    /**
     * 查询需求绑定简历
     * 
     * @param id 需求绑定简历ID
     * @return 需求绑定简历
     */
    @Override
    public MarDemandresume selectMarDemandresumeById(String id)
    {
        return marDemandresumeMapper.selectMarDemandresumeById(id);
    }

    /**
     * 查询需求绑定简历列表
     * 
     * @param marDemandresume 需求绑定简历
     * @return 需求绑定简历
     */
    @Override
    public List<MarDemandresume> selectMarDemandresumeList(MarDemandresume marDemandresume)
    {
        return marDemandresumeMapper.selectMarDemandresumeList(marDemandresume);
    }

    /**
     * 新增需求绑定简历
     * 
     * @param marDemandresume 需求绑定简历
     * @return 结果
     */
    @Override
    public int insertMarDemandresume(MarDemandresume marDemandresume)
    {
        return marDemandresumeMapper.insertMarDemandresume(marDemandresume);
    }

    /**
     * 修改需求绑定简历
     * 
     * @param marDemandresume 需求绑定简历
     * @return 结果
     */
    @Override
    public int updateMarDemandresume(MarDemandresume marDemandresume)
    {
        return marDemandresumeMapper.updateMarDemandresume(marDemandresume);
    }

    /**
     * 批量删除需求绑定简历
     * 
     * @param ids 需要删除的需求绑定简历ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandresumeByIds(String[] ids)
    {
        return marDemandresumeMapper.deleteMarDemandresumeByIds(ids);
    }

    /**
     * 删除需求绑定简历信息
     * 
     * @param id 需求绑定简历ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandresumeById(String id)
    {
        return marDemandresumeMapper.deleteMarDemandresumeById(id);
    }
    /**
     * 获取我绑定的简历
     */
    @Override
    public  List<MybindingResume>  getMybindingResume(PerCustomerinfo perCustomerinfo, LoginUser loginUser) {
        Map map=new HashMap<>();
        map.put("customerName",perCustomerinfo.getCustomerName());
        map.put("customerTel",perCustomerinfo.getCustomerTel());
        map.put("followStatus",perCustomerinfo.getRelationshipZq());
        map.put("userName",loginUser.getUsername());
        List<MybindingResume> list=marDemandresumeMapper.getMybindingResume(map);
        return list;
    }
}
