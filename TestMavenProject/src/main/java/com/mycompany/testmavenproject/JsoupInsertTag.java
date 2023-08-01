//package com.mycompany.testmavenproject;
//
//import java.io.IOException;
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.parser.*;
//import org.jsoup.select.Elements;
//
//public class JsoupInsertTag {
//	public static void main(String[] arg) {
//		// Create a new trust manager that trust all certificates
//		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//			@Override
//			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//
//			@Override
//			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
//			}
//
//			@Override
//			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
//			}
//		} };
//
//		// Activate the new trust manager
//		try {
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		try {
//			String url = "https://www.businesswire.com/portal/site/home/";
////			Document doc = Jsoup.connect(url).header("User-Agent",
////					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
////					.header("Accept",
////							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
////					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
////					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").parser(Parser.xmlParser()).get();
//
////			Document document = Jsoup.parse(execute.body());
////			
////			Document doc = Jsoup.parse(execute.body()., "", Parser.xmlParser());
//			
//			Elements link = doc.select("rss channel link");
//			System.out.println(link); // prints empty string
//
////			System.out.println(document);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
