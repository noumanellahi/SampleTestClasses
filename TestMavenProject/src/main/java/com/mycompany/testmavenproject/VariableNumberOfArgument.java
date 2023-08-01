package com.mycompany.testmavenproject;

public class VariableNumberOfArgument {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(1,2,3,4,5,6);
	}
	
	static void test(int... args) {
		System.out.println(args.length);
	}

}
