package com.mycompany.date;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;

public class CustomDateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCustomDate("YYYY.WW", 10));
	}

	public static String getCustomDate(String dateFormat, Integer offset) {
		String dateStr = null;
		Calendar calendar = Calendar.getInstance();
		switch (dateFormat) {
		case "year.week":
		case "YYYY.WW": {
			String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
			int week;

			if (offset != null && (offset > 0 || offset < 0)) {
				calendar.add(Calendar.WEEK_OF_YEAR, offset);
				week = calendar.get(Calendar.WEEK_OF_YEAR);
			} else {
				week = calendar.get(Calendar.WEEK_OF_YEAR);
			}

			if (week > 0) {
				if (dateFormat.equals("YYYY.WW") && week < 10) {
					currentYear = currentYear + "0";
					dateStr = currentYear + week;
				} else {
					dateStr = currentYear + week;
				}
			}
		}
			break;
		case "year.month": {
			String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
			int month;

			if (offset != null && (offset > 0 || offset < 0)) {
				calendar.add(Calendar.MONTH, offset);
				month = calendar.get(Calendar.MONTH);
			} else {
				month = calendar.get(Calendar.MONTH);
			}

			dateStr = currentYear + month;
		}
			break;
		case "year.quarter": {
			dateStr = calendar.get(Calendar.YEAR) + "quarter" + LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
		}
			break;
		case "year.Q": {
			dateStr = calendar.get(Calendar.YEAR) + "Q" + LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
		}
			break;
		case "PerviousYear.CurrentYear": {
			// Minus one year to get previous year from current date
			calendar.add(Calendar.YEAR, -1);
			dateStr = calendar.get(Calendar.YEAR) + ".";

			// get current year
			calendar = Calendar.getInstance();
			dateStr = dateStr + calendar.get(Calendar.YEAR);
		}
			break;
		}
		return dateStr;
	}

}
