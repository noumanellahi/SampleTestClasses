package com.mycompany.builderPattner;

import static com.mycompany.builderPattner.CustomerBuilder.customer;

public class MIanCustomerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer customer = customer().withSurname("Smith").withFirstName("Fred").withSsn("123XS1").build();
	}

}
