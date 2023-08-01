package com.mycompany.file;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelCorruptFile {
	public static void main(String args[]) {
		try {
			Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\Noman.Alahi\\Desktop\\file_example_XLSX_10.xlsx"));
			System.out.println(workbook.getNumberOfSheets());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
