package com.mycompany.file;

import java.io.File;

public class SizeOfFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = new File("C:\\Users\\NomanAlahi\\Desktop\\USPTO\\app_yb2_2023032390");
			if(file.length() <= 0) {
				System.out.println("FALSE");
			} else {
				System.out.println(file.length());
				System.out.println("TRUE");
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
