package com.mycompany.rock;

public class EqualTest {
	public static void main(String[] args) {
		TestObject obj1 = new TestObject("a", "b", "c");
		TestObject obj2 = new TestObject("a", "b", "c");
		
		System.out.println(obj1.equals(null));
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
	}
}
