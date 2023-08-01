/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Noman.Alahi
 */
public class RegexTest {

	public static void main(String[] args) {
//        String test = "\\ / Hello +~` (2019*) 意意字 & #%@(_&^%$#@!+;{}[]'\"<>.pdf" ;
		String test ="page1";
//        System.out.println( test.replaceAll("\\p{ASCII}\\p{Alpha}", "_"));
        
//        test = "/|\\\\|\\:|\\;|\\!|\"|\\||\\,|\\s|\\?|\\=|\"|\\*|\\-";
//        
//        (_&^%$#@!__+;_'__<>
        
//        Pattern regex = Pattern.compile("^[a-zA-Z0-9]*$");
        
//       test = test.replaceAll("[\\[\\]{}()!*+~`%^<>$&+,:;=?@#|'\"\\\\/]", "_");
//       System.out.println(test);
		if(test.matches("-?\\d+(\\.\\d+)?")) {
			System.out.println("yes");
		}
        
        
        
//        String stringURL = "https://stackoverflow.com/questions/4283351/how-to-replace-special-characters-in-a-string";
//        String[] urlParts = stringURL.split("/");
//        System.out.println(urlParts.length);
//        System.out.println(urlParts[6]);
    }

}
