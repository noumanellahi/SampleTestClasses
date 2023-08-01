package com.mycompany.selenium.onClick;

import java.util.ArrayList;
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

public class Recursive {
	public static void main(String[] args) {

		String url = "https://law.go.kr/LSW/lsAstSc.do?tabMenuId=tab6#cptOfiAll";

		List<String> crawlingLevelDetails = new ArrayList<String>();
		
		crawlingLevelDetails.add("#resultTable > tbody");
		crawlingLevelDetails.add("#viewHeightDiv > table > tbody");
		crawlingLevelDetails.add("#sideCenter > h2");
		

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
			System.out.println("First " + webDriver.getWindowHandles().size());
			List<WebElement> clickThis = wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.cssSelector("#resultTable > tbody > tr > td.ctn1 > a")));

			for (WebElement element : clickThis) {
				actions.keyDown(Keys.LEFT_CONTROL).click(element).build().perform();
				System.out.println("Second " + webDriver.getWindowHandles().size());

				if (webDriver.getWindowHandles().size() > 1) {
					webDriver.switchTo().window((new ArrayList<String>(webDriver.getWindowHandles()).get(1)));
					WebElement data = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#conTop > h2")));
					System.out.println(data.getText());
					webDriver.close();
					webDriver.switchTo().window((new ArrayList<String>(webDriver.getWindowHandles()).get(0)));
				}
			}

			System.out.println("The end");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

	public void crawl(WebDriver webDriver, int crawlLevels, String clickableTag) {

	}
}
