package com.mycompany.selenium.onClick;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

public class DynamicFlow {
	private static String[] DOWNLOAD_FILE_EXTENSION = new String[] { "crdownload" };
	private static String OPEN_IN_NEW_TAB = "OPEN_IN_NEW_TAB";
	private static String OPEN_IN_SAME_TAB = "OPEN_IN_SAME_TAB";
	private static String PAGE_NAVIGATION = "PAGE_NAVIGATION";

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
			/**
			 * NL use case
			 */
//			String url = "https://mijnoctrooi.rvo.nl/fo-eregister-view/#/query/KChyZWNodHNvb3J0Ok5QICkgQU5EIChwdWJsaWNhdGllZGF0dW06WzIwMjItMDctMDEgVE8gMjAyMi0wNy0yMV0gT1IgaW5zY2hyaWp2aW5nc2RhdHVtOlsyMDIyLTA3LTAxIFRPIDIwMjItMDctMjFdIE9SIHZlcmxlbmluZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSBPUiB2b29ycmFuZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSApKSBBTkQgcGF0ZW50UmVjb3JkU2VxOjE=";

			// new window de usecase
			String url = "https://onlineweb.dkpto.dk/pvsonline/Varemaerke?action=104&sagID=FA%202001%2000023";

			Map<String, String> tags = new LinkedHashMap<String, String>();

//			tags.put("td:nth-child(2) > div > a", OPEN_IN_NEW_TAB);
//			tags.put(
//					"#Main > div.WrapperTabs > div.easyui-tabs.tabs-container > div.tabs-header > div.tabs-wrap > ul > li.tabs-last",
//					PAGE_NAVIGATION);
//			tags.put(
//					"#documentsGrid > div > div > div > div.datagrid-view2 > div.datagrid-header > div > table > tbody > tr > td:nth-child(1) > div > input[type=checkbox]",
//					PAGE_NAVIGATION);
//			tags.put("#generatePDF > a", PAGE_NAVIGATION);
//			tags.put("#ConfirmBtn", PAGE_NAVIGATION);

			/**
			 * LU use case
			 */
//			String url = "https://patent.public.lu/fo-eregister-view/search#/query/KChyZWNodHNvb3J0Ok5QICkgQU5EIChwdWJsaWNhdGlvbkdyYW50RGF0ZTpbMjAyMi0xMC0wMSBUTyAyMDIyLTEwLTExXSBPUiB2ZXJ2YWxkYXR1bTpbMjAyMi0xMC0wMSBUTyAyMDIyLTEwLTExXSBPUiBwdWJsaWNhdGlvbkRhdGU6WzIwMjItMTAtMDEgVE8gMjAyMi0xMC0xMV0gT1IgdmVybGVuaW5nc2RhdHVtOlsyMDIyLTEwLTAxIFRPIDIwMjItMTAtMTFdIE9SIHZvb3JyYW5nc2RhdHVtOlsyMDIyLTEwLTAxIFRPIDIwMjItMTAtMTFdICkpIEFORCBwYXRlbnRSZWNvcmRTZXE6MQ==";
//
//			Map<String, String> tags = new LinkedHashMap<String, String>();
//			tags.put("td:nth-child(2) > div > a", OPEN_IN_NEW_TAB);
//			tags.put(
//					"#Main > div.WrapperTabs > div.easyui-tabs.tabs-container > div.tabs-header > div.tabs-wrap > ul > li.tabs-last",
//					PAGE_NAVIGATION);
//			tags.put("tr > td:nth-child(2) > div > a", PAGE_NAVIGATION);

			/**
			 * EP use case
			 */
//			String url = "https://publication-bdds.apps.epo.org/raw-data/oidc/login";
//
//			emailInputTag = "#idp-discovery-username";
//			email = "nikola.stanic@clarivate.com";
//			confirmEmailTag = "#idp-discovery-submit";
//
//			passwordInputTag = "#okta-signin-password";
//			password = "new@Cl@r!vate!P22";
//			logInButtonTag = "#okta-signin-submit";
//
//			Map<String, String> tags = new LinkedHashMap<String, String>();
//			tags.put("div.d-flex.justify-content-between.align-items-center.w-100 > div > button", OPEN_IN_SAME_TAB);
//			tags.put("\r\n" + 
//					"#root > div:nth-child(1) > div > div:nth-child(2) > section > div > div:nth-child(5) > div:nth-child(3) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(4) > button", PAGE_NAVIGATION);
//			tags.put(" div > button:nth-child(2)", PAGE_NAVIGATION);

			/**
			 * Download in new window
			 */
//			String url = "https://onlineweb.dkpto.dk/pvsonline/Patent?action=104&sagID=PA%202021%2000015";
//
//			Map<String, String> tags = new LinkedHashMap<String, String>();
//
//			tags.put(
//					"#midte1 > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr:nth-child(6) > td > table > tbody > tr > td:nth-child(3) > input",
//					PAGE_NAVIGATION);

			/**
			 * I-FRAME ISSUE
			 */
//			String url = "https://ijdvl.com/articles/ahead-of-print/";
//
//			Map<String, String> tags = new LinkedHashMap<String, String>();
//
//			tags.put("div > div > p > a:nth-child(4)", PAGE_NAVIGATION);

			/*
			 * Open PDF in new window
			 */
			tags.put(
					"#midte1 > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr:nth-child(6) > td > table > tbody > tr > td:nth-child(3) > input",
					PAGE_NAVIGATION);

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

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(tags.keySet().toArray()[0].toString())));

			List<WebElement> elements1 = webDriver.findElements(By.cssSelector(tags.keySet().toArray()[0].toString()));
			traverser(webDriver, wait, actions, jsExecutor, elements1, tags, 0, 0, downloadFilepath);

			Thread.sleep(10000);
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

	public static void traverser(WebDriver webDriver, WebDriverWait wait, Actions actions,
			JavascriptExecutor jsExecutor, List<WebElement> elements, Map<String, String> tags, int level,
			int windowNumber, String downloadFilepath) throws InterruptedException {
		int counter = 0;
		for (WebElement element : elements) {
			if (level == tags.size() - 1) {
//				actions.keyDown(Keys.LEFT_CONTROL).click(element).build().perform();

				try {
					String description = (String) jsExecutor.executeScript("return jQuery(arguments[0]).text();",
							element);
				} catch (Exception ex) {
					System.out.println("NOT ABLE TO EXTRACT DESCRIPTION TEXT FROM DOWNLOAD BUTTON");
				}

				jsExecutor.executeScript("arguments[0].click();", element);
				do {
					Thread.sleep(3000);
				} while (!FileUtils.listFiles(new File(downloadFilepath), DOWNLOAD_FILE_EXTENSION, false).isEmpty());
				{
					System.out.println(counter + "Download Complete");
				}
			} else {
				if (tags.get(tags.keySet().toArray()[level]).toString().equalsIgnoreCase(OPEN_IN_NEW_TAB)) {
					actions.keyDown(Keys.LEFT_CONTROL).click(element).build().perform();

					if (webDriver.getWindowHandles().size() > windowNumber + 1) {
						webDriver.switchTo()
								.window((new ArrayList<String>(webDriver.getWindowHandles()).get(windowNumber + 1)));
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.cssSelector(tags.keySet().toArray()[level + 1].toString())));
						List<WebElement> subElements = webDriver
								.findElements(By.cssSelector(tags.keySet().toArray()[level + 1].toString()));
						if (level + 1 < tags.size()) {
							traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1,
									windowNumber + 1, downloadFilepath);
						}
						webDriver.close();
						webDriver.switchTo().window((new ArrayList<String>(webDriver.getWindowHandles()).get(0)));
					}
				} else if (tags.get(tags.keySet().toArray()[level]).toString().equalsIgnoreCase(OPEN_IN_SAME_TAB)) {
					if (counter > 0) {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.cssSelector(tags.keySet().toArray()[level].toString())));
						element = webDriver.findElements(By.cssSelector(tags.keySet().toArray()[level].toString()))
								.get(counter);
					}

					jsExecutor.executeScript("arguments[0].click();", element);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(tags.keySet().toArray()[level + 1].toString())));
					List<WebElement> subElements = webDriver
							.findElements(By.cssSelector(tags.keySet().toArray()[level + 1].toString()));
					if (level + 1 < tags.size()) {
						traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1, windowNumber + 1,
								downloadFilepath);
					}
					webDriver.navigate().back();
				} else {
					jsExecutor.executeScript("arguments[0].click();", element);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(tags.keySet().toArray()[level + 1].toString())));
					List<WebElement> subElements = webDriver
							.findElements(By.cssSelector(tags.keySet().toArray()[level + 1].toString()));
					traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1, windowNumber,
							downloadFilepath);
				}

			}
			counter++;
		}
	}
}
