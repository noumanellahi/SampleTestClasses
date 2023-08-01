
package com.mycompany.selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctc.wstx.util.StringUtil;

public class Pagination {
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

//		String url = "https://www.pr.com/news-by-category/129";
//		String paginatorTag = "body > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > table:nth-child(6) > tbody > tr > td:nth-child(2)";
//		String tagInclude = "body > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > table.table_bg > tbody > tr:nth-child(2) > td > div.x20.justify > div:nth-child(2) > div.blue11b.b3 > strong > a";

//		String url = "https://www.eurekalert.org/covid-19/newsroom/index.php";
//		String paginatorTag = "#main-content > div > ul";
//		String tagInclude = "#main-content > section > article:nth-child(1) > a > header > h2";

//		String url = "https://www.ansm.sante.fr/Activites/Mise-sur-le-marche-des-dispositifs-medicaux-et-dispositifs-medicaux-de-diagnostic-in-vitro-DM-DMIA-DMDIV/COVID-19-Commercialisation-des-DM-et-DMDIV/(offset)/0";
//		String paginatorTag = "#project_chapter > div:nth-child(3)";
//		String tagInclude = "#project_chapter > div.list > div:nth-child(1) > h3";

		String url = "http://www.globenewswire.com/NewsRoom";
		
		
		String paginatorTag = "#content-R3 > div > ul";
		String tagInclude = "#container-newsroom > div.content-shadow-cont > div.rl-master-container";

		String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

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
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		try {
			webDriver.get(url);
			paginator(webDriver, wait, paginatorTag, tagInclude, 1);
			webDriver.manage().deleteAllCookies();
			System.out.println("THE END ..... :)");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

	public static void paginator(WebDriver webDriver, WebDriverWait wait, String paginatorTag, String tagInclude,
			int counter) {
		try {
			String url2 = "https://www.emerald.com/insight/publication/issn/1366-6282/vol/13/iss/4";
			System.out.println("Page NUmber " + counter);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(paginatorTag)));
			List<WebElement> paginator = webDriver.findElement(By.cssSelector(paginatorTag))
					.findElements(By.xpath(".//*"));

			WebElement nextButton = null;
			boolean numberButtonFlag = false;
			for (WebElement element : paginator) {
				try {
					String elementText = element.getText();
					if (numberButtonFlag && buttonTextCheck(elementText)) {
						if (element.getTagName().equals("a")) {
							nextButton = element;
						} else {
							nextButton = element.findElement(By.tagName("a"));
						}
						break;
					} else if (elementText.matches("-?\\d+(\\.\\d+)?")) {
						numberButtonFlag = true;
					}
				} catch (Exception ex) {
					System.out.println("Not have any anchor tag");
				}
			}

			if (nextButton != null) {
				String currentPageText = webDriver.findElement(By.cssSelector(tagInclude)).getText();
				System.out.println(counter + " -> " + currentPageText);

				try {
					nextButton.click();
				} catch (WebDriverException clickException) {
					jsExecutor.executeScript("arguments[0].click();", nextButton);
				}

//				Thread.sleep(5000);
				if (!currentPageText.equals(webDriver.findElement(By.cssSelector(tagInclude)).getText())) {
					counter++;

					for (int i = 0; i <= 3; i++) {
						ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
						if (tabs.size() > 1) {
							webDriver.switchTo().window(tabs.get(1));
							webDriver.get(url2);
							webDriver.switchTo().window(tabs.get(0));
							Thread.sleep(5000);
						} else {
							jsExecutor.executeScript("window.open()");
							tabs = new ArrayList<String>(webDriver.getWindowHandles());
							webDriver.switchTo().window(tabs.get(1));
							webDriver.get(url2);
							webDriver.switchTo().window(tabs.get(0));
							Thread.sleep(5000);
						}
					}

					paginator(webDriver, wait, paginatorTag, tagInclude, counter);
				}
			} else {
				String currentPageText = webDriver.findElement(By.cssSelector(tagInclude)).getText();
				System.out.println(counter + " -> " + currentPageText);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean buttonTextCheck(String elementText) {
		if (StringUtils.isBlank(elementText)) {
			return true;
		} else if (elementText.equals("...")) {
			return false;
		} else if (elementText.equals("…")) {
			return false;
		} else if (elementText.matches("-?\\d+(\\.\\d+)?")) {
			return false;
		} else if (elementText.toLowerCase().contains("previous")) {
			return false;
		} else if (elementText.toLowerCase().contains("first")) {
			return false;
		} else if (elementText.toLowerCase().contains("last")) {
			return false;
		} else if (elementText.toLowerCase().contains("<")) {
			return false;
		}
		return true;
	}
}
