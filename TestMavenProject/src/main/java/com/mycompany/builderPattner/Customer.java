package com.mycompany.builderPattner;

public class Customer {
	private final String firstName;
	private final String surname;
	private final String ssn;

	Customer(CustomerBuilder builder) {
		if (builder.firstName == null)
			throw new NullPointerException("firstName");
		if (builder.surname == null)
			throw new NullPointerException("surname");
		if (builder.ssn == null)
			throw new NullPointerException("ssn");
		this.firstName = builder.firstName;
		this.surname = builder.surname;
		this.ssn = builder.ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getSsn() {
		return ssn;
	}
}
