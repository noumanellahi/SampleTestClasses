package com.mycompany.testmavenproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.tika.Tika;
import org.apache.tika.detect.TypeDetector;
import org.apache.tika.mime.MimeTypes;

public class Tikka_2 {
	public static void main(String[] arg) {
		try {
			MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
			String fileName = "C:\\Users\\NomanAlahi\\Desktop\\tika_test\\TCTR20220430001";
//			URL webURL = new URL("https://www.mfds.go.kr/brd/m_211/down.do?brd_id=data0005&seq=14457&data_tp=A&file_seq=1");
//			
//			MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
//	
//			System.out.println(allTypes.forName(webURL.openConnection().getHeaderField("Content-Type")).getExtension());
//			System.out.println(allTypes.forName((new Tika()).detect(TikaInputStream.get(webURL))).getExtension());
//			System.out.println("*************************************************");

//			URL webURL = new URL("http://www.africau.edu/images/default/sample.pdf");

//			URLConnection urlConnection = webURL.openConnection();

//			Tika tika = new Tika();
//			TikaInputStream tikaInputStream = TikaInputStream.get(urlConnection.getInputStream());
//			
//			MimeType mimeType = allTypes.forName(tika.detect(tikaInputStream));

			/** Instance of Tika facade class with default configuration. */
			Tika defaultTika = new Tika();
			/** Instance of Tika facade class with MimeTypes detector. */
			Tika mimeTika = new Tika(new MimeTypes());
			/** Instance of Tika facade class with Type detector. */
			Tika typeTika = new Tika(new TypeDetector());

			System.out.println(defaultTika.detect(new File(fileName)));
			System.out.println(mimeTika.detect(new File(fileName)));
			System.out.println(typeTika.detect(new File(fileName)));
			System.out.println(allTypes.forName(defaultTika.detect(new File(fileName))).getExtension());
			System.out.println(allTypes.forName("asdasdasdad").getExtension());

		} catch (Exception ex) {
			System.out.println("Error occurred " + ex);
		}
	}

	public static int FileDownloader(URLConnection urlConnection, String filePath) {
		String url = urlConnection.getURL().toString();
		FileOutputStream fos = null;
		ReadableByteChannel rbc = null;
		try {
			System.out.println("DOWNLOADING FILE FROM [ " + url + " ]");

			HttpURLConnection httpcon = (HttpURLConnection) urlConnection;
			rbc = Channels.newChannel(httpcon.getInputStream());
			fos = new FileOutputStream(filePath);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();
			return 1;
		} catch (FileNotFoundException ex) {
			System.out.println("ERROR OCCURED WHILE TRY TO DOWNLOAD FILE FROM [ " + url + " ] ");
			System.out.println("Error occurred " + ex);
			return 404;
		} catch (MalformedURLException ex) {
			System.out.println("ERROR OCCURED WHILE TRY TO DOWNLOAD FILE FROM [ " + url + " ] ");
			System.out.println("Error occurred " + ex);
			return 405;
		} catch (IOException ex) {
			System.out.println("ERROR OCCURED WHILE TRY TO DOWNLOAD FILE FROM [ " + url + " ] ");
			System.out.println("Error occurred " + ex);
			return 403;
		} finally {
			if (fos != null || rbc != null) {
				try {
					rbc.close();
					fos.close();
				} catch (Exception ex) {
					System.out.println("Error occurred " + ex);
				}
			}
		}
	}

}
