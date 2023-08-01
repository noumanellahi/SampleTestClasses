package com.mycompany.selenium.onClick;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class HeadlessTagNotFoundIssue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChromeDriver webDriver = null;
		String emailInputTag = null;
		String email = null;
		String confirmEmailTag = null;
		String passwordInputTag = null;
		String password = null;
		String logInButtonTag = null;
		try {
			String url = "https://meetings.asco.org/abstracts-presentations/208990";

			String textTag = "body > asco-root > div > asco-presentation-details > div > div.header-container.mt-3 > h3";

			WebDriverManager.chromedriver().setup();
			String downloadFilepath = "C:\\Users\\NomanAlahi\\Desktop\\chrome_download_test";

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
			options.addArguments("--incognito");
//			options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.merge(capabilities);

			ChromeDriverService driverService = ChromeDriverService.createDefaultService();
			webDriver = new ChromeDriver(driverService, options);

			Map<String, Object> commandParams = new HashMap<>();
			commandParams.put("cmd", "Page.setDownloadBehavior");
			Map<String, String> params = new HashMap<>();
			params.put("behavior", "allow");
			params.put("downloadPath", downloadFilepath);
			commandParams.put("params", params);
			ObjectMapper objectMapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			String command = objectMapper.writeValueAsString(commandParams);
			String u = driverService.getUrl().toString() + "/session/" + webDriver.getSessionId()
					+ "/chromium/send_command";
			HttpPost request = new HttpPost(u);
			request.addHeader("content-type", "application/json");
			request.setEntity(new StringEntity(command));
			httpClient.execute(request);

			WebDriverWait wait = new WebDriverWait(webDriver, 20);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			Actions actions = new Actions(webDriver);

			webDriver.get(url);

			/**
			 * EP log in
			 */

			if (StringUtils.isNotBlank(emailInputTag)) {
				WebElement emailField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTag)));
				emailField.sendKeys(email);
			}

			if (StringUtils.isNotBlank(confirmEmailTag)) {
				WebElement confirmEmailButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(confirmEmailTag)));
				confirmEmailButton.click();
			}

			if (StringUtils.isNotBlank(passwordInputTag)) {
				WebElement passwordField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTag)));
				passwordField.sendKeys(password);
			}

			if (StringUtils.isNotBlank(logInButtonTag)) {
				WebElement logInButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTag)));
				logInButton.click();
			}

//			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(textTag)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(textTag)));

//			Thread.sleep(10000);
			System.out.println("The end");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				webDriver.close();
				webDriver.quit();
				webDriver = null;
			} catch (Exception ex) {
				System.out.println("HERE WE ARE");
				ex.printStackTrace();
			}

		}
	}

}
