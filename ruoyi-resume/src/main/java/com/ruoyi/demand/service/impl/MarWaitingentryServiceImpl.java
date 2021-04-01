package com.ruoyi.demand.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarWaitingentryMapper;
import com.ruoyi.demand.domain.MarWaitingentry;
import com.ruoyi.demand.service.IMarWaitingentryService;

/**
 * 待入项Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-31
 */
@Service
public class MarWaitingentryServiceImpl implements IMarWaitingentryService 
{
    @Autowired
    private MarWaitingentryMapper marWaitingentryMapper;


    @Override
    public List<Map> getMarWaitingentr(MarWaitingentry marWaitingentry) {
        return marWaitingentryMapper.getMarWaitingentr();


    }
}
