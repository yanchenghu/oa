package com.ruoyi.demand.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.demand.domain.MarWaitingentry;

/**
 * 待入项Service接口
 * 
 * @author ruoyi
 * @date 2021-03-31
 */
public interface IMarWaitingentryService 
{


    /**
     * 查询待入项列表
     * 
     * @param marWaitingentry 待入项
     * @return 待入项集合
     */
    public List<Map> getMarWaitingentr(MarWaitingentry marWaitingentry);


}
