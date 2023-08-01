package com.mycompany.file;

import java.io.File;
import java.util.Scanner;

public class ReadFilesInTextFormate {
	public static void main(String args[]) {
		try {
			File file = new File("C:\\Users\\Noman.Alahi\\Desktop\\test_xml.xml");
			Scanner myReader = new Scanner(file);
//		      while (myReader.hasNextLine()) {
		          String data = myReader.nextLine();
		          System.out.println("++++"+data);
//		        }
		        myReader.close();
		} catch (Exception ex) {

		}

	}
}
