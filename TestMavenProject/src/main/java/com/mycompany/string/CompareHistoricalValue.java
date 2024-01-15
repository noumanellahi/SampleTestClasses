package com.mycompany.string;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

public class CompareHistoricalValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> u = new HashSet<>();
		u.add("abc");
		u.add("def");
		u.add("ghi");

		Set<String> d = new HashSet<>();

		boolean flag = historicalDataComparison(u, d, "abc");

		System.out.println(flag);
	}

	public static boolean historicalDataComparison(Set<String> historicalDownloadedDocumentUrl,
			Set<String> historicalDownloadedDocumentDescription, String url) {

		boolean contentComparisonOn = false;
		boolean urlComparisonOn = true;
		boolean descriptionComparisonOn = false;

		if (!contentComparisonOn && urlComparisonOn) {
			return CollectionUtils.isEmpty(historicalDownloadedDocumentUrl)
					|| !historicalDownloadedDocumentUrl.contains(url);
		}

		if (!contentComparisonOn && descriptionComparisonOn) {
			return CollectionUtils.isEmpty(historicalDownloadedDocumentDescription)
					|| !historicalDownloadedDocumentDescription.contains(url);
		}

		return true;
	}
}
