package com.mycompany.login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLConnectionRedirectIssue {
	public static void main(String[] arg) {
		try {
			String url = "https://ieeexplore.ieee.org/xpl/tocresult.jsp?isnumber=4359912";
			HttpURLConnection httpUrlConnection = getURLConnection(url);
			System.out.println(httpUrlConnection.getURL());
			System.out.println(httpUrlConnection.getResponseCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static HttpURLConnection getURLConnection(String stringURL) throws URISyntaxException, IOException {

		URL webURL = new URL(stringURL);

		// get clean and standard URL
		webURL = new URI(webURL.getProtocol(), webURL.getUserInfo(), webURL.getHost(), webURL.getPort(),
				webURL.getPath(), webURL.getQuery(), webURL.getRef()).toURL();

		// check if queryparams have any URL
		if (webURL.getQuery() != null) {
			Map<String, String> queryParamsMap = splitQueryParams(webURL.getQuery());
			if (queryParamsMap.containsKey("url")) {
				try {
					webURL = new URL(queryParamsMap.get("url"));
					// get clean and standard URL
					webURL = new URI(webURL.getProtocol(), webURL.getUserInfo(), webURL.getHost(), webURL.getPort(),
							webURL.getPath(), webURL.getQuery(), webURL.getRef()).toURL();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		HttpURLConnection httpUrlConnection = (HttpURLConnection) webURL.openConnection();

		httpUrlConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");

		httpUrlConnection.setRequestProperty("cookie", "OJSSID=0o23g7agd3i1qr89p237k14n44");

		// in case there is redirect URL
		if (httpUrlConnection.getHeaderField("Location") != null) {
			System.out.println("redirect it");
		}
		return httpUrlConnection;
	}

	public static Map<String, String> splitQueryParams(String queryParams) {
		String[] params = queryParams.split("&");
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		for (String param : params) {
			if (param.split("=").length == 2) {
				String name = param.split("=")[0];
				String value = param.split("=")[1];
				queryParamsMap.put(name, value);
			}
		}
		return queryParamsMap;
	}

}
