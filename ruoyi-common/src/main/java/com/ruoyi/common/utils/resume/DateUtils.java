package com.ruoyi.common.utils.resume;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtils {
	
	public static final String FORMAT_Y_M_D = "yyyy-MM-dd";
	public static final String FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_H_M_S = "HH:mm:ss";
	
	
	public static Time strToTime(String strDate,String formatStr) {  
	     SimpleDateFormat format = new SimpleDateFormat(formatStr);  
	     Date d = null;  
	     try {  
	         d = format.parse(strDate);  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     }  
	     return new Time(d.getTime());
	} 
	
	public static Date formatY_M_D2Date(String dateStr, String formatStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.parse(dateStr);
	}
	public static String formatY_M_D2String(Date date, String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}
	
	/**
	 * 获取上周五
	 * @return
	 * @throws ParseException 
	 */
	public static Date getLastWeekFive(Date date) throws ParseException{
		date = formatY_M_D2Date(formatY_M_D2String(date,FORMAT_Y_M_D),FORMAT_Y_M_D);
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK);
        if(week_index == 6){
        	cal.add(Calendar.DATE, -7);
        }else if(week_index == 1){
    		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
    		cal.add(Calendar.DATE, -14);
        }else{
        	cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        	cal.add(Calendar.DATE, -7);
        }
        cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
        return cal.getTime();
	}
	
	
	/**
	 * 获取本周五
	 * @return
	 * @throws ParseException 
	 */
	public static Date getWeekFiveDate(Date date) throws ParseException{
		date = formatY_M_D2Date(formatY_M_D2String(date,FORMAT_Y_M_D),FORMAT_Y_M_D);
		
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK);
        if(week_index == 6){
        	return cal.getTime();
        }else if(week_index == 1){
    		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
    		cal.add(Calendar.DATE, -7);
        }else{
        	cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        }
        cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
        return cal.getTime();
	}
	
	
	/**
	 * 获取本周第一天
	 * @return
	 */
	public static Date getWeekStartDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 获取本周最后一天
	 * @return
	 */
	public static Date getWeekLastDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 获取去年的本月第一天
	 * @return
	 */
	public static Date getyearFirstDate(){
		Calendar c = Calendar.getInstance();  
		c.add(Calendar.YEAR, -1);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既去年为本月第一天 
		return c.getTime();
	}
	/**
	 * 获取去年本月最后一天
	 * @return
	 */
	public static Date getyearLastDate(Date nowdate){		
		Calendar cale = Calendar.getInstance(); 
		cale.setTime(nowdate);
		cale.add(Calendar.YEAR, -1);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return cale.getTime();
	}
	/**
	 * 获取前一个月第一天
	 * @return
	 */
	
	public static Date getlastMonthfirstDate(){
	Calendar calendar1 = Calendar.getInstance();
	calendar1.add(Calendar.MONTH, -1);
	calendar1.set(Calendar.DAY_OF_MONTH,1);
	return calendar1.getTime();
	}
	/**
	 * 获取前一个月最后一天
	 * @return
	 */
	
	public static Date getlastMonthlastDate(Date date){

	Calendar calendar2 = Calendar.getInstance();
	calendar2.setTime(date);
	calendar2.set(Calendar.DAY_OF_MONTH, 0);
	return calendar2.getTime();
	}
	
	/**
	 * 获取前N月最后一天
	 * @return
	 */
	public static Date getNMonthlastDate(int a){
	Calendar c = Calendar.getInstance();
	c.setTime(new Date());
	c.add(Calendar.MONTH, -a);
	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	return c.getTime();
	}
	
	
	/**
	 * 获取前N月第一天
	 * @return
	 */
	public static Date getNMonthfirstDate(int a){
	Calendar c = Calendar.getInstance();
	c.setTime(new Date());
	c.add(Calendar.MONTH, -a);
	c.set(Calendar.DAY_OF_MONTH, 1);
	return c.getTime();
	}
	/**
	 * 获取本月第一天
	 * @return
	 */
	public static Date getMonthFirstDate(){
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		return c.getTime();
	}
	
	/**
	 * 获取本月最后一天
	 * @return
	 */
	public static Date getMonthLastDate(){
		Calendar cale = Calendar.getInstance(); 
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return cale.getTime();
	}
	
	/**
	 * 获取本月最后一天
	 * @return
	 */
	public static Date getMonthNowLastDate(Date date){
		Calendar cale = Calendar.getInstance(); 
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return cale.getTime();
	}
	
	/**
	 * 获取几天前的日期
	 * @return
	 */
	public static Date getDayBefore(Date date,int daycount){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -daycount);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	
	
	public static boolean isDate(String date){    
        /**  
         * 判断日期格式和范围  
         */    
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\.\\/\\s]?((((0?[13578])|(1[02]))[\\-\\.\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\.\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\.\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\.\\/\\s]?((((0?[13578])|(1[02]))[\\-\\.\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\.\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\.\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";       
        Pattern pat = Pattern.compile(rexp);            
        Matcher mat = pat.matcher(date);            
        boolean dateType = mat.matches();
        return dateType;    
    }  
	
	public static long getDayDiff(String startdate,String enddate) throws ParseException{
		DateFormat df = new SimpleDateFormat(FORMAT_Y_M_D_H_M_S);
	    Date d1 = df.parse(enddate);
	    Date d2 = df.parse(startdate);
	    long diff = d1.getTime() - d2.getTime();
	    long days = diff / (1000 * 60 * 60 * 24);
	    return days;
	}
	
	
	
	public static long getDayDiff(Date startdate,Date enddate) throws ParseException{
	    long diff = enddate.getTime() - startdate.getTime();
	    long days = diff / (1000 * 60 * 60 * 24);
	    return days;
	}
	
	
	public static Map<String,Object> getDiff(String startdate,String enddate) throws ParseException{
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat dfs = new SimpleDateFormat(FORMAT_Y_M_D_H_M_S);
	   Date begin = dfs.parse(startdate);
	   Date end = dfs.parse(enddate);
	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒

	   long day1=between/(24*3600);
	   long hour1=between%(24*3600)/3600;
	   long minute1=between%3600/60;
	   long second1=between%60/60;
	   map.put("days", day1);
	   map.put("hours", hour1);
	   map.put("minutes", minute1);
	   map.put("seconds", second1);	   
	   return map;
	}
	
	
	public static Map<String,Object> getDiff(Date begin,Date end) throws ParseException{
	   Map<String,Object> map = new HashMap<String,Object>();
	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒

	   long day1=between/(24*3600);
	   long hour1=between%(24*3600)/3600;
	   long minute1=between%3600/60;
	   long second1=between%60/60;
	   map.put("days", day1);
	   map.put("hours", hour1);
	   map.put("minutes", minute1);
	   map.put("seconds", second1);	   
	   return map;
	}
	
	
	/**   
	* 获取两个日历的月份之差   
	*    
	* @param
	* @param
	* @return   
	 * @throws ParseException 
	*/   
	public static int getMonthsOfAge(Date startdate,Date enddate) throws ParseException {  
//		  SimpleDateFormat sdf=new SimpleDateFormat(FORMAT_Y_M_D);
//		  Date dt=sdf.parse(startdate);
//		  Date dt2=sdf.parse(enddate);
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.setTime(startdate);
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(enddate);
		
		return (calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR))* 12+ calendarNow.get(Calendar.MONTH)	- calendarBirth.get(Calendar.MONTH);    
    }  
	
	
	/**   
	* 获取n个月份前的数据  
	*    
	* @param calendarBirth   
	* @param calendarNow   
	* @return   
	 * @throws ParseException 
	*/   
	public static Date getMonthsOfAge(int a){  
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -a);
		Date m = c.getTime();
		return m;
    }  
	
	
	
	
	public static Date timeIntStrToDate(String str) throws ParseException{
		long now = new Date().getTime();
		Date resut = null;
		if(str.indexOf("分钟")>=0){
			int mins = Integer.parseInt(str.substring(0,str.indexOf("分钟")));
			long diffst = now - mins*60*1000;
			resut = new Date(diffst);
		}else if(str.indexOf("小时")>=0){
			int hours = Integer.parseInt(str.substring(0,str.indexOf("小时")));
			long diffst = now - hours*60*60*1000;
			resut = new Date(diffst);			
		}else{
			String reg = "^(\\d{2})-(\\d{2})$";
			Pattern pat = Pattern.compile(reg);  
			Matcher mat = pat.matcher(str);
			if(mat.matches()){
				resut = formatY_M_D2Date("2017-" + str, FORMAT_Y_M_D);
			}
		}
		return resut;
	}
	
	
	/*public static void main(String[] args) throws ParseException {
		Date lasday = getMonthLastDate();
		String datstr = formatY_M_D2String(lasday, FORMAT_Y_M_D_H_M_S);
		System.out.println(datstr);
		
	}
	*/
	//获取工作时间间隔（9:00-12:00 13:00-17:30）
	public static Map<String,Object> getWorkDiff(Date begin,Date end) throws ParseException{
	   Map<String,Object> map = new HashMap<String,Object>();
	   Date workstartdate = new Date(end.getYear(),end.getMonth(),end.getDate(),9,0,0);
	   Date twerldate = new Date(end.getYear(),end.getMonth(),end.getDate(),12,0,0);
	   Date thirtydate = new Date(end.getYear(),end.getMonth(),end.getDate(),13,0,0);
	   Date workenddate = new Date(end.getYear(),end.getMonth(),end.getDate(),17,30,0);
	   
	   if(begin.before(workstartdate)){
		   begin = workstartdate;
	   }
	   if(begin.after(twerldate) && begin.before(thirtydate)){
		   begin = twerldate;
	   }else if(end.after(workenddate)){
		   end = workenddate;
	   }
	   if(begin.before(thirtydate) && end.after(thirtydate)){
		   begin.setHours(begin.getHours()+1);
	   }
	   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
	   long day1=between/(24*3600);
	   long hour1=between%(24*3600)/3600;
	   long minute1=between%3600/60;
	   long second1=between%60/60;
	   
	   
	   
	   map.put("days", day1);
	   map.put("hours", hour1);
	   map.put("minutes", minute1);
	   map.put("seconds", second1);	   
	   return map;
	}
	
	
	public static boolean sameday(Date date1,Date date2){
		String date1str = formatY_M_D2String(date1,FORMAT_Y_M_D);
		String date2str = formatY_M_D2String(date2,FORMAT_Y_M_D);
		return date1str.equals(date2str);
	}
	
	public static Date nexMonth(Date date,Integer month){
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,month); 
        return c.getTime();
	}

	
	public  static String getYearMonth() {
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH);
		String m;
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		return now.get(Calendar.YEAR) + "-" + m;
	}
	
	

	public static boolean equalsDate(Date date1,Date date2){
		Calendar calendar1=Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 =Calendar.getInstance();
		calendar2.setTime(date2);
//		int year1=calendar1.get(Calendar.YEAR);
//		int year2=calendar2.get(Calendar.YEAR);
//		int month1=calendar1.get(Calendar.MONTH);
//		int month2=calendar2.get(Calendar.MONTH);
		return calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR)&&calendar1.get(Calendar.MONTH)== calendar2.get(Calendar.MONTH);

	}
	
	/**
	 *    获取当月的某一天
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getMonthday(Integer month){
	   Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, month);
        calendar1.add(Calendar.MONTH, 0);
        return calendar1.getTime();
	}
	/**
	 *   判断当前时间在某个季度
	 * @param date
	 * @param month
	 * @return 1、第一季度 2、第二季度 3、第三季度4、第四季度
	 */
	public static int getDateseason(Date date) {
        int a=4;
		//第一季度初
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date spingstart = c.getTime();
		//第二季度初
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.MONTH, 3);
		cale.set(Calendar.DAY_OF_MONTH, 1);  
		Date summerstart = cale.getTime();
		//第三季度初
		Calendar cale2 = Calendar.getInstance();
		cale2.set(Calendar.MONTH, 6);
		cale2.set(Calendar.DAY_OF_MONTH, 1);  
		Date autumnstart = cale2.getTime();
		//第四季度初
		Calendar cale3 = Calendar.getInstance();
		cale3.set(Calendar.MONTH, 9);
		cale3.set(Calendar.DAY_OF_MONTH, 1);  
		Date winterstart = cale3.getTime();
		if(spingstart.getTime()<date.getTime()&&date.getTime()<summerstart.getTime()) {
			a=1;
		}else if(summerstart.getTime()<date.getTime()&&date.getTime()<autumnstart.getTime()){
			a=2;
		}else if(autumnstart.getTime()<date.getTime()&&date.getTime()<winterstart.getTime()) {
			a=3;
		}
		return a;
		
	}
	/**
	 * 获取(指定日期的)前N月最后一天
	 * @return
	 */
	public static Date getNMonthlastDate(Date date, int a){
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	c.add(Calendar.MONTH, -a);
	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	return c.getTime();
	}
	
	/**
	 * 获取(指定日期的)后N月最后一天
	 * @return
	 */
	public static Date getNMonthafterDate(Date date, int a){
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	c.add(Calendar.MONTH, a);
	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	return c.getTime();
	}
	/**
	 * 获取当天的0点
	 * @return
	 */
	public static Date getzero(){
	Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    Date zero = calendar.getTime();
    return zero;
	}
	

	public static void main(String[] args) throws ParseException {

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(getlastMonthfirstDate());
		int month2=calendar1.get(Calendar.MONTH);
		System.out.println(month2);	
//		String asd=formatY_M_D2String(getlastMonthfirstDate(),FORMAT_Y_M_D);
		System.out.println(formatY_M_D2String(getlastMonthfirstDate(),FORMAT_Y_M_D));

			
		Date date = formatY_M_D2Date("2020-09-00",FORMAT_Y_M_D);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date);
		int month1=calendar2.get(calendar2.MONTH);
		System.out.println(month1);	
	}
	
	/**
	 * 获取上周一
	 * @return
	 * @throws ParseException 
	 */
	public static Date getLastWeekOne(Date date) throws ParseException{
		date = formatY_M_D2Date(formatY_M_D2String(date,FORMAT_Y_M_D),FORMAT_Y_M_D);
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK);
        if(week_index == 6){
        	cal.add(Calendar.DATE, -7);
        }else if(week_index == 1){
    		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    		cal.add(Calendar.DATE, -14);
        }else{
        	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        	cal.add(Calendar.DATE, -7);
        }
        cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
        return cal.getTime();
	}









	/*
	 *fromDate 开始时间，toDate结束时间
	 *获取两个日期之间的天数
	*@au ych
	* */

	public static List<Date> getBetweenDates(Date fromDate, Date toDate) {
		List<Date> stamps = new ArrayList<>();
		while (fromDate.getTime() <= toDate.getTime()) {
			stamps.add(fromDate);
			fromDate = DateUtils.addDays(1, fromDate);
		}
		return stamps;
	}
	//addDays方法是增加天数。
	public static Date addDays(int days, Date stamp) {
		Long milliseconds = Long.valueOf(days * 24 * 60 * 60 * 1000);
		return new Date(stamp.getTime() + milliseconds);
	}


}
