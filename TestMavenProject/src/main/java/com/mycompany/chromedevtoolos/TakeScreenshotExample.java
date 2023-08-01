package com.mycompany.chromedevtoolos;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class TakeScreenshotExample {
	public static void main(String[] args) {
		// Create chrome launcher.
		final ChromeLauncher launcher = new ChromeLauncher();

		// Launch chrome either as headless (true) or regular (false).
		final ChromeService chromeService = launcher.launch(false);

		// Create empty tab ie about:blank.
		final ChromeTab tab = chromeService.createTab();

		// Get DevTools service to this tab
		final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

		// Get individual commands
		final Page page = devToolsService.getPage();

		String fileName = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\screenshot.png";

		page.onLoadEventFired(event -> {
			System.out.println("Taking screenshot...");
			dump(fileName, page.captureScreenshot());
			try {
				saveInPdf();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Done!");

			devToolsService.close();
		});

		// Enable page events.
		page.enable();

		// Navigate to github.com.
		page.navigate("http://github.com");

		devToolsService.waitUntilClosed();
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

	private static void saveInPdf() throws IOException {
		String filename = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\SampleScreenShot.pdf";

		PDDocument doc = new PDDocument();
		try {
			PDPage page = new PDPage();

			// Creating PDImageXObject object
			PDImageXObject pdImage = PDImageXObject
					.createFromFile("C:\\Users\\Noman.Alahi\\Desktop\\Testing\\screenshot.png", doc);

			// creating the PDPageContentStream object
			PDPageContentStream contents = new PDPageContentStream(doc, page);

			// Drawing the image in the PDF document
			contents.drawImage(pdImage, 70, 250);

			System.out.println("Image inserted");

			// Closing the PDPageContentStream object
			contents.close();
			
			//adding page
			doc.addPage(page);

			// Saving the document
			doc.save(filename);

			// Closing the document
			doc.close();
		} finally {
			doc.close();
		}
	}
}
