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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickToDownload {
	public static void main(String[] args) {
		ChromeDriver driver = null;
		try {
			String url = "https://mijnoctrooi.rvo.nl/fo-eregister-view/search/details/1034749_NP/0/0/1/10/0/1/0/null_en_null/KChyZWNodHNvb3J0Ok5QICkgQU5EIChwdWJsaWNhdGllZGF0dW06WzIwMjItMDctMDEgVE8gMjAyMi0wNy0yMV0gT1IgaW5zY2hyaWp2aW5nc2RhdHVtOlsyMDIyLTA3LTAxIFRPIDIwMjItMDctMjFdIE9SIHZlcmxlbmluZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSBPUiB2b29ycmFuZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSApKSBBTkQgcGF0ZW50UmVjb3JkU2VxOjE=#";

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
			options.addArguments("--incognito");
//			options.addArguments("--headless");
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

			WebDriverWait wait = new WebDriverWait(driver, 30);
			driver.get(url);

			//select tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#Main > div.WrapperTabs > div.easyui-tabs.tabs-container > div.tabs-header > div.tabs-wrap > ul > li.tabs-last")));
			driver.findElement(By.cssSelector(
					"#Main > div.WrapperTabs > div.easyui-tabs.tabs-container > div.tabs-header > div.tabs-wrap > ul > li.tabs-last"))
					.click();
			Thread.sleep(2000);

			//select all
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#documentsGrid > div > div > div > div.datagrid-view2 > div.datagrid-header > div > table > tbody > tr > td:nth-child(1) > div > input[type=checkbox]")));
			driver.findElement(By.cssSelector(
					"#documentsGrid > div > div > div > div.datagrid-view2 > div.datagrid-header > div > table > tbody > tr > td:nth-child(1) > div > input[type=checkbox]"))
					.click();
			Thread.sleep(5000);

			//generate pdf
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#generatePDF > a")));
			driver.findElement(By.cssSelector(
					"#generatePDF > a"))
					.click();
			Thread.sleep(5000);
			
			//confirm download
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#ConfirmBtn")));
			driver.findElement(By.cssSelector(
					"#ConfirmBtn"))
					.click();
			Thread.sleep(5000);

			Thread.sleep(10000);

//			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//
//			WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.cssSelector("#MainForm > fieldset > div:nth-child(10) > div > input")));
//			searchButton.click();
//
//			WebElement downloadDialogButton = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#save-list-link")));
//			downloadDialogButton.click();

//			WebElement downloadZipButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.cssSelector("#downloadAdvancedForm > ul > li:nth-child(1) > input[type=submit]")));
//			WebElement downloadZipButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.cssSelector("#submit-download-list")));
//			downloadZipButton.click();
//
//			System.out.print("Downloading");
//			do {
//				System.out.print(".");
//				Thread.sleep(2000);
//			} while (!org.apache.commons.io.FileUtils.listFiles(path, new String[] { "crdownload" }, false).isEmpty());
//			{
//				System.out.println("\nDownload completed");
//			}
//			do {
//				System.out.print(".");
//				size = FileUtils.sizeOfDirectory(path);
//				Thread.sleep(2000);
//				reSize = FileUtils.sizeOfDirectory(path);
//			} while (size != reSize);
//			{
//				System.out.println("\nDownload completed");
//			}

		} catch (Exception ex) {
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
