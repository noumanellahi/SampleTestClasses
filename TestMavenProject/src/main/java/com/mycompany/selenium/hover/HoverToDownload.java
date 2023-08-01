package com.mycompany.selenium.hover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class HoverToDownload {
	public static void main(String[] args) {
		ChromeDriver webDriver = null;
		try {
			String url = "https://sciendo.com/journal/ABCSJ";

			String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";

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
			options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);

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

			jsExecutor.executeScript(
					"(function(){x=document.querySelectorAll('*');for(i=0;i<x.length;i++){elementStyle=getComputedStyle(x[i]);if(elementStyle.position=='fixed'||elementStyle.position=='sticky'){x[i].remove();}}}())");
			jsExecutor.executeScript(
					"(function() {l = document.getElementsByTagName(\"a\"); for (var i =0; i<l.length; i++) {l[i].href = \"\"; } }())");

			/**
			 * Single file download on one hover
			 */
			WebElement hoverButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#__next > div.cover-bg > div.searchTop > div > div.Product_seriesMiddle__21pyN.Product_productMiddleGrid__GGL-6 > div.Product_seriesProduct__3aOWQ.Product_grid-cell-1__2GdDW > div > div.Product_seriesProductDetailSection__sZEb5 > div.Product_seriesProductButtonsPanel__1KrNw > div:nth-child(1) > div:nth-child(1) > button")));

			// Creating object of an Actions class
			actions.moveToElement(hoverButton).perform();

			WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#popup-1 > div.Buttons_popup-options__1mdZJ > div.Buttons_popup-options__body__o6zYE > ul > li > a")));

			downloadButton.click();

			Thread.sleep(5000);
			/**
			 * Multiple file downloads on single hover
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}
}
