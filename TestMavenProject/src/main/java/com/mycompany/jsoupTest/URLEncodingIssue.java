package com.mycompany.jsoupTest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class URLEncodingIssue {
	public static void main(String args[]) {
		try {
			Document document = Jsoup.parse("<html></html>");
			String url = "http://gazette.laws.gov.ag/";
			Connection.Response execute = Jsoup.connect(url).header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").execute();
			document = Jsoup.parse(execute.body(), url);
			
			String description = document
					.select("body > main > div.row.new-publications > div.new-official > ul > li:nth-child(1) > a").text();
			description = description.replaceAll("[\\[\\]{}()!*+~`%^<>$&+,:;=?@#|'\"\\\\/\\s\u2003]", "_");
//			description = description.replaceAll("\u2003", "_");
			System.out.println(description);
			
//			Elements srcElements = document.select("body > table:nth-child(2) > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > p:nth-child(13)").select("*");
//			for (Element element : srcElements.select("a")) {
//				System.out.println(""+Jsoup.clean(element.attr("abs:href"), Whitelist.none()));
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
