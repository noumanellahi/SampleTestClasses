package com.mycompany.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListConcurrency {
	public static void main(String args[]) {
//		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("ab");
		list.add("ac");
		list.add("bd");
		list.add("bd");
		list.add("bd");
		list.add("bd");
		
		System.out.println(list.toString());
		
		for(String item : list) {
			if(item.startsWith("a")) {
				list.remove(item);
			}
		}
		System.out.println(list.toString());
	}

}
