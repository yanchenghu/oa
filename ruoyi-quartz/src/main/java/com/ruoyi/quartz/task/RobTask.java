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
    public void markeRelease(){
        System.out.println("查询营销部全部录入公司记录，监测是否过期，过期则释放");
        yxdemandService.markeRelease();
    }

    /**
     * 查看所有的我抢占的简历信息有没有简历原件
     * 5点提醒
     */
    public void resumeOriginal(){
        System.out.println("查看所有的我抢占的简历信息有没有简历原件");
        try {
            iPerRobcustomerService.resumeOriginal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 一个小时跟新一下钉钉token
     */
    public void updateDingtoken(){
        System.out.println("一个小时跟新一下钉钉token");
        try {
            iPerRobcustomerService.updateDingtoken();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
