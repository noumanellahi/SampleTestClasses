package com.mycompany.streams;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntergerTest {
	public static void main(String[] args) {
		AtomicInteger ordinal = new AtomicInteger(0);
		ordinal.addAndGet(1);

		System.out.println(ordinal.get());
	}

}
