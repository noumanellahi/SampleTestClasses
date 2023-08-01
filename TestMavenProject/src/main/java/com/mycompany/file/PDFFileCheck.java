package com.mycompany.file;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFFileCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File pdfFile = new File("C:\\Users\\Noman.Alahi\\Desktop\\2101.09701.pdf");
		PDDocument doc = null;
		try {
			doc = PDDocument.load(pdfFile);
//			System.out.println(new Date());
//			if(StringUtils.isNotBlank(new PDFTextStripper().getText(doc))) {
//				System.out.println(true);
//			}
//			System.out.println(new Date());

			System.out.println(new Date());
			PDFTextStripper reader = new PDFTextStripper();
			reader.setStartPage(0);
			reader.setEndPage(1);

			if (StringUtils.isNotBlank(reader.getText(doc))) {
				System.out.println(true);
			}
			System.out.println(new Date());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
