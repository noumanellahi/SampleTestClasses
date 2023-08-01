/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Noman.Alahi
 */
public class test {

	static public void main(String[] arg) {
		try {
			int a = 1;

			printMethod(a++);

			System.out.println(a);

//        	String a = "abc";
//        	a.concat("def");
//        	System.out.println(a);
//        	
//        	String a2 = new String("abc");
//        	System.out.println(a2 == a);
//        	a = a.concat("123");
//        	System.out.println(100 * 0.10);
//        	System.out.println("20"+(20+10)+"12");
//        	String url = "{SEARCHTERM:aaaa}https://howtodoinjava.com/regex/java-regex-specific-contain-word/";
//        	if(url.startsWith("{SEARCHTERM:")) {
//        		System.out.println("yes");
//        		int index = url.indexOf("http");
//        		
////        		url = url.substring(index, url.length());
//        		
//        		String term = url.substring(0, index);
//        		
//        		System.out.println(term);
//        	}
//        	System.out.println("Here its starts");
//            String htmlFilePath = "D:\\var\\lib\\543\\Source_URL.html";
//            String pdfFilePath = "D:\\var\\lib\\543\\test.pdf";
//            Process p = Runtime.getRuntime().exec("cmd /C start chrome --headless --disable-gpu --print-to-pdf=" + pdfFilePath + " " + htmlFilePath);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(p.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            Thread.sleep(10000);
//            System.out.println("Here its ends");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void printMethod(int a) {
		System.out.println(a);
	}
}
