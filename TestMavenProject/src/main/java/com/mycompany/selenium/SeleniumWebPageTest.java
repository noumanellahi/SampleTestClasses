package com.mycompany.selenium;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWebPageTest {
	public static void main(String[] args) {
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
		ChromeDriver driver = null;
		try {
//			String url = "https://vpb.lrv.lt/";
			String url = "https://www.google.com/";
			WebDriverManager.chromedriver().setup();

			String downloadFilepath = "C:\\Users\\NomanAlahi\\Desktop\\chrome_download_test";

			long size;
			long reSize;

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("plugins.always_open_pdf_externally", true);
			chromePrefs.put("safebrowsing.enabled", false);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox"); // Bypass OS security model
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--start-maximized");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--incognito");
			options.addArguments("user-agent=" + userAgent);
//			options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.merge(capabilities);

			ChromeDriverService driverService = ChromeDriverService.createDefaultService();
			driver = new ChromeDriver(driverService, options);

//			Map<String, Object> commandParams = new HashMap<>();
//			commandParams.put("cmd", "Page.setDownloadBehavior");
//			Map<String, String> params = new HashMap<>();
//			params.put("behavior", "allow");
//			params.put("downloadPath", downloadFilepath);
//			commandParams.put("params", params);
//			ObjectMapper objectMapper = new ObjectMapper();
//			HttpClient httpClient = HttpClientBuilder.create().build();
//			String command = objectMapper.writeValueAsString(commandParams);
//			String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId()
//					+ "/chromium/send_command";
//			HttpPost request = new HttpPost(u);
//			request.addHeader("content-type", "application/json");
//			request.setEntity(new StringEntity(command));
//			httpClient.execute(request);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			Actions actions = new Actions(driver);

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			jsExecutor.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
			driver.get(url);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("asdadas")));

		} catch (TimeoutException ex) {
			ex.printStackTrace();

		} finally {
			if (driver != null) {
//				driver.close();
//				driver.quit();
//				driver = null;
			}
		}
	}
}
