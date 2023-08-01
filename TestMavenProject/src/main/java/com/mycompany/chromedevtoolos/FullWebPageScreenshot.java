//package com.mycompany.chromedevtoolos;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//
//import com.github.kklisura.cdt.launch.ChromeArguments;
//import com.github.kklisura.cdt.launch.ChromeLauncher;
//import com.github.kklisura.cdt.protocol.commands.Emulation;
//import com.github.kklisura.cdt.protocol.commands.Page;
//import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat;
//import com.github.kklisura.cdt.protocol.types.page.LayoutMetrics;
//import com.github.kklisura.cdt.protocol.types.page.Viewport;
//import com.github.kklisura.cdt.services.ChromeDevToolsService;
//import com.github.kklisura.cdt.services.ChromeService;
//import com.github.kklisura.cdt.services.types.ChromeTab;
//
//public class FullWebPageScreenshot {
//	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//		// Make a PDF file with Web page ScreenShot
//		String url = "file:///C:/Users/Noman.Alahi/Desktop/2019__12__05_Abgenomics_welcomes_Dr_Judy_Chou_as_new_President_CEO.html";
//		String filePath = "C:\\Users\\Noman.Alahi\\Desktop\\new1";
//		captureScreenshot(20000.00,url, filePath);
//	}
//
//	public static void captureScreenshot(Double screenshotLoadTime, String url, String filePath) {
//		String tempPath = randomTempDir("cdt-user-data-dir");
//		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
//		ExecutorService screenShootExecutor = Executors.newSingleThreadExecutor();
//		try (ChromeLauncher launcher = new ChromeLauncher()) {
//			Callable<Boolean> mainTask = () -> {
//				String imageFilePath = filePath + ".png";
//				String pdfFilePath = filePath + ".pdf";
//				// set no-sandbox true to bypass OS security in docker image
//				Map<String, Object> additionalChromeArguments = new HashMap<>();
//				additionalChromeArguments.put("no-sandbox", true);
//				// create chrome argument with additional properties and headless true
//				ChromeArguments chromeArguments = ChromeArguments.builder().noFirstRun().noDefaultBrowserCheck()
//						.userDataDir(tempPath).disableBackgroundNetworking().disableBackgroundTimerThrottling()
//						.disableClientSidePhishingDetection().disableDefaultApps().disableExtensions()
//						.disableHangMonitor().disablePopupBlocking().disablePromptOnRepost().disableSync()
//						.disableTranslate().metricsRecordingOnly().safebrowsingDisableAutoUpdate().headless(false)
//						.disableGpu(true).hideScrollbars(true).muteAudio(true)
//						.additionalArguments(additionalChromeArguments).build();
//				
//				ChromeService chromeService = launcher.launch(chromeArguments);
//				// Create empty tab ie about:blank.
//				ChromeTab tab = chromeService.createTab();
//				// Get DevTools service to this tab
//				ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);
//				// Get individual commands
//				Page page = devToolsService.getPage();
//				page.onLoadEventFired(event -> {
//					Callable<Boolean> screenShootTask = () -> {
//						captureFullPageScreenshot(screenshotLoadTime, devToolsService, page,
//								imageFilePath);
//						return true;
//					};
//					Future<Boolean> screenShootFuture = screenShootExecutor.submit(screenShootTask);
//					try {
//						screenShootFuture.get(2, TimeUnit.MINUTES);
//					} catch (InterruptedException | ExecutionException | TimeoutException e) {
//						e.printStackTrace();
//					}
//					devToolsService.close();
//				});
//				// Enable page events.
//				page.enable();
//				page.navigate(url);
//				devToolsService.waitUntilClosed();
//
//				if (new File(imageFilePath).exists()) {
//					try (PDDocument doc = new PDDocument()) {
//						addImageAToPDF(doc, imageFilePath);
//						doc.save(pdfFilePath);
//						FileUtils.deleteQuietly(new File(imageFilePath)); // image file delete
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//				return true;
//			};
//			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
//			mainFuture.get(3, TimeUnit.MINUTES);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			try {
//				Thread.sleep(100);
//				FileUtils.deleteQuietly(new File(tempPath));
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//
//		}
//	}
//
//	public static void captureFullPageScreenshot(Double screenshotLoadTime,
//			ChromeDevToolsService devToolsService, Page page, String outputFilename) {
//		try {
//			if (screenshotLoadTime != null && screenshotLoadTime > 0) {
//				Thread.sleep(Math.round(screenshotLoadTime));
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		final LayoutMetrics layoutMetrics = page.getLayoutMetrics();
//		final double width = layoutMetrics.getContentSize().getWidth();
//		final double height = layoutMetrics.getContentSize().getHeight();
//		final Emulation emulation = devToolsService.getEmulation();
//		emulation.setDeviceMetricsOverride(Double.valueOf(width).intValue(), Double.valueOf(height).intValue(), 1.0d,
//				Boolean.FALSE);
//		Viewport viewport = new Viewport();
//		viewport.setScale(1d);
//		// You can set offset with X, Y
//		viewport.setX(0d);
//		viewport.setY(0d);
//		// Set a width, height of a page to take screenshot at
//		viewport.setWidth(width);
//		viewport.setHeight(height);
//		dump(outputFilename,
//				page.captureScreenshot(CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE));
//	}
//
//	private static void dump(String fileName, String data) {
//		FileOutputStream fileOutputStream = null;
//		try {
//			File file = new File(fileName);
//			fileOutputStream = new FileOutputStream(file);
//			fileOutputStream.write(Base64.getDecoder().decode(data));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (fileOutputStream != null) {
//				try {
//					fileOutputStream.flush();
//					fileOutputStream.close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//	}
//
//	public static void addImageAToPDF(PDDocument doc, String imagePath) {
//		try {
//			PDImageXObject image = PDImageXObject.createFromFile(imagePath, doc);
//			PDRectangle pageSize = PDRectangle.A4;
//
//			int originalWidth = image.getWidth();
//			int originalHeight = image.getHeight();
//			float pageWidth = pageSize.getWidth();
//			float pageHeight = pageSize.getHeight();
//			float ratio = Math.min(pageWidth / originalWidth, pageHeight / originalHeight);
//			float scaledWidth = originalWidth * ratio;
//			float scaledHeight = originalHeight * ratio;
//			float x = (pageWidth - scaledWidth) / 2;
//			float y = (pageHeight - scaledHeight) / 2;
//
//			PDPage page = new PDPage(pageSize);
//			doc.addPage(page);
//			try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
//				contents.drawImage(image, x, y, scaledWidth, scaledHeight);
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//
//		}
//	}
//
//	/**
//	 * Returns a random temp dir.
//	 *
//	 * @return Random temp data dir absolute path.
//	 * @throws RuntimeException If it fails to create temp directory.
//	 */
//	public static String randomTempDir(String prefix) {
//		try {
//			return Files.createTempDirectory(prefix).toAbsolutePath().toString();
//		} catch (IOException e) {
//			throw new RuntimeException("Failed creating temp directory for " + prefix, e);
//		}
//	}
//}
