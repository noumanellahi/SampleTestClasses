package com.mycompany.login;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypes;

public class LoginSessionCookie {
	public static void main(String[] arg) {
		try {
			URL webURL = new URL("https://avmajournals.avma.org/doi/pdf/10.2460/javma.258.5.437");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) webURL.openConnection();

			httpUrlConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
//			httpUrlConnection.setRequestProperty("authority", "pacificarchaeology.org");
//			httpUrlConnection.setRequestProperty("method", "GET");
//			httpUrlConnection.setRequestProperty("path", "/index.php/journal/article/download/299/385/");
//			httpUrlConnection.setRequestProperty("scheme", "https");
//			httpUrlConnection.setRequestProperty("accept",
//					" text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//			httpUrlConnection.setRequestProperty("accept-encoding", "gzip, deflate, br");
//			httpUrlConnection.setRequestProperty("accept-language", "en-US,en;q=0.9,ur;q=0.8,ru;q=0.7");
//			httpUrlConnection.setRequestProperty("cookie",
//					"_ga=GA1.2.1433589027.1612865484; _gid=GA1.2.931316301.1612865484; _gat=1; OJSSID=7cc4a0051e1a38a17e825300bcc2a835");
//			httpUrlConnection.setRequestProperty("sec-fetch-mode", "navigate");
//			httpUrlConnection.setRequestProperty("sec-fetch-site", "none");
//			httpUrlConnection.setRequestProperty("upgrade-insecure-requests", "1");
//			httpUrlConnection.setRequestProperty("sec-fetch-user", "?1");
//			if (httpUrlConnection.getHeaderField("Location") != null) {
//				System.out.println(httpUrlConnection.getHeaderField("Location"));
//			}
//			System.out.println(httpUrlConnection.getHeaderFields());
//
			httpUrlConnection.setRequestProperty("cookie",
					"__cfduid=de3ce63b4161a2775998722852441911e1614172683; JSESSIONID=aaakr4mwjQzhMh8rHVWEx; SERVER=WZ6myaEXBLHE8qKMB4SBxg==; MAID=QJ9fmL9pdhlmTsDkochSCw==; MACHINE_LAST_SEEN=2021-02-24T05%3A18%3A04.261-08%3A00; I2KBRCK=1; PLUID=pRzQSHki52Vi29nf+uN8MS5xFpk=");
			System.out.println(getFileExtension(httpUrlConnection));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getFileExtension(HttpURLConnection httpUrlConnection) {
		String contentDispositionHeaderFieldValue = "";
		String fileName = "";
		String extension = "";
		List<String> contentDispositionHeaderName = new ArrayList<String>(
				Arrays.asList("Content-Disposition", "Content-disposition", "content-disposition"));

		List<String> headerTypeForHtml = new ArrayList<String>(
				Arrays.asList("Content-Type", "Content-type", "content-type"));

		MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
		try {
			// Get file extension from Content-Disposition header
			if (StringUtils.isEmpty(extension)) {
				// Get Content-Disposition header value from URL
				for (String headerName : contentDispositionHeaderName) {
					if (httpUrlConnection.getHeaderField(headerName) != null) {
						contentDispositionHeaderFieldValue = httpUrlConnection.getHeaderField(headerName);
						break;
					}
				}

				// Get file name from Content-Disposition header value
				if (StringUtils.isNotEmpty(contentDispositionHeaderFieldValue)) {
					fileName = getFileNameFromContentDisposition(contentDispositionHeaderFieldValue.toLowerCase());
				}

				// Get file extension from file name
				if (StringUtils.isNotEmpty(fileName.trim())) {
					extension = fileName;
				}
			}

			// Get file extension from Apachi Tika
			if (StringUtils.isEmpty(extension)) {

				/**
				 * Get extension using content type header.
				 */
				for (String headerName : headerTypeForHtml) {
					if (httpUrlConnection.getHeaderField(headerName) != null) {
						extension = allTypes.forName(
								MediaType.parse(httpUrlConnection.getHeaderField(headerName)).getBaseType().toString())
								.getExtension();
						break;
					}
				}

				/**
				 * convert the input stream to TikaInputStream detect the media type and then
				 * get extension.
				 */
				if (StringUtils.isEmpty(extension)) {
					InputStream is = httpUrlConnection.getInputStream();
					TikaInputStream tis = TikaInputStream.get(is);
					extension = allTypes.forName((new Tika()).detect(tis)).getExtension();

					tis.close();
					is.close();
				}
				if (extension.equalsIgnoreCase(".eml")) {
					extension = ".mht";
				}
			}
			httpUrlConnection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return extension;
	}

	public static String getFileNameFromContentDisposition(String contentDispositionValue) {
		int index = contentDispositionValue.indexOf("filename=");
		String fileName = "";
		if (index >= 0) {
			fileName = contentDispositionValue.substring(index + 9, contentDispositionValue.length());
		}
		fileName = fileName.replaceAll(";|\"", "");
		return fileName;
	}
}
