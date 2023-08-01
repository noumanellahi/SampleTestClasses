package com.mycompany.selenium;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvalidTagInXML {
	public static void main(String[] args) {
		String url = "https://registry.healthresearch.ph/index.php/component/healthregistry/?view=research&layout=details&cid=1017";
		String tags = "Title is : rhcpf, #content > div:nth-child(14) > h13";
		Document document = seleniumChromeDriver(url, tags, null);
		if(document != null) {
			System.out.println(document.text());
		} else {
			System.out.println("document is null");
		}
	}

	public static Document seleniumChromeDriver(String url, String tags, Charset charset) {
		Document document = null;

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("safebrowsing.enabled", false);

		ChromeOptions options = new ChromeOptions();
		options = new ChromeOptions();
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
		WebDriver webDriver = new ChromeDriver(options);
		webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		try {
			webDriver.get(url);
			WebDriverWait wait = new WebDriverWait(webDriver, 10);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tags)));
			} catch (Exception ex) {
				boolean flag = false;
				for (String tag : tags.split(",")) {
					try {
						webDriver.findElement(By.cssSelector(tag.trim()));
						flag = true;
						break;
					} catch (Exception e) {
						continue;
					}
				}
				if (!flag) {
					throw new Exception();
				}
			}
			document = Jsoup.parse(webDriver.getPageSource(), url);
			if (charset == null) {
				document = Jsoup.parse(webDriver.getPageSource(), url);
			} else {
				document = Jsoup.parse(new String(webDriver.getPageSource().getBytes(), charset), url);
			}
			webDriver.manage().deleteAllCookies();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
		return document;
	}
}
