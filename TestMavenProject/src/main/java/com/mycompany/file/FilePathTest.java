package com.mycompany.file;

import java.io.File;

public class FilePathTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File localPath = new File("E:\\mac-branch");
		for(File name : localPath.listFiles()) {
			System.out.println(name.getName());
		}
	}

}
