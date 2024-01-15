package com.mycompany.url;

import java.net.MalformedURLException;
import java.net.URL;

public class ElsevierDomainName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL("https://business.api.elsevier.com/v1/journal-impact-factor/v1/daily-feed/2023-09-03 ");
			System.out.println(url.getHost());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
