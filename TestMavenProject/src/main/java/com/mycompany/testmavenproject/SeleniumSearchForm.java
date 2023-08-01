package com.mycompany.testmavenproject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jsoup.nodes.Document;

public class SeleniumSearchForm {
	public static void main(String[] args) {
//		String url = "https://www.consilium.europa.eu/register/en/content/int?typ=ADV";
//		String url = "https://www.accessdata.fda.gov/scripts/cder/daf/";
//		String url = "https://clinicaltrials.gov/";\
//		String url = "https://content.sciendo.com/view/journals/bjes/bjes-overview.xml?tab_body=toc-68871";
//		String url="https://www.degruyter.com/view/journals/comp/comp-overview.xml?tab_body=toc-68871";
//		String url = "https://www.fda.gov/news-events/speeches-fda-officials";	
		String url = "https://european-biotechnology.com/up-to-date/latest-news.html";
		
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

		WebDriver webDriver = new ChromeDriver(capabilities);
		WebDriverWait wait = new WebDriverWait(webDriver, 5);
		try {
			webDriver.get(url);
//			Actions act= new Actions(webDriver);
//			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			
//			webDriver.findElement(By.cssSelector("body > div.contents > form > table > tbody > tr:nth-child(12) > td.data2 > input:nth-child(2)")).click();
//			act.moveToElement(element1).click().build().perform();
//			js.executeScript("arguments[0].click();", element1);
			
//			WebElement element2=webDriver.findElement(By.cssSelector("body > div.contents > form > table > tbody > tr:nth-child(13) > td.data2 > input:nth-child(2)"));
//			act.moveToElement(element2).click().build().perform();
//			js.executeScript("arguments[0].click();", element2);
//			
//			WebElement element3=webDriver.findElement(By.cssSelector("body > nav > div.search-panel.active"));
//			act.moveToElement(element3).click().build().perform();

			
			
	        try{
	            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#c93 > div > div > div > div.page-navigation > ul > li.last.next")));
	            webDriver.findElement(By.cssSelector("#c93 > div > div > div > div.page-navigation > ul > li.last.next")).click();
	            
	            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#c93 > div > div > div > div.page-navigation > ul > li:nth-child(7)")));
				webDriver.findElement(By.cssSelector("#c93 > div > div > div > div.page-navigation > ul > li:nth-child(7)")).click();
	        }
	        catch (Exception e){
	            System.out.println("False");
	            e.printStackTrace();
	        }
			
	        Thread.sleep(5000);
	        System.out.println("done ............");
	        webDriver.findElement(By.cssSelector("#c93 > div > div > div > div.page-navigation > ul > li.last.next")).click();
	        
	        
			Document document = Jsoup.parse(webDriver.getPageSource(), url);
			System.out.println("here we go"+document.select("#block-views-block-datatable-documents-speeches-fda-officials > div"));
			
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
		} finally {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}
}
