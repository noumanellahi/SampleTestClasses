package com.mycompany.selenium;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomDropDown {
	public static void main(String[] args) {
		String url = "https://www.accesswire.com/newsroom/";
		String tag1 = "#industries";
		String tag2 = "#industries > a:nth-child(5)";

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
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		webDriver.get(url);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		Actions hover = new Actions(webDriver);
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag1)));
			try {
				hover.moveToElement(webDriver.findElement(By.cssSelector(tag1)));
			} catch (WebDriverException clickException) {
				jsExecutor.executeScript("arguments[0].click();", webDriver.findElement(By.cssSelector(tag1)));
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag1)));
			
			try {
				WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tag2)));
				option.click();
			} catch (Exception ex) {
				jsExecutor.executeScript("arguments[0].click();", webDriver.findElement(By.cssSelector(tag2)));
			}


			webDriver.manage().deleteAllCookies();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}
}
