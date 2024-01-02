package com.knitkota.core.utils;

import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

	@Value("${application.timezone}")
	String timezoneId;
	
	private static TimeService timeService;
	
	@PostConstruct
	public void init() {
		timeService = this;
	}
	
	public static Optional<TimeService> getInstance() {
		return Optional.ofNullable(timeService);
	}
	
	public Calendar getCalendarObj() {
		Calendar calendar = Calendar.getInstance();
		
		if(timezoneId!=null) {
			calendar = Calendar.getInstance(TimeZone.getTimeZone(timezoneId));
		}else {
			calendar = Calendar.getInstance();
		}
		
		return calendar;
	}
	
	public static Calendar getCalendar() {
		return timeService.getCalendarObj();
	}
	
	public static Calendar setCalanderToZeroHours(long timeInMillisecond) {
	
		Calendar calendar = getCalendar();
		
		calendar.setTimeInMillis(timeInMillisecond);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println(timeInMillisecond);
		System.out.println(calendar.getTimeInMillis());
		return calendar;
	}
	
}
