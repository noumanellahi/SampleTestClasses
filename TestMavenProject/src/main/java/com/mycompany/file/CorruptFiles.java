package com.mycompany.file;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.io.IOException;

public class CorruptFiles {
	public static void main(String args[]) {
		try {
	      File pdfFile = new File("C:\\Users\\Noman.Alahi\\Desktop\\ocrscan.pdf");
	        PDDocument doc = null;
	        try {
	            doc = PDDocument.load(pdfFile);
	            System.out.println(new PDFTextStripper().getText(doc));
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
