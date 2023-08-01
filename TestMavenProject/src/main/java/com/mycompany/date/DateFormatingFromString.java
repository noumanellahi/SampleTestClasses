package com.mycompany.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatingFromString {

	public static void main(String[] args) {
		try {
			String dateString = "Jan 06 2022 04:08";
			Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateString);
			dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date1);
			System.out.println(dateString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		
		
	}

}
