package com.mycompany.extension;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class GetFileNameFromStringPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String filePath = "C:\\Users\\NomanAlahi\\Desktop\\grant_yb2_20220215.tar";
		String fileName = "grant_yb2_20220215.tar";
		
		System.out.println(FilenameUtils.getBaseName(fileName));
		System.out.println(FilenameUtils.getExtension(fileName));
		System.out.println(FilenameUtils.getFullPath(fileName));
		
//		File latestDownloadedFile = new File(filePath);
		
//		System.out.println(FilenameUtils.getBaseName(latestDownloadedFile.getPath()).replace(".tar", ""));
//		System.out.println(FilenameUtils.getExtension(filePath));
//		System.out.println(FilenameUtils.getFullPath(filePath));
//		
//		System.out.println(latestDownloadedFile.getAbsolutePath());
//		System.out.println(latestDownloadedFile.getPath());
		
	}

}
