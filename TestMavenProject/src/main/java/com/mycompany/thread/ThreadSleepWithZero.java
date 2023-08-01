package com.mycompany.thread;

public class ThreadSleepWithZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(0);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
