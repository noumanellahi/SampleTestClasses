package com.mycompany.selenium.onClick;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicFlowWithTextComparison {
	private static String[] DOWNLOAD_FILE_EXTENSION = new String[] { "crdownload" };
	private static String OPEN_IN_NEW_TAB = "OPEN_IN_NEW_TAB";
	private static String OPEN_IN_SAME_TAB = "OPEN_IN_SAME_TAB";
	private static String PAGE_NAVIGATION = "PAGE_NAVIGATION";
	private static String FILTER = "FILTER";
	private static String ON_CLICK_DOWNLOAD = "download";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChromeDriver webDriver = null;
		String emailInputTag = null;
		String email = null;
		String confirmEmailTag = null;
		String passwordInputTag = null;
		String password = null;
		String logInButtonTag = null;
		try {
			/**
			 * NL use case
			 */
//			String url = "https://mijnoctrooi.rvo.nl/fo-eregister-view/search/details/1034749_NP/0/0/1/10/0/1/0/null_en_null/KChyZWNodHNvb3J0Ok5QICkgQU5EIChwdWJsaWNhdGllZGF0dW06WzIwMjItMDctMDEgVE8gMjAyMi0wNy0yMV0gT1IgaW5zY2hyaWp2aW5nc2RhdHVtOlsyMDIyLTA3LTAxIFRPIDIwMjItMDctMjFdIE9SIHZlcmxlbmluZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSBPUiB2b29ycmFuZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSApKSBBTkQgcGF0ZW50UmVjb3JkU2VxOjE=";

			/**
			 * KR usecase open in new window
			 */
//			String url = "https://law.go.kr/LSW/lsAstSc.do?tabMenuId=tab6#cptOfiAll";

			/**
			 * keyword use case
			 */
//			String url = "http://www.ftb.com.hr/current-issue";

			/**
			 * aimsciences prod 47753 issue
			 */
//			String url = "https://www.aimsciences.org/mfc/latest";

			/**
			 * JP use case
			 */
//			String url = "https://www.gazette.jpo.go.jp/scciidl020?hyouziFlag=2";

			/**
			 * cz uat 100411 issue
			 */
//			String url = "https://isdv.upv.cz/webapp/!resdb.pta.frm";

			/**
			 * ijic use case
			 */
//			String url = "https://ijic.org/issue/archive";
			String url = "https://ijic.org/165/volume/23/issue/1";

			List<OnClickDetails> tags = loadData();

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
//			options.addArguments("--disable-extensions");
			options.addArguments("--incognito");
//			options.addArguments("--headless");
			options.setExperimentalOption("prefs", chromePrefs);
//			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

			options.merge(capabilities);

			ChromeDriverService driverService = ChromeDriverService.createDefaultService();
			webDriver = new ChromeDriver(driverService, options);

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
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			Actions actions = new Actions(webDriver);

			webDriver.get(url);

			/**
			 * EP log in
			 */

			if (StringUtils.isNotBlank(emailInputTag)) {
				WebElement emailField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailInputTag)));
				emailField.sendKeys(email);
			}

			if (StringUtils.isNotBlank(confirmEmailTag)) {
				WebElement confirmEmailButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(confirmEmailTag)));
				confirmEmailButton.click();
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

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tags.get(0).getOnClickTag())));

			List<WebElement> elements1 = tagsIdentifier(webDriver, wait, tags.get(0).getTextTag(),
					tags.get(0).getOnClickTag(), tags.get(0).getKeyWords(), tags.get(0).getKeywordsType());
			traverser(driverService, webDriver, wait, actions, jsExecutor, elements1, tags, 0, 0, downloadFilepath);

			Thread.sleep(10000);
			System.out.println("The end");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			try {
//				webDriver.close();
//				webDriver.quit();
//				webDriver = null;
//			} catch (Exception ex) {
//				System.out.println("HERE WE ARE");
//				ex.printStackTrace();
//			}

		}
	}

	public static void traverser(ChromeDriverService driverService, WebDriver webDriver, WebDriverWait wait,
			Actions actions, JavascriptExecutor jsExecutor, List<WebElement> elements, List<OnClickDetails> tags,
			int level, int windowNumber, String downloadFilepath) throws InterruptedException {
		int counter = 0;

		if (level > 0) {
			if (tags.get(level).getWebPageLoadTime() != null && Math.round(tags.get(level).getWebPageLoadTime()) > 0) {
				Thread.sleep(Math.round(tags.get(level).getWebPageLoadTime()));
			}
		}

		for (WebElement element : elements) {
			try {
				if (tags.get(level).getOnClickResult().equalsIgnoreCase(ON_CLICK_DOWNLOAD)) {
					try {
						try {
							String description = (String) jsExecutor
									.executeScript("return jQuery(arguments[0]).text();", element);
						} catch (Exception ex) {
							System.out.println("NOT ABLE TO EXTRACT DESCRIPTION TEXT FROM DOWNLOAD BUTTON");
						}

						jsExecutor.executeScript("arguments[0].click();", element);
//						jsExecutor.executeScript("arguments[0].click();", element);
//						element.click();

						System.out.println(driverService.getUrl());
						Alert alert = webDriver.switchTo().alert();
						System.out.println(alert.getText());
						alert.accept();

						do {
							Thread.sleep(3000);
						} while (!FileUtils.listFiles(new File(downloadFilepath), DOWNLOAD_FILE_EXTENSION, false)
								.isEmpty());
						{
							System.out.println(counter + "Download Complete");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else if (tags.get(level).getOnClickResult().equalsIgnoreCase(OPEN_IN_NEW_TAB)) {
					actions.keyDown(Keys.LEFT_CONTROL).click(element).build().perform();

					if (webDriver.getWindowHandles().size() > windowNumber + 1) {
						webDriver.switchTo()
								.window((new ArrayList<String>(webDriver.getWindowHandles()).get(windowNumber + 1)));
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.cssSelector(tags.get(level + 1).getOnClickTag())));
						List<WebElement> subElements = tagsIdentifier(webDriver, wait, tags.get(level + 1).getTextTag(),
								tags.get(level + 1).getOnClickTag(), tags.get(level + 1).getKeyWords(),
								tags.get(level + 1).getKeywordsType());
						if (level + 1 < tags.size()) {
							traverser(driverService, webDriver, wait, actions, jsExecutor, subElements, tags, level + 1,
									windowNumber + 1, downloadFilepath);
						}
						webDriver.close();
						webDriver.switchTo().window((new ArrayList<String>(webDriver.getWindowHandles()).get(0)));
					}
				} else if (tags.get(level).getOnClickResult().equalsIgnoreCase(OPEN_IN_SAME_TAB)) {
					if (counter > 0) {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.cssSelector(tags.get(level + 1).getOnClickTag())));
						element = webDriver.findElements(By.cssSelector(tags.get(level + 1).getOnClickTag()))
								.get(counter);
					}

					jsExecutor.executeScript("arguments[0].click();", element);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(tags.get(level + 1).getOnClickTag())));
					List<WebElement> subElements = tagsIdentifier(webDriver, wait, tags.get(level + 1).getTextTag(),
							tags.get(level + 1).getOnClickTag(), tags.get(level + 1).getKeyWords(),
							tags.get(level + 1).getKeywordsType());
					if (level + 1 < tags.size()) {
						traverser(driverService, webDriver, wait, actions, jsExecutor, subElements, tags, level + 1,
								windowNumber + 1, downloadFilepath);
					}
					webDriver.navigate().back();
				} else if (tags.get(level).getOnClickResult().equalsIgnoreCase(FILTER)) {
					element.click();

					if (counter == elements.size() - 1) {
						List<WebElement> subElements = tagsIdentifier(webDriver, wait, tags.get(level + 1).getTextTag(),
								tags.get(level + 1).getOnClickTag(), tags.get(level + 1).getKeyWords(),
								tags.get(level + 1).getKeywordsType());
						traverser(driverService, webDriver, wait, actions, jsExecutor, subElements, tags, level + 1,
								windowNumber, downloadFilepath);
					}
				} else if (tags.get(level).getOnClickResult().equalsIgnoreCase(PAGE_NAVIGATION)) {
					jsExecutor.executeScript("arguments[0].click();", element);
//					element.click();
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(tags.get(level + 1).getOnClickTag())));

					List<WebElement> subElements = tagsIdentifier(webDriver, wait, tags.get(level + 1).getTextTag(),
							tags.get(level + 1).getOnClickTag(), tags.get(level + 1).getKeyWords(),
							tags.get(level + 1).getKeywordsType());
					traverser(driverService, webDriver, wait, actions, jsExecutor, subElements, tags, level + 1,
							windowNumber, downloadFilepath);
				}
			} catch (UnhandledAlertException ex) {
				ex.printStackTrace();
			}
			counter++;
		}
	}

	public static List<WebElement> tagsIdentifier(WebDriver webDriver, WebDriverWait wait, String textTags,
			String onclickTags, String[] keyWords, String keywordsType) {
		if (textTags == null) {
			return webDriver.findElements(By.cssSelector(onclickTags));
		} else {
			List<WebElement> textElements = webDriver.findElements(By.cssSelector(textTags));

			List<WebElement> onCliElements = webDriver.findElements(By.cssSelector(onclickTags));

			List<WebElement> resultElements = new ArrayList<WebElement>();

			if (textElements.size() == onCliElements.size()) {
				for (int i = 0; i < textElements.size(); i++) {
					System.out.println(textElements.get(i).getText());

					for (int j = 0; j < keyWords.length; j++) {
						if (textElements.get(i).getText().equalsIgnoreCase(keyWords[j])) {
							if (keywordsType.equalsIgnoreCase("include")) {
								resultElements.add(onCliElements.get(i));
								break;
							} else if (keywordsType.equalsIgnoreCase("exclude")) {
								break;
							}
						} else {
							if (j == keyWords.length - 1 && keywordsType.equalsIgnoreCase("exclude")) {
								resultElements.add(onCliElements.get(i));
							}
						}
					}
				}
			}
			return resultElements;
		}
	}

	public static List<OnClickDetails> loadData() {
		List<OnClickDetails> listOfDetails = new ArrayList<OnClickDetails>();

		/**
		 * NL use case config start
		 */

//		listOfDetails.add(new OnClickDetails("ul > li.tabs-last", PAGE_NAVIGATION, 0, null, null, null, null));

		// NL For check box download
//		listOfDetails.add(new OnClickDetails("tr > td:nth-child(1) > div > input[type=checkbox]", FILTER, 1,
//				"tr > td:nth-child(3) > div", new String[] { "Publication", "Document type" }, "Include", null));
//		listOfDetails.add(new OnClickDetails("#generatePDF > a", PAGE_NAVIGATION, 2, null, null, null, null));
//		listOfDetails
//				.add(new OnClickDetails("#ConfirmBtn, #Main > div.WrapperTabs > div.Links > ul > li.DownloadXml > a",
//						PAGE_NAVIGATION, 3, null, null, null, null));

		// NL For INdividual file download
//		listOfDetails.add(new OnClickDetails("tr > td:nth-child(3) > div > a", PAGE_NAVIGATION, 1,
//				"tr > td:nth-child(3) > div > a", new String[]{"Publication"}, "Include", null));
//		listOfDetails.add(
//				new OnClickDetails("body > table > tbody > tr > td:nth-child(5) > a", PAGE_NAVIGATION, 2, null, null, null, null));

		/**
		 * NL use case config end
		 */

		// KR usecase open in new window
//		listOfDetails.add(new OnClickDetails("#resultTable > tbody > tr:nth-child(13) > td.ctn1 > a", OPEN_IN_NEW_TAB,
//				0, null, null, null, null));
//		listOfDetails.add(new OnClickDetails("#bdySaveBtn", PAGE_NAVIGATION, 1, null, null, null, 30000.0));
//		listOfDetails.add(new OnClickDetails("#FileSavePdf1", PAGE_NAVIGATION, 2, null, null, null, 5000.0));
//		listOfDetails.add(
//				new OnClickDetails("#noticeDiv > div.btn > a:nth-child(1)", PAGE_NAVIGATION, 3, null, null, null, 5000.0));

		// Keyword include usecase
//		listOfDetails.add(
//				new OnClickDetails("span > a, strong > a, b > a", PAGE_NAVIGATION, 0, "span > a, strong > a, b > a",
//						new String[] { "Full Text (PDF)", "Supplement", "Abstract (Croatian)" }, "Include", null));

		/**
		 * aimsciences prod 47753 issue
		 */

//		listOfDetails.add(new OnClickDetails(
//				"ul > li:nth-child(5) > div > div > div.article-list-title > a, ul > li:nth-child(6) > div > div > div.article-list-title > a",
//				OPEN_IN_NEW_TAB, 0, null, null, null, null));
//		listOfDetails.add(new OnClickDetails(
//				"#body-mfc > div.article-pc.article-box-content > div.article-main > div > div.clearfix.article-right-box.bgwhite.ac > div.article-main-right.fr > div > div > div.download-btns.clear-size > div.pdf-xml.clearfix > div.download-pdf.fl > a",
//				ON_CLICK_DOWNLOAD, 0, null, null, null, null));

		/**
		 * JP use case
		 */

//		listOfDetails.add(new OnClickDetails(
//				"#SearchResult > div > table > tbody > tr:nth-child(3) > td.table_list_center > input:nth-child(1)",
//				FILTER, 0, null, null, null, null));
//		listOfDetails.add(new OnClickDetails("#downloadBtn", ON_CLICK_DOWNLOAD, 0, null, null, null, null));

		/**
		 * cz uat 100411 issue
		 */

//		listOfDetails.add(new OnClickDetails("#di1", OPEN_IN_SAME_TAB, 0, null, null, null, null));
//		listOfDetails.add(new OnClickDetails("#DetailNabidka > ul > li.nav-item.dropdown > a", PAGE_NAVIGATION, 1, null,
//				null, null, null));
//		listOfDetails.add(new OnClickDetails("#DetailNabidka > ul > li.nav-item.dropdown.show > div > a:nth-child(2)",
//				ON_CLICK_DOWNLOAD, 2, null, null, null, null));

		/**
		 * ijic use case
		 */
//		listOfDetails.add(new OnClickDetails(
//				"div > div > div > div > button",
//				PAGE_NAVIGATION, 1, null, null, null, null));
//		listOfDetails.add(new OnClickDetails("div > main > section > ul > li:nth-child(1) > a", OPEN_IN_NEW_TAB, 0,
//				null, null, null, null));
		listOfDetails.add(new OnClickDetails(
				"ul > li:nth-child(1) > ul > li:nth-child(1) > div > ul > li > button",
				PAGE_NAVIGATION, 1, null, null, null, null));
		listOfDetails.add(new OnClickDetails("body > div > div> div:nth-child(1) > div", ON_CLICK_DOWNLOAD, 2, null,
				null, null, null));

		return listOfDetails;
	}
}
