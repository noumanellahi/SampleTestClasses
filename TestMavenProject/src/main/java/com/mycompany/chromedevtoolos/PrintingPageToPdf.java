package com.mycompany.chromedevtoolos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;

import com.github.kklisura.cdt.launch.ChromeArguments;
import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.commands.Runtime;
import com.github.kklisura.cdt.protocol.types.page.PrintToPDF;
import com.github.kklisura.cdt.protocol.types.page.PrintToPDFTransferMode;
import com.github.kklisura.cdt.protocol.types.runtime.Evaluate;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * The following example opens the http://github.com and prints its page to PDF.
 * PDF printing is supported on chrome headless at the moment.
 *
 * @author Kenan Klisura
 */
public class PrintingPageToPdf {

	public static void main(String[] args) {
		String tempPath = randomTempDir("cdt-user-data-dir");
		WebDriverManager.chromedriver().setup();
		
		
		try (ChromeLauncher launcher = new ChromeLauncher()) {
			String htmlFilePath = "file:///C:/Users/NomanAlahi/Desktop/New folder/test.html";
			String pdfFilePath = "C:\\Users\\NomanAlahi\\Desktop\\test_11.pdf";

//			String htmlFilePath1 = "C:\\Users\\Noman.Alahi\\Desktop\\Converter\\Source_HTML_Page_1_ORIG.html";
//			String pdfFilePath1 = "C:\\Users\\Noman.Alahi\\Desktop\\Converter\\Source_HTML_Page_1_ORIG.pdf";

			// set no-sandbox true to bypass OS security in docker image
			Map<String, Object> additionalChromeArguments = new HashMap<>();
			additionalChromeArguments.put("no-sandbox", true);
			additionalChromeArguments.put("--window-size=1920,1080", true);

			// create chrome argument with additional properties and headless true
			ChromeArguments chromeArguments = ChromeArguments.builder().noFirstRun().noDefaultBrowserCheck()
					.userDataDir(tempPath).disableBackgroundNetworking().disableBackgroundTimerThrottling()
					.disableClientSidePhishingDetection().disableDefaultApps().disableExtensions().disableHangMonitor()
					.disablePopupBlocking().disablePromptOnRepost().disableSync().disableTranslate()
					.metricsRecordingOnly(false).safebrowsingDisableAutoUpdate().headless(true).disableGpu(true)
					.hideScrollbars(true).muteAudio(true).additionalArguments(additionalChromeArguments).build();

			ChromeService chromeService = launcher.launch(chromeArguments);
			print_Page(htmlFilePath, pdfFilePath, chromeService);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				Thread.sleep(100);
				FileUtils.deleteQuietly(new File(tempPath));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void print_Page(String htmlFilePath, String pdfFilePath, ChromeService chromeService) {
		
		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
		ExecutorService htmlToPdfExecutor = Executors.newSingleThreadExecutor();
		// Create chrome launcher.
		try {
			Callable<Boolean> mainTask = () -> {

				// Create empty tab ie about:blank.
				ChromeTab tab = chromeService.createTab();

				// Get DevTools service to this tab
				ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

				// Get individual commands
				Page page = devToolsService.getPage();
				page.enable();

				// Navigate to page
//				page.navigate("file:///" + htmlFilePath);
//				page.navigate("file:///" + htmlFilePath);
				page.navigate(htmlFilePath);

				page.onLoadEventFired(loadEventFired -> {

					final String outputFilename = pdfFilePath;

					Boolean landscape = true;
					Boolean displayHeaderFooter = false;
					Boolean printBackground = true;
					Double scale = 1d;
					Double paperWidth = 11.7d; // A4 paper format
					Double paperHeight = 16.5d; // A4 paper format
					Double marginTop = 0.1d;
					Double marginBottom = 0.1d;
					Double marginLeft = 0.1d;
					Double marginRight = 0.1d;
					String pageRanges = "";
					Boolean ignoreInvalidPageRanges = false;
					String headerTemplate = "";
					String footerTemplate = "";
					Boolean preferCSSPageSize = true;
					PrintToPDFTransferMode mode = PrintToPDFTransferMode.RETURN_AS_BASE_64;

					Callable<Boolean> htmlToPdfTask = () -> {
						try {

//							// Make a PDF file with Web page ScreenShot
//							double height = page.getLayoutMetrics().getContentSize().getHeight();
//							double scrollLimit = height * 0.10;
//
//							for (int i = 1; i * scrollLimit <= height; i++) {
//								devToolsService.getRuntime().evaluate("window.scrollTo(0," + (i * scrollLimit) + ")");
//								Thread.sleep(1500);
//							}

//							dump(outputFilename,
//									devToolsService.getPage().printToPDF(landscape, displayHeaderFooter,
//											printBackground, scale, paperWidth, paperHeight, marginTop, marginBottom,
//											marginLeft, marginRight, pageRanges, ignoreInvalidPageRanges,
//											headerTemplate, footerTemplate, preferCSSPageSize, mode));

//							dump(outputFilename,
//									devToolsService.getPage().printToPDF());

							// default
							Thread.sleep(5000);
							final Runtime runtime = devToolsService.getRuntime();
							runtime.evaluate("(function(){x=document.querySelectorAll('*');for(i=0;i<x.length;i++){elementStyle=getComputedStyle(x[i]);if(elementStyle.position=='fixed'||elementStyle.position=='sticky'){x[i].remove();}}}())");
							runtime.evaluate("(function() {l = document.getElementsByTagName(\"a\"); for (var i =0; i<l.length; i++) {l[i].href = \"\"; }  }())");
							dump(outputFilename, devToolsService.getPage().printToPDF());
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						return true;
					};
					Future<Boolean> htmlToPdfFuture = htmlToPdfExecutor.submit(htmlToPdfTask);
					try {
						htmlToPdfFuture.get(2, TimeUnit.MINUTES);
					} catch (InterruptedException | ExecutionException | TimeoutException e) {
						e.printStackTrace();
					}
					devToolsService.close();
				});
				devToolsService.waitUntilClosed();
				chromeService.closeTab(tab);
				return true;
			};
			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
			mainFuture.get(4, TimeUnit.MINUTES);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			mainExecutor.shutdownNow();
			htmlToPdfExecutor.shutdownNow();
		}
	}

	private static void dump(String fileName, PrintToPDF printToPDF) {
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(fileName);
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(Base64.getDecoder().decode(printToPDF.getData()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Returns a random temp dir.
	 *
	 * @return Random temp data dir absolute path.
	 * @throws RuntimeException If it fails to create temp directory.
	 */
	public static String randomTempDir(String prefix) {
		try {
			return Files.createTempDirectory(prefix).toAbsolutePath().toString();
		} catch (IOException e) {
			throw new RuntimeException("Failed creating temp directory for " + prefix, e);
		}
	}
}
