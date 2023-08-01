package com.mycompany.testmavenproject;

import java.net.MalformedURLException;
import java.net.URL;

public class TernaryOperator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";
		String Custom_USER_AGENT = "Clarivate-Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";
		
		URL webURL;
		try {
			webURL = new URL("http://tutorials.jenkov.com/java/ternary-operator.html");
			System.out.println(webURL.getHost());
			System.out.println(webURL.getHost().toLowerCase().equals("tutorials.jenkov.com") ? Custom_USER_AGENT : USER_AGENT);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
