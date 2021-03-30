package com.ruoyi.web.controller.demand;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.demand.domain.MarAuditeditor;
import com.ruoyi.demand.domain.MarTopic;
import com.ruoyi.demand.service.IMarTopicService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.Topic;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 人事发布的面试题列表
 * @Author ych
 * @Date 2021/3/2
 */
@RestController
@RequestMapping("/demand/topic")
public class MarTopicController extends BaseController {

    @Autowired
    private IMarTopicService marTopicService;
    @Autowired
    private TokenService tokenService;

    /**
     * 我发布的面试题
     */
    @PreAuthorize("@ss.hasPermi('demand:topic:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarTopic topic)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        topic.setTopicUsername(loginUser.getUsername());
        List<MarTopic> list = marTopicService.selectMarTopicList(topic);
        return getDataTable(list);
    }




}
