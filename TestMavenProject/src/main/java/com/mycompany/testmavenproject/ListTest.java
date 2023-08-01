package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] arg) {
		List<String> tmp = new ArrayList<>();
		tmp.add("abcd");
		tmp.add("2");
		tmp.add("3");
//		System.out.println(tmp.size());
//		System.out.println(tmp.get(1));
		System.out.println(tmp.toString());
	}
}
