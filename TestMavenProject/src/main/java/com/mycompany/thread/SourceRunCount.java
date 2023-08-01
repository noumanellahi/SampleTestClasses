package com.mycompany.thread;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SourceRunCount {

	public static ConcurrentHashMap<String, String> SOURCE_RUN_COUNT = new ConcurrentHashMap<String, String>();
	public static List<String> SOURCE_RUN_COUNT_1 = new CopyOnWriteArrayList();
	
	public static void main(String[] args) {
		int limit = 10;
		// TODO Auto-generated method stub
		while(limit > SOURCE_RUN_COUNT_1.size()) {
			SOURCE_RUN_COUNT_1.add("1");
			System.out.println("here we go");
		}
	}

}
