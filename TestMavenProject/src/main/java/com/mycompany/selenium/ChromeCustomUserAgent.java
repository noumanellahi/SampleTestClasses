package com.mycompany.selenium;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mycompany.extension.ScraperConstant;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeCustomUserAgent {

	public static void main(String[] args) {
		ChromeDriver webDriver = null;
		try {
			String url = "https://www.nature.com/npjclimatsci/articles";

			WebDriverManager.chromedriver().setup();
			String downloadFilepath = "C:\\Users\\NomanAlahi\\Desktop\\chrome_download_test";

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

			if (isCustomUserAgentDomain(url)) {
				options.addArguments("user-agent=" + ScraperConstant.Custom_USER_AGENT);
			}

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			options.merge(capabilities);

			ChromeDriverService driverService = ChromeDriverService.createDefaultService();
			webDriver = new ChromeDriver(driverService, options);
			
			webDriver.get(url);
		} catch (Exception ex) {

		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

	public static boolean isCustomUserAgentDomain(String stringUrl) {
		try {
			URL url = new URL(stringUrl);
			if (com.mycompany.extension.ScraperConstant.customUserAgentSources.contains(url.getHost())) {
				System.out.println("SETTING CUSTOM USER AGENT IN CHROME BROWSER FOR URL : " + url + ": "
						+ ScraperConstant.Custom_USER_AGENT);
				return Boolean.TRUE;
			} else {
				System.out.println("SETTING DEFAULT USER AGENT IN CHROME BROWSER FOR URL : " + url + ": "
						+ ScraperConstant.Custom_USER_AGENT);
				return Boolean.FALSE;
			}
		} catch (Exception ex) {
			System.out.println("EXCEPTION OCCURED WHILE GETTING USER AGENT FOR URL : " + stringUrl);
		}
		return Boolean.FALSE;
	}
}
