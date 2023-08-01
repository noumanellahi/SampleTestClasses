package com.mycompany.url;

import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionClose {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String stringURL = "https://www.fda.gov/news-events/%";
			URL webURL = new URL(stringURL);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) webURL.openConnection();
//			System.out.println(httpUrlConnection.getContentType());
			httpUrlConnection.disconnect();
			
			System.out.println(httpUrlConnection.getResponseCode());
//			httpUrlConnection.connect();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
