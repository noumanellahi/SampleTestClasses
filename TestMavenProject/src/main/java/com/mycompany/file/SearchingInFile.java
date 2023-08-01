package com.mycompany.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SearchingInFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File revieved = new File("C:\\Users\\NomanAlahi\\Desktop\\xml.txt");
			File consumed = new File("C:\\Users\\NomanAlahi\\Desktop\\pdf.txt");

			List<String> recievedList = new ArrayList<String>();
			List<String> consumedList = new ArrayList<String>();

			fr = new FileReader(revieved);
			br = new BufferedReader(fr);
			String str = null;
			while ((str = br.readLine()) != null) {
				recievedList.add(str.toString());
			}
			fr = null;
			br = null;

			fr = new FileReader(consumed);
			br = new BufferedReader(fr);

			while ((str = br.readLine()) != null) {
				consumedList.add(str.toString());
			}

			System.out.println(recievedList.size());
			System.out.println(consumedList.size());

			for (int i = 0; i < recievedList.size(); i++) {
				if (!consumedList.contains(recievedList.get(i))) {
					System.out.println(recievedList.get(i));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

}
