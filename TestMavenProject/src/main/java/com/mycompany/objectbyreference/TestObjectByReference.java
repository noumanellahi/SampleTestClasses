package com.mycompany.objectbyreference;

public class TestObjectByReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Foo f = new Foo("a");
		System.out.println(f.getTestValue());
		
		change1(f);
		System.out.println(f.getTestValue());
		
		
		change2(f);
		System.out.println(f.getTestValue());
	}

	//changeReference
	public static void change1(Foo a) {
		Foo b = new Foo("b");
		a = b;
	}

	//modifyReference
	public static void change2(Foo c) {
		c.setTestValue("c");
	}
}
