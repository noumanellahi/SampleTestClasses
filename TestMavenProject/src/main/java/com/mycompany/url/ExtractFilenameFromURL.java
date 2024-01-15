package com.mycompany.url;

import java.net.URL;

public class ExtractFilenameFromURL {
	public static void main(String[] args) {
		String urlStr = "http://ops.epo.org/3.2/rest-services/published-data/images/CH/718740/B1/fullimage.tiff?Range=1";

		try {
			String[] pathSegments = urlStr.split("/");
			String filename = pathSegments[pathSegments.length - 1].split("\\.")[0];

			System.out.println("Extracted filename: " + filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
