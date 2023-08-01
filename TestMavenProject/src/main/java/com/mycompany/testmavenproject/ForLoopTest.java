package com.mycompany.testmavenproject;

public class ForLoopTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(1);
	}

	public static void test(int retryCount) {
		System.out.println(retryCount);
		if (retryCount < 3) {
			retryCount = retryCount + 1;
			test(retryCount);
		}
	}

}
