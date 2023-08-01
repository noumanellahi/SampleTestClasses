package com.mycompany.testmavenproject;

import java.util.HashMap;
import java.util.Map;

public class HashMapSearch {
	public static void main(String[] args) {
		try {
			 Map<Integer, String> httpStatusCodes = new HashMap<Integer, String>();
			httpStatusCodes.put(300, "Multiple Choices");
			httpStatusCodes.put(301, "Moved Permanently");
			httpStatusCodes.put(302, "Payment required");
			httpStatusCodes.put(307, "Temporary Redirect");
			httpStatusCodes.put(308, "Permanent Redirect");
			httpStatusCodes.put(400, "Bad request");
			httpStatusCodes.put(401, "Authorization required");
			httpStatusCodes.put(402, "Payment required");
			httpStatusCodes.put(403, "Access forbidden");
			httpStatusCodes.put(404, "Page not found");
			httpStatusCodes.put(408, "Request Timeout");
			httpStatusCodes.put(429, "Too Many Requests");
			httpStatusCodes.put(500, "Internal Server Error");
			httpStatusCodes.put(501, "Not Implemented");
			httpStatusCodes.put(502, "Bad Gateway");
			httpStatusCodes.put(503, "Service Unavailable");
			httpStatusCodes.put(504, "Gateway Timeout");
			httpStatusCodes.put(502, "Network Authentication Required");
			httpStatusCodes.put(905, "IOEXCEPTION in processing URL");
			
			System.out.println(httpStatusCodes.get(503));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
