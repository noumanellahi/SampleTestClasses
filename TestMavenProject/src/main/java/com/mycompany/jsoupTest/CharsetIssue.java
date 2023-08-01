package com.mycompany.jsoupTest;

import java.nio.charset.Charset;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CharsetIssue {
	public static void main(String args[]) {
		try {
			Document document = Jsoup.parse("<html></html>");
			String url = "http://esubmission.ema.europa.eu/eASMF/index.htm";
			Connection.Response execute = Jsoup.connect(url).header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").execute();
			document = Jsoup.parse(execute.body(), url);

			System.out.println("BEFORE   ------>  " + document.charset());

			Elements metaTags = document.getElementsByTag("meta");
			String metaCharsetValue = "";

			for (Element metaTag : metaTags) {
				String metaTagString = metaTag.toString().toLowerCase();
				int index = metaTagString.indexOf("charset=");
				if (index >= 0) {
					metaCharsetValue = metaTagString.substring(index + 8, metaTagString.length()).trim();
					break;
				}
			}

			if (!metaCharsetValue.isEmpty()) {
				metaCharsetValue = metaCharsetValue.replaceAll("[;\">]", "").toLowerCase();

				if (metaCharsetValue.equals(document.charset().toString().toLowerCase())) {
					System.out.println("All GOOD");
				} else {
					if (Charset.availableCharsets().containsKey(metaCharsetValue)) {
						document.charset(Charset.availableCharsets().get(metaCharsetValue));
					}
				}
			}

			System.out.println("AFTER   ------>  " + document.charset());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
