package com.mycompany.regex;

public class ReplaceCurlyBrackets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String urlTemplate = "https://api.biorxiv.org/details/biorxiv/{fromDate}/{toDate}/{cursor}/xml";
		String controlName = "fromDate";
		String regex = "\\[(.*?)\\]";

		urlTemplate = urlTemplate.replace("{" + controlName + "}", "2022-02-24");

		System.out.println(urlTemplate);
	}

}
