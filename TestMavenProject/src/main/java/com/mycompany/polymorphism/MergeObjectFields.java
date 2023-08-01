package com.mycompany.polymorphism;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class MergeObjectFields {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car a1 = new Car("90");
		Car a2 = new Car();
		Car a3 = new Car("100");
		Car a4 = new Car(null);
		List<Car> carList = new ArrayList<Car>();
		carList.add(a1);
		carList.add(a2);
		carList.add(a3);
		carList.add(a4);
		
		
		String result = carList.stream().filter(e -> StringUtils.isNotBlank(e.getSpeed()))
		.map(e -> e.getSpeed())
		.collect(Collectors.joining(","));
		
		System.out.println(result);
	}

}
