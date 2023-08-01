package com.mycompany.testmavenproject;

import java.io.File;

public class FilePathSplitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "\\var\\lib\\1080\\20-06-01";
		
		String newPath = path.substring(0, path.lastIndexOf(File.separator));
		
		System.out.println(newPath);
		
//		for(String item : pathArray) {
//			System.out.println(item);
//		}
	}

}
