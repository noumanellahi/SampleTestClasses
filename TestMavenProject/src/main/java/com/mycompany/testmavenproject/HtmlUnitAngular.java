/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Noman.Alahi
 */
public class HtmlUnitAngular {

    public static void main(String[] args) {
    	
		// Create a new trust manager that trust all certificates
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		// Activate the new trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    	
        Document document = Jsoup.parse("<html></html>");
        String url = "https://ericaweiner.com/collections";
//        String url = "https://www.clinicaltrialsregister.eu/ctr-search/rest/feed/bydates?query";
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {

			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setTimeout(100 * 1000);
			// new change added by nabeel
			webClient.getOptions().setAppletEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setCssErrorHandler(new SilentCssErrorHandler());
			webClient.setHTMLParserListener(null);
			webClient.setJavaScriptErrorListener(null);
			webClient.setJavaScriptTimeout(10000);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			HtmlPage page = webClient.getPage(new URL(url));
//			webClient.waitForBackgroundJavaScript(30 * 1000);

			System.out.println(page.asXml());
//			document = Jsoup.parse(page.asXml(), url);

                page.cleanUp();
                page.remove();

                webClient.getCache().clear();
                webClient.close();

//                System.out.println(document.select("#pane-06e42bca-3d5c-4f1c-8094-69c2cb0c08e2-d2020-y2020 > ul > li:nth-child(1) > div.loi__issue.col-lg-9.col-md-8.col-sm-9.col-xs-12 > div.parent-item > a:nth-child(2)"));
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
