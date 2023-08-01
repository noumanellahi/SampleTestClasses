package com.mycompany.string;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SplitString {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
//		String basePath = "/efs/82794/2022-12-09/013950";
//		System.out.println(StringUtils.ordinalIndexOf(basePath, "/", 3));
//		System.out.println(basePath.substring(0, StringUtils.ordinalIndexOf(basePath, "/", 3)));

			ObjectMapper mapper = new ObjectMapper();

			String keywordsString = "123";

			List<String> list = Arrays.asList(keywordsString.split(","));
			
			

			List<String> values = mapper.readValue(keywordsString, List.class);

			System.out.println(list);
			System.out.println(values);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
