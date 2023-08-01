package com.mycompany.selenium.searchform;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchFormDropDown {
	public static void main(String[] args) {

//		String url = "https://investors.vrtx.com/press-releases";
//		String url = "https://euclinicaltrials.eu/app/#/search?lang=en";
		String url = "https://www.vendorportal.ecms.va.gov/nac/Pharma/List";

		WebDriverManager.chromedriver().setup();

		String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";
		File path = new File("C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test");
		long size;
		long reSize;

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

		WebDriver webDriver = new ChromeDriver(capabilities);
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		webDriver.get(url);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
//			String tag = "#edit-field-nir-news-category-target-id";
//			WebElement dropDown = webDriver.findElement(By.cssSelector("#edit-field-nir-news-category-target-id"));
//			jsExecutor.executeScript(
//					"var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }",
//					dropDown, "4031");

//			String script =
//			        "function selectOption(s) {\r\n" +
//			        "   var sel = document.querySelector('"+ tag +"');\r\n" +
//			        "   for (var i = 0; i < sel.options.length; i++)\r\n" +
//			        "   {\r\n" +
//			        "       if (sel.options[i].text.indexOf(s) > -1)\r\n" +
//			        "       {\r\n" +
//			        "           sel.options[i].selected = true;\r\n" +
//			        "           break;\r\n" +
//			        "       }\r\n" +
//			        "   }\r\n" +
//			        "}\r\n" +
//			        "return selectOption('" + "General Business" + "');";

//			jsExecutor.executeScript(script);

//			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", dropDown, "style", "");
//			new Select(dropDown).selectByVisibleText("General Business");

//			new Select(dropDown).selectByValue("4021");
//			wait.until(ExpectedConditions
//					.presenceOfAllElementsLocatedBy(By.cssSelector("#edit-field-nir-news-category-target-id")));
//
//			webDriver
//					.findElement(By.cssSelector(
//							"#edit_field_nir_news_category_target_id_chosen > ul > li.search-field > input[type=text]"))
//					.click();
//
//			WebElement dropDown = webDriver.findElement(By.cssSelector(
//					"#edit_field_nir_news_category_target_id_chosen > ul > li.search-field > input[type=text]"));
//
//			dropDown.sendKeys("Financial Results");
//
//			webDriver.findElement(By.cssSelector("#edit_field_nir_news_category_target_id_chosen > div > ul > li"))
//					.click();

//
//			wait.until(ExpectedConditions
//					.presenceOfAllElementsLocatedBy(By.cssSelector("#edit-field-nir-news-category-target-id")));
//			
//			jsExecutor.executeScript("arguments[0].click();",
//					webDriver.findElement(By.cssSelector("#edit_field_nir_news_date_value_min_chosen > a")));
//
//			WebElement dropDown = webDriver.findElement(By.cssSelector("#edit-field-nir-news-category-target-id"));
//			new Select(dropDown).selectByIndex(1);

//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
//					"body > app-root > div > ct-search > ct-tabs > div.tabs-body > ct-search-criteria > ct-panel > div > div.content > form > a")))
//					.click();
//
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
//					"body > app-root > div > ct-search > ct-tabs > div.tabs-body > ct-search-criteria > ct-panel > div > div.content > form > div:nth-child(5) > div > div:nth-child(2) > div:nth-child(6) > ng-select > div > div > div.ng-input > input[type=text]")))
//			.sendKeys("Male");
//			System.out.println("done");

			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cboContractNumbers")))
					.sendKeys("36E79718C0007");

//			webDriver.manage().deleteAllCookies();

		} catch (Exception ex) {
			ex.printStackTrace();
//			jsExecutor.executeScript("arguments[0].click();",
//					webDriver.findElement(By.cssSelector("#facetfield_SubjectCode_Economic_Research_and_Reports")));
//			System.out.println(webDriver.getPageSource());
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}
}
