package com.mycompany.string;

import org.apache.commons.lang3.StringUtils;

public class SplitString {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
		String basePath = "/efs/82794/";
		System.out.println(StringUtils.ordinalIndexOf(basePath, "/", 3));
		System.out.println(basePath.substring(0, StringUtils.ordinalIndexOf(basePath, "/", 3)));

//			ObjectMapper mapper = new ObjectMapper();
//
//			String keywordsString = "123";
//
//			List<String> list = Arrays.asList(keywordsString.split(","));
//			
//			
//
//			List<String> values = mapper.readValue(keywordsString, List.class);
//
//			System.out.println(list);
//			System.out.println(values);
//			int numberOfXpath = 2;
//			String fileName = "";
//
//			String test = "abc_xyz_12";
//
//			String[] fileNameParts = test.split("_");
//
//	        // Check if the original array has enough elements
//	        int copyLength = Math.min(numberOfXpath, fileNameParts.length);
//			
//			
//			System.out.println(String.join("_", Arrays.copyOf(fileNameParts, copyLength)));
//			
//			for (int i = 0; i < numberOfXpath; i++) {
//				if (i == 0) {
//					fileName = fileNameParts[i];
//				} else {
//					fileName = fileName + "_" + fileNameParts[i] + "_";
//				}
//
//			}
//			fileName = fileName + "frontimage" + "_";
//
//			System.out.println(fileName);
//			System.out.println(test.lastIndexOf("_"));
//
//			System.out.println(test.indexOf("_", 3));
//
//			System.out.println(test.substring(0, test.lastIndexOf("_")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
