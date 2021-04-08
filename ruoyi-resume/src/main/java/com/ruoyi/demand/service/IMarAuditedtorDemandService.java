package com.ruoyi.demand.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.demand.domain.MarAuditedtorDemand;

/**
 * 面试题绑定需求Service接口
 * 
 * @author ych
 * @date 2021-03-03
 */
public interface IMarAuditedtorDemandService 
{
//    List<MarAuditedtorDemand> selafterbinding(Integer auditedId);

    AjaxResult addaudbindingdemand(String demandId,Integer auditedId);
}
