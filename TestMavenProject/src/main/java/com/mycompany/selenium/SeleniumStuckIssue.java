package com.mycompany.selenium;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumStuckIssue {
	public static void main(String[] args) {
//			System.out.println("Attempt No : " + i);
			WebDriver webDriver = null;
			try {
				String url = "https://pharmaphorum.com/category/press-releases/";

				String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox"); // Bypass OS security model
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--start-maximized");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--incognito");
				options.addArguments("--headless");

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				webDriver = new ChromeDriver(capabilities);
				for (int i = 1; i <= 200; i++) {
				webDriver.get(url);
				System.out.println("Done Getting URL source" + i);
				}
			} catch (Exception ex) {

			} finally {
				if (webDriver != null) {
					webDriver.close();
					webDriver.quit();
					webDriver = null;
				}
		}
	}
}
