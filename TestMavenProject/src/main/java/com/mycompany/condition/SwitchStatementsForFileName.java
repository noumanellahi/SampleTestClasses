package com.mycompany.condition;

public class SwitchStatementsForFileName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userPreference = "123";

		String headerFileName = "abc";
		String urlUniquePart = "efg";
		String description = "description";
		String appGeneratedFileName = "";

		switch (userPreference) {
		case "#header": {
			System.out.println(headerFileName);
		}
			break;
		case "#description": {
			System.out.println(description);
		}
			break;
		case "#url_unique_part": {
			System.out.println(urlUniquePart);
		}
			break;
		default: {
			System.out.println("none");
		}
			break;
		}
	}

}
