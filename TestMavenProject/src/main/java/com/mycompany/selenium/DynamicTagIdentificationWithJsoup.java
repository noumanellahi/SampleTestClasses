package com.mycompany.selenium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicTagIdentificationWithJsoup {
	public static void main(String[] args) {

		String url = "https://journals.sagepub.com/doi/full/10.1177/0046958021991276";
		String tag = "#\\32 5e8d4fe-c5d2-4686-bdef-3b125e01db4b > div > div > div:nth-child(6) > div > div.hlFld-Title > div > h1";
		String jsoupTag = "#\\\\32 5e8d4fe-c5d2-4686-bdef-3b125e01db4b > div > div > div:nth-child(6) > div > div.hlFld-Title > div > h1";

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
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			webDriver.get(url);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag)));

			Document document = Jsoup.parse(webDriver.getPageSource(), url);

			if (tag.startsWith("#")) {
				getDynamicTagsForJsoup(jsoupTag,document);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

	private static void getDynamicTagsForJsoup(String tagsPath, Document document) {
		List<String>  splitPath = Arrays.asList(tagsPath.split(">")) ;
		String id = splitPath.get(0).trim();
		System.out.println(id);
		splitPath.remove(0);
		String joinedString = String.join(">", splitPath);
		
		System.out.println("By ID here" + document.getElementById(id));

	}
}
