package com.mycompany.url;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URIRedirection {
	static String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";

	public static void main(String args[]) {
		HttpURLConnection httpUrlConnection = null;
		try {
				String url = "https://api.hugverk.is/media/raonxesi/apríl2023.pdf";
//			String url = "https://www.hse.ie/eng/services/list/5/cancer/profinfo/chemoprotocols/leukemia-bmt/613-vxyeos-%C2%AE-liposomal-daunorubicin-and-cytarabine-induction-therapy.pdf";
			httpUrlConnection = getURLConnection(url);
			httpUrlConnection.disconnect();
			httpUrlConnection = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static HttpURLConnection getURLConnection(String stringURL) throws URISyntaxException, IOException {
		CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
		// Create a new trust manager that trust all certificates
//		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//			@Override
//			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//
//			@Override
//			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
//			}
//
//			@Override
//			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
//			}
//		} };
//
//		// Activate the new trust manager
//		try {
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//		} catch (Exception ex) {
//			System.out.println("ERROR OCCUED IN acceptAllCertificates " + ex);
//		}

		URI webURI = new URI(stringURL);
//		if (stringURL.contains("–")) {
//			stringURL = stringURL.replace("–", "%E2%80%93");
//			webURI = new URL(stringURL);
//		} else if (stringURL.contains("®")) {
//			stringURL = stringURL.replace("®", "%C2%AE");
//			webURL = new URL(stringURL);
//		} else {
//			webURL = new URL(stringURL);
//			// get clean and standard URL
//			webURL = new URI(webURL.getProtocol(), webURL.getUserInfo(), webURL.getHost(), webURL.getPort(),
//					webURL.getPath(), webURL.getQuery(), webURL.getRef()).toURL();
//		}

		// check if queryparams have any URL
		if (webURI.getQuery() != null) {
			System.out.println("In query params");
			Map<String, String> queryParamsMap = splitQueryParams(webURI.getQuery());
			if (queryParamsMap.containsKey("url")) {
				try {
					webURI = new URI(queryParamsMap.get("url"));
					// get clean and standard URL
//					webURI = new URI(webURL.getProtocol(), webURL.getUserInfo(), webURL.getHost(), webURL.getPort(),
//							webURL.getPath(), webURL.getQuery(), webURL.getRef()).toURL();
				} catch (Exception ex) {
					System.out.println(" ERROR OCCURED WHILE GETTING URL FROM PARAMS : " + ex);
				}
			}
		}

		HttpURLConnection httpUrlConnection = (HttpURLConnection) webURI.toURL().openConnection();
		httpUrlConnection.setRequestProperty("User-Agent", USER_AGENT);

		// in case there is redirect URL
		if (httpUrlConnection.getHeaderField("Location") != null) {
			System.out.println("In redirect");
			httpUrlConnection = (HttpURLConnection) getRedirectUrl(httpUrlConnection, 1);
		}
		System.out.println(httpUrlConnection.getResponseCode());
		System.out.println(httpUrlConnection.getHeaderFields());
		System.out.println(httpUrlConnection.getURL());
		return httpUrlConnection;
	}

	public static HttpURLConnection getRedirectUrl(HttpURLConnection httpUrlConnection, int count) {
		try {
			// in case there is redirect URL
			if (httpUrlConnection.getHeaderField("Location") != null && count <= 5) {

				// get the re direct URL from header
				URL webURL = new URL(httpUrlConnection.getHeaderField("Location"));

				httpUrlConnection = (HttpURLConnection) webURL.openConnection();
				httpUrlConnection.setRequestProperty("User-Agent", USER_AGENT);
				count++;

				getRedirectUrl(httpUrlConnection, count);
			}
		} catch (Exception ex) {
			System.out.println("Error occurred WHILE GETTING REDIRECT URL : " + ex);
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
