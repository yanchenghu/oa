package com.ruoyi.demand.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarAuditedtorDemandMapper;
import com.ruoyi.demand.domain.MarAuditedtorDemand;
import com.ruoyi.demand.service.IMarAuditedtorDemandService;

/**
 * 面试题绑定需求Service业务层处理
 * 
 * @author ych
 * @date 2021-03-03
 */
@Service
public class MarAuditedtorDemandServiceImpl implements IMarAuditedtorDemandService 
{
    @Autowired
    private MarAuditedtorDemandMapper marAuditedtorDemandMapper;



    @Override
    public List<MarAuditedtorDemand> selafterbinding(Integer auditedId) {
        MarAuditedtorDemand marAuditedtorDemand=new MarAuditedtorDemand();
        marAuditedtorDemand.setAuditedId(auditedId);
        return marAuditedtorDemandMapper.selectMarAuditedtorDemandList(marAuditedtorDemand);
    }

    @Override
    public AjaxResult addaudbindingdemand(String zm) {
      List<String> strList = JSON.parseArray(JSON.parseObject(zm).getString("list"), String.class);
      Integer auditedId= Integer.valueOf(JSON.parseObject(zm).getString("auditedId"));
      if(auditedId==null){
          return AjaxResult.error("未传入面试题ID，请联系管理员");
      }
      Date date = new Date();
      MarAuditedtorDemand marAuditedtorDemand=new MarAuditedtorDemand();
      marAuditedtorDemand.setAuditedId(auditedId);
      List<MarAuditedtorDemand> marAuditedtorDemands = marAuditedtorDemandMapper.selectMarAuditedtorDemandList(marAuditedtorDemand);
      if(marAuditedtorDemands.size()>0){
          marAuditedtorDemandMapper.deleteMarAuditedtorDemandByAuditedId(auditedId);
      }
      for(String demandId: strList){
          marAuditedtorDemand.setDemandId(demandId);
          marAuditedtorDemand.setAddTime(date);
          marAuditedtorDemandMapper.insertMarAuditedtorDemand(marAuditedtorDemand);
      }
      return AjaxResult.success("添加成功");
    }
}
