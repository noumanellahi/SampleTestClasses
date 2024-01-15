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
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//import org.openqa.selenium.Rectangle;
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
//public class GetScreenShootOfSpecificArea {
//
//	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//		// Make a PDF file with Web page ScreenShot
//		Rectangle rectangle = GetElementCoordinates.getCoordinates();
//		System.out.println("Width" + rectangle.width);
//		System.out.println("height" + rectangle.height);
//		System.out.println("x" + rectangle.x);
//		System.out.println("y" + rectangle.y);
//		test(new Double(rectangle.width), new Double(rectangle.height), 19d, new Double(rectangle.y));
//	}
//
//	public static void test(Double width, Double height, Double x, Double y) {
//		String tempPath = randomTempDir("cdt-user-data-dir");
//		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
//		ExecutorService screenShootexecutor = Executors.newSingleThreadExecutor();
//		// set no-sandbox true to bypass OS security in docker image
//		Map<String, Object> additionalChromeArguments = new HashMap<>();
//		additionalChromeArguments.put("no-sandbox", true);
//		additionalChromeArguments.put("start-maximized", true);
//		additionalChromeArguments.put("window-size", "1920,1080");
//
//		ChromeLauncher launcher = new ChromeLauncher();
//		// create chrome argument with additional properties and headless true
//		ChromeArguments chromeArguments = ChromeArguments.builder().noFirstRun().noDefaultBrowserCheck()
//				.userDataDir(tempPath).disableBackgroundNetworking().disableBackgroundTimerThrottling()
//				.disableClientSidePhishingDetection().disableDefaultApps().disableExtensions().disableHangMonitor()
//				.disablePopupBlocking().disablePromptOnRepost().disableSync().disableTranslate()
//				.metricsRecordingOnly(false).safebrowsingDisableAutoUpdate().headless(false).disableGpu(true)
//				.hideScrollbars(true).muteAudio(true).additionalArguments(additionalChromeArguments).build();
//
//		ChromeService chromeService = launcher.launch(chromeArguments);
//		try {
////			Callable<Boolean> mainTask = () -> {
//
//			// Create empty tab ie about:blank.
//			final ChromeTab tab = chromeService.createTab();
//
//			// Get DevTools service to this tab
//			final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);
//
//			// Get individual commands
//			final Page page = devToolsService.getPage();
//
//			String fileName = "C:\\Users\\Noman.Alahi\\Desktop\\sc\\screenshot2.png";
//
//			// new approach
//			page.onLoadEventFired(event -> {
//				System.out.println("Taking screenshot...");
//				Callable<Boolean> callableTask = () -> {
//					// Make a PDF file with Web page ScreenShot
//					captureFullPageScreenshot(devToolsService, page, fileName, width, height, x, y);
//					return true;
//				};
//				Future<Boolean> future = screenShootexecutor.submit(callableTask);
//				try {
//					future.get(120, TimeUnit.SECONDS);
//				} catch (InterruptedException | ExecutionException | TimeoutException e) {
//					// TODO Auto-generated catch block
//					System.out.println("Event listner interupted");
//					e.printStackTrace();
//				}
//
//				System.out.println("Done!");
//				devToolsService.close();
//			});
//
//			// Enable page events.
//			page.enable();
//
//			// Navigate to github.com.
////			page.navigate("https://www.bavarian-nordic.com/what-we-do/pipeline.aspx");
//			page.navigate("https://www.hanmipharm.com/ehanmi/handler/Rnd-PipelineTable");
////			page.navigate("https://www.4sc.com/clinical-trials/");
//			devToolsService.waitUntilClosed();
//
////			if (new File(fileName).exists()) {
////				try (PDDocument doc = new PDDocument()) {
////					addImageAsNewPage(doc, fileName);
////					doc.save("C:\\Users\\Noman.Alahi\\Desktop\\Testing\\SampleScreenShot.pdf");
////				} catch (Exception ex) {
////					ex.printStackTrace();
////				}
////			}
////				return true;
////			};
////			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
////			mainFuture.get(20, TimeUnit.SECONDS);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			try {
//				System.out.println(screenShootexecutor.shutdownNow());
//				System.out.println(mainExecutor.shutdownNow());
//				Thread.sleep(200);
////				launcher.close();
////				System.out.println("lancher is alive" + launcher.isAlive());
////				FileUtils.deleteQuietly(new File(tempPath));
//			} catch (Exception ex) {
//
//			}
//		}
//
//	}
//
//	private static void captureFullPageScreenshot(ChromeDevToolsService devToolsService, Page page,
//			String outputFilename, Double width, Double height, Double x, Double y) throws InterruptedException {
//		System.out.println("Caputring start");
//		final LayoutMetrics layoutMetrics = page.getLayoutMetrics();
//
////		final double width11 = layoutMetrics.getCssContentSize().getWidth();
////		final double height11 = layoutMetrics.getCssContentSize().getHeight();
//
//		final Emulation emulation = devToolsService.getEmulation();
//	    emulation.setDeviceMetricsOverride(
//	        Double.valueOf(width).intValue(), Double.valueOf(height).intValue(), 1.0d, Boolean.FALSE);
//
//		Viewport viewport = new Viewport();
//		viewport.setScale(1.0d);
//
//		// You can set offset with X, Y
//		viewport.setX(x);
//		viewport.setY(y);
//
//		// Set a width, height of a page to take screenshot at
//		viewport.setWidth(width);
//		viewport.setHeight(height);
//
//		String sc = page.captureScreenshot(CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE);
//		dump(outputFilename, sc);
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
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	private static void addImageAsNewPage(PDDocument doc, String imagePath) {
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
//			System.out.println("Added: " + imagePath);
//		} catch (IOException e) {
//			System.err.println("Failed to process: " + imagePath);
//			e.printStackTrace(System.err);
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
