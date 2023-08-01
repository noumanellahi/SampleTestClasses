/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Noman.Alahi
 */
public class ChromeHeadless {

	public static void main(String[] args) {
        Document document = Jsoup.parse("<html></html>");
        try {
        	String downloadFilepath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\download";
            String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            
//            HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
//            chromePreferences.put("profile.default_content_settings.popups", 0);
//            chromePreferences.put("download.prompt_for_download", "false");
//            chromePreferences.put("download.default_directory", downloadFilepath);
//
//    		ChromeOptions options = new ChromeOptions();
//    		options.addArguments("--no-sandbox"); // Bypass OS security model
//    		options.addArguments("--ignore-certificate-errors");
//    		options.addArguments("--start-maximized");
//    		options.addArguments("--disable-dev-shm-usage");
//    		options.addArguments("--incognito");
//    		options.addArguments("--headless");
//    		options.setExperimentalOption("prefs", chromePreferences);
//    				
//    		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//    		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
//    		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//    		
//    		ChromeDriverService driverService = ChromeDriverService.createDefaultService();
//    		ChromeDriver driver = new ChromeDriver(driverService, options);
//    				
//    		Map<String, Object> commandParams = new HashMap<>();
//    		commandParams.put("cmd", "Page.setDownloadBehavior");
//    		Map<String, String> params = new HashMap<>();
//    		params.put("behavior", "allow");
//    		params.put("downloadPath", downloadFilepath);
//    		commandParams.put("params", params);
//    		ObjectMapper objectMapper = new ObjectMapper();
//    		HttpClient httpClient = HttpClientBuilder.create().build();
//    		String command = objectMapper.writeValueAsString(commandParams);
//    		String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId() + "/chromium/send_command";
//    		HttpPost request = new HttpPost(u);
//    		request.addHeader("content-type", "application/json");
//    		request.setEntity(new StringEntity(command));
//    		try {
//    			httpClient.execute(request);
//    			driver.get("https://www.mfds.go.kr/brd/m_211/list.do");
////    			WebUI.delay(30);
//    			Thread.sleep(10000);
//    			System.out.println("Task complete, please go to save folder to see it.");
//    			driver.close();
//    		} catch (IOException e2) {
//    			e2.printStackTrace();
//    		}
            
            String url = "https://www.paab.ca/resources.htm";
            
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

    		try {
    			webDriver.get(url);
    			WebDriverWait wait = new WebDriverWait(webDriver, 1);
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.article_item.first")));
    			document = Jsoup.parse(webDriver.getPageSource(), url);
    			webDriver.manage().deleteAllCookies();

    		} catch (Exception ex) {
    			ex.printStackTrace();
    		} finally {
    			webDriver.close();
    			webDriver.quit();
    			webDriver = null;
    		}
            
    		
    		System.out.println(document);
            
            
            
            
            
            
            
//            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--silent");
//            WebDriver driver = new ChromeDriver(options);

//            driver.get("http://portal.anvisa.gov.br/legislacao#/");
//            driver.get("https://www.paab.ca/resources.htm");
//            Thread.sleep(30000);
//            
//            WebDriverWait wait = new WebDriverWait(driver,30);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.landingResources.grid-wrapper.filterResources.articlesmore")));
//            
//            document = Jsoup.parse(driver.getPageSource());
//            
//            System.out.println(document);
//            
//            System.out.println(document.select("articlesmore"));
//            System.out.println(driver.findElement(By.cssSelector("#article_2BB3ED602260CC1762BF0D966EF804E8 > div > article:nth-child(1) > div")).getText());
            
            
//            System.out.println(document.select("#article_2BB3ED602260CC1762BF0D966EF804E8 > div > article:nth-child(1) > div"));
           
            
            
            
            
            
//            document = Jsoup.parse(driver.getPageSource());
//            
//            System.out.println(document.select("#article_4EC5B76CAD7E0D375F6A80F86B136F74 > div > article:nth-child(1) > div"));
//                    //            Thread.sleep(150000);
                    //            System.out.println(System.currentTimeMillis());
            
//            Thread.sleep(150000);
//            System.out.println(driver.getPageSource());
//             System.out.println("Here we go" + driver.findElement(By.xpath("//*[@id=\"article_35C4B6CF9566AE5DBF29B5BD2B263BA7\"]/div/article[1]/div/div[1]")).getText());
//             System.out.println(driver.getPageSource());
//            System.out.println("Here we go" + driver.findElement(By.xpath("/html/body/center/table[3]/tbody/tr/td/table[2]/tbody/tr/td/table[1]/tbody")).getText());

//            System.out.println("Here we go" + driver.findElement(By.xpath("//*[@id=\"p_p_id_legislacao_WAR_etapasregulatoriasportlet_\"]/div/div/div/div/div/div[3]")).getText());
            //            System.out.println("Here we go" + driver.findElement(By.xpath("#p_p_id_legislacao_WAR_etapasregulatoriasportlet_ > div > div > div > div > div > div.listagem")));

            //            new WebDriverWait(driver, 120000).until(
            //                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            //            System.out.println("here we go " + driver.getPageSource());

            //            document = Jsoup.parse(driver.getPageSource());
                    //            System.out.println("Here we go" + document);
                    //            driver.get("http://www.cde.org.cn/policy.do?method=policy_index&frameStr=1");
                    //            driver.wait(120000);
                    //            Thread.sleep(120000);
                    //            System.out.println("herer it is " + driver.findElement(By.xpath("#p_p_id_legislacao_WAR_etapasregulatoriasportlet_ > div > div > div > div > div > div.listagem")).isDisplayed());
                    //            if (driver.findElement(By.xpath("//*[@id=\"p_p_id_legislacao_WAR_etapasregulatoriasportlet_\"]/div/div/div/div/div/div[3]")).isDisplayed()) {
                    //                System.out.println("found the gem");
                    //            } else {
                    //                for (int i = 0; i < 25; i++) {
                    //                    Thread.sleep(1000);
                    //                    if (driver.findElement(By.xpath("//*[@id=\"p_p_id_legislacao_WAR_etapasregulatoriasportlet_\"]/div/div/div/div/div/div[3]")).isDisplayed()) {
                    //                        System.out.println("found the gem");
                    //                        break;
                    //                    }
                    //                }
                    //                System.out.println("Nothing found");
                    //            }
                    //            System.out.println("Here we go" + driver.findElement(By.xpath("#p_p_id_legislacao_WAR_etapasregulatoriasportlet_ > div > div > div > div > div > div.listagem")));
                    //            document = Jsoup.parse(driver.getPageSource());
                    //
                    //            System.out.println("Here we go" + document);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
