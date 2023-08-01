package com.mycompany.selenium;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomComparison {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://exporter.nih.gov/ExPORTER_Catalog.aspx?sid=0&index=1";
		String tag = "#ContentPlaceHolder1_ProjectAbstracts_dgProjectData > tbody";
		String comparisonTag = "#ContentPlaceHolder1_ProjectAbstracts_dgProjectData > tbody > tr > td:nth-child(6)";

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("safebrowsing.enabled", false);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--start-maximized");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--incognito");
		options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 20);
		try {
			webDriver.get(url);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag)));
			Document document = Jsoup.parse(webDriver.getPageSource(), url);
			getMultipleLinkFromTableRow(document, tag, comparisonTag);

			webDriver.manage().deleteAllCookies();
			System.out.println("THE END ..... :)");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

	public static Map<String, String> getMultipleLinkFromTableRow(Document document, String attribute,
			String comparisonTag) {
		Elements tables = document.select(attribute);
		Elements comparisonFields = document.select(comparisonTag).select("td");
		System.out.println(comparisonFields.get(0).text());

		Map<String, String> urlMap = new LinkedHashMap<>();
		try {
			for (Element table : tables) {
				int rowCount = 0;
				for (Element row : table.select("tr")) {
					String description = "";
					for (Element div : row.select("td")) {
						Elements hrefTags = div.select("a").select("*");
						if (hrefTags.isEmpty()) {
							// if div contains any url
							if (!div.text().contains("https") || !div.text().contains("http")) {
								description = description + div.text() + " ";
							}
						}
					}
					for (Element div : row.select("td")) {
						Elements hrefTags = div.select("a").select("*");
						if (!hrefTags.isEmpty()) {
							for (Element tag : hrefTags) {
								// checks the list item have href tag and the URL in it ends with .pdf
								if (!tag.attr("abs:href").isEmpty()) {
									System.out.println(comparisonFields.get(rowCount).text() + " | "
											+ tag.attr("abs:href") + " | " + tag.text() + " " + description);
//									urlMap.put(tag.attr("abs:href"), tag.text() + " " + description);
								}
							}
						}
					}
					rowCount++;
					System.out.println(rowCount);
				}
			}
			return urlMap;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return urlMap;
	}
}
