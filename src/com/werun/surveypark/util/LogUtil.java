package com.werun.surveypark.util;

import java.util.Calendar;

public class LogUtil {
	
	public static String generateLogTableName(int offset)
	{
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1+offset;
		
		if(month>12)
		{
			year++;
			month-=12;
		}
		else if(month<1)
		{
			year--;
			month+=12;
		}
		return "logs_" + year + "_" + month ;
	}
}
