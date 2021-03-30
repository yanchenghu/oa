package com.ruoyi.demand.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.demand.domain.MarTopic;
import org.springframework.web.multipart.MultipartFile;

/**
 * 面试题Service接口
 * 
 * @author ych
 * @date 2021-03-02
 */
public interface IMarTopicService 
{


    AjaxResult postInterview(String zm, MultipartFile multipartFile, LoginUser loginUser);

    String ispostInterview(MarTopic marTopic);

    List<MarTopic> selectMarTopicList(MarTopic topic);

    List<MarTopic> selectextopicList(MarTopic topic);

    int updateMarTopic(MarTopic marTopic);
}
