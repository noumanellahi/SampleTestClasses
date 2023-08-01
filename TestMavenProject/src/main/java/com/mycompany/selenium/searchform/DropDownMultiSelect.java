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

public class DropDownMultiSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://www.accessdata.fda.gov/scripts/ires/index.cfm";

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

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
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		webDriver.get(url);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			WebElement advanceSearch = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#advancedTab > a")));
			advanceSearch.click();
//			String tag = "#edit-field-nir-news-category-target-id";
			WebElement dropDown = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#productType")));

			new Select(dropDown).selectByVisibleText("Biologics");

			System.out.println("done");
			webDriver.manage().deleteAllCookies();

		} catch (Exception ex) {
			ex.printStackTrace();
//			jsExecutor.executeScript("arguments[0].click();",
//					webDriver.findElement(By.cssSelector("#facetfield_SubjectCode_Economic_Research_and_Reports")));
//			System.out.println(webDriver.getPageSource());
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

}
