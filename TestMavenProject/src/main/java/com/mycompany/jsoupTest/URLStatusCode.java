package com.mycompany.jsoupTest;

import java.net.HttpURLConnection;
import java.net.URL;

public class URLStatusCode {
	public static void main(String args[]) {
		try {
//			String url = "http://formularynavigator.com/";
//			Connection.Response execute = Jsoup.connect(url).header("User-Agent",
//					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
//					.header("Accept",
//							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
//					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").execute();
//			System.out.println(execute.statusCode());

//			HttpURLConnection conn = (HttpURLConnection) new URL("https://www.beyondair.net/pipeline/severe-lung-infections").openConnection();
//			new URL("https://www.asdfasfasfgwfsergfserfgwerfg.com/");
			HttpURLConnection conn = (HttpURLConnection) new URL("https://www.asdfasfasfgwfsergfserfgwerfg.com/")
					.openConnection();
			System.out.println(conn.getResponseCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
