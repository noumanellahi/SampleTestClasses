package com.mycompany.selenium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumGetHtml {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";
		String url = "https://www.ijemst.com/index.php/ijemst/issue/view/52";
		String tagToWaitFor = "#\\32 5e8d4fe-c5d2-4686-bdef-3b125e01db4b > div > div > div.publication-tabs.ja.publication-tabs-dropdown > div > div > div.tab.tab-pane.active > article";
		String fileName = "e-com-test.html";

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("safebrowsing.enabled", false);
//		chromePrefs.put("user-agent",
//				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--start-maximized");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--incognito");
		options.addArguments(
				"user-agent=Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");
//		options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		Actions action = new Actions(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {

			webDriver.get(url);
//			Thread.sleep(10000);
//			String userAgent = (String) ((JavascriptExecutor) webDriver).executeScript("return navigator.userAgent;");
//			System.out.println(userAgent);
//			Document document = Jsoup.parse(webDriver.getPageSource(), url);
//			if (document.select("#list-tipos-artigos > div:nth-child(1) > div:nth-child(30) > h2 > a").first().tagName()
//					.matches("iframe|frame")) {
//				System.out.println("here we are");
//			}

		} catch (TimeoutException ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

	public static void writeTofile(String dataString, String localPath, String fileName) throws IOException {
		FileOutputStream fos = null;
		Writer unicodeFileWriter = null;
		String completeFilePath = localPath + File.separator + fileName;
		try {
			if (!new File(localPath).exists()) {
				makeDir(localPath);
			}
			fos = new FileOutputStream(completeFilePath);
			unicodeFileWriter = new OutputStreamWriter(fos, "UTF-8");
			unicodeFileWriter.write(dataString);
			unicodeFileWriter.flush();
			unicodeFileWriter.close();
			unicodeFileWriter = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fos != null) {
				fos.flush();
				fos.close();
			}
			if (unicodeFileWriter != null) {
				unicodeFileWriter.flush();
				unicodeFileWriter.close();
			}
		}
	}

	public static boolean makeDir(String basePath) {
		try {
			File finalDir = new File(basePath);
			if (!finalDir.exists()) {
				System.out.println(" MAKING NEW DIRECTORY AT PATH [ " + basePath + " ]");
				return finalDir.mkdirs();
			} else {
				System.out.println("DIRECTORY ALREADY EXIST AT PATH [ " + basePath + " ]");
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
