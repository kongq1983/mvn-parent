package com.kq.util;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 * @author kongqi
 * @date  2017-12-26 16:18 
 * @since 
 */
public class DateUtil {

	private static final ThreadLocal<SimpleDateFormat> threadLocalFormat = ThreadLocal
			.withInitial(() -> new SimpleDateFormat());
	

	
	public static String getDateTimeFormat() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(date);
	}

	public static String getDateFormat() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
	
	public static String getDateTimeFormat(Date date) {
		
		if(date==null) {
			return null;
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(date);
	}

	public static String getFormatFull() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmsszzz");
		return f.format(date).replaceAll("CST", "");
	}




}
