package com.mycompany.string;

public class ReplaceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String HOST_PATH_DATE_IDENTIFIER = "{currentDt}";
		 String HOST_PATH_TIME_IDENTIFIER = "{executionTime}";
		
		String hostLocation = "raw/patents/AU/XMLPDF/{currentDt}/{executionTime}";
		
		hostLocation = hostLocation.replace(HOST_PATH_DATE_IDENTIFIER,"123456");
		
		System.out.println(hostLocation);
	}

}
