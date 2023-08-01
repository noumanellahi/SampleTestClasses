package com.mycompany.selenium.onClick;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Overlay {

	public static void main(String[] args) {

		String url = "https://www.altrubio.com/abgenomics/en/news";

		String mainTag = "ul.tab-box";
		String overlayTag = "#news > article > div > section.show-content.anchor_top > article:nth-child(2) > b";

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

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
//		options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 20);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		Actions actions = new Actions(webDriver);
		try {
			webDriver.get(url);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(mainTag)));

			List<WebElement> webElements = webDriver.findElement(By.cssSelector(mainTag)).findElements(By.tagName("a"));

			for (WebElement item : webElements) {
				try {
//					System.out.println("*******************************************************************************");
					System.out.println((String) jsExecutor.executeScript("return jQuery(arguments[0]).text();", item));
//					item.click();
//					WebElement clickTHis = wait.until(ExpectedConditions
//							.visibilityOfElementLocated(By.cssSelector(overlayTag)));
//					System.out.println(clickTHis.getText());
					System.out
							.println("*******************************************************************************");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			System.out.println("The end");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

}
