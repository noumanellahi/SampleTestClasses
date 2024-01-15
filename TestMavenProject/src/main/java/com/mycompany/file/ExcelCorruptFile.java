package com.mycompany.file;

import java.io.File;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelCorruptFile {
	public static void main(String args[]) {
		try {
//			ZipSecureFile.setMinInflateRatio(0);
			Workbook workbook = WorkbookFactory
					.create(new File("C:\\Users\\NomanAlahi\\Desktop\\INPADOC_coverage_202322.xlsx"));
			System.out.println(workbook.getNumberOfSheets());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
