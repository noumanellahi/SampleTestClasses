package com.mycompany.url;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SecureUrlConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
			CookieHandler.setDefault(cookieManager);
			
			String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";
//			String url = "https://dpmaconnect.dpma.de/dpmaws/rest-services/DPMAregisterPatService/getOffenlegungsschriften_Komplett_PDF/202228";
			String url = "https://ascopubs.org/toc/jco/40/16_suppl";
			HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
			httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
//			httpURLConnection.setRequestMethod("GET");

//			httpURLConnection.setRequestProperty("Authorization", "Basic dHJpcHNfcGF0OkNudDVydmNz");

			System.out.println(" ----> " + httpURLConnection.getResponseCode());
			System.out.println(httpURLConnection.getContentType());
			System.out.println(httpURLConnection.getHeaderField("Content-Length"));
			System.out.println(httpURLConnection.getContentType());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void getHeaderDetails(URLConnection urlConnection) {

	}

}
