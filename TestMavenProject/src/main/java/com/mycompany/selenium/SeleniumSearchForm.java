package com.mycompany.selenium;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumSearchForm {
	public static void main(String[] args) {
//		String url = "https://www.consilium.europa.eu/register/en/content/int?typ=ADV";
//		String url = "https://www.accessdata.fda.gov/scripts/cder/daf/";
//		String url = "https://clinicaltrials.gov/";\
//		String url = "https://content.sciendo.com/view/journals/bjes/bjes-overview.xml?tab_body=toc-68871";
//		String url="https://www.degruyter.com/view/journals/comp/comp-overview.xml?tab_body=toc-68871";
//		String url = "https://www.fda.gov/news-events/speeches-fda-officials";	
//		String url = "https://european-biotechnology.com/up-to-date/latest-news.html";
//		String url = "https://journals.co.za/content/journal/10520/EJC-1b922f913a";
//		String url = "https://research.nhm.org/publications/pdfpick.html?id=33311&pdfroot=http://research.nhm.org/pdfs";
//		String url = "https://www.businesswire.com/portal/site/home/template.PAGE/news/industry/?javax.portlet.tpst=08c2aa13f2fe3d4dc1b6751ae1de75dd&javax.portlet.prp_08c2aa13f2fe3d4dc1b6751ae1de75dd_vnsId=31250&javax.portlet.prp_08c2aa13f2fe3d4dc1b6751ae1de75dd_viewID=MY_PORTAL_VIEW&javax.portlet.prp_08c2aa13f2fe3d4dc1b6751ae1de75dd_ndmHsc=v2*A1589367600000*B1591983051402*DgroupByDate*G154*M31250*N1001385&javax.portlet.begCacheTok=com.vignette.cachetoken&javax.portlet.endCacheTok=com.vignette.cachetoken";

//		String url = "https://cris.nih.go.kr/cris/en/search/detail_search.jsp";
//		String url = "http://www.hkuctr.com/Search";
//		String url = "https://patent.public.lu/fo-eregister-view/search";
		String url = "https://euclinicaltrials.eu/search-for-clinical-trials";

		String advanceSearch = "form > div > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(4) > div > div > a";

		String selector1 = "form > div > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(1) > div:nth-child(2) > div > input:nth-child(3)";
		String selector2 = "form > div > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(2) > div:nth-child(2) > div > input:nth-child(3)";
		String value1 = "test1";
		String value2 = "test2";

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

		WebDriver webDriver = new ChromeDriver(capabilities);
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		webDriver.get(url);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(advanceSearch)));
			webDriver.findElement(By.cssSelector(advanceSearch)).click();

			Thread.sleep(3000);

			WebElement textField1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector1)));
			WebElement textField2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector2)));

			textField1.sendKeys(value1);
//			textField.sendKeys(Keys.ENTER);

			textField2.sendKeys(value2);
//			textField.sendKeys(Keys.ENTER);
			
			Thread.sleep(30000);

//			webDriver.findElement(By.cssSelector("#facetfield_SubjectCode_Economic_Research_and_Reports")).click();

//			WebElement popUpButton = wait.until(ExpectedConditions.elementToBeClickable(
//					By.cssSelector("#paging > div:nth-child(2) > div.pagingNext > a")));
//			popUpButton.click();

//			WebElement popUpButton = wait.until(ExpectedConditions.elementToBeClickable(
//					By.cssSelector("#sign-in > ul:nth-child(2) > li.list-group-item.hidden-xs.hidden-sm > h3 > a")));
//			popUpButton.click();
//
//			webDriver.findElement(By.cssSelector("#signname")).sendKeys("neotest");
//			webDriver.findElement(By.cssSelector("#signpsswd")).sendKeys("neotest03ia");
//
//			webDriver.findElement(By.cssSelector("#form-signin > fieldset > div:nth-child(3) > button")).click();
//
//			webDriver
//					.findElement(By.cssSelector(
//							"#bellowheadercontainer > main > div:nth-child(7) > div:nth-child(2) > div > div > a"))
//					.click();
//			
//			
//			System.out.print("Downloading");
//            do {
//            	System.out.print(".");
//                size = FileUtils.sizeOfDirectory(path);
//                Thread.sleep(2000);
//                reSize = FileUtils.sizeOfDirectory(path);
//            } while (size != reSize);{
//            System.out.println("\nDownload completed");
//        }

//			Thread.sleep(4000);

//			Document document = Jsoup.parse(webDriver.getPageSource(), url);
//			System.out.println("here we go" + document.select("#bellowheadercontainer > main"));

//			JavascriptExecutor js = (JavascriptExecutor) webDriver;
//			js.executeScript("document.querySelector('#main-content > div > div > div:nth-child(3) > form > div:nth-child(14) > div > input[type=hidden]:nth-child(4)').setAttribute('value', '01/04/2020');");
//			js.executeScript("document.querySelector('#main-content > div > div > div:nth-child(3) > form > div:nth-child(15) > div > input[type=hidden]:nth-child(4)').setAttribute('value', '17/04/2020');");

//			System.out.println(webDriver.findElement(By.cssSelector("#reportSelectMonth")).getTagName());
//			webDriver.findElement(By.cssSelector("#reportSelectMonth")).click();
//			Select select = new Select(webDriver.findElement(By.cssSelector("#reportSelectYear")));
//			System.out.println("asdasdasd"+select.selectByIndex(1).getText());

//			WebElement month = webDriver.findElement(By.cssSelector("#reportSelectMonth"));
//			Select select = new Select(month);

//			System.out.println(select.getWrappedElement());

//			select.getOptions()

//			System.out.println(month.getAttribute("outerHTML"));

//			List<WebElement> l = month.findElements(By.tagName("option"));
//			
//			for(WebElement i:l) {
//				System.out.println(i.getAttribute("outerHTML"));
//				System.out.println("---------> " + i.getTagName());
//			}
//			
//			
//			select.selectByVisibleText("May");

//			Select select = new Select(webDriver.findElement(By.cssSelector("#Country")));
//			select.selectByVisibleText("United States");

//			select.selectByValue("6");

//			System.out.println(webDriver.findElement(By.cssSelector("#reportSelectMonth")));

//			new Select(webDriver.findElement(By.cssSelector("#reportSelectMonth"))).selectByValue("April");
//	
//			webDriver.findElement(By.cssSelector("#collapseReports > div > form > div:nth-child(7) > div > button:nth-child(1)")).click();
//			Thread.sleep(5000);

//			
//			Document document = Jsoup.parse(webDriver.getPageSource(), url);
//			System.out.println(document);

			webDriver.manage().deleteAllCookies();

		} catch (Exception ex) {
			ex.printStackTrace();
//			jsExecutor.executeScript("arguments[0].click();",
//					webDriver.findElement(By.cssSelector("#facetfield_SubjectCode_Economic_Research_and_Reports")));
//			System.out.println(webDriver.getPageSource());
		} finally {
//			webDriver.close();
//			webDriver.quit();
//			webDriver = null;
		}
	}
}
