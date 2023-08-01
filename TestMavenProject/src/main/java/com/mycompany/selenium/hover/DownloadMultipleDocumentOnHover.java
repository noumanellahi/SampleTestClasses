package com.mycompany.selenium.hover;

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

public class DownloadMultipleDocumentOnHover {

	public static void main(String[] args) {
		ChromeDriver webDriver = null;
		try {
			// TODO Auto-generated method stub
			String url = "https://sciendo.com/journal/ABCSJ";

			String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";

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
			options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.merge(capabilities);
			webDriver = new ChromeDriver(options);

			WebDriverWait wait = new WebDriverWait(webDriver, 20);

			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			Actions actions = new Actions(webDriver);
			jsExecutor.executeScript("window.open()");

			ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
			webDriver.switchTo().window(tabs.get(1));

			webDriver.get(url);

			jsExecutor.executeScript(
					"(function(){x=document.querySelectorAll('*');for(i=0;i<x.length;i++){elementStyle=getComputedStyle(x[i]);if(elementStyle.position=='fixed'||elementStyle.position=='sticky'){x[i].remove();}}}())");
			jsExecutor.executeScript(
					"(function() {l = document.getElementsByTagName(\"a\"); for (var i =0; i<l.length; i++) {l[i].href = \"\"; } }())");

			Thread.sleep(2000);
			/**
			 * Single file download on one hover
			 */
			WebElement hoverButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#__next > div.cover-bg > div.searchTop > div > div.Product_seriesMiddle__21pyN.Product_productMiddleGrid__GGL-6 > div.Product_seriesProduct__3aOWQ.Product_grid-cell-1__2GdDW > div > div.Product_seriesProductDetailSection__sZEb5 > div.Product_seriesProductButtonsPanel__1KrNw > div:nth-child(3) > div:nth-child(1) > button")));

			// Creating object of an Actions class
			actions.moveToElement(hoverButton).perform();

			List<WebElement> downloadButton = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(
							"#popup-4 > div.Buttons_popup-options__1mdZJ > div.Buttons_popup-options__body__o6zYE > ul")));
			List<WebElement> anchorTags = new ArrayList<WebElement>();

			for (int i = 0; i < downloadButton.size(); i++) {
				if (downloadButton.get(i).getTagName().equals("a")) {
					anchorTags.add(downloadButton.get(i));
				} else {
					anchorTags.addAll(downloadButton.get(i).findElements(By.tagName("a")));
				}
			}

			for (int i = 0; i < anchorTags.size(); i++) {
				if (i != 0) {
					actions.moveToElement(hoverButton).perform();
				}
				anchorTags.get(i).click();
				Thread.sleep(5000);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

}
