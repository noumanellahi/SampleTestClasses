package com.mycompany.testmavenproject.pdfBox;

import java.io.File;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.PDFMergerUtility.AcroFormMergeMode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;

public class MergeFileIssue {
	public static void main(String args[]) {
		try {
			String basePath = "C:\\Users\\Noman.Alahi\\Desktop\\MergeIssue";
			String mergeFileName = "Merged_File.pdf";
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.setDestinationFileName(basePath + File.separator + mergeFileName);
			ut.setAcroFormMergeMode(AcroFormMergeMode.JOIN_FORM_FIELDS_MODE);
			File[] files = new File(basePath).listFiles();
			for (File file : files) {
				try (PDDocument pdfDocument = PDDocument.load(file)) {
					if (new File(basePath + File.separator + file.getName()).isFile()) {
						PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();
//						if (acroForm != null && acroForm.xfaIsDynamic()) {
//							System.out.println("XFA file format not supported");
//						} else {
							ut.addSource(basePath + File.separator + file.getName());
//						}
					}
					ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
				} catch (Exception ex) {

					System.out.println("Can not merge this file " + file.getName());
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
