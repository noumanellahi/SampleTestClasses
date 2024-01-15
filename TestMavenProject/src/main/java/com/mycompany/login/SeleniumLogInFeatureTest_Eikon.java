package com.mycompany.login;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumLogInFeatureTest_Eikon {
	public static void main(String[] args) {
		String url = "https://amers2.apps.cp.thomsonreuters.com/web/peopledirectory/";


		String emailTag = "#AAA-AS-SI1-SE003";
		String passwordTag = "#AAA-AS-SI1-SE006";
		String logInButtonTag = "#AAA-AS-SI1-SE014";
		String email = "shabna.kareti@clarivate.com";
		String password = "Yash2007*";

//		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
		WebDriverManager.chromedriver().setup();

		String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";
		File path = new File("C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test");
		long size;
		long reSize;

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

		ChromeDriverService driverService = ChromeDriverService.createDefaultService();
		ChromeDriver webDriver = new ChromeDriver(driverService, options);
		try {
			Map<String, Object> commandParams = new HashMap<>();
			commandParams.put("cmd", "Page.setDownloadBehavior");
			Map<String, String> params = new HashMap<>();
			params.put("behavior", "allow");
			params.put("downloadPath", downloadFilepath);
			commandParams.put("params", params);
			ObjectMapper objectMapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			String command = objectMapper.writeValueAsString(commandParams);
			String u = driverService.getUrl().toString() + "/session/" + webDriver.getSessionId()
					+ "/chromium/send_command";
			HttpPost request = new HttpPost(u);
			request.addHeader("content-type", "application/json");
			request.setEntity(new StringEntity(command));
			httpClient.execute(request);

			WebDriverWait wait = new WebDriverWait(webDriver, 20);
			Actions action = new Actions(webDriver);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;

			webDriver.get(url);

			if (StringUtils.isNotBlank(emailTag)) {
				WebElement userName = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailTag)));
				userName.sendKeys(email);
			}

			if (StringUtils.isNotBlank(passwordTag)) {
				WebElement passwordElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordTag)));
				passwordElement.sendKeys(password);
			}

			if (StringUtils.isNotBlank(logInButtonTag)) {
				WebElement logInButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTag)));
				logInButton.click();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

	public static String getFileExtension(HttpURLConnection httpUrlConnection) {
		String contentDispositionHeaderFieldValue = "";
		String fileName = "";
		String extension = "";
		List<String> contentDispositionHeaderName = new ArrayList<String>(
				Arrays.asList("Content-Disposition", "Content-disposition", "content-disposition"));

		List<String> headerTypeForHtml = new ArrayList<String>(
				Arrays.asList("Content-Type", "Content-type", "content-type"));

		MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
		try {
			// Get file extension from Content-Disposition header
			if (StringUtils.isEmpty(extension)) {
				// Get Content-Disposition header value from URL
				for (String headerName : contentDispositionHeaderName) {
					if (httpUrlConnection.getHeaderField(headerName) != null) {
						contentDispositionHeaderFieldValue = httpUrlConnection.getHeaderField(headerName);
						break;
					}
				}

				// Get file name from Content-Disposition header value
				if (StringUtils.isNotEmpty(contentDispositionHeaderFieldValue)) {
					fileName = getFileNameFromContentDisposition(contentDispositionHeaderFieldValue.toLowerCase());
				}

				// Get file extension from file name
				if (StringUtils.isNotEmpty(fileName.trim())) {
					extension = fileName;
				}
			}

			// Get file extension from Apachi Tika
			if (StringUtils.isEmpty(extension)) {

				/**
				 * Get extension using content type header.
				 */
				for (String headerName : headerTypeForHtml) {
					if (httpUrlConnection.getHeaderField(headerName) != null) {
						extension = allTypes.forName(
								MediaType.parse(httpUrlConnection.getHeaderField(headerName)).getBaseType().toString())
								.getExtension();
						break;
					}
				}

				/**
				 * convert the input stream to TikaInputStream detect the media type and then
				 * get extension.
				 */
				if (StringUtils.isEmpty(extension)) {
					InputStream is = httpUrlConnection.getInputStream();
					TikaInputStream tis = TikaInputStream.get(is);
					extension = allTypes.forName((new Tika()).detect(tis)).getExtension();

					tis.close();
					is.close();
				}
				if (extension.equalsIgnoreCase(".eml")) {
					extension = ".mht";
				}
			}
			httpUrlConnection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return extension;
	}

	public static String getFileNameFromContentDisposition(String contentDispositionValue) {
		int index = contentDispositionValue.indexOf("filename=");
		String fileName = "";
		if (index >= 0) {
			fileName = contentDispositionValue.substring(index + 9, contentDispositionValue.length());
		}
		fileName = fileName.replaceAll(";|\"", "");
		return fileName;
	}

	public static String cookieCreator(Set<Cookie> cookies) {

		return cookies.stream().map(cookie -> cookie.getName() + "=" + cookie.getValue())
				.collect(Collectors.joining(";"));

	}
}
