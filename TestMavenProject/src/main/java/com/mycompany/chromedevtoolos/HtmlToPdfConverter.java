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
import com.github.kklisura.cdt.protocol.types.page.PrintToPDF;
import com.github.kklisura.cdt.protocol.types.page.PrintToPDFTransferMode;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import com.mycompany.extension.ScraperConstant;

public class HtmlToPdfConverter {

	private static final Boolean land_landscape = false;
	private static final Boolean land_displayHeaderFooter = false;
	private static final Boolean land_printBackground = true;
	private static final Double land_scale = 1d;
	private static final Double land_paperWidth = 11d; // A4 paper format
	private static final Double land_paperHeight = 8.5d; // A4 paper format
	private static final Double land_marginTop = 0.4d;
	private static final Double land_marginBottom = 0.4d;
	private static final Double land_marginLeft = 0.4d;
	private static final Double land_marginRight = 0.4d;
	private static final String land_pageRanges = "";
	private static final Boolean land_ignoreInvalidPageRanges = false;
	private static final String land_headerTemplate = "";
	private static final String land_footerTemplate = "";
	private static final Boolean land_preferCSSPageSize = true;
	private static final PrintToPDFTransferMode land_mode = PrintToPDFTransferMode.RETURN_AS_BASE_64;

	private static final Boolean port_landscape = false;
	private static final Boolean port_displayHeaderFooter = false;
	private static final Boolean port_printBackground = true;
	private static final Double port_scale = 1d;
	private static final Double port_paperWidth = 8.5d; // A4 paper format
	private static final Double port_paperHeight = 11d; // A4 paper format
	private static final Double port_marginTop = 0.4d;
	private static final Double port_marginBottom = 0.4d;
	private static final Double port_marginLeft = 0.4d;
	private static final Double port_marginRight = 0.4d;
	private static final String port_pageRanges = "";
	private static final Boolean port_ignoreInvalidPageRanges = false;
	private static final String port_headerTemplate = "";
	private static final String port_footerTemplate = "";
	private static final Boolean port_preferCSSPageSize = true;
	private static final PrintToPDFTransferMode port_mode = PrintToPDFTransferMode.RETURN_AS_BASE_64;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String htmlFilePath = "file:///C:/Users/NomanAlahi/Desktop/New folder/test.html";
		String pdfFilePath = "C:/Users/NomanAlahi/Desktop/New folder/test.pdf";

		convertToPdf(htmlFilePath, pdfFilePath, ScraperConstant.DEFAULT_PAGE_ORIENTATION);
	}

	public static void convertToPdf(String htmlFilePath, String pdfFilePath, String pageOrientation) {
		String tempPath = randomTempDir("cdt-user-data-dir");
		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
		ExecutorService htmlToPdfExecutor = Executors.newSingleThreadExecutor();
		// Create chrome launcher.
		try (ChromeLauncher launcher = new ChromeLauncher()) {
			Callable<Boolean> mainTask = () -> {
				// set no-sandbox true to bypass OS security in docker image
				Map<String, Object> additionalChromeArguments = new HashMap<>();
				additionalChromeArguments.put("no-sandbox", true);

				// create chrome argument with additional properties and headless true
				ChromeArguments chromeArguments = ChromeArguments.builder().noFirstRun().noDefaultBrowserCheck()
						.userDataDir(tempPath).disableBackgroundNetworking().disableBackgroundTimerThrottling()
						.disableClientSidePhishingDetection().disableDefaultApps().disableExtensions()
						.disableHangMonitor().disablePopupBlocking().disablePromptOnRepost().disableSync()
						.disableTranslate().metricsRecordingOnly(false).safebrowsingDisableAutoUpdate().headless(true)
						.disableGpu(true).hideScrollbars(true).muteAudio(true)
						.additionalArguments(additionalChromeArguments).build();

				ChromeService chromeService = launcher.launch(chromeArguments);

				// Create empty tab ie about:blank.
				ChromeTab tab = chromeService.createTab();

				// Get DevTools service to this tab
				ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

				// Get individual commands
				Page page = devToolsService.getPage();
				page.enable();

				// Navigate to page
				page.navigate("file:///" + htmlFilePath);

				page.onLoadEventFired(loadEventFired -> {
					System.out.println("Printing to PDF " + htmlFilePath);

					final String outputFilename = pdfFilePath;

					Callable<Boolean> htmlToPdfTask = () -> {
						// Make a PDF file with Web page ScreenShot
						if (pageOrientation.equalsIgnoreCase(ScraperConstant.DEFAULT_PAGE_ORIENTATION)) {
							dump(outputFilename, devToolsService.getPage().printToPDF());
						} else if (pageOrientation.equalsIgnoreCase(ScraperConstant.LANDSCAPE_PAGE_ORIENTATION)) {
							dump(outputFilename,
									devToolsService.getPage().printToPDF(land_landscape, land_displayHeaderFooter,
											land_printBackground, land_scale, land_paperWidth, land_paperHeight,
											land_marginTop, land_marginBottom, land_marginLeft, land_marginRight,
											land_pageRanges, land_ignoreInvalidPageRanges, land_headerTemplate,
											land_footerTemplate, land_preferCSSPageSize, land_mode));
						} else if (pageOrientation.equalsIgnoreCase(ScraperConstant.PORTRAIT_PAGE_ORIENTATION)) {
							dump(outputFilename,
									devToolsService.getPage().printToPDF(port_landscape, port_displayHeaderFooter,
											port_printBackground, port_scale, port_paperWidth, port_paperHeight,
											port_marginTop, port_marginBottom, port_marginLeft, port_marginRight,
											port_pageRanges, port_ignoreInvalidPageRanges, port_headerTemplate,
											port_footerTemplate, port_preferCSSPageSize, port_mode));
						}

						return true;
					};
					Future<Boolean> htmlToPdfFuture = htmlToPdfExecutor.submit(htmlToPdfTask);
					try {
						htmlToPdfFuture.get(2, TimeUnit.MINUTES);
					} catch (InterruptedException | ExecutionException | TimeoutException e) {
						System.out.println(e);
					}
					devToolsService.close();
				});
				devToolsService.waitUntilClosed();
				chromeService.closeTab(tab);
				chromeService = null;
				return true;
			};
			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
			mainFuture.get(4, TimeUnit.MINUTES);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				Thread.sleep(100);
				FileUtils.deleteQuietly(new File(tempPath));
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	private static void dump(String fileName, PrintToPDF printToPDF) {
		System.out.println("Dumping file : " + fileName);
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(fileName);
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(Base64.getDecoder().decode(printToPDF.getData()));
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	public static String randomTempDir(String prefix) {
		try {
			return Files.createTempDirectory(prefix).toAbsolutePath().toString();
		} catch (IOException e) {
			throw new RuntimeException("Failed creating temp directory for " + prefix, e);
		}
	}

}
