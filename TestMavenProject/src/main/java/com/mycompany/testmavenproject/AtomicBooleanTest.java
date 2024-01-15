package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.clarivate.singularity.webacq.api.util.ConfigConstants;

public class AtomicBooleanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String timedOutScrappingStatus = null;

		List<Integer> masterIds = new ArrayList<>();
		masterIds.add(123);
		masterIds.add(456);
		masterIds.add(789);

		AtomicBoolean results = new AtomicBoolean(false);
		masterIds.forEach(masterId -> {
			results.set(true);
		});

		timedOutScrappingStatus = results.get() ? "STATUS_PARTIAL_ACQUIRED" : "SCRAPING_STATUS_PARTIAL_DELIVERED";
		
		System.out.println(timedOutScrappingStatus);
		
		System.out.println(results);

	}

}
