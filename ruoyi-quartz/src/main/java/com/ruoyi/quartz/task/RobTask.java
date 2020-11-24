package com.ruoyi.quartz.task;

import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.resume.service.IPerRobcustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *OA项目的定时器
 *
 * @author ych
 */
@Component("oaTask")
public class RobTask {
    @Autowired
    private IPerRobcustomerService iPerRobcustomerService;
    @Autowired
    private IYxdemandService yxdemandService;
    /**
     * 抢占简历释放任务
     * 查询全部抢占记录，监测是否过期，过期则释放
     */
    public void initRobStatus(){
        System.out.println("查询全部抢占记录，监测是否过期，过期则释放");
        iPerRobcustomerService.initRobStatus();
    }
    /**
     * 营销录入公司释放公海任务
     * 查询全部录入公司记录，监测是否过期，过期则释放
     */
    public void markeRelease (){
        System.out.println("查询营销部全部录入公司记录，监测是否过期，过期则释放");
        yxdemandService.markeRelease();
    }


}
