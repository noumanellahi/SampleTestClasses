package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.List;

public class DivisionWithInt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println((999 / 500));
		int partitionSize = 500;
		List<String> testList = new ArrayList<>();

		for (int i = 0; i < 1343; i++) {
			testList.add("a" + i);
		}
		int chunkCounter = 1;
		for (int i = 0; i < testList.size(); i++) {
			if (i < testList.size() - 1 && partitionSize > chunkCounter) {
				chunkCounter++;
			} else {
				chunkCounter = 1;
				System.out.println(i + ": Message Sent");
			}
		}

	}

}
