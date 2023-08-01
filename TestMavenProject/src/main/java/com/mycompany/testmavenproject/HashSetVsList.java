package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetVsList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> testList = new ArrayList<>();

		for (int i = 0; i <= 10000; i++) {
			testList.add("test_it" + i);
		}

		Set<String> testSet = new HashSet<String>();

		for (int i = 0; i <= 10000; i++) {
			testSet.add("test_it" + i);
		}

		System.out.println(new Date().getTime());
		System.out.println(testList.contains("test_it" + 1950));
		System.out.println(new Date().getTime());

		System.out.println(new Date().getTime());
		System.out.println(testSet.contains("test_it" + 1950));
		System.out.println(new Date().getTime());

	}

}
