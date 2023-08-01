package com.mycompany.file;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PDFFileValidationCheck {

	public static void main(String[] args) {
		Workbook workbook = null;
		try {
			File pdfFile = new File("C:\\Users\\NomanAlahi\\Desktop\\corrupt_file_test\\test.pdf");
			workbook = WorkbookFactory.create(pdfFile);

			workbook.getNumberOfSheets();
			System.out.println("true");
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}
}
