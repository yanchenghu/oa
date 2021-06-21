package com.ruoyi.analysis.service.impl;

import com.ruoyi.analysis.mapper.GetAllMethodMapper;
import com.ruoyi.analysis.service.IGetAllMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ych
 * @Description
 * @create 2021-06-17 17:05
 */
@Service
public class GetAllMethodServiceImpl implements IGetAllMethodService {

    @Autowired
    private GetAllMethodMapper getAllMethodMapper;
    @Override
    public List<Map> getPersonnelgroup() {
        return getAllMethodMapper.getpersonnelGroupList();
    }
}
