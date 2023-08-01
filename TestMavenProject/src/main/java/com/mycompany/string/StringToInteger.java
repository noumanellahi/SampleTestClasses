package com.mycompany.string;

import org.apache.commons.lang3.math.NumberUtils;

public class StringToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String test = "*";
			if(NumberUtils.isParsable(test)) {
				System.out.println(Integer.parseInt(test));
			} else {
				System.out.println("NO");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
