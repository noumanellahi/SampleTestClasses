package com.mycompany.date;

import java.time.LocalDate;
import java.util.Date;

public class GetDateWithONeWeekGap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		
	    LocalDate previousSaturday = today.minusDays(8);
	    LocalDate previousFriday = today.minusDays(2);
	    
	    System.out.println("ARCHIVING SCRAPER TABLE DATA FROM "+ previousSaturday +" TO "+ previousFriday);

	    Date startDate = java.sql.Date.valueOf(previousSaturday);
	    Date endDate = java.sql.Date.valueOf(previousFriday);

	    System.out.println("ARCHIVING SCRAPER TABLE DATA FROM "+ startDate +" TO "+ endDate);
	}

}
