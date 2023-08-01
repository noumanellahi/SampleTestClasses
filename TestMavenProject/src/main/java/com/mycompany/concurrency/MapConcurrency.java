package com.mycompany.concurrency;

import java.util.HashMap;
import java.util.Map;

public class MapConcurrency {
	public static void main(String args[]) {
//		Map<Integer, String> map = new ConcurrentHashMap<> ();
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "aa");
		map.put(2, "ab");
		map.put(3, "ac");
		map.put(4, "ad");
		map.put(5, "bb");
		map.put(6, "bc");

		System.out.println(map.keySet());
		
		for(Integer key : map.keySet()) {
			if(map.get(key).startsWith("a")) {
				map.remove(key);
			}
		}
		System.out.println(map.keySet());
	}
}
