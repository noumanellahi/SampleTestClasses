package com.mycompany.objectbyreference;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class BooleanReferenceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MutableBoolean flag = new MutableBoolean(Boolean.FALSE);
		flag.setFalse();
		System.out.println("Before change " + flag.getValue());
		
		BooleanValueChange obj = new BooleanValueChange();
		obj.changeValue(flag);
		
		System.out.println("After change " + flag.getValue());
	}

}
