package com.mycompany.amazontest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StramTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> stringList = new ArrayList<String>();
		stringList.add("10");
		stringList.add("9");
		stringList.add("8");
		stringList.add("7");

		List<Integer> intList = stringList.stream().map(num -> Integer.parseInt(num)).filter(num -> num < 10)
				.map(num -> num * num).collect(Collectors.toList());
		
		System.out.println(intList);

	}
}