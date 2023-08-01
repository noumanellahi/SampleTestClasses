package com.mycompany.selenium;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TagsNotFoundErrorCapture {

	public static void main(String[] args) {
		String url = "http://www.pharmatimes.com/news";
		String tagsInclude = "#txtDrugSearch";
		String imageTags = "";
		
		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		int seleniumTimeOut = 20; // Seconds
		int seleniumSleepTime = 20000; // Mili-seconds

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
		
		try {
		WebDriver webDriver = new ChromeDriver(options);
		Actions action = new Actions(webDriver);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		Document document = null;
		try {
			webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
			webDriver.get(url);
			if (StringUtils.isNotBlank(tagsInclude)) {
				WebDriverWait wait = new WebDriverWait(webDriver, seleniumTimeOut);
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(tagsInclude)));
				if (StringUtils.isNotBlank(imageTags)) {
					try {
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(imageTags)));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} else {
				Thread.sleep(seleniumSleepTime);
			}

			document = Jsoup.parse(webDriver.getPageSource(), url);

			webDriver.manage().deleteAllCookies();

		} catch (Exception ex) {
			System.out.println("Not able to load url");
			ex.printStackTrace();
		} finally {
			if (webDriver != null) {
				webDriver.close();
				webDriver.quit();
				webDriver = null;
			}
		} } catch(Exception ex) {
			System.out.println("Not able to start chrome");
			ex.printStackTrace();
		}
	}

}
