package com.mycompany.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestObject obj = new TestObject();
		obj.setSt1("calendar");
		obj.setSt2("2022-03-10");
		obj.setRetry(3);

		TestObject obj2 = new TestObject();
		obj2.setSt1("calendar");
		obj2.setSt2("2022-03-11");
		obj2.setRetry(2);

		TestObject obj3 = new TestObject();
		obj3.setSt1("iterator");
		obj3.setSt2("token_value");
		obj3.setRetry(1);

		List<TestObject> listOfObjects = new ArrayList<>();
		listOfObjects.add(obj);
		listOfObjects.add(obj2);
		listOfObjects.add(obj3);

//		String tagsInclude = listOfObjects.stream().filter(setting -> setting.getSt1().equals("iterator"))
//				.filter(setting -> StringUtils.isNotBlank(setting.getSt2())).map(setting -> setting.getSt2().trim())
//				.collect(Collectors.joining(","));
//		System.out.println(tagsInclude);

		if (listOfObjects.stream()
				.anyMatch(scraper -> scraper != null && scraper.getRetry() != null && scraper.getRetry() < 3)) {
			System.out.println("retry required");

			List<TestObject> listOfRetryObjects = listOfObjects.stream()
					.filter(scraper -> scraper != null && scraper.getRetry() != null && scraper.getRetry() < 3)
					.collect(Collectors.toList());
			
			System.out.println(listOfRetryObjects.size());

		} else {
			System.out.println("retry doesn't required");
		}

	}

}
