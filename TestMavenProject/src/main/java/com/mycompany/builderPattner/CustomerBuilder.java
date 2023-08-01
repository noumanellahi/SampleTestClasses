package com.mycompany.builderPattner;

public class CustomerBuilder {
	String surname;
	String firstName;
	String ssn;

	public static CustomerBuilder customer() {
		return new CustomerBuilder();
	}

	public CustomerBuilder withSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public CustomerBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public CustomerBuilder withSsn(String ssn) {
		this.ssn = ssn;
		return this;
	}

	// client doesn't get to instantiate Customer directly
	public Customer build() {
		return new Customer(this);
	}
}
