package com.mycompany.selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckDownLoadURL {
	public static void main(String[] args) {
		ChromeDriver driver = null;
		try {
			String url = "https://dbcentre3.jmacct.med.or.jp/jmactr/Default_Eng.aspx";

//			String url = "https://s3.amazonaws.com/www.webacq-sample.com/3_level_crawling/page-1.html";

			String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			String downloadFilepath = "E:\\chrome_download_test";
			File path = new File("E:\\chrome_download_test");

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
			options.addArguments("--incognito");
			options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.merge(capabilities);

			ChromeDriverService driverService = ChromeDriverService.createDefaultService();
			driver = new ChromeDriver(driverService, options);

			Map<String, Object> commandParams = new HashMap<>();
			commandParams.put("cmd", "Page.setDownloadBehavior");
			Map<String, String> params = new HashMap<>();
			params.put("behavior", "allow");
			params.put("downloadPath", downloadFilepath);
			commandParams.put("params", params);
			ObjectMapper objectMapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			String command = objectMapper.writeValueAsString(commandParams);
			String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId()
					+ "/chromium/send_command";
			HttpPost request = new HttpPost(u);
			request.addHeader("content-type", "application/json");
			request.setEntity(new StringEntity(command));
			httpClient.execute(request);

			WebDriverWait wait = new WebDriverWait(driver, 20);
			driver.get(url);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			jsExecutor.executeScript("window.open()");
			driver.switchTo().window((new ArrayList<String>(driver.getWindowHandles()).get(1)));
			driver.get("chrome://downloads");
			driver.switchTo().window((new ArrayList<String>(driver.getWindowHandles()).get(0)));

			WebElement mainElement = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#ctl00_mainContent_GridView1 > tbody")));

			List<WebElement> elementsToClick = mainElement.findElements(
					By.cssSelector("#ctl00_mainContent_GridView1 > tbody > tr > td:nth-child(5) > input"));

//			WebElement mainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
//
//			List<WebElement> elementsToClick = mainElement.findElements(By.cssSelector("a"));

			for (WebElement element : elementsToClick) {
				try {
					element.click();

					System.out.print("Downloading");
					do {
						System.out.print(".");
						Thread.sleep(2000);
					} while (!org.apache.commons.io.FileUtils.listFiles(path, new String[] { "crdownload" }, false)
							.isEmpty());
					{
						System.out.println("\nDownload completed");
					}

					driver.switchTo().window((new ArrayList<String>(driver.getWindowHandles()).get(1)));
					
					
					WebElement manager = wait.until(
							ExpectedConditions.presenceOfElementLocated(By.cssSelector("downloads-manager")));
					WebElement shadow1 = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;",
							manager);

					Thread.sleep(2000);
					WebElement item = shadow1.findElement(By.cssSelector("downloads-item"));
					WebElement shadow3 = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", item);

					Thread.sleep(2000);
					WebElement urlTag = shadow3.findElement(By.cssSelector("a"));
					System.out.println(urlTag.getAttribute("href"));

					driver.switchTo().window((new ArrayList<String>(driver.getWindowHandles()).get(0)));
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println(driver.getPageSource());
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (driver != null) {
				driver.close();
				driver.quit();
				driver = null;
			}
		}
	}
}
