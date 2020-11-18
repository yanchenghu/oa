package com.ruoyi.quartz.task;

import com.ruoyi.resume.service.IPerRobcustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 抢占简历定时释放定时任务
 *
 * @author ych
 */
@Component("robTask")
public class RobTask {
    @Autowired
    private IPerRobcustomerService iPerRobcustomerService;

    /**
     * 查询全部抢占记录，监测是否过期，过期则释放
     */
    public void initRobStatus(){
        System.out.println("查询全部抢占记录，监测是否过期，过期则释放");
        iPerRobcustomerService.initRobStatus();
    }


}
