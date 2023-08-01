package com.mycompany.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PreviousDay {

	static public  void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPreviousDate(-7, "MM/dd/yyyy"));
	}
	public static String getPreviousDate(Integer daysBack, String datePattern) {
		String dateStr = null;
		try {
			if (datePattern.startsWith("#CurrentDate_")) {
				datePattern = datePattern.split("_")[1];
			}
			DateFormat dateFormat = new SimpleDateFormat(datePattern);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, daysBack);
			Date todate1 = cal.getTime();
			dateStr = dateFormat.format(todate1);
		} catch (Exception ex) {
			return "Error in date";
		}
		return dateStr.replace("\"", "");
	}
}
