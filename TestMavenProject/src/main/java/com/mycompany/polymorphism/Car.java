package com.mycompany.polymorphism;

public class Car {
	private String speed;

	public Car() {

	}

	public Car(String speed) {
		this.speed = speed;
	}

	void run() {
		System.out.println("car can running");
	}

	void safety() {
		System.out.println("car should be safe");
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}
}
