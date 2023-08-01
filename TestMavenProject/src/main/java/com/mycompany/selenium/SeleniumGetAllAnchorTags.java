package com.mycompany.selenium;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SeleniumGetAllAnchorTags {

	public static void main(String[] args) {
		ChromeDriver webDriver = null;
		try {
			// TODO Auto-generated method stub
			String url = "https://sciendo.com/journal/ABCSJ";

			String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			webDriver = getwebDriver();

			WebDriverWait wait = new WebDriverWait(webDriver, 20);

			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			Actions actions = new Actions(webDriver);

			webDriver.get(url);

			jsExecutor.executeScript(
					"(function(){x=document.querySelectorAll('*');for(i=0;i<x.length;i++){elementStyle=getComputedStyle(x[i]);if(elementStyle.position=='fixed'||elementStyle.position=='sticky'){x[i].remove();}}}())");
			jsExecutor.executeScript(
					"(function() {l = document.getElementsByTagName(\"a\"); for (var i =0; i<l.length; i++) {l[i].href = \"\"; } }())");

			Thread.sleep(2000);
			/**
			 * Single file download on one hover
			 */
			WebElement hoverButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#__next > div.cover-bg > div.searchTop > div > div.Product_seriesMiddle__21pyN.Product_productMiddleGrid__GGL-6 > div.Product_seriesProduct__3aOWQ.Product_grid-cell-1__2GdDW > div > div.Product_seriesProductDetailSection__sZEb5 > div.Product_seriesProductButtonsPanel__1KrNw > div:nth-child(3) > div:nth-child(1) > button")));

			// Creating object of an Actions class
			actions.moveToElement(hoverButton).perform();
			Thread.sleep(2000);
			List<WebElement> downloadButton = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(
							"#popup-4 > div.Buttons_popup-options__1mdZJ > div.Buttons_popup-options__body__o6zYE > ul")));
			List<WebElement> anchorTags = new ArrayList<WebElement>();
			List<String> hrefs = new ArrayList<String>();

			for (int i = 0; i < downloadButton.size(); i++) {
				if (downloadButton.get(i).getTagName().equals("a")) {
					anchorTags.add(downloadButton.get(i));
				} else {
					anchorTags.addAll(downloadButton.get(i).findElements(By.tagName("a")));
				}
			}

//			for (WebElement anchorTag : anchorTags) {
//				if (StringUtils.isNotBlank(anchorTag.getAttribute("href"))) {
//					hrefs.add(anchorTag.getAttribute("href"));
//				}
//			}

			for (int i = 0; i < anchorTags.size(); i++) {
				if (StringUtils.isNotBlank(anchorTags.get(i).getAttribute("href"))) {
					String downloadUrl = anchorTags.get(i).getAttribute("href");
					System.out.println(downloadUrl);
					webDriver.get(downloadUrl);
					Thread.sleep(5000);
				} else {
					if (i != 0) {
						actions.moveToElement(hoverButton).perform();
					}
					anchorTags.get(i).click();
					Thread.sleep(5000);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

	public static ChromeDriver getwebDriver() throws ClientProtocolException, IOException {
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
		ChromeDriver webDriver = new ChromeDriver(driverService, options);

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

		return webDriver;
	}

}
