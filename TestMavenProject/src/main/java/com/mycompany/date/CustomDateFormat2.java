package com.mycompany.date;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;

public class CustomDateFormat2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCustomDate("year.week", -31));
	}

	public static String getCustomDate(String dateFormat, Integer offset) {
		String dateStr = null;
		LocalDate calendar = LocalDate.now();
		switch (dateFormat) {
		case "year.week": {
			String currentYear = String.valueOf(calendar.getYear());
			int week;

			if (offset != null && (offset > 0 || offset < 0)) {
				week = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
				week = week + offset;
			} else {
				week = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
			}

			dateStr = currentYear + week;
		}
			break;
		case "year.month": {
			String currentYear = String.valueOf(calendar.getYear());
			int month;

			if (offset != null && (offset > 0 || offset < 0)) {
				month = LocalDate.now().getMonthValue();
				month = month + offset;
			} else {
				month = LocalDate.now().getMonthValue();
			}

			dateStr = currentYear + month;
		}
			break;
//		case "year.quarter": {
//			dateStr = calendar.get(Calendar.YEAR) + "quarter" + LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
//		}
//			break;
//		case "year.Q": {
//			dateStr = calendar.get(Calendar.YEAR) + "Q" + LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
//		}
//			break;
//		case "PerviousYear.CurrentYear": {
//			// Minus one year to get previous year from current date
//			calendar.add(Calendar.YEAR, -1);
//			dateStr = calendar.get(Calendar.YEAR) + ".";
//
//			// get current year
//			calendar = Calendar.getInstance();
//			dateStr = dateStr + calendar.get(Calendar.YEAR);
//		}
//			break;
		}
		return dateStr;
	}

}
