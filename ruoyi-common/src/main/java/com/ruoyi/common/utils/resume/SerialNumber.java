package com.ruoyi.common.utils.resume;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialNumber {

    /**
     * 生成业务流水号
     * 系统标识（sysFlg.length位）+时间（14位，年月日时分秒）+系统流水（randomCount位）
     * @param sysFlg        系统标识
     * @param randomCount   随机数位数
     * @return
     */
    public static synchronized String createSerial(String sysFlg, int randomCount){
        safeSleep(1);
        SimpleDateFormat sdft = new SimpleDateFormat("yyMMddhhmmss");
        Date nowdate = new Date();
        String str = sdft.format(nowdate);
        return sysFlg + str + RandomStringUtils.randomNumeric(randomCount);
    }
    /**
     * 生成业务流水号毫秒数
     * 系统标识（sysFlg.length位）+时间（9位，时分秒毫秒）+系统流水（randomCount位）
     * @param sysFlg        系统标识
     * @param randomCount   随机数位数
     * @return
     */

    public static synchronized String createSesl(String sysFlg, int randomCount){
        safeSleep(1);
        SimpleDateFormat sdft = new SimpleDateFormat("hhmmssSSS");
        Date nowdate = new Date();
        String str = sdft.format(nowdate);
        return sysFlg + str + RandomStringUtils.randomNumeric(randomCount);
    }
    public static void safeSleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }

}
