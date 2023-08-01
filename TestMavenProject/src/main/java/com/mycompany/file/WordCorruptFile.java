package com.mycompany.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordCorruptFile {
	public static void main(String[] args) {
//		File file = null;
//		WordExtractor extractor = null;
//		try {
//			file = new File("C:\\Users\\Noman.Alahi\\Desktop\\test_rtf.rtf");
//			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//			HWPFDocument document = new HWPFDocument(fis);
//			extractor = new WordExtractor(document);
//			String[] fileData = extractor.getParagraphText();
//			for (int i = 0; i < fileData.length; i++) {
//				if (fileData[i] != null)
//					System.out.println(fileData[i]);
//			}
//		} catch (Exception exep) {
//			exep.printStackTrace();
//		}
//	}
		try {
			File file = new File("C:\\Users\\Noman.Alahi\\Desktop\\test_rtf.rtf");
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);

			List<XWPFParagraph> paragraphs = document.getParagraphs();

			for (XWPFParagraph para : paragraphs) {
				para.getText();
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
