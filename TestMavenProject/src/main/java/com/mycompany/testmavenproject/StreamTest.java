package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class StreamTest {
	public static void main(String[] args) {
//		List<String> scraperOptional = new ArrayList<>();
//		scraperOptional.add("Geeks");
//		scraperOptional.add("for");
//		scraperOptional.add("Geeks");

		List<String> scraperList = new ArrayList<>();
		scraperList.add("Geeks");
		scraperList.add("for");
		scraperList.add("Geeks");

		String tagsInclude = scraperList.stream().collect(Collectors.joining(","));

		System.out.println(tagsInclude);

//		scraperOptional.stream().forEach((dbValueOfScraper) -> {
//			scraperList.stream()
//					.filter(localScraperValue -> localScraperValue.getDataType().equals(ScraperConstant.ZIP_EXTENSION)
//							&& localScraperValue.getDownloadUrl().equals(dbValueOfScraper.getDownloadUrl()))
//					.forEach(localScraperValue -> {
//						scraperList.remove(localScraperValue);
//					});
//		});
	}
}
