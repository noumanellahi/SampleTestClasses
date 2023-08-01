package com.mycompany.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class TextFileValidation {

	public static void main(String[] args) {
		InputStream inputStream = null;
		Scanner myReader = null;
		try {
			inputStream = new FileInputStream(new File("C:\\Users\\NomanAlahi\\Desktop\\2013_07_344.txt"));
			myReader = new Scanner(inputStream);
			myReader.nextLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (myReader != null) {
					myReader.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}
}
