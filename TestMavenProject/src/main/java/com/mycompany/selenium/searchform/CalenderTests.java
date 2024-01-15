package com.mycompany.selenium.searchform;

import java.io.File;
import java.util.HashMap;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderTests {

	public static void main(String[] args) {
		ChromeDriver webDriver = null;
		try {
			// TODO Auto-generated method stub

//			String url = "https://www.in.gov.br/leiturajornal?data=11-12-2021&secao=do1";

//			String url = "https://patent.public.lu/fo-eregister-view/search";

//			String url = "https://teollisoikeuslehdet.prh.fi/en/trademarkgazette";

			String url = "https://ipsearch.saip.gov.sa/wopublish-search/public/patents?1&lang=en#";

//			String advanceSearch = "#accordionDiv > div > div.panel-header.accordion-header > div.panel-tool > a.accordion-collapse.accordion-expand";

			WebDriverManager.chromedriver().setup();

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

			WebElement advanceSearch = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("body > div.wrapper > div:nth-child(1) > ul  > li:nth-child(2) > a")));

			advanceSearch.click();

			WebElement calander = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#advancedInputWrapper > div:nth-child(8) > div > div > div:nth-child(2 ) > input:nth-child(1)")));

			calander.sendKeys("2023.10.16 TO 2023.11.21");
			
			WebElement apply = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("body > div:nth-child(24) > div.ui-helper-clearfix > div > button.ui-priority-primary")));

			apply.click();
			
			WebElement search = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#advanceSearchButton")));

			search.click();

//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
//					"#searchCriteriaForm > div.table-control.removeLeftPadding > div:nth-child(1) > div:nth-child(7) > div.table-control > div:nth-child(2) > div > div.col-md-3.col-sm-2.col-xs-2.removeLeftPadding.removeRightPadding > input[type=hidden]:nth-child(4)")))
//					.sendKeys("31/01/2000");

//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
//					"#accordionDiv > div > div.panel-header.accordion-header > div.panel-tool > a.accordion-collapse.accordion-expand")))
//					.click();
//
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#_easyui_textbox_input3")))
//					.sendKeys("01/10/2022");
//
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#_easyui_textbox_input4")))
//					.sendKeys("01/11/2022");
//
//			wait.until(ExpectedConditions
//					.presenceOfElementLocated(By.cssSelector("#searchCriteria > div.Buttons>button:nth-child(1)")))
//					.click();

//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#data-secao")))
//					.sendKeys("11/12/2021");

//			Thread.sleep(10000);
//			jsExecutor.executeScript("document.getElementById('classifiedFromDate').value='10/22/2021'");
//			jsExecutor.executeScript("document.getElementById('classifiedToDate').value='10/28/2021'");

//			WebElement fromDate = wait.until(ExpectedConditions
//					.presenceOfElementLocated(By.cssSelector("#searchCriteriaForm > div.table-control.removeLeftPadding > div:nth-child(1) > div:nth-child(7) > div.table-control > div:nth-child(1) > div.table-control > div.col-md-3.col-sm-2.col-xs-2.removeLeftPadding > div > input[type=hidden]:nth-child(4)")));
//			jsExecutor.executeScript("arguments[0].value='10/21/2021'", fromDate);
//			
//			WebElement toDate = wait.until(ExpectedConditions
//					.presenceOfElementLocated(By.cssSelector("#searchCriteriaForm > div.table-control.removeLeftPadding > div:nth-child(1) > div:nth-child(7) > div.table-control > div:nth-child(2) > div > div.col-md-3.col-sm-2.col-xs-2.removeLeftPadding.removeRightPadding > input[type=hidden]:nth-child(4)")));
//			jsExecutor.executeScript("arguments[0].value='10/28/2021'", toDate);

//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#searchCriteriaForm > div.table-control.removeLeftPadding > div:nth-child(1) > div:nth-child(7) > div.table-control > div:nth-child(1) > div.table-control > div.col-md-3.col-sm-2.col-xs-2.removeLeftPadding > div > input[type=hidden]:nth-child(4)")))
//					.sendKeys("10/21/2021");
//			
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#searchCriteriaForm > div.table-control.removeLeftPadding > div:nth-child(1) > div:nth-child(7) > div.table-control > div:nth-child(2) > div > div.col-md-3.col-sm-2.col-xs-2.removeLeftPadding.removeRightPadding > input[type=hidden]:nth-child(4)")))
//			.sendKeys("10/28/2021");

//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btnSubmit"))).click();

//			WebElement toDate = wait.until(ExpectedConditions
//					.presenceOfElementLocated(By.cssSelector("#fechaHastaInput")));
//			toDate.click();
//			toDate.sendKeys("19/10/2021");
//
//			WebElement searchButton = wait.until(ExpectedConditions
//					.presenceOfElementLocated(By.cssSelector("#btnBusquedaAvanzada")));
//			searchButton.click();

			System.out.println("done");
			webDriver.manage().deleteAllCookies();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

}
