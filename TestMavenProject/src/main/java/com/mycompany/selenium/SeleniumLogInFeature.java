package com.mycompany.selenium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumLogInFeature {
	public static void main(String[] args) {
//		String url1 = "https://www.sciencedirect.com/";
//		String url2 = "https://journals.co.za/content/journal/neotest/browse";
//		String url3 = "https://pacificarchaeology.org/index.php/journal/login";
		String url3 = "https://twitter.com/i/flow/login";

//		String logInPageButtonTagCase1 = "#gh-signin-btn";
//		String emailInputTagCase1 = "#bdd-email";
//		String emailSubmitButtonTagCase1 = "#bdd-elsPrimaryBtn";
//		String passwordInputTagCase1 = "#bdd-password";
//		String logInButtonTagCase1 = "#bdd-elsPrimaryBtn";
//		String emailCase1 = "rommel.sioson@clarivate.com";
//		String passwordCase1 = "@Clarivate2019";

//		String logInPageButtonTagCase2 = "#sign-in > ul:nth-child(2) > li.list-group-item.hidden-xs.hidden-sm > h3 > a";
		String emailInputTagCase2 = "div > input";
		String emailSubmitButtonTagCase2 = "#layers > div > div > div > div > div > div > div.css-1dbjc4n.r-1awozwy.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv > div.css-1dbjc4n.r-1867qdf.r-1wbh5a2.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1.r-htvplk.r-1udh08x > div > div > div.css-1dbjc4n.r-kemksi.r-6koalj.r-16y2uox.r-1wbh5a2 > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1jgb5lz.r-1ye8kvj.r-13qz1uu > div > div > div > div:nth-child(6) > div";
		String userNamenIputTagCase2 = "div > input";
		String userNameSubmitButtonTagCase2 = "#layers > div > div > div > div > div > div > div.css-1dbjc4n.r-1awozwy.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv > div.css-1dbjc4n.r-1867qdf.r-1wbh5a2.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1.r-htvplk.r-1udh08x > div > div > div.css-1dbjc4n.r-kemksi.r-6koalj.r-16y2uox.r-1wbh5a2 > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1jgb5lz.r-1ye8kvj.r-13qz1uu > div.css-1dbjc4n.r-1isdzm1 > div > div > div > div > div";
		String passwordInputTagCase2 = "#layers > div > div > div > div > div > div > div.css-1dbjc4n.r-1awozwy.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv > div.css-1dbjc4n.r-1867qdf.r-1wbh5a2.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1.r-htvplk.r-1udh08x > div > div > div.css-1dbjc4n.r-kemksi.r-6koalj.r-16y2uox.r-1wbh5a2 > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1jgb5lz.r-1ye8kvj.r-13qz1uu > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1dqxon3 > div > div > div.css-1dbjc4n.r-mk0yit.r-13qz1uu > div > label > div > div.css-1dbjc4n.r-18u37iz.r-16y2uox.r-1wbh5a2.r-1wzrnnt.r-1udh08x.r-xd6kpl.r-1pn2ns4.r-ttdzmv > div.css-901oao.r-1awozwy.r-6koalj.r-37j5jr.r-1inkyih.r-16dba41.r-135wba7.r-bcqeeo.r-13qz1uu.r-qvutc0 > input";
		String logInButtonTagCase2 = "#layers > div > div > div > div > div > div > div.css-1dbjc4n.r-1awozwy.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv > div.css-1dbjc4n.r-1867qdf.r-1wbh5a2.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1.r-htvplk.r-1udh08x > div > div > div.css-1dbjc4n.r-kemksi.r-6koalj.r-16y2uox.r-1wbh5a2 > div.css-1dbjc4n.r-16y2uox.r-1wbh5a2.r-1jgb5lz.r-1ye8kvj.r-13qz1uu > div.css-1dbjc4n.r-1isdzm1 > div > div.css-1dbjc4n > div > div > div";
		String emailCase2 = "nomanellahi@yahoo.com";
		String userNameCase2 = "noman_ellahi";
		String passwordCase2 = "03337419004";

//		String emailInputTagCase3 = "div > input";
//		String passwordInputTagCase3 = "div > input";
//		String logInButtonTagCase3 = "#login > fieldset > div.buttons > button";
//		String emailCase3 = "nomanellahi@yahoo.com";
//		String passwordCase3 = "03337419004";

		WebDriverManager.chromedriver().setup();

		String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test";
		File path = new File("C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test");
		long size;
		long reSize;

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
		Actions action = new Actions(webDriver);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			webDriver.get(url3);

//			//case1
//			if (StringUtils.isNotBlank(logInPageButtonTagCase1)) {
//				WebElement logInPageButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInPageButtonTagCase1)));
//				logInPageButton.click();
//			}
//
//			if (StringUtils.isNotBlank(emailInputTagCase1)) {
//				WebElement userName = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTagCase1)));
//				userName.sendKeys(emailCase1);
//			}
//
//			if (StringUtils.isNotBlank(emailSubmitButtonTagCase1)) {
//				WebElement userNameSubmitButton = wait.until(
//						ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailSubmitButtonTagCase1)));
//				userNameSubmitButton.click();
//			}
//
//			if (StringUtils.isNotBlank(passwordInputTagCase1)) {
//				WebElement password = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTagCase1)));
//				password.sendKeys(passwordCase1);
//			}
//
//			if (StringUtils.isNotBlank(logInButtonTagCase1)) {
//				WebElement logInButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTagCase1)));
//				logInButton.click();
//			}

			// Case 2
//			if (StringUtils.isNotBlank(logInPageButtonTagCase2)) {
//				WebElement logInPageButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInPageButtonTagCase2)));
//				logInPageButton.click();
//			}

			if (StringUtils.isNotBlank(emailInputTagCase2)) {
				WebElement userName = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTagCase2)));
				userName.sendKeys(emailCase2);
			}

			if (StringUtils.isNotBlank(emailSubmitButtonTagCase2)) {
				WebElement emailSubmitButton = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailSubmitButtonTagCase2)));
				emailSubmitButton.click();
			}

			Thread.sleep(2000);

			if (StringUtils.isNotBlank(userNamenIputTagCase2)) {
				WebElement userName = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(userNamenIputTagCase2)));
				userName.sendKeys(userNameCase2);
			}

			if (StringUtils.isNotBlank(userNameSubmitButtonTagCase2)) {
				WebElement userNameSubmitButton = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector(userNameSubmitButtonTagCase2)));
				userNameSubmitButton.click();
			}

			Thread.sleep(2000);

			if (StringUtils.isNotBlank(passwordInputTagCase2)) {
				WebElement password = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordInputTagCase2)));
				password.sendKeys(passwordCase2);
			}

			if (StringUtils.isNotBlank(logInButtonTagCase2)) {
				WebElement logInButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logInButtonTagCase2)));
				logInButton.click();
			}

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

			Thread.sleep(5000);

			String cookies = cookieCreator(webDriver.manage().getCookies());
			String user_agent = (String) jsExecutor.executeScript("return navigator.userAgent;");

			System.out.println(user_agent);

			URL webURL = new URL("https://pacificarchaeology.org/index.php/journal/article/download/299/385/");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) webURL.openConnection();
			httpUrlConnection.setRequestProperty("User-Agent", user_agent);
			httpUrlConnection.setRequestProperty("Cookie", cookies);
			System.out.println(httpUrlConnection.getHeaderFields());

//			BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			final byte[] inputStreamAsBytes = IOUtils.toByteArray(httpUrlConnection.getInputStream());
			try (FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\Noman.Alahi\\Desktop\\chrome_download_test\\test.pdf")) {
				fos.write(inputStreamAsBytes);
			}
			System.out.println("THE END ..... :)");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}

	public static String cookieCreator(Set<Cookie> cookies) {
		return cookies.stream().map(cookie -> cookie.getName() + "=" + cookie.getValue())
				.collect(Collectors.joining(";"));

	}
}
