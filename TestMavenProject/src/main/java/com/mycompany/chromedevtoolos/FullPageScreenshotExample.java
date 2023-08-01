package com.mycompany.chromedevtoolos;

import org.openqa.selenium.TakesScreenshot;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.github.kklisura.cdt.launch.ChromeArguments;
import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.DOM;
import com.github.kklisura.cdt.protocol.commands.Emulation;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat;
import com.github.kklisura.cdt.protocol.types.page.LayoutMetrics;
import com.github.kklisura.cdt.protocol.types.page.Viewport;
import com.github.kklisura.cdt.protocol.commands.Runtime;
import com.github.kklisura.cdt.protocol.types.runtime.Evaluate;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

/**
 *
 * @author Noman.Alahi
 */
public class FullPageScreenshotExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		// Make a PDF file with Web page ScreenShot
		test();
	}

	public static void test() {
//		String url = "https://www.bavarian-nordic.com/what-we-do/pipeline.aspx";
//		String tag = "body > div.mainContentWrapper > div > div > div > div > article > div > table";

//		String url = "https://www.hanmipharm.com/ehanmi/handler/Rnd-PipelineTable";
//		String tag = "#printContent";

//		String url = "https://www.4sc.com/clinical-trials/";
//		String tag = "table.MsoNormalTable";

//		String url = "https://acceleronpharma.com/science-pipeline/luspatercept/";
//		String tag = "#pipeline";
//
//		String url = "http://acceleronpharma.com/science-pipeline/pipeline/";
//		String tag = "#pipeline";

//		String url = "https://acceleronpharma.com/science-pipeline/sotatercept/";
//		String tag = "#pipeline > div.table-responsive.exp-on-scroll > table";

//		String url = "https://www.adamispharmaceuticals.com/pipeline/";
//		String tag = "table.pipeline_table.responsive.stacktable.large-only";

//		String url = "https://www.soligenix.com/pipeline-programs/";
//		String tag = "div.wp-block-emagine-pipeline.em-pipeline.em-pipeline--full-width-phases";

//		String url = "https://sombiotech.com/pipeline/";
//		String tag = "div.col.PIPELINE-TABLE.small-12.large-12 > div > table";

//		String url = "https://sorrentotherapeutics.com/research/pipeline/";
//		String tag = "table.extras_table2.desktop.extras_fourths > tbody";

//		String url = "https://www.tychonbio.com/pipeline";
//		String tag = "#pipelineTable > tbody";

//		String url = "https://asclepix.com/pipeline/";
//		String tag = "#tab-our-pipeline > div.wpb_raw_code.wpb_content_element.wpb_raw_html > div > table";

//		String url = "https://mannkindcorp.com/pipeline/";
//		String tag = "#mkPipeline";

//		String url = "https://www.pdsbiotech.com/pipeline/infectious-disease";
//		String tag = "#g-main > div > div > div > div > div > div > div > div > div > div > div.progress-overview.pipelinechart > table";

//		String url = "https://www.pdsbiotech.com/pipeline/oncology";
//		String tag = "#g-main > div > div > div > div > div > div > div > div > div > div > div.progress-overview.pipelinechart > table";

//		String url = "https://www.arenapharm.com/pipeline/";
//		String tag = "body > main > section:nth-child(10) > div";

//		String url = "https://www.armatapharma.com/pipeline/pipeline-overview/";
//		String tag = "table.wp-block-table.alignwide.pipeline-desk";

//		String url = "https://www.atarabio.com/pipeline/";
//		String tag = "#genesis-content > section.expandable_table.expandable_table_last";

//		String url = "https://www.athenex.com/pipeline/";
//		String tag = "#footable_38667";

//		String url = "https://autifony.com/pipeline/";
//		String tag = "#tablepress-1";

		String url = "https://www.atarabio.com/pipeline/";
		String tag = "#genesis-content > section:nth-child(4)";
		
		
//		String url = "https://liddspharma.com/pipeline/";
//		String tag = "table.tablepress.tablepress-id-3";

		String fileName = "C:\\Users\\Noman.Alahi\\Desktop\\sc\\new_24.png";

		String tempPath = randomTempDir("cdt-user-data-dir");
		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
		ExecutorService screenShootexecutor = Executors.newSingleThreadExecutor();
		// set no-sandbox true to bypass OS security in docker image
		Map<String, Object> additionalChromeArguments = new HashMap<>();
		additionalChromeArguments.put("no-sandbox", true);
		additionalChromeArguments.put("start-maximized", true);
		additionalChromeArguments.put("window-size", "1920,1080");

		ChromeLauncher launcher = new ChromeLauncher();
		// create chrome argument with additional properties and headless true
		ChromeArguments chromeArguments = ChromeArguments.builder().noFirstRun().noDefaultBrowserCheck()
				.userDataDir(tempPath).disableBackgroundNetworking().disableBackgroundTimerThrottling()
				.disableClientSidePhishingDetection().disableDefaultApps().disableExtensions().disableHangMonitor()
				.disablePopupBlocking().disablePromptOnRepost().disableSync().disableTranslate()
				.metricsRecordingOnly(false).safebrowsingDisableAutoUpdate().headless(false).disableGpu(true)
				.hideScrollbars(true).muteAudio(true).additionalArguments(additionalChromeArguments).build();

		ChromeService chromeService = launcher.launch(chromeArguments);
		try {
//			Callable<Boolean> mainTask = () -> {

			// Create empty tab ie about:blank.
			final ChromeTab tab = chromeService.createTab();

			// Get DevTools service to this tab
			final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

			// Get individual commands
			final Page page = devToolsService.getPage();

			// new approach
			page.onLoadEventFired(event -> {
				System.out.println("Taking screenshot...");
				Callable<Boolean> callableTask = () -> {
					// Make a PDF file with Web page ScreenShot
					captureFullPageScreenshot(devToolsService, page, fileName, tag);
					return true;
				};
				Future<Boolean> future = screenShootexecutor.submit(callableTask);
				try {
					future.get(120, TimeUnit.MINUTES);
				} catch (InterruptedException | ExecutionException | TimeoutException e) {
					// TODO Auto-generated catch block
					System.out.println("Event listner interupted");
					e.printStackTrace();
				}

				System.out.println("Done!");
				devToolsService.close();
			});

			// Enable page events.
			page.enable();

			// Navigate to github.com.
			page.navigate(url);
			devToolsService.waitUntilClosed();

//				if (new File(fileName).exists()) {
//					try (PDDocument doc = new PDDocument()) {
//						addImageAsNewPage(doc, fileName);
//						doc.save("C:\\Users\\Noman.Alahi\\Desktop\\sc\\SampleScreenShot.pdf");
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//				return true;
//			};
//			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
//			mainFuture.get(20, TimeUnit.SECONDS);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				System.out.println(screenShootexecutor.shutdownNow());
				System.out.println(mainExecutor.shutdownNow());
				Thread.sleep(200);
//				launcher.close();
//				System.out.println("lancher is alive" + launcher.isAlive());
				FileUtils.deleteQuietly(new File(tempPath));
			} catch (Exception ex) {

			}
		}

	}

	private static void captureFullPageScreenshot(ChromeDevToolsService devToolsService, Page page,
			String outputFilename, String tag) throws InterruptedException {
		// scroll down
//		final Runtime runtime = devToolsService.getRuntime();
//		int totalHeight = (int) runtime.evaluate("document.body.scrollHeight").getResult().getValue();
//		for (int i = 1; i <= 5; i++) {
//			runtime.evaluate("window.scrollTo(0," + (totalHeight / 5) * i + ")");
//			Thread.sleep(2000);
//		}

//		Thread.sleep(5000);
		final Runtime runtime = devToolsService.getRuntime();
		runtime.evaluate(
				"(function(){x=document.querySelectorAll('*');for(i=0;i<x.length;i++){elementStyle=getComputedStyle(x[i]);if(elementStyle.position=='fixed'||elementStyle.position=='sticky'){x[i].remove();}}}())");
		runtime.evaluate(
				"(function() {l = document.getElementsByTagName(\"a\"); for (var i =0; i<l.length; i++) {l[i].href = \"\"; } }())");
		System.out.println("Caputring start");
		
		final LayoutMetrics layoutMetrics = page.getLayoutMetrics();

		final double pageWidth = layoutMetrics.getContentSize().getWidth();
		final double pageHeight = layoutMetrics.getContentSize().getHeight();

		System.out.println(pageWidth);
		System.out.println(pageHeight);
		
		final Emulation emulation = devToolsService.getEmulation();
		emulation.setDeviceMetricsOverride(Double.valueOf(pageWidth).intValue(), Double.valueOf(pageHeight).intValue(),
				1.0d, Boolean.FALSE);

		final DOM dom = devToolsService.getDOM();
		final Integer nodeId = dom.querySelector(devToolsService.getDOM().getDocument().getNodeId(), tag);

		Viewport viewport = new Viewport();
		viewport.setScale(1d);

		
		System.out.println(dom.getContentQuads(nodeId, null, null).get(0).get(0));
		System.out.println(dom.getContentQuads(nodeId, null, null).get(0).get(1));
		// You can set offset with X, Y
		viewport.setX(dom.getContentQuads(nodeId, null, null).get(0).get(0));
		viewport.setY(dom.getContentQuads(nodeId, null, null).get(0).get(1));

		// Set a width, height of a page to take screenshot at
		viewport.setWidth(new Double(dom.getBoxModel(nodeId, null, null).getWidth()));
		viewport.setHeight(new Double(dom.getBoxModel(nodeId, null, null).getHeight()));

		Thread.sleep(5000);
		String sc = page.captureScreenshot(CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE);

		dump(outputFilename, sc);
	}

	private static void dump(String fileName, String data) {
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(fileName);
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(Base64.getDecoder().decode(data));
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

	private static void addImageAsNewPage(PDDocument doc, String imagePath) {
		try {
			PDImageXObject image = PDImageXObject.createFromFile(imagePath, doc);
			PDRectangle pageSize = PDRectangle.A4;

			int originalWidth = image.getWidth();
			int originalHeight = image.getHeight();
			float pageWidth = pageSize.getWidth();
			float pageHeight = pageSize.getHeight();
			float ratio = Math.min(pageWidth / originalWidth, pageHeight / originalHeight);
			float scaledWidth = originalWidth * ratio;
			float scaledHeight = originalHeight * ratio;
			float x = (pageWidth - scaledWidth) / 2;
			float y = (pageHeight - scaledHeight) / 2;

			PDPage page = new PDPage(pageSize);
			doc.addPage(page);
			try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
				contents.drawImage(image, x, y, scaledWidth, scaledHeight);
			}
			System.out.println("Added: " + imagePath);
		} catch (IOException e) {
			System.err.println("Failed to process: " + imagePath);
			e.printStackTrace(System.err);
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
