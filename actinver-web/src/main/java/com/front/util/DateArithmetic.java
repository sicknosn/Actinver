package com.front.util;

import java.util.Calendar;
import java.util.Date;

public class DateArithmetic {
	
	public static final int NUM_OF_DAYS_IN_WEEK = 7;
	
	private static final long milisInOneSecond = 1000;
	private static final long secondInAMinute = 60;
	private static final long minutesInAnHour = 60;

	public static Date addDays( Date refDate, int noOfDays ){
		Calendar cal = Calendar.getInstance();
		cal.setTime( refDate );
		cal.add( Calendar.DAY_OF_MONTH, noOfDays );
		return cal.getTime();
	}
	
	public Date getCurrentDate(){
		return Calendar.getInstance().getTime();
	}
	
	public Date get24HourReferenceDate( int hour, int minute ){
		return get24HourReferenceDate(hour, minute, 0);
	}
	
	public Date get24HourReferenceDate( int hour, int minute, int second ){
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.HOUR_OF_DAY, hour ); //24 H - clock format
		cal.set( Calendar.MINUTE, minute );
		cal.set( Calendar.SECOND, second );
		return cal.getTime();
	}
	
	public String abshhMMssDif( Date date1, Date date2 ){
		String result = "-1";
		StringBuilder builder = new StringBuilder(); 
		long date1Milis, date2Milis, milisDif, hoursDif, minutesDif, secondsDif;
		long hoursDifInMilis, minutesDifAsMilis;
		if( date1 != null  && date2 != null ){
			date1Milis = date1.getTime();
			date2Milis = date2.getTime();
			milisDif = Math.abs( date2Milis - date1Milis );
			//1. calulate hours
			hoursDif = ((milisDif / milisInOneSecond) / secondInAMinute) / minutesInAnHour;
			//2. calculate minutes
			hoursDifInMilis = hoursDif * minutesInAnHour * secondInAMinute * milisInOneSecond;
			milisDif = milisDif - hoursDifInMilis;
			minutesDif = (milisDif / milisInOneSecond) / secondInAMinute;
			//3. calculate seconds
			minutesDifAsMilis = minutesDif * secondInAMinute * milisInOneSecond;
			milisDif = milisDif - minutesDifAsMilis;
			secondsDif = milisDif / milisInOneSecond;
			builder.append(hoursDif).append(":").append(minutesDif).append(":").append(secondsDif);
			result = builder.toString();
		}
		return result;
	}
	
}
