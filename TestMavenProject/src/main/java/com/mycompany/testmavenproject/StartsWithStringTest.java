package com.mycompany.testmavenproject;

public class StartsWithStringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "/ghhgh";

		if ((!test.startsWith("#") || test.startsWith("#/")) && !test.isEmpty() && !test.contains(".jpg") && !test.contains(".png")) {
			System.out.println("******Download*********");
		} else {
			System.out.println("Not Download");
		}
	}

}
