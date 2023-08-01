package com.mycompany.testmavenproject.pdfBox;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractText {
	public static void main(String args[]) {
		try {
			File file = new File(
					"C:\\Users\\Noman.Alahi\\Downloads\\Procedure_and_Guidelines_for_CADTH_CDR.pdf");
			PDFTextStripper tStripper = new PDFTextStripper();
			String content = "";
			String pdfFileInText;
			String[] lines;
			try (PDDocument document = PDDocument.load(file)) {
				document.getClass();
				document.setAllSecurityToBeRemoved(true);
//				if (!document.isEncrypted()) {
					pdfFileInText = tStripper.getText(document);
					lines = pdfFileInText.split("\\r\\n\\r\\n");
					for (String line : lines) {
						content += line.trim();
					}
//				}
			}
			System.out.println("here we are " + content.trim());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
