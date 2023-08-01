package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PaginationTest {
	public static void main(String[] args) {
		String url = "https://www.eurekalert.org/bysubject/?kw=104&start=10";
//		String url = "https://www.ema.europa.eu/en";

		try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {

			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setTimeout(100 * 1000);
			webClient.getOptions().setAppletEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setCssErrorHandler(new SilentCssErrorHandler());
			webClient.setHTMLParserListener(null);
			webClient.setJavaScriptErrorListener(null);
			webClient.setJavaScriptTimeout(10000);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			HtmlPage page = webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(30 * 1000);
			Map<String, String> validUrlsFromTable = new LinkedHashMap<>();
			test1(page, url, validUrlsFromTable);
			System.out.println(validUrlsFromTable);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void test1(HtmlPage newPage, String url, Map<String, String> validUrlsFromTable) {
		try {
			String attribute = "#main-content > section";
			HtmlElement next = (HtmlElement) newPage.querySelector(
					" #main-content > div.text-center > ul > li:nth-child(4) > a, #main-content > div.text-center > ul > li:nth-child(2) > a");

			Document document = Jsoup.parse(newPage.asXml(), url);

			Map<String, String> scrapedUrl = getURLFromSpecificAttribute(document, attribute, null);
			Map<String, String> latestURL = previousDayLogCheck(scrapedUrl);

			if (scrapedUrl.size() == latestURL.size()) {
				validUrlsFromTable.putAll(scrapedUrl);
				String previousPage = newPage.asText();
				newPage = next.click();
				System.out.println(previousPage);
				System.out.println(
						"*************************************************************************************");
				System.out.println(newPage.asText());
				if (!previousPage.equals(newPage.asText())) {
					test1(newPage, url, validUrlsFromTable);
				}
			} else {
				validUrlsFromTable.putAll(latestURL);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Map<String, String> previousDayLogCheck(Map<String, String> downloadUrls) {
		List<String> downloadedUrls = new ArrayList<>();
		Map<String, String> returnUrl = new LinkedHashMap<>();

		downloadedUrls.add(
				"https://www.fda.gov/news-events/dr-sharplesss-remarks-nih-tobacco-regulatory-science-meeting-10212019");
		downloadedUrls.add(
				"https://www.fda.gov/news-events/speeches-fda-officials/fda-moving-forward-new-era-smarter-public-food-safety-10212019");
		downloadedUrls.add(
				"https://www.fda.gov/news-events/speeches-fda-officials/remarks-lowell-schiller-jd-food-and-drug-law-institutes-annual-conference-medical-product");
		downloadedUrls.add(
				"https://www.fda.gov/news-events/speeches-fda-officials/remarks-dr-sharpless-2019-biopharma-congress-10072019");
		downloadedUrls.add(
				"https://www.fda.gov/news-events/speeches-fda-officials/remarks-dr-susan-mayne-public-meeting-horizontal-approaches-food-standards-identity-modernization");

		for (String key : downloadUrls.keySet()) {
			if (downloadedUrls.contains(key)) {
				break;
			} else {
				returnUrl.put(key, downloadUrls.get(key));
			}
		}
		return returnUrl;
	}

	/**
	 * Returns all absolute URL's from given attributes of document
	 *
	 * @param document
	 * @param attribute
	 * @param descriptionTag
	 * @return Map - URL as key and description as value or Empty Map
	 */
	public static Map<String, String> getURLFromSpecificAttribute(Document document, String attribute,
			String descriptionTag) {
		Map<String, String> urlMap = new LinkedHashMap<>();
		// Removing Jsoup.clean because its removing double space from absolute URL.
		// Source : http://esubmission.ema.europa.eu/cessp/cessp.htm
		try {
			Elements srcElements;
			if (attribute == null || "".equals(attribute)) {
				srcElements = document.getAllElements();
			} else {
				srcElements = document.select(attribute).select("*");
			}
			for (Element element : srcElements.select("a")) {
				if (descriptionTag == null || "".equals(descriptionTag)) {
					if (!element.attr("abs:href").isEmpty() && !element.attr("abs:href").contains(".jpg")
							&& !element.attr("abs:href").contains(".png")) {
						if (!urlMap.containsKey(element.attr("abs:href"))) {
							if (element.attr("title").isEmpty()) {
//                                urlMap.put(Jsoup.clean(element.attr("abs:href"), Whitelist.basic()).replaceAll("&amp;", "&").replaceAll("%20", " "), element.text());
								urlMap.put((element.attr("abs:href")).replaceAll("&amp;", "&").replaceAll("%20", " "),
										element.text());
							} else {
//                                urlMap.put(Jsoup.clean(element.attr("abs:href"), Whitelist.basic()).replaceAll("&amp;", "&").replaceAll("%20", " "),
//                                        element.attr("title"));
								urlMap.put((element.attr("abs:href")).replaceAll("&amp;", "&").replaceAll("%20", " "),
										element.attr("title"));
							}
						} else if (urlMap.containsKey(element.attr("abs:href"))
								&& urlMap.get(element.attr("abs:href")) == null
								|| urlMap.get(element.attr("abs:href")).isEmpty()) {
							if (element.attr("title").isEmpty()) {
//                                urlMap.put(Jsoup.clean(element.attr("abs:href"), Whitelist.basic()).replaceAll("&amp;", "&").replaceAll("%20", " "), element.text());
								urlMap.put((element.attr("abs:href")).replaceAll("&amp;", "&").replaceAll("%20", " "),
										element.text());
							} else {
//                                urlMap.put(Jsoup.clean(element.attr("abs:href"), Whitelist.basic()).replaceAll("&amp;", "&").replaceAll("%20", " "),
//                                        element.attr("title"));
								urlMap.put((element.attr("abs:href")).replaceAll("&amp;", "&").replaceAll("%20", " "),
										element.attr("title"));
							}
						}
					}
				} else {
					urlMap.put(Jsoup.clean(element.attr("abs:href"), Whitelist.basic()).replaceAll("&amp;", "&")
							.replaceAll("%20", " "), element.select(descriptionTag).text());
				}
			}
			return urlMap;
		} catch (Exception ex) {
			System.out.println("Exception occurred [ " + ex + " ]");
		}
		return urlMap;
	}
}
