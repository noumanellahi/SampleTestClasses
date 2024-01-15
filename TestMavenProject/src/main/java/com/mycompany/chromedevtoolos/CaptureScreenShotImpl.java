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
//import org.apache.commons.lang3.StringUtils;
//
//import com.github.kklisura.cdt.launch.ChromeArguments;
//import com.github.kklisura.cdt.launch.ChromeLauncher;
//import com.github.kklisura.cdt.protocol.commands.DOM;
//import com.github.kklisura.cdt.protocol.commands.Emulation;
//import com.github.kklisura.cdt.protocol.commands.Page;
//import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat;
//import com.github.kklisura.cdt.protocol.types.page.LayoutMetrics;
//import com.github.kklisura.cdt.protocol.types.page.Viewport;
//import com.github.kklisura.cdt.services.ChromeDevToolsService;
//import com.github.kklisura.cdt.services.ChromeService;
//import com.github.kklisura.cdt.services.exceptions.ChromeDevToolsInvocationException;
//import com.github.kklisura.cdt.services.types.ChromeTab;
//
//public class CaptureScreenShotImpl {
//	private static boolean fullWebPageScreenshot;
//
//	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//		// Make a PDF file with Web page ScreenShot
//		captureSinglePageScreenshot("https://www.hanmipharm.com/ehanmi/handler/Rnd-PipelineTable",
//				"C:\\Users\\Noman.Alahi\\Desktop\\sc\\test.png", "#printContentdfg");
//	}
//
//	public static boolean captureSinglePageScreenshot(String url, String imageFilePath, String tag) {
//		String tempPath = randomTempDir("cdt-user-data-dir");
//		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
//		ExecutorService screenShootExecutor = Executors.newSingleThreadExecutor();
//		try (ChromeLauncher launcher = new ChromeLauncher()) {
//			Callable<Boolean> mainTask = () -> {
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
//				ChromeService chromeService = launcher.launch(chromeArguments);
//				// Create empty tab ie about:blank.
//				ChromeTab tab = chromeService.createTab();
//				// Get DevTools service to this tab
//				ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);
//				// Get individual commands
//				Page page = devToolsService.getPage();
//				page.onLoadEventFired(event -> {
//
//					Callable<Boolean> screenShootTask = () -> {
//						captureFullPageScreenshot(devToolsService, page, imageFilePath, tag);
//						return true;
//					};
//					Future<Boolean> screenShootFuture = screenShootExecutor.submit(screenShootTask);
//					try {
//						screenShootFuture.get(2, TimeUnit.MINUTES);
//						setFullWebPageScreenshot(true);
//					} catch (InterruptedException | ExecutionException | TimeoutException e) {
//						setFullWebPageScreenshot(false);
//						System.out.println(e);
//					}
//					devToolsService.close();
//				});
//				// Enable page events.
//				page.enable();
//				page.navigate(url);
//				devToolsService.waitUntilClosed();
//				return true;
//			};
//			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
//			mainFuture.get(3, TimeUnit.MINUTES);
//
//			return isFullWebPageScreenshot();
//		} catch (Exception ex) {
//			System.out.println("Failed to load chrome" + ex);
//			return isFullWebPageScreenshot();
//		} finally {
//			try {
//				mainExecutor.shutdownNow();
//				screenShootExecutor.shutdownNow();
//				Thread.sleep(100);
//				FileUtils.deleteQuietly(new File(tempPath));
//			} catch (Exception ex) {
//				System.out.println(ex);
//			}
//
//		}
//	}
//
//	public static void captureFullPageScreenshot(ChromeDevToolsService devToolsService, Page page,
//			String outputFilename, String tag) {
//		try {
////			if (true) {
////				// scroll down
////				final Runtime runtime = devToolsService.getRuntime();
////				int totalHeight = (int) runtime.evaluate("document.body.scrollHeight").getResult().getValue();
////				for (int i = 1; i <= 5; i++) {
////					runtime.evaluate("window.scrollTo(0," + (totalHeight / 5) * i + ")");
////					Thread.sleep(2000);
////				}
////			}
//			final LayoutMetrics layoutMetrics = page.getLayoutMetrics();
//			final double width = layoutMetrics.getContentSize().getWidth();
//			final double height = layoutMetrics.getContentSize().getHeight();
//			final Emulation emulation = devToolsService.getEmulation();
//			if ((StringUtils.isBlank(tag))) {
//				Thread.sleep(3000);
//				emulation.setDeviceMetricsOverride(Double.valueOf(width).intValue(), Double.valueOf(height).intValue(),
//						1.0d, Boolean.FALSE);
//				Viewport viewport = new Viewport();
//				viewport.setScale(1d);
//				// You can set offset with X, Y
//				viewport.setX(0d);
//				viewport.setY(0d);
//				// Set a width, height of a page to take screenshot at
//				viewport.setWidth(width);
//				viewport.setHeight(height);
//				dump(outputFilename, page.captureScreenshot(CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE));
//			} else {
//				emulation.setDeviceMetricsOverride(Double.valueOf(width).intValue(), Double.valueOf(height).intValue(),
//						1.0d, Boolean.FALSE);
//
//				final DOM dom = devToolsService.getDOM();
//				final Integer nodeId = dom.querySelector(devToolsService.getDOM().getDocument().getNodeId(), tag);
//
//				Viewport viewport = new Viewport();
//				viewport.setScale(1d);
//
//				// You can set offset with X, Y
//				viewport.setX(dom.getContentQuads(nodeId, null, null).get(0).get(0));
//				viewport.setY(dom.getContentQuads(nodeId, null, null).get(0).get(1));
//
//				// Set a width, height of a page to take screenshot at
//				viewport.setWidth(new Double(dom.getBoxModel(nodeId, null, null).getWidth()));
//				viewport.setHeight(new Double(dom.getBoxModel(nodeId, null, null).getHeight()));
//
//				Thread.sleep(3000);
//				dump(outputFilename, page.captureScreenshot(CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE));
//			}
//		} catch (ChromeDevToolsInvocationException e) {
//			System.out.println(e.getMessage());
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//
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
//					System.out.println(ex);
//				}
//			}
//		}
//	}
//
//	public static boolean isFullWebPageScreenshot() {
//		return fullWebPageScreenshot;
//	}
//
//	public static void setFullWebPageScreenshot(boolean fullWebPageScreenshot) {
//		fullWebPageScreenshot = fullWebPageScreenshot;
//	}
//
//	public static String randomTempDir(String prefix) {
//		try {
//			return Files.createTempDirectory(prefix).toAbsolutePath().toString();
//		} catch (IOException e) {
//			throw new RuntimeException("Failed creating temp directory for " + prefix, e);
//		}
//	}
//}
