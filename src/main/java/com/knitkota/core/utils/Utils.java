package com.knitkota.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {

	public static final String adminEmailAddress = "wnmtest0@gmail.com";
	private static SimpleDateFormat simpleDateFormatDateOnly = new SimpleDateFormat("ddMMyyyy");

	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();

	}

	public static String getRequesUrl() {
		
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}
	
	public static String getRequesUrl(String path) {
		
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).toUriString();
	}

	public static String firstLeterCapital(String str) throws Exception {
		String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
		return cap;
	}

	
	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}
	
	public static String getDateString(long timeInMillisecond) {
		//simpleDateFormatDateOnly
		
		Date date = new Date(timeInMillisecond);
		
		String dateStr = simpleDateFormatDateOnly.format(date);
		
		return dateStr;
		
	}
	
	

}
