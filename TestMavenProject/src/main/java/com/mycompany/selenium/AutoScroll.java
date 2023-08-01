package com.mycompany.selenium;

import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoScroll {
	public static void main(String[] args) {
		String url = "https://www.trialregister.nl/trials";
		String tag = "#root > div > div > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(2) > div.result-list-container";
		String sample = "a";

//		String url = "https://news.cision.com/?i=07000000&i=07000000";
//		String tag = "#item-listing-container";
//		String sample = "b";

//		String url = "https://smallcaps.com.au/biotech";
//		String tag = "#td-outer-wrap > div > div.td-container.td-category-container > div > div > div";
//		String loadMoreButtonTag = "#td-outer-wrap > div > div.td-container.td-category-container > div > div > div > div > div.td-load-more-wrap.td-load-more-infinite-wrap > a";
//		String sample = "c";

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
//		options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 30);

		try {
			webDriver.get(url);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag)));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			long lastHeight = (long) ((JavascriptExecutor) webDriver)
					.executeScript("return document.body.scrollHeight");
			int count = 1;
			int startIndex = 0;
			int endIndex = 0;
			while (true && count <= 2) {
				System.out.println(count);
				webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);

				switch (sample) {
				case "a":
					Thread.sleep(3000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Loading...")));
					break;
				case "b":
					Thread.sleep(5000);
					break;
				case "c":
//					Thread.sleep(3000);
//					try {
//						jsExecutor.executeScript("arguments[0].click();", webDriver.findElement(By.cssSelector(
//								loadMoreButtonTag)));
//						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(loadMoreButtonTag)));
//						break;
//					} catch (Exception ex) {
//
//					}
				}

				long newHeight = (long) ((JavascriptExecutor) webDriver)
						.executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				} else {
					List<WebElement> webElements = webDriver.findElement(By.cssSelector(tag))
							.findElements(By.xpath(".//*"));
					startIndex = endIndex;
					System.out.println("Size of list is " + webElements.size());
					endIndex = webElements.size() - 1;
					getUrls(startIndex, endIndex, webElements, url);
					lastHeight = newHeight;
					count++;
				}
			}
			webDriver.manage().deleteAllCookies();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

	public static void getUrls(int startIndex, int endIndex, List<WebElement> webElements, String url) {
		try {
			for (int i = startIndex; i <= endIndex; i++) {
				try {
					if (webElements.get(i).getTagName().equals("a")) {
						if(webElements.get(i).getText().contains("\\n")) {
							String description = webElements.get(i).getText().replaceAll("\\n", "_");
							String fileName = description.substring(0, description.indexOf("_"));
							System.out.println(webElements.get(i).getAttribute("href"));
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
