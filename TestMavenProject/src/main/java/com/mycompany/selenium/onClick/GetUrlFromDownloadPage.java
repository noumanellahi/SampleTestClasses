package com.mycompany.selenium.onClick;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetUrlFromDownloadPage {
	private static String[] DOWNLOAD_FILE_EXTENSION = new String[] { "crdownload" };
	private static String OPEN_IN_NEW_TAB = "OPEN_IN_NEW_TAB";
	private static String OPEN_IN_SAME_TAB = "OPEN_IN_SAME_TAB";
	private static String PAGE_NAVIGATION = "PAGE_NAVIGATION";
	private static String FILTER = "FILTER";

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
			String url = "https://mijnoctrooi.rvo.nl/fo-eregister-view/search/details/1034749_NP/0/0/1/10/0/1/0/null_en_null/KChyZWNodHNvb3J0Ok5QICkgQU5EIChwdWJsaWNhdGllZGF0dW06WzIwMjItMDctMDEgVE8gMjAyMi0wNy0yMV0gT1IgaW5zY2hyaWp2aW5nc2RhdHVtOlsyMDIyLTA3LTAxIFRPIDIwMjItMDctMjFdIE9SIHZlcmxlbmluZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSBPUiB2b29ycmFuZ3NkYXR1bTpbMjAyMi0wNy0wMSBUTyAyMDIyLTA3LTIxXSApKSBBTkQgcGF0ZW50UmVjb3JkU2VxOjE=";

			/**
			 * KR usecase open in new window
			 */
//			String url = "https://law.go.kr/LSW/lsAstSc.do?tabMenuId=tab6#cptOfiAll";

			/**
			 * keyword use case
			 */
//			String url = "http://www.ftb.com.hr/current-issue";

			List<OnClickDetails> tags = loadData();

			String textTag = "";

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
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

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
			traverser(webDriver, wait, actions, jsExecutor, elements1, tags, 0, 0, downloadFilepath);

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

	public static void traverser(WebDriver webDriver, WebDriverWait wait, Actions actions,
			JavascriptExecutor jsExecutor, List<WebElement> elements, List<OnClickDetails> tags, int level,
			int windowNumber, String downloadFilepath) throws InterruptedException {
		int counter = 0;

		if (level > 0) {
			if (tags.get(level).getWebPageLoadTime() != null && Math.round(tags.get(level).getWebPageLoadTime()) > 0) {
				Thread.sleep(Math.round(tags.get(level).getWebPageLoadTime()));
			}
		}

		for (WebElement element : elements) {
			if (level == tags.size() - 1) {

				try {
					String description = (String) jsExecutor.executeScript("return jQuery(arguments[0]).text();",
							element);
				} catch (Exception ex) {
					System.out.println("NOT ABLE TO EXTRACT DESCRIPTION TEXT FROM DOWNLOAD BUTTON");
				}

				jsExecutor.executeScript("arguments[0].click();", element);
				do {
					Thread.sleep(3000);
				} while (!FileUtils.listFiles(new File(downloadFilepath), DOWNLOAD_FILE_EXTENSION, false).isEmpty());
				{
//					System.out.println(counter + "Download Complete");
				}

				testGetText_FromShadowDOMElements(webDriver);
			} else {
				if (tags.get(level).getOnClickResult().equalsIgnoreCase(OPEN_IN_NEW_TAB)) {
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
							traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1,
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
						traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1, windowNumber + 1,
								downloadFilepath);
					}
					webDriver.navigate().back();
				} else if (tags.get(level).getOnClickResult().equalsIgnoreCase(FILTER)) {
					element.click();

					if (counter == elements.size() - 1) {
						List<WebElement> subElements = tagsIdentifier(webDriver, wait, tags.get(level + 1).getTextTag(),
								tags.get(level + 1).getOnClickTag(), tags.get(level + 1).getKeyWords(),
								tags.get(level + 1).getKeywordsType());
						traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1, windowNumber,
								downloadFilepath);
					}
				} else {
					jsExecutor.executeScript("arguments[0].click();", element);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(tags.get(level + 1).getOnClickTag())));

					List<WebElement> subElements = tagsIdentifier(webDriver, wait, tags.get(level + 1).getTextTag(),
							tags.get(level + 1).getOnClickTag(), tags.get(level + 1).getKeyWords(),
							tags.get(level + 1).getKeywordsType());
					traverser(webDriver, wait, actions, jsExecutor, subElements, tags, level + 1, windowNumber,
							downloadFilepath);
				}

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

			Set<String> keywordsSet = new HashSet<>(Arrays.asList(keyWords));

			if (textElements.size() == onCliElements.size()) {
				for (int i = 0; i < textElements.size(); i++) {

					boolean flag = keywordsSet.contains(textElements.get(i).getText());

					if (keywordsType.equalsIgnoreCase("include") && flag) {
						resultElements.add(onCliElements.get(i));
					} else if (keywordsType.equalsIgnoreCase("exclude") && !flag) {
						resultElements.add(onCliElements.get(i));
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

		listOfDetails.add(new OnClickDetails("ul > li.tabs-last", PAGE_NAVIGATION, 0, null, null, null, null));

		// NL For check box download
		listOfDetails.add(new OnClickDetails("tr > td:nth-child(1) > div > input[type=checkbox]", FILTER, 1,
				"tr > td:nth-child(3) > div", new String[] { "Certificate" }, "Include", null));
		listOfDetails.add(new OnClickDetails("#generatePDF > a", PAGE_NAVIGATION, 2, null, null, null, null));
		listOfDetails.add(new OnClickDetails("#ConfirmBtn", PAGE_NAVIGATION, 3, null, null, null, null));

		// NL For INdividual file download
//		listOfDetails.add(new OnClickDetails("tr > td:nth-child(3) > div > a", OPEN_IN_NEW_TAB, 1,
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

		return listOfDetails;
	}

	public static void testGetText_FromShadowDOMElements(WebDriver webDriver) {
		webDriver.get("chrome://downloads/");

		System.out.println("Validate downloads page header text");
		WebElement root1 = webDriver.findElement(By.tagName("downloads-manager"));

		// Get shadow root element
		WebElement shadowRoot1 = expandRootElement(webDriver, root1);

		WebElement root2 = shadowRoot1.findElement(By.cssSelector("downloads-toolbar"));
		WebElement shadowRoot2 = expandRootElement(webDriver, root2);

		WebElement root3 = shadowRoot2.findElement(By.cssSelector("cr-toolbar"));
		WebElement shadowRoot3 = expandRootElement(webDriver, root3);

		String actualHeading = shadowRoot3.findElement(By.cssSelector("div[id=leftContent]>h1")).getText();

	}

	public static WebElement expandRootElement(WebDriver webDriver, WebElement element) {

		Object shadowRoot = ((JavascriptExecutor) webDriver).executeScript("return arguments[0].shadowRoot", element);
		String id = (String) ((Map<String, Object>) shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf");
		RemoteWebElement shadowRootElement = new RemoteWebElement();
		shadowRootElement.setParent((RemoteWebDriver) webDriver);
		shadowRootElement.setId(id);

		WebElement shadowContent = shadowRootElement.findElement(By.tagName("downloads-item"));

		shadowRoot = ((JavascriptExecutor) webDriver).executeScript("return arguments[0].shadowRoot", shadowContent);
		id = (String) ((Map<String, Object>) shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf");
		shadowRootElement = new RemoteWebElement();
		shadowRootElement.setParent((RemoteWebDriver) webDriver);
		shadowRootElement.setId(id);

		WebElement url = shadowRootElement.findElement(By.cssSelector("#details > div:nth-child(2) > a"));

		System.out.println(url.getText());

		return null;
	}
}
