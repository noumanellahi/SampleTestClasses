package com.mycompany.testmavenproject;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileSize {
	public static void main(String args[]) {
		File file = new File("C:\\Users\\Noman.Alahi\\Desktop\\270972.pdf");
//		System.out.println(FileUtils.byteCountToDisplaySize(file.length()));
		double bytes = file.length();
		double kilobytes = (bytes / 1024);
		System.out.println(kilobytes);
	}
}
