package com.mycompany.testmavenproject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	public static void main(String[] args) {
		
//		#CurrentDate_MM/dd/YYYY
//		#CurrentDate_dd/MM/YYYY
//		#CurrentDate_YYYY/MM/dd
//		#CurrentDate_YYYY-MM-dd
//		#CurrentDate_MM-dd-YYYY
//		#CurrentDate_dd-MM-YYYY
//		#CurrentDate_YYYYMMdd
//		#CurrentDate_ddMMYYYY
//		#CurrentDate_MMddYYYY
		
		String date = new SimpleDateFormat("YYMMdd").format(new Date());
		
		System.out.println(date);
	}

}
