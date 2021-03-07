package org.com.pack.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	public static Date createFormatedDate() {
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = DateFor.parse("2019-01-20");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
