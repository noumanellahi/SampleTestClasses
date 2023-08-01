package com.mycompany.file;

import java.io.File;

public class WrongFilePath {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			File file = new File("C:\\Users\\Noman.Alahi\\Desktop\\1272\\defination.pdf");
			if (file.isFile()) {
				System.out.println("yabadabadooo");
			} else {
				System.out.println("ni mili");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
