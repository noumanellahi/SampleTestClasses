/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;
//import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Noman.Alahi
 */
public class StringDifference {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("565656");
		list.add(">>");
		list.add("abc");
		for (String str : list) {
			if (str.matches("-?\\d+(\\.\\d+)?")) {
				System.out.println(str);
			}
		}

//    	List<String> crawlingLevelDetailsList = null;
//    	
//    	if(crawlingLevelDetailsList != null && crawlingLevelDetailsList.isEmpty()) {
//    		System.out.println("true");
//    	} else {
//    		System.out.println("herer");
//    	}

//        String s1 = ".eml";
//        String s2 = ".pdf";
//        
//        if(s1 == ".eml") {
//        	System.out.println("here we go");
//        }
//        
//        String[] filePart = s1.split("\\.");
//        String newFileName = filePart[0] + "_Mac." + filePart[0];
//        
//        System.out.println(filePart[0]);
//        
//        String s=  "AAAAAAAAAA";
//        s = s.toLowerCase();
//        System.out.println(s);
//        String str1 = "http:/www.pmda.go.jp/drugs/2016/P20161214002/170050000_22800AMX00696000_A100_1.pdf";
//
//        String str2 = "http://www.pmda.go.jp/drugs/2019/P20190115001/170050000_22800AMX00696000_A100_1.pdf";
//        
//        String[] urlParts = str1.split("/");
//        
//        System.out.println(urlParts.length);
//        
//        System.out.println(urlParts[urlParts.length - 1]);
//
//        System.out.println(StringUtils.difference("str1", "str2"));
	}

}
