package com.mycompany.extension;

import org.apache.commons.io.FilenameUtils;

public class SplitFileNameAndExtension {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "test.pdf" ;
		String basename = FilenameUtils.getBaseName(fileName);
		String extension = FilenameUtils.getExtension(fileName);
		
		System.out.println(basename);
		System.out.println(extension);
	}

}
