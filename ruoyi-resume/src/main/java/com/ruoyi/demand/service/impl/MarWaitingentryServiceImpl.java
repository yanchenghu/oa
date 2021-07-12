package com.ruoyi.demand.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.model.LoginUser;
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
    public List<Map> getMarWaitingentr( LoginUser loginUser) {
        String username = loginUser.getUsername();
        List<SysRole> roles = loginUser.getUser().getRoles();
        List<String> rol=new ArrayList<>();
        for (SysRole sysRo:roles){
            rol.add(sysRo.getRoleKey());
        }
        //老板 boss，
        if(rol.contains("Boss")){
            username="";
        }
        return marWaitingentryMapper.getMarWaitingentr(username);


    }
}
