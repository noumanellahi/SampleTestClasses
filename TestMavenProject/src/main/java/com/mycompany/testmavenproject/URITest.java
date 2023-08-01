/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.net.HttpURLConnection;
import java.net.URI;

/**
 *
 * @author Noman.Alahi
 */
public class URITest {

	public static void main(String[] args) {
		try {
			String orignalUrl = "https://api.hugverk.is/media/raonxesi/apríl2023.pdf";

//			String orignalUrl = "https://www.hse.ie/eng/services/list/5/cancer/profinfo/chemoprotocols/leukemia-bmt/613-vxyeos-%C2%AE-liposomal-daunorubicin-and-cytarabine-induction-therapy.pdf";

//			URL webUrl = new URL(orignalUrl);
			
			URI webUrl = new URI(orignalUrl);

//			String host = webUrl.getHost();
//
//			System.out.println(host);
//			String[] urlPath = webUrl.getPath().split("/");
//
//			for (int i = 0; i < urlPath.length; i++) {
//				if (!urlPath[i].matches("^[\\p{ASCII}]+$")) {
//					urlPath[i] = URLEncoder.encode(urlPath[i], "UTF-8");
//				}
//			}
//
//			String encodedUrlPath = String.join("/", urlPath);
//
//			String encodedUrl = orignalUrl.substring(0, orignalUrl.indexOf(host)) + host + encodedUrlPath;
//
//			System.out.println(encodedUrl);

//			webUrl = new URL(url);

//			String decodedUrl = URLDecoder.decode(url, "UTF-8");

//			System.out.println(url);
//			System.out.println(decodedUrl);
//			URL webUrl = new URL(url);
//
//			try {
//				String url2 = "../release/nr/2020/MTPC200629.html";
//				webUrl = new URL(url2);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//
//			System.out.println(webUrl.toURI());

//			HttpURLConnection httpcon = (HttpURLConnection) webUrl.openConnection();
			HttpURLConnection httpcon = (HttpURLConnection) webUrl.toURL().openConnection();
//			httpcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

			System.out.println(httpcon.getResponseCode());

//			httpcon.setRequestProperty("User-Agent",
//					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
			// check if the required header exist and have the desired value
//			System.out.println(httpcon.getResponseCode());
			System.out.println(httpcon.getHeaderField("Location"));

			System.out.println(httpcon.getURL());
//			if (urlConnection.getContentType() != null
//					&& urlConnection.getContentType().toLowerCase().startsWith("text/html")) {
//				System.out.println(true);
//			} else {
//				System.out.println(false);
//			}
		} catch (Exception ex) {
			System.out.println("ERROR OCCURED " + ex);
		}

	}
}
