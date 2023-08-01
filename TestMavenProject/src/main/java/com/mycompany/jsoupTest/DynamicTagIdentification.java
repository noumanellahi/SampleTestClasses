package com.mycompany.jsoupTest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DynamicTagIdentification {
	public static void main(String args[]) {
		try {
			String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";

			Document document = Jsoup.parse("<html></html>");
			String url = "https://meetings.asco.org/abstracts-presentations/208990";
			Connection.Response execute = Jsoup.connect(url).header("User-Agent", USER_AGENT)
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").execute();
			document = Jsoup.parse(execute.body(), url);

			System.out.println(document
					.select("body > asco-root > div > asco-presentation-details > div > div.header-container.mt-3 > h3")
					.text());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
