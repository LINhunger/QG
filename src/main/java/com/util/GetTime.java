package com.util;

import java.util.Calendar;

public class GetTime {
	
    public static String GetNowTime() {
	Calendar ca = Calendar.getInstance();
	int year = ca.get(Calendar.YEAR);
	int month=ca.get(Calendar.MONTH)+1;
	int day=ca.get(Calendar.DATE);
	
	String newMonth=month+"";
	String newDay=day+"";
	if(month<10)   newMonth="0"+month;
	if(day<10)		 newDay="0"+day;
	return new String(year+"-"+newMonth+"-"+newDay);
    }
}