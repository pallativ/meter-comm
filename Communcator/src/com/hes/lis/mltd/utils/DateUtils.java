package com.hes.lis.mltd.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author psvr
 *
 */
public class DateUtils {
	
	public static Integer diffInMinutes(Date toDateTime,Date fromDatetime) {
		Integer minutes=0;
		
		try {
			long diff = fromDatetime.getTime() - toDateTime.getTime();//as given
			minutes = (int) TimeUnit.MILLISECONDS.toMinutes(diff);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return minutes;
	}
	
	
	public static Integer diffInDays(Date toDateTime,Date fromDatetime) {
		Integer days=0;
		
		try {
			days=diffInMinutes(toDateTime, fromDatetime)/1440;
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return days;
	}
	
	
	public static Date fetchDateBeforeDays(Date toDateTime,Integer daysDiff) {
		Date fromdate=null;
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(toDateTime);
			cal.add(Calendar.DATE, -daysDiff);
			fromdate = cal.getTime();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return fromdate;
	}
	
	
	public static String dateTimeHexBytesByDate(Date date){
		String strDate="";
		Calendar currentDate = Calendar.getInstance();
    	currentDate.setTime(date);

    	strDate=strDate+StringUtils.leftPad(String.format("%x", currentDate.get(Calendar.YEAR)), 4,"0")+" ";
    	strDate=strDate+StringUtils.leftPad(String.format("%x", currentDate.get(Calendar.MONTH)+1), 2,"0")+" ";
    	strDate=strDate+StringUtils.leftPad(String.format("%x", currentDate.get(Calendar.DAY_OF_MONTH)), 2,"0")+" ";
    	strDate=strDate+"FF ";
    	strDate=strDate+StringUtils.leftPad(String.format("%x", currentDate.get(Calendar.HOUR_OF_DAY)), 2,"0")+" ";
    	strDate=strDate+StringUtils.leftPad(String.format("%x", currentDate.get(Calendar.MINUTE)), 2,"0")+" ";
    	strDate=strDate+StringUtils.leftPad(String.format("%x", currentDate.get(Calendar.SECOND)), 2,"0")+" ";
    	strDate=strDate+"FF 80 00 FF";
		return " 09 0c "+strDate;
	}

	public static void main(String[] args) {

		try {
//			Timestamp fromDatetime=new Timestamp(new Date().getTime());
//			Timestamp toDatetime=new Timestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("04-11-21 08:02:57").getTime());
//
//			System.out.println(new DateUtils().diffInMinutes(toDatetime, fromDatetime));
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date toLoadDate=new Date();
			Date fromLoadDate=fetchDateBeforeDays(toLoadDate, 1);
			
			fromLoadDate=dateFormat.parse((dateTimeFormat.format(fromLoadDate)));
			toLoadDate=dateFormat.parse((dateTimeFormat.format(toLoadDate)));
			
			System.out.println("toLoadDate  :"+toLoadDate);
			System.out.println("fromLoadDate:"+fromLoadDate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
