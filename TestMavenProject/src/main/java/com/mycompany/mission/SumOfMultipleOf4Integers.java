package com.mycompany.mission;

public class SumOfMultipleOf4Integers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = { -6, -91, 1011, -100, 84, -22, 0, 1, 473 };
		int sum = 0;
		for (int i : test) {
			if (i % 4 == 0) {
				sum = sum + i;
			}
		}
		System.out.println(sum);
	}
}
