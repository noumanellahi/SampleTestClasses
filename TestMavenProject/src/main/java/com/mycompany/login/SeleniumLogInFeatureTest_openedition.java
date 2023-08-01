package com.mycompany.login;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumLogInFeatureTest_openedition {
	public static void main(String[] args) {
		String url1 = "https://journals.openedition.org/communiquer/lodel";
		;

		String emailInputTag = "#login";
		String email = "Clarivate";
		String passwordInputTag = "#passwd";
		String password = "Clarivate2019!";
		String logInButtonTag = "#loginscreen > form > fieldset > p > input";

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
//		options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		options.merge(capabilities);
		WebDriver webDriver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(webDriver, 50);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			webDriver.get(url1);

			if (StringUtils.isNotBlank(emailInputTag)) {
				WebElement emailField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTag)));
				emailField.sendKeys(email);
			}

			if (StringUtils.isNotBlank(passwordInputTag)) {
				WebElement passwordField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTag)));
				passwordField.sendKeys(password);
			}

			if (StringUtils.isNotBlank(logInButtonTag)) {
				WebElement logInButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTag)));
				logInButton.click();
			}

			Thread.sleep(5000);

			String user_agent = (String) jsExecutor.executeScript("return navigator.userAgent;");
			String cookies = cookieCreator(webDriver.manage().getCookies());

			System.out.println(user_agent);
			System.out.println(cookies);

			URL webURL = new URL("https://journals.openedition.org/communiquer/pdf/7396");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) webURL.openConnection();
			httpUrlConnection.setRequestProperty("User-Agent", user_agent);
			httpUrlConnection.setRequestProperty("cookie", cookies);
			System.out.println(getFileExtension(httpUrlConnection));
			System.out.println("THE END ..... :)");

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
