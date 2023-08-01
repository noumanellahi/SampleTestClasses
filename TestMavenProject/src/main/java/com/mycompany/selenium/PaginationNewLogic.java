package com.mycompany.selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginationNewLogic {
	public static void main(String[] args) {

		/**
		 * Usecase one
		 */
//		String url = "https://jrct.niph.go.jp/search?page=1";
//		String paginatorTag = "body > div > form.form-horizontal > div.panel.panel-default > div.text-center > ul";
//		String tagInclude = "body > div > form.form-horizontal > div.panel.panel-default > div.panel-body > table > tbody";

		/**
		 * Usecase two
		 */
		String url = "http://www.abxis.com/eng/cscenter/notice.jsp";
		String paginatorTag = "#container > div > div.text-center > nav > ul";
		String tagInclude = "#container > div > table > tbody";
		
		/**
		 * Usecase three
		 */
//		String url = "https://cris.nih.go.kr/cris/board/listNoticeBoard.do?board_seq=1";
//		String paginatorTag = "#paging";
//		String tagInclude = "#dataList";
		
		/**
		 * Old usecase
		 */
//		String url = "https://www.fda.gov/news-events/speeches-fda-officials";
//		String paginatorTag = "#DataTables_Table_0_paginate > ul";
//		String tagInclude = "#DataTables_Table_0 > tbody";

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";
		File path = new File("C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test");
		long size;
		long reSize;

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
			paginator(webDriver, wait, paginatorTag, tagInclude, 1);
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

	public static void paginator(WebDriver webDriver, WebDriverWait wait, String paginatorTag, String tagInclude,
			int counter) {
		try {
			String url2 = "https://www.emerald.com/insight/publication/issn/1366-6282/vol/13/iss/4";
//			System.out.println("Page NUmber " + counter);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(paginatorTag)));
			List<WebElement> paginator = webDriver.findElement(By.cssSelector(paginatorTag))
					.findElements(By.xpath(".//*"));

			WebElement nextButton = null;
			WebElement tmpNextButton = null;
			boolean numberButtonFlag = false;
			for (WebElement element : paginator) {
				try {
					String elementText = element.getText();
					if (numberButtonFlag && buttonTextCheck(elementText)) {
						if (element.getTagName().equals("a")) {
							nextButton = element;
						} else {
							nextButton = element.findElement(By.tagName("a"));
						}
						try {
							if (nextButton.getAttribute("href").endsWith("#none")) {
								nextButton = null;
								continue;
							}
						} catch (Exception ex) {

						}
						break;
					} else if (elementText.matches("-?\\d+(\\.\\d+)?")) {
						numberButtonFlag = true;
						if (element.getTagName().equals("a") && elementText.contains(String.valueOf(counter + 1))) {
							tmpNextButton = element;
						}
					}
				} catch (Exception ex) {
//					System.out.println("Not have any anchor tag");
				}
			}

			if (nextButton != null || tmpNextButton != null) {
				if (nextButton == null && tmpNextButton != null) {
					nextButton = tmpNextButton;
				}
				String currentPageText = webDriver.findElement(By.cssSelector(tagInclude)).getText();
//				System.out.println(counter + " -> " + currentPageText);

				try {
					jsExecutor.executeScript("arguments[0].click();", nextButton);
				} catch (WebDriverException clickException) {
					jsExecutor.executeScript("arguments[0].click();", nextButton);
				}

				if (!currentPageText.equals(webDriver.findElement(By.cssSelector(tagInclude)).getText())) {
					counter++;

					paginator(webDriver, wait, paginatorTag, tagInclude, counter);
				}
			} else {
				String currentPageText = webDriver.findElement(By.cssSelector(tagInclude)).getText();
//				System.out.println(counter + " -> " + currentPageText);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean buttonTextCheck(String elementText) {
		if (StringUtils.isBlank(elementText)) {
			return true;
		} else if (elementText.equals("...")) {
			return false;
		} else if (elementText.equals("…")) {
			return false;
		} else if (elementText.matches("-?\\d+(\\.\\d+)?")) {
			return false;
		} else if (elementText.toLowerCase().contains("previous")) {
			return false;
		} else if (elementText.toLowerCase().contains("first")) {
			return false;
		} else if (elementText.toLowerCase().contains("last")) {
			return false;
		} else if (elementText.toLowerCase().contains("<")) {
			return false;
		}
		return true;
	}
}
