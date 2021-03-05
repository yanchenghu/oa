package com.ruoyi.demand.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarTopicMapper;
import com.ruoyi.demand.domain.MarTopic;
import com.ruoyi.demand.service.IMarTopicService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 面试题Service业务层处理
 * 
 * @author ych
 * @date 2021-03-02
 */
@Service
public class MarTopicServiceImpl implements IMarTopicService 
{
    @Autowired
    private MarTopicMapper marTopicMapper;


    @Override
    public AjaxResult postInterview(String zm, MultipartFile multipartFile, LoginUser loginUser) {
        String fsafsa="";
        try {
            fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MarTopic marTopic = JSON.parseObject(JSON.parseObject(zm).getString("marTopic"), MarTopic.class);
        marTopic.setTopicNickname(loginUser.getUser().getNickName());
        marTopic.setTopicUsername(loginUser.getUsername());
        marTopic.setTopicPath(fsafsa);
        marTopic.setAddTime(new Date());
        int i = marTopicMapper.insertMarTopic(marTopic);
        if(i==1){
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.success("添加失败，请联系管理员");

    }

    @Override
    public String ispostInterview(MarTopic marTopic) {
        List<MarTopic> marTopics = marTopicMapper.selectMarTopicList(marTopic);
        if(marTopics.size()>0){
            return "你已经发布过该需求的面试题";
        }
        return "你未发布过该需求的面试题";
    }

    @Override
    public List<MarTopic> selectMarTopicList(MarTopic topic) {
        return marTopicMapper.selectMarTopicList(topic);
    }

    @Override
    public List<MarTopic> selectextopicList(MarTopic topic) {
        return marTopicMapper.selectextopicList(topic);
    }

    @Override
    public int updateMarTopic(MarTopic marTopic) {
        return marTopicMapper.updateMarTopic(marTopic);
    }
}
