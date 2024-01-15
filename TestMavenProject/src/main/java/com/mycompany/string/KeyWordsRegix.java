package com.mycompany.string;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyWordsRegix {
	public static void main(String[] args) {

		Map<String, String> elementMap = new LinkedHashMap<>();
		String keywordsString = "Udělený patent";
		String keywordsType = "include";
		String[] keywords = keywordsString.split(",");

		List<String> textElements = new ArrayList<>();
		textElements.add(" Udělený patent");
		textElements.add("World");
		textElements.add("descriptions");
		
		textElements.add("Publication (A1) (en).pdf");
		textElements.add("Abstract (fi).pdf");
		textElements.add("Official action technical (en).pdf");
		
		textElements.add("&nbsp;Udělený patent");
		textElements.add("Zveřejněná přihláška");


		// Create a regular expression pattern by joining the keywords with the "|" (OR)
		// operator
		String regexPattern = String.join("|", keywords);

		// Compile the pattern
		Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		test(textElements, pattern, keywordsType, elementMap);

		// Iterate through the map and print key-value pairs
		for (Map.Entry<String, String> entry : elementMap.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}

//		// Traverse all the text elements
//		for (int i = 0; i < textElements.size(); i++) {
//
//			// Use Matcher to find matches based on the pattern
//			Matcher matcher = pattern.matcher(textElements.get(i));
//
//			if ((keywordsType.equalsIgnoreCase("include") && matcher.find())
//					|| (keywordsType.equalsIgnoreCase("exclude") && !matcher.find())) {
//				System.out.println("OK kro : " + textElements.get(i));
//			} else {
//				System.out.println("ignore kro : " + textElements.get(i));
//			}
//		}
	}

	public static void test(List<String> textElements, Pattern pattern, String keywordsType,
			Map<String, String> elementMap) {
		// Traverse all the text elements
		for (int i = 0; i < textElements.size(); i++) {

			// Use Matcher to find matches based on the pattern
			Matcher matcher = pattern.matcher(textElements.get(i));

			if ((keywordsType.equalsIgnoreCase("include") && matcher.find())
					|| (keywordsType.equalsIgnoreCase("exclude") && !matcher.find())) {
//				System.out.println("OK kro : " + textElements.get(i));
				elementMap.put(textElements.get(i), "OK KRO");
			} else {
				System.out.println("ignore kro : " + textElements.get(i));
			}
		}
	}

}
