package com.mycompany.testmavenproject;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

public class HashMapLoopTest {
	public static void main(String[] args) {
		Map<String, Map<String, String>> scraperedUrlMap = new LinkedHashMap<>();
		int counter = 1;
		Map<String, String> tempMap = new LinkedHashMap<>();
		tempMap.put("1", "a");
//		tempMap.put("2","b");
		
		for (Map.Entry<String, String> entry : tempMap.entrySet()) {
			if(counter == tempMap.size()){
				System.out.println(tempMap.size());
				System.out.println("Here we go");
			}

		}
		
//		System.out.println(tempMap.size());
//		scraperedUrlMap.put("1", tempMap);
//
//		for (int i = 0; i <= crawlingLevels; i++) {
//			for (String key : tempMap.keySet()) {
//				tempMap = mapFilellr(i);
////				tempMap.remove(key);
//			}
//			System.out.println(tempMap);
//		}
	}

//	public static Map<String, String> mapFilellr(int level) {
//		Map<String, String> tempMap = new LinkedHashMap<>();
//		for (int i = 0; i <= 10; i++) {
//			tempMap.put("" + i, "Level is" + level);
//		}
//		return tempMap;
//	}
}
