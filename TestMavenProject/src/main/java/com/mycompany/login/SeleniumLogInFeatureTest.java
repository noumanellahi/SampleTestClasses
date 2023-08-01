package com.mycompany.login;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
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

public class SeleniumLogInFeatureTest {
	public static void main(String[] args) {
		String url1 = "https://www.sciencedirect.com/journal/fuel/articles-in-press";
//		String url2 = "https://journals.co.za/content/journal/neotest/browse";
//		String url3 = "https://pacificarchaeology.org/index.php/journal/login";

		String logInPageButtonTagCase1 = "#gh-signin-btn";
		String emailInputTagCase1 = "#bdd-email";
		String emailSubmitButtonTagCase1 = "#bdd-elsPrimaryBtn";
		String passwordInputTagCase1 = "#bdd-password";
		String logInButtonTagCase1 = "#bdd-elsPrimaryBtn";
		String emailCase1 = "rommel.sioson@clarivate.com";
		String passwordCase1 = "@Clarivate2019";

//		String logInPageButtonTagCase2 = "#sign-in > ul:nth-child(2) > li.list-group-item.hidden-xs.hidden-sm > h3 > a";
//		String emailInputTagCase2 = "#signname";
//		String emailSubmitButtonTagCase2 = "";
//		String passwordInputTagCase2 = "#signpsswd";
//		String logInButtonTagCase2 = "#form-signin > fieldset > div:nth-child(3) > button";
//		String emailCase2 = "neotest";
//		String passwordCase2 = "neotest03ia";

//		String emailInputTagCase3 = "#username";
//		String passwordInputTagCase3 = "#password";
//		String logInButtonTagCase3 = "#login > fieldset > div.buttons > button";
//		String emailCase3 = "clarivate_a";
//		String passwordCase3 = "jpa_guest";

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

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

			WebDriverWait wait = new WebDriverWait(webDriver, 50);
			Actions action = new Actions(webDriver);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;

			webDriver.get(url1);

//			//case1
			if (StringUtils.isNotBlank(logInPageButtonTagCase1)) {
				WebElement logInPageButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInPageButtonTagCase1)));
				logInPageButton.click();
			}

			if (StringUtils.isNotBlank(emailInputTagCase1)) {
				WebElement userName = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTagCase1)));
				userName.sendKeys(emailCase1);
			}

			if (StringUtils.isNotBlank(emailSubmitButtonTagCase1)) {
				WebElement userNameSubmitButton = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailSubmitButtonTagCase1)));
				userNameSubmitButton.click();
			}

			if (StringUtils.isNotBlank(passwordInputTagCase1)) {
				WebElement password = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTagCase1)));
				password.sendKeys(passwordCase1);
			}

			if (StringUtils.isNotBlank(logInButtonTagCase1)) {
				WebElement logInButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTagCase1)));
				logInButton.click();
			}

			// Case 2
//			if (StringUtils.isNotBlank(logInPageButtonTagCase2)) {
//				WebElement logInPageButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInPageButtonTagCase2)));
//				logInPageButton.click();
//			}
//
//			if (StringUtils.isNotBlank(emailInputTagCase2)) {
//				WebElement userName = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTagCase2)));
//				userName.sendKeys(emailCase2);
//			}
//
//			if (StringUtils.isNotBlank(emailSubmitButtonTagCase2)) {
//				WebElement userNameSubmitButton = wait.until(
//						ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailSubmitButtonTagCase2)));
//				userNameSubmitButton.click();
//			}
//
//			if (StringUtils.isNotBlank(passwordInputTagCase2)) {
//				WebElement password = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTagCase2)));
//				password.sendKeys(passwordCase2);
//			}
//
//			if (StringUtils.isNotBlank(logInButtonTagCase2)) {
//				WebElement logInButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTagCase2)));
//				logInButton.click();
//			}
//
//			if (StringUtils.isNotBlank(emailInputTagCase3)) {
//				WebElement userName = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTagCase3)));
//				userName.sendKeys(emailCase3);
//			}
//
//			if (StringUtils.isNotBlank(passwordInputTagCase3)) {
//				WebElement password = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTagCase3)));
//				password.sendKeys(passwordCase3);
//			}
//
//			if (StringUtils.isNotBlank(logInButtonTagCase3)) {
//				WebElement logInButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTagCase3)));
//				logInButton.click();
//			}

//			Thread.sleep(5000);
//
//			String user_agent = (String) jsExecutor.executeScript("return navigator.userAgent;");
//			String cookies = cookieCreator(webDriver.manage().getCookies());
//
//			System.out.println(user_agent);
//			System.out.println(cookies);

//			URL webURL = new URL(
//					"https://www.sciencedirect.com/science/article/pii/S0016236121003963/pdfft?md5=c32789de1e21c0c9819071922df3de65&pid=1-s2.0-S0016236121003963-main.pdf");
//			HttpURLConnection httpUrlConnection = (HttpURLConnection) webURL.openConnection();
//			
//			httpUrlConnection.setRequestMethod("GET");
//			httpUrlConnection.setRequestProperty("user-agent", user_agent);
//			httpUrlConnection.setRequestProperty("cookie", cookies);
//			
//			if(httpUrlConnection.getHeaderField("Location") != null) {
//				System.out.println("LOcation is " + httpUrlConnection.getHeaderField("Location"));
//			}
//			
//			
//			System.out.println(getFileExtension(httpUrlConnection));
//			System.out.println("THE END ..... :)");
			String url = "https://www.sciencedirect.com/science/article/pii/S0016236120329781/pdfft?md5=e770dc0b47fb4d61ca9426be4548acce&pid=1-s2.0-S0016236120329781-main.pdf";
			webDriver.get(url);

			do {
				Thread.sleep(200);
			} while (!FileUtils.listFiles(path, new String[] { "crdownload" }, false).isEmpty());
			
			
			System.out.println("1"+ webDriver.getCurrentUrl());
			
			Thread.sleep(10000);
			
			System.out.println("1" + webDriver.getCurrentUrl());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
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
