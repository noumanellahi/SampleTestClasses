package com.mycompany.amazontest.keywords;

public class FinallyTest {
	public void finalize() {
		System.out.println("Finalize is called");
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			System.out.println("Start");
			FinallyTest f1=new FinallyTest();
			FinallyTest f2=new FinallyTest();
			f1= null;
			f2=null;
//			System.gc();
	}

}
