package com.mycompany.testmavenproject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class HeaderTest {
	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.setProperty("gate.home", "http://gate.ac.uk/wiki/code-repository");

			URL webURL = new URL("https://revista.fct.unesp.br/index.php/formacao/issue/view/454");
			HttpURLConnection con = (HttpURLConnection) webURL.openConnection();
			System.out.println(con.getHeaderFields());

//			httpUrlConnection.addRequestProperty("User-Agent",
//					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");

//			HttpURLConnection httpcon = (HttpURLConnection) urlConnection;

//			System.out.println(httpUrlConnection.getRequestProperties());

			boolean redirect = false;

			// normally, 3xx is redirect
//			int status = httpcon.getResponseCode();
//			System.out.println(status);
//			if (status != HttpURLConnection.HTTP_OK) {
//				if (status == HttpURLConnection.HTTP_MOVED_TEMP
//					|| status == HttpURLConnection.HTTP_MOVED_PERM
//						|| status == HttpURLConnection.HTTP_SEE_OTHER)
//				redirect = true;
//			}
			System.out.println(redirect);
//			String contentType = urlConnection.getHeaderField("Content-ype");
//
//			System.out.println(urlConnection.getHeaderFields());
//
//			if (contentType != null && contentType.toLowerCase().startsWith("text/html")) {
//				System.out.println(urlConnection.getContentType());
//				System.out.println(urlConnection.getHeaderFields());
//			} else {
//				System.out.println("not possible");
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
