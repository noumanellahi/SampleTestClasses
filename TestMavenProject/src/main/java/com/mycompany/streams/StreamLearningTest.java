package com.mycompany.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamLearningTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("ankit");
		list.add("mayank");
		list.add("irfan");
		list.add("jai");
		list.add("jai");
		list.add("jai");
		
		List<String> list2 = list.stream().filter(name -> name.equals("jai")).collect(Collectors.toList());
		System.out.println(list2);
		
		List<Integer> numberList = new ArrayList<Integer>();
		numberList.add(300);
		numberList.add(200);
		numberList.add(175);
		numberList.add(500);
		numberList.add(4785);
		
		Map<String, Long> result = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(result);

		List<Integer> numberList2 = numberList.stream().sorted().collect(Collectors.toList());
		System.out.println(numberList2);
		
		int minNumber = numberList.stream().min(Comparator.comparing(Integer::valueOf)).get();
		System.out.println(minNumber);
		
		int maxNumber = numberList.stream().max(Comparator.comparing(Integer::valueOf)).get();
		System.out.println(maxNumber);
	}

}
