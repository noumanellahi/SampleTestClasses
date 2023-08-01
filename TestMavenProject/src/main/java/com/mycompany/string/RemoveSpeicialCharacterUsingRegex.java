package com.mycompany.string;

import com.mycompany.extension.ScraperConstant;

public class RemoveSpeicialCharacterUsingRegex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test ="1_Ð¡ont%&$.pdf";
		System.out.println(test.replaceAll(ScraperConstant.SPECIAL_CHARACTER_REGEX, ""));
		
		
	}

}
