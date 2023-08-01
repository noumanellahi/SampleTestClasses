package com.mycompany.selenium.onClick;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindElementByTagType {
	public static void main(String[] args) {

		ChromeDriver webDriver = null;
		try {
			
			String url = "https://apospublications.com/view-pdf/?article=720d041f40534896a9004bf242e4f2e2Q9KiVLx3WNg=";

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
			Actions actions = new Actions(webDriver);
			
			webDriver.get(url);
			System.out.println("First " + webDriver.getWindowHandles().size());
			
			
//			webDriver.switchTo().frame(webDriver
//					.findElement(By.cssSelector("div > div > div > div > div > div > iframe")));
			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.cssSelector("#cc-approve-button-thissite"))).click();
			
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.cssSelector("#main-content > a"))).click();
			
			
			Thread.sleep(10000);

			/** url 1 test
			 * 			List<WebElement> elements = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.cssSelector("#enquiry > table.fmBigTbl > tbody > tr > td > table:nth-child(5) > tbody")))
					.findElements(By.cssSelector("a"));

			for (int i = 0; i < 10; i++) {
				actions.click(wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
								"#enquiry > table.fmBigTbl > tbody > tr > td > table:nth-child(5) > tbody")))
						.findElements(By.cssSelector("a")).get(i)).build().perform();

				WebElement data = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
						"#enquiry > table.fmBigTbl > tbody > tr > td > table:nth-child(5) > tbody > tr:nth-child(2) > td:nth-child(2)")));
				System.out.println(data.getText());
				webDriver.navigate().back();

			}
			 */


			System.out.println("The end");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}
}
