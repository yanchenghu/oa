package com.ruoyi.tool;

import com.ruoyi.common.utils.resume.DateUtils;
import com.ruoyi.conn.service.IConHolidayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class WorkDay {
    @Resource
    private IConHolidayService conHolidayService;
    private List<Date> wklist = new ArrayList<Date>();
    private List<Date> hdlist = new ArrayList<Date>();
    private boolean quflag = false;



    void init(){
        Date date = new Date();
        wklist = conHolidayService.queryHolidayList(date, 1, 1);
        hdlist = conHolidayService.queryHolidayList(date, 1, 2);
        quflag = true;
    }


    //判断是否是节日
    private boolean isHoliday(Date date) {
        String dte = DateUtils.formatY_M_D2String(date, DateUtils.FORMAT_Y_M_D);
        boolean result = false;
        if (hdlist!=null && hdlist.size()>0) {
            for (Date holiday : hdlist) {
                String dt2 = DateUtils.formatY_M_D2String(holiday, DateUtils.FORMAT_Y_M_D);
                if (dte.equals(dt2)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    //判断是否是法定工作日
    private boolean isFdWorkDay(Date date) {
        String dte = DateUtils.formatY_M_D2String(date, DateUtils.FORMAT_Y_M_D);
        boolean result = false;
        if (wklist!=null && wklist.size()>0) {
            for (Date holiday : wklist) {
                String dt2 = DateUtils.formatY_M_D2String(holiday, DateUtils.FORMAT_Y_M_D);
                if (dte.equals(dt2)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    //判断是否是工作日
    public boolean isWorkDay(Date date) {
        //判断是否是法定工作日
        boolean result = isFdWorkDay(date);
        if(!result){//非法定工作日
            //判断是否是法定节假日
            boolean faresult = isHoliday(date);
            if(faresult){//是法定节假日
                result = false;
            }else{//非法定节假日
                //判断是否是周六周日
                Calendar calendar = Calendar.getInstance();
                if(date != null){
                    calendar.setTime(date);
                }
                int w = calendar.get(Calendar.DAY_OF_WEEK);
                if(w!=1 && w!=7){
                    result = true;
                }
            }
        }
        return result;
    }

    //当前时间前几天
    public Date getBeforeWorkDay(Date date,int daycount){
        if(!quflag){
            init();
        }
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        while(daycount>0){
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            boolean iswokrflag = isWorkDay(calendar.getTime());
            if(iswokrflag){
                daycount--;
            }
        }
        return calendar.getTime();
    }

    //当前时间后几天
    public Date getAfterWorkDay(Date date,int daycount){
        if(!quflag){
            init();
        }
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        while(daycount>0){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            boolean iswokrflag = isWorkDay(calendar.getTime());
            if(iswokrflag){
                daycount--;
            }
        }
        return calendar.getTime();
    }

    // 当前时间前几个小时
    public Date getBeforeHour(Date date,int daycount){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        while(daycount>0){
            calendar.add(Calendar.HOUR_OF_DAY, -1);
            daycount--;
        }
        return calendar.getTime();
    }

    //当前时间后几个小时
    public Date getAfterHour(Date date,int daycount){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        while(daycount>0){
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            daycount--;
        }
        return calendar.getTime();
    }

    // 当前时间前几分钟
    public Date getBeforeMinutes(Date date,int daycount){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        while(daycount>0){
            calendar.add(Calendar.MINUTE, -1);
            daycount--;
        }
        return calendar.getTime();
    }

    // 当前时间后几分钟
    public Date getAfterMinutes(Date date,int daycount){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        while(daycount>0){
            calendar.add(Calendar.MINUTE, 1);
            daycount--;
        }
        return calendar.getTime();
    }

    //获取当前月的最后一个工作日
    public Date getLastWorkDay(){
        Date lastdate = DateUtils.getMonthLastDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastdate);

        boolean workdayflag = isWorkDay(calendar.getTime());
        while(!workdayflag){
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            workdayflag = isWorkDay(calendar.getTime());
        }
        return calendar.getTime();
    }

    //获取本周的最后一个工作日
    public Date getLastWeekWorkDay(){
        Date lastdate = DateUtils.getWeekLastDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastdate);

        boolean workdayflag = isWorkDay(calendar.getTime());
        while(!workdayflag){
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            workdayflag = isWorkDay(calendar.getTime());
        }
        return calendar.getTime();
    }



    public static void main(String[] args) {
        WorkDay day = new WorkDay();
        Date lasday = day.getLastWorkDay();
        String datstr = DateUtils.formatY_M_D2String(lasday, DateUtils.FORMAT_Y_M_D_H_M_S);
        System.out.println(datstr);
    }


    public Date getWorking(Date startTime,int  hours) {
        Date expireTime = new Date();
        long h = 1000*60*60;
        for (int i = 0; i < hours; i++) {
            expireTime=forWorking(expireTime,h);
        }
        return expireTime;
    }

    public Date forWorking(Date startTime,long  milliscond) {
        long expireTime = 0;

        long oneHour = milliscond;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        //取当天的中午12点
        cal.set(Calendar.HOUR_OF_DAY,12);
        cal.set(Calendar.MINUTE,0);
        long noon = cal.getTime().getTime();
        //取当天的中午13点
        cal.set(Calendar.HOUR_OF_DAY,13);
        cal.set(Calendar.MINUTE,0);
        long noon13 = cal.getTime().getTime();
        //取当前时间
        long currTime = startTime.getTime();


        if(noon13-currTime>0) {//13点以前的
            if(currTime-noon<0) {//12点前
                if(noon-currTime>oneHour) {//12点以前且大于1个小时
                    expireTime = currTime+oneHour;//过期时间等于当前时间+1小时
                }else {
                    expireTime =noon13+oneHour-(noon-currTime);//从13点开始加超过的分钟数
                }
            }else {//12点后13点前
                expireTime =noon13+oneHour;//14点
            }
        }else {//13点之后
            //取当天的17点30分
            cal.set(Calendar.HOUR_OF_DAY,17);
            cal.set(Calendar.MINUTE,30);
            long afternoon = cal.getTime().getTime();
            //取第二个工作日的上午9点
            cal.setTime(getAfterWorkDay(new Date(), 1));
            cal.set(Calendar.HOUR_OF_DAY,9);
            cal.set(Calendar.MINUTE,0);
            long afterDateForenoon = cal.getTime().getTime();
            if(currTime-afternoon<0) {//17点30分前
                if(afternoon-currTime>oneHour) {//如果17点30分大于当前时间超过1小时
                    expireTime = currTime+oneHour;//过期时间等于当前时间+1小时
                }else {
                    expireTime =afterDateForenoon+oneHour-(afternoon-currTime);//过期时间为第二个工作日时间+超过的分钟数
                }
            }else {//17点30分之后
                expireTime = afterDateForenoon+oneHour;
            }
        }



        return new Date(expireTime);
    }

    //回访内容用到的日期方法
    public boolean getSomeDay(Date date) {

        if(this.isWorkDay(date)) {
            return true;
        };

        return false;
    }

    //判断当前时间是否大于当月几号的时间，大于返回true
    public boolean overTime(Date date,String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean results = date.after(sf.parse(sdf.format(date)+"-0"+time+" 23:59:59"));
        return results;
    }


}
