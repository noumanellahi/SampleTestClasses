package com.mycompany.selenium;

import java.io.File;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeliniumSingelton {
	public static void main(String[] args) {

		String url = "https://www.karolinskadevelopment.com/en/press-releases";
		String tag = "#invdct-iframe,div.view-content";

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
		WebDriverWait wait = new WebDriverWait(webDriver, 20);
		try {
			webDriver.get(url);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag)));
			if (webDriver.findElement(By.cssSelector(tag)).getTagName().equals("iframe")) {
				webDriver.switchTo().frame(webDriver.findElement(By.cssSelector(tag)));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag)));
//				System.out.println(webDriver.findElement(By.cssSelector(tag)).getAttribute("innerHTML"));
//				System.out.println(webDriver.getPageSource());
				Document document = Jsoup.parse(webDriver.getPageSource(), url);
				Elements mainDiv = document.select(tag);
				System.out.println(mainDiv.toString());
			}

//			System.out.println(webDriver.findElement(By.cssSelector("#invdct-iframe")).getAttribute("src"));
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
}
