package com.torinist.goocom.util;

public class StringUtils extends org.springframework.util.StringUtils {

	private static final int DATE_FORMAT_LENGTH = 8;

	/**
	 * DATE_FORMAT_LENGTH桁のdateの最初から4桁を取得する.
	 * 
	 * @param date DATE_FORMAT_LENGTH桁の年月日
	 * @return 年
	 */
	public static String getStringYear(String date) {
		if (date.length() != DATE_FORMAT_LENGTH) {
			return null;
		}
		String year = date.substring(0, 4);
		return year;
	}
}
