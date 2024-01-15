package com.mycompany.selenium;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JsPrompt {

	public static void main(String[] args) {

		String url = "https://www.gazette.jpo.go.jp/scciidl020?hyouziFlag=2";

		String chromeDriverPath = "C:\\Users\\NomanAlahi\\.m2\\repository\\webdriver\\chromedriver\\win32\\108.0.5359.71\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

//		WebDriverManager.chromedriver().setup();

		String downloadFilepath = "C:\\Users\\NomanAlahi\\Desktop\\chrome_download_test";

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
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 20);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			webDriver.get(url);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#SearchResult > div > table > tbody > tr:nth-child(8) > td.table_list_center > input"))).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#downloadBtn"))).click();
			try {
				wait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By.cssSelector("#SearchResult > div > table")));
			} catch (Exception ex) {
//				ex.printStackTrace();
			}

			Alert alert = webDriver.switchTo().alert();

			System.out.println("Stop here" + alert.getText());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}

	}

}
