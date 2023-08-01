package com.mycompany.mission;

public class CoinSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { 1, 0, 1, 0, 1, 1 };

		int heads = 0;
		int tails = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == 1 - (i % 2)) {

				heads++;

			}

			if (A[i] == i % 2) {

				tails++;

			}
		}

		System.out.println(Math.min(heads, tails));

//		if (A[0] == 1) {
//			for (int i = 1; i <= A.length; i++) {
//				if (i % 2 == 0) {
//					if (A[i - 1] == 1) {
//						heads++;
//					}
//				} else {
//					if (A[i - 1] == 0) {
//						tails++;
//					}
//				}
//			}
//		} else {
//			for (int i = 1; i <= A.length; i++) {
//				if (i % 2 == 0) {
//					if (A[i - 1] == 0) {
//						tails++;
//					}
//				} else {
//					if (A[i - 1] == 1) {
//						heads++;
//					}
//				}
//			}
//		}
//
//		System.out.println(heads + tails);
	}

}
