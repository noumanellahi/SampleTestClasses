package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
	public static void main(String args[]) {
		 String sArray1[] = new String []{"A", "B", "C", "D"};
		 String sArray2[] = new String []{"E", "F"};
		 String sArray3[] = new String []{"G", "H", "I","J"};
		    //convert array to list
		    List<String> list1 = Arrays.asList(sArray1);
		    List<String> list2 = Arrays.asList(sArray2);
		    List<String> list3 = Arrays.asList(sArray3);
		    
		List<List<String>> permutation = new ArrayList<>();  
		
		permutation.add(list1);
		permutation.add(list2);
		permutation.add(list3);
		
		List<String> result = new ArrayList<>();
		
		generatePermutations(permutation, result, 0, "");
		int counter = 1;
		for(String r: result) {
			System.out.println(counter + "----" + r);
//			String[] values = r.split(",");
//			System.out.println(values.length);
//			for(String v : values) {
//				System.out.println(counter + "------" +v);
//			}
			counter++;
		}
	}

	static void generatePermutations(List<List<String>> lists, List<String> result, int depth, String current) {
		if (depth == lists.size()) {
			result.add(current);
			return;
		}

		for (int i = 0; i < lists.get(depth).size(); i++) {
			generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i)+",");
		}
	}
}
