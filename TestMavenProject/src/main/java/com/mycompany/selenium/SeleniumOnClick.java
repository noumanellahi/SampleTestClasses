package com.mycompany.selenium;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumOnClick {
	public static void main(String[] args) {
		String url = "https://client.formularynavigator.com/Search.aspx?siteCode=0690010599";

		// Level 0 Tags
		String textInput = "#txtDrugSearch";
		String textInputValue = "Enbrel";
		String searchButton = "#btnDrugSearch";

		// Level 1 Tags
		String onClickArea = "#divDrugList";

		// Level 2 Tags
		String popUpButton = "#tblResults > tbody > tr:nth-child(2) > td:nth-child(5) > span:nth-child(1) > a";
		String scrapText = "#divPopup";

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

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 50);
		Actions action = new Actions(webDriver);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			webDriver.get(url);

			String currentUrl = url;
			// Level 0
			if (StringUtils.isNotBlank(textInput)) {
				WebElement searchField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(textInput)));
				searchField.sendKeys(textInputValue);
			}

			if (StringUtils.isNotBlank(searchButton)) {
				WebElement search = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(searchButton)));
				search.click();
			}

			// Level 1
			if (StringUtils.isNotBlank(onClickArea)) {
				if (webDriver.getCurrentUrl() != currentUrl) {
					currentUrl = webDriver.getCurrentUrl();
				}
				int totalNumberOfElements = webDriver.findElement(By.cssSelector(onClickArea))
						.findElements(By.tagName("a")).size();
				for (int i = 0; i < totalNumberOfElements; i++) {
					System.out.println("**********Number of click is :" + i + "************");
					webDriver.findElement(By.cssSelector(onClickArea)).findElements(By.tagName("a")).get(i).click();
					WebElement popUp = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(popUpButton)));
					popUp.click();
					System.out.println(webDriver.findElement(By.cssSelector(scrapText)).getText());
					webDriver.navigate().back();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(onClickArea)));
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
}
