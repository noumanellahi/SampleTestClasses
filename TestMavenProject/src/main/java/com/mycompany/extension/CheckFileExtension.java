package com.mycompany.extension;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypes;

public class CheckFileExtension {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			String url = "https://www.trabajosycomunicaciones.fahce.unlp.edu.ar/article/download/tyce172/16163/44614";
//			String url = "https://revistavirtual.ucn.edu.co/index.php/RevistaUCN/article/download/1469/1763/6377";
//			String url = "https://revistas.unlp.edu.ar/Habitat/article/download/14264/13146/52834";
//			String url = "https://www.actaphys.uj.edu.pl/fulltext?series=Sup&vol=15&aid=1-X1";
			String url = "https://www.impan.pl/shop/en/publication/transaction/download/product/114695";
//			HttpURLConnection httpURLConnection = UrlRedirection.getURLConnection(url);

			URL webURL = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();

//			ContentType contentType = ContentType.parse(httpURLConnection.getContentType());
//			System.out.println(contentType.getMimeType());
//			System.out.println(contentType.getCharset());
			System.out.println("here it is " + httpURLConnection.getURL().toString());
			System.out.println(httpURLConnection.getHeaderFields());

			System.out.println(getFileExtension(httpURLConnection));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getFileExtension(HttpURLConnection httpUrlConnection) {
		MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
		String contentDispositionHeaderFieldValue = "";
		String fileName = "";
		String extension = "";
		FileExtension fileExtension = new FileExtension();
		ContentDispositionFileNameParser contentDispositionFileNameParser = new ContentDispositionFileNameParser();
		try {
//			 Get file extension from URL
			extension = fileExtension.getFileExtensionFromString(httpUrlConnection.getURL().toString().toLowerCase());

			// Get file extension from Content-Disposition header
			if (StringUtils.isEmpty(extension)) {
				// Get Content-Disposition header value from URL
				for (String headerName : ScraperConstant.contentDispositionHeaderName) {
					if (httpUrlConnection.getHeaderField(headerName) != null) {
						contentDispositionHeaderFieldValue = httpUrlConnection.getHeaderField(headerName);
						break;
					}
				}

				// Get file name from Content-Disposition header value
				if (StringUtils.isNotEmpty(contentDispositionHeaderFieldValue)) {
					fileName = contentDispositionFileNameParser.parse(contentDispositionHeaderFieldValue.toLowerCase());
				}

				// Get file extension from file name
				if (StringUtils.isNotEmpty(fileName.trim())) {
					extension = fileExtension.getFileExtensionFromString(fileName);
				}
			}

			// Get file extension from Apachi Tika
			if (StringUtils.isEmpty(extension) || extension.equals(ScraperConstant.XML_EXTENSION)) {

				/**
				 * Get extension using content type header.
				 */
				for (String headerName : ScraperConstant.headerTypeForHtml) {
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
					extension = ScraperConstant.MHT_EXTENSION;
				}
			}
			httpUrlConnection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return extension;
	}

}
