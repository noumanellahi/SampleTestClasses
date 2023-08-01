package com.mycompany.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import net.lingala.zip4j.ZipFile;

public class ZipCorruptFile {
	public static void main(String args[]) {
		ZipFile zipfile = null;
		try {
			zipfile = new ZipFile(new File("C:\\Users\\NomanAlahi\\Downloads\\FULLTEXT_PDF_20230503.zip"));
			System.out.println(zipfile.isValidZipFile());
			System.out.println(zipfile.getFileHeaders());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (zipfile != null) {
					zipfile.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
