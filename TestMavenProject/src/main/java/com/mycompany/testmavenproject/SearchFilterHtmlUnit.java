/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 *
 * @author Noman.Alahi
 */
public class SearchFilterHtmlUnit {

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

//        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
//        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
//		String url = "https://www.accessdata.fda.gov/scripts/cder/daf/";
//        String url = "https://cris.nih.go.kr/cris/en/search/detail_search.jsp";
//		String url = "https://www.consilium.europa.eu/register/en/content/int?lang=EN&typ=ADV";
//		String url = "https://www.sec.gov/cgi-bin/browse-edgar?company=&CIK=&type=10-K&owner=include&count=40&action=getcurrent";
//		String url = "https://www.globenewswire.com/NewsRoom";
//		String url = "https://projectreporter.nih.gov/reporter.cfm?frs=1&icde=";
//		String url = "https://www.businesswire.com/portal/site/home/template.LOGIN/";
//		String url = "https://www.clinicaltrials.jp/cti-user/trial/Search.jsp";
//		String url = "https://www.fda.gov/news-events/speeches-fda-officials";	
//		String url = "https://european-biotechnology.com/up-to-date/latest-news.html";
		String url = "https://www.b3cnewswire.com/";
		Document document = Jsoup.parse("<html></html>");
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
			webClient.waitForBackgroundJavaScript(30 * 1000);
			
			
			HtmlElement pageNumber = (HtmlElement) page.querySelector("#aidapagesbottom127 > span > a:nth-child(16)");
			page = pageNumber.click();
			
			HtmlElement nextButton = (HtmlElement) page.querySelector("#aidapagesbottom127 > a:nth-child(3)");
			page = nextButton.click();
			
//			ClickableElement clickable = (ClickableElement )page.querySelector("#aidapagesbottom127 > a:nth-child(3)");
			
			document = Jsoup.parse(page.asXml(), url);
			
			System.out.println(document.select("#DataTables_Table_0_wrapper"));

//			HtmlCheckBoxInput checkBox = (HtmlCheckBoxInput) page.querySelector("#facetfield_Icb_8000");
//			page = (HtmlPage) checkBox.setChecked(true);
//
//			HtmlCheckBoxInput checkBox2 = (HtmlCheckBoxInput) page.querySelector("#facetfield_SubjectCode_Company_Announcement");
//			page = (HtmlPage) checkBox2.setChecked(true);
//			
//			HtmlCheckBoxInput checkBox3 = (HtmlCheckBoxInput) page.querySelector("#facetfield_Tag_BANKING");
//			page = (HtmlPage) checkBox3.setChecked(true);

//			HtmlImage checkBox = (HtmlImage) page.querySelector("#divOptionsDEPT > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(2) > img");

//			System.out.println(checkBox);

//			page = (HtmlPage) checkBox.click();

//			checkBox = (HtmlImage) page.querySelector("#divOptionsDEPT > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td:nth-child(2) > img");
//			System.out.println(checkBox);

//			HtmlButton submitButton = (HtmlButton) page.querySelector("#collapseReports > div > form > div:nth-child(7) > div > button:nth-child(1)");
//			HtmlElement submitButton = (HtmlElement) page.querySelector("#rnsSubmitButton");
//			page = submitButton.click();

//			System.out.println(page.asXml());

//			HtmlPage page = webClient.getPage(url);
//			
//			HtmlElement fromDate = (HtmlElement) page
//					.querySelector("#document_date_from_date");
//			fromDate.setTextContent("01/04/2020");
//			
//			HtmlElement toDate = (HtmlElement) page
//					.querySelector("#document_date_from_date");
//			toDate.setTextContent("17/04/2020");
//			
//			HtmlSubmitInput submitButton = (HtmlSubmitInput) page.querySelector("#main-content > div > div > div:nth-child(3) > form > input.btn.btn-primary");
//			
//			page = submitButton.click();
//
//			System.out.println(page.asXml());

//			System.out.println(document.select("body > div > table:nth-child(6)"));

//			HtmlHiddenInput fromDate = (HtmlHiddenInput) page.querySelector("#main-content > div > div > div:nth-child(3) > form > div:nth-child(14) > div > input[type=hidden]:nth-child(4)");
//			fromDate.setValueAttribute("01/04/2020");
//			HtmlHiddenInput toDate = (HtmlHiddenInput) page.querySelector("#main-content > div > div > div:nth-child(3) > form > div:nth-child(15) > div > input[type=hidden]:nth-child(4)");
//			fromDate.setValueAttribute("17/04/2020");
//			HtmlSubmitInput submitButton = (HtmlSubmitInput) page.querySelector("#main-content > div > div > div:nth-child(3) > form > input.btn.btn-primary");

//			page = submitButton.click();

//            webClient.waitForBackgroundJavaScript(40 * 1000);

//            System.out.println(page.asXml());
//            System.out.println("**********************************************************");
//            System.out.println("***********here it is :*********** " + page.getElementById("reportSelectMonth"));
//            HtmlSelect select = (HtmlSelect) page.getElementById("research_step");
//
//            HtmlOption option = select.getOptionByText("Not Yet Recruiting");
//            select.setSelectedAttribute(option, true);
//            System.out.println(select.getOptions());
//            System.out.println(select);
//
//            HtmlElement htmlElement = page.getFirstByXPath("//*[@id=\"searchform\"]/div[1]/input");
//            page = htmlElement.click();

//            HtmlTextInput textField = (HtmlTextInput) page.querySelectorAll("#DrugNameform > div:nth-child(2) > button:nth-child(1)").get(0);

//            System.out.println(textField);

//            HtmlOption option = select.getOptionByValue("2018");
//            select.setSelectedAttribute(option, true);
//            HtmlElement htmlElement = page.getFirstByXPath("//*[@id=\"mp-pusher\"]/div/div/div/div/div[3]/div/form/div[2]/button");
//            page = htmlElement.click();
//
//            HtmlElement input1 = page.getFirstByXPath("//*[@id=\"s_sub_date_s\"]");
//            input1.setAttribute("value", "2019-10-01");
//
//            HtmlElement input2 = page.getFirstByXPath("//*[@id=\"s_sub_date_e\"]");
//            input2.setAttribute("value", "2019-10-09");
//            
//            HtmlElement button = page.getFirstByXPath("//*[@id=\"searchform\"]/div[1]/input");
//            page = button.click();
//
//            System.out.println(page.asXml());
//            
//            document = Jsoup.parse(page.asXml(), url);
//            Elements eles = document.select("a").select("*");
//            for (Element ele : eles){
//                System.out.println(ele.select("a[href]").attr("abs:href"));
//            }
//            document = Jsoup.parse(page.asXml(), url);
			page.cleanUp();
			page.remove();

			webClient.getCache().clear();
			webClient.close();

//            System.out.println(document);
		} catch (Exception ex) {
			System.out.println("Exception : " + ex);
		}
		System.out.println(document.select("body > div > table:nth-child(6)"));
	}
}
