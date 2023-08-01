package com.mycompany.testmavenproject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import com.google.gson.Gson;

public class HtmlToJson {
	public static void main(String args[]) {
		try {
			Document document = Jsoup.parse("<html></html>");
			String url = "http://ctri.nic.in/Clinicaltrials/pmaindet2.php?trialid=10176&EncHid=&userName=CTRI/2019/06";
			Connection.Response execute = Jsoup.connect(url).header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").execute();
			document = Jsoup.parse(execute.body(), url);
			
			Whitelist wl = Whitelist.simpleText();
			String clean = Jsoup.clean(document.toString(), wl);
			System.out.println(clean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
