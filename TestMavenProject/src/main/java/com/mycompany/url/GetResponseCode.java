package com.mycompany.url;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class GetResponseCode {

	public static void main(String[] args) {

		// Set default cookies in every http request
		CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);

		try {
			CookieHandler.setDefault(cookieManager);
			installTrustAllCertificates();

			HttpURLConnection conn = null;
//		String stringURL = "http://www.chinadrugtrials.org.cn/clinicaltrials.searchlist.dhtml?reg_no=&indication=&case_no=&drugs_name=&drugs_type=&appliers=&communities=&researchers=&agencies=&state=";
			String stringURL = "https://www.ijemst.com/index.php/ijemst/issue/view/52";

			try {
				conn = null;
				conn = getURLConnection(stringURL);
				System.out.println(conn.getResponseCode());

				String actualUserAgent = conn.getHeaderField("User-Agent");
				System.out.println("Actual User-Agent: " + actualUserAgent);
				System.out.println("Actual User-Agent: " + conn.getHeaderFields());

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void installTrustAllCertificates() throws Exception {
		TrustManager[] trustAllCertificates = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Create a custom SSL context that trusts all certificates
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, trustAllCertificates, new java.security.SecureRandom());

		// Set this custom SSL context as the default for all SSL connections
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Bypass hostname verification
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
	}

//	public static void installTrustAllCertificates() throws Exception {
//		TrustManager[] trustAllCertificates = new TrustManager[] { new X509TrustManager() {
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//
//			public void checkClientTrusted(X509Certificate[] certs, String authType) {
//			}
//
//			public void checkServerTrusted(X509Certificate[] certs, String authType) {
//			}
//		} };
//
//		// Create a custom SSL context that trusts all certificates
//		SSLContext sc = SSLContext.getInstance("TLS");
//		sc.init(null, trustAllCertificates, new java.security.SecureRandom());
//
//		// Set this custom SSL context as the default for all SSL connections
//		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//		// Bypass hostname verification
//		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
//	}

	/**
	 * Return the URLConnection for the given string URL.
	 * 
	 * @param stringURL
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static HttpURLConnection getURLConnection(String stringURL) throws URISyntaxException, IOException {

		URL webURL;

		/**
		 * Non-ASCII characters like –,® are not allowed in URL's so replace them. Fix
		 * for URL :
		 * https://www.hse.ie/eng/services/list/5/cancer/profinfo/medonc/cdmp/new.html
		 */
		if (stringURL.contains("–")) {
			stringURL = stringURL.replace("–", "%E2%80%93");
			webURL = new URL(stringURL);
		} else if (stringURL.contains("®")) {
			stringURL = stringURL.replace("®", "%C2%AE");
			webURL = new URL(stringURL);
		} else {
			webURL = new URL(stringURL);
			// get clean and standard URL
			webURL = new URI(webURL.getProtocol(), webURL.getUserInfo(), webURL.getHost(), webURL.getPort(),
					webURL.getPath(), webURL.getQuery(), webURL.getRef()).toURL();
		}

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

		httpUrlConnection.setRequestProperty("User-Agent", getUserAgent(webURL));

		// in case there is redirect URL
		if (httpUrlConnection.getHeaderField("Location") != null) {
			httpUrlConnection = (HttpURLConnection) getRedirectUrl(httpUrlConnection, 1);
		}

		return httpUrlConnection;
	}

	/**
	 * URL Redirection check
	 */
	public static HttpURLConnection getRedirectUrl(HttpURLConnection httpUrlConnection, int count) {
		try {
			// in case there is redirect URL
			if (httpUrlConnection.getHeaderField("Location") != null && count <= 5) {

				// get the re direct URL from header
				URL webURL = new URL(httpUrlConnection.getHeaderField("Location"));

				httpUrlConnection = (HttpURLConnection) webURL.openConnection();
				httpUrlConnection.setRequestProperty("User-Agent",
						"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");
				count++;

				getRedirectUrl(httpUrlConnection, count);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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

	public static String getUserAgent(URL url) {
		String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";
		String Custom_USER_AGENT = "Clarivate-Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550";

		try {
			List<String> customUserAgentSources = new ArrayList<>();
			customUserAgentSources.add("link.springer.com");
			customUserAgentSources.add("www.springer.com");
			customUserAgentSources.add("www.nature.com");
			customUserAgentSources.add("www.springeropen.com");
			customUserAgentSources.add("connect.springerpub.com");
			customUserAgentSources.add("www.springerpub.com");
			customUserAgentSources.add("www.scientificamerican.com");
			customUserAgentSources.add("www.biomedcentral.com");
			customUserAgentSources.add("www.ingentaconnect.com");
			customUserAgentSources.add("www.palgrave.com");
			customUserAgentSources.add("www.atlantis-press.com");
			customUserAgentSources.add("www.springeronline.com");
			customUserAgentSources.add("www.springerprofessional.de");
			customUserAgentSources.add("www.springermedizin.de");
			customUserAgentSources.add("bunsen.springernature.app");

			if (customUserAgentSources.contains(url.getHost())) {
				System.out.println("SETTING CUSTOM USER AGENT FOR URL : " + url + " : " + Custom_USER_AGENT);
				return Custom_USER_AGENT;
			} else {
				System.out.println("SETTING NORMAL USER AGENT FOR URL : " + url + " : " + USER_AGENT);
				return USER_AGENT;
			}
		} catch (Exception ex) {
			System.out.println("EXCEPTION OCCURED WHILE GETTING USER AGENT FOR URL : " + url);
		}
		return USER_AGENT;
	}
}
