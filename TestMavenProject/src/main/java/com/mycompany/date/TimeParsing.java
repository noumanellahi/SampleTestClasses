package com.mycompany.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeParsing {
	public static void main(String[] args) {
		Long scraperDate = new Timestamp(System.currentTimeMillis()).getTime();
		
		System.out.println(new SimpleDateFormat("hhmmss").format(scraperDate));
	}
}
