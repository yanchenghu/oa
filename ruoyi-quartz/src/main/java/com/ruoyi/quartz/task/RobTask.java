package com.ruoyi.quartz.task;

import com.ruoyi.customer.service.IMarCompanyService;
import com.ruoyi.customer.service.IYxdemandService;
import com.ruoyi.demand.service.IMarDemandService;
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
    @Autowired
    private IMarDemandService marDemandService;

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



    /*
    *需求关闭 每七天查询一次
     */
    public void timingDemandClosure(){
        System.out.println("需求关闭每七天天查询一次");
        marDemandService.timingDemandClosure();
    }

    /*
     *绑定需求的简历 每五天查询一次
     */
    public void bindingResume(){
        System.out.println("绑定需求的简历每五天查询一次");
        marDemandService.bindingResume();
    }
    /*
     *录入需求的时候提醒录入合同
     */
    public void demandCooperationCompany(){
        System.out.println("录入需求的时候提醒录入合同");
        try {
            marDemandService.demandCooperationCompany();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
     * 待入项定时器处理
     */
    public void itemsIncluded(){
        System.out.println("待入项定时器处理");
        try {
            marDemandService.itemsIncluded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
     *开票3、5后 钉钉提醒该人是否收款
     */
    public void reminderCollection(){


    }





}
