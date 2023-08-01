package com.mycompany.testmavenproject;

public class ShutDownHook {
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Inside Add Shutdown Hook");
			}
		});

		System.out.println("Shut Down Hook Attached.");
		
	}
}
