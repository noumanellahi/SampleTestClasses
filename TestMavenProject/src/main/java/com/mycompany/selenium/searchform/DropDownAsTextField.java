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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownAsTextField {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		String url = "https://selecthealth.rxeob.com/mdb/public/router?account=rxs_t3_ut_ds";

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
		WebDriverWait wait = new WebDriverWait(webDriver, 60);
		webDriver.get(url);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#leftSide > div > div > div.columns > div > section > div > div > div.control.has-icons-left.is-clearfix > input")));
			searchBar.sendKeys("ABILIFY 10 MG TABLET");

			Thread.sleep(2000);
			
			webDriver
					.findElement(By.cssSelector(
							"#leftSide > div > div > div.columns > div > section > div > p:nth-child(4) > button"))
					.click();

			System.out.println("here it is");

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
