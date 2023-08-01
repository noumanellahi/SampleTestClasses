package com.mycompany.testmavenproject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFile {
	public static void main(String args[]) {
		String url = "http://www.tobaccoinduceddiseases.org/pdf-132859-62331?filename=Effects of government.pdf";
		String filePath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\test.pdf";
		FileOutputStream fos = null;
		ReadableByteChannel rbc = null;
		try {
			//Set default cookies in every http request
			CookieManager cookieManager = new CookieManager();
			CookieHandler.setDefault(cookieManager);
			
			URL webURL = new URL(url);
			webURL = new URI(webURL.getProtocol(), webURL.getUserInfo(), webURL.getHost(), webURL.getPort(),
					webURL.getPath(), webURL.getQuery(), webURL.getRef()).toURL();
			
			HttpURLConnection httpcon = (HttpURLConnection) webURL.openConnection();
			rbc = Channels.newChannel(httpcon.getInputStream());
			fos = new FileOutputStream(filePath);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IOEXCEPTION");
			ex.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null || rbc != null) {
				try {
					rbc.close();
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
