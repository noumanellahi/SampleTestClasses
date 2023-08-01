package com.mycompany.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

public class DownloadHugeFile {

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("START TIME : " + new Date());
			URL webURL = new URL("https://www.sample-videos.com/zip/50mb.zip");
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
			long fileSize = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
			System.out.println(fileSize);

			String filename = "C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part_3\\test_org.zip";

			System.out.println("START WRITING FILE STREAM ON FILE : " + filename);

			try (FileOutputStream fos = new FileOutputStream(new File(filename))) {
				ReadableByteChannel rbc = null;
				try {
					rbc = Channels.newChannel(httpURLConnection.getInputStream());
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				} finally {
					if (rbc != null && rbc.isOpen()) {
						rbc.close();
					}
				}
			}
			System.out.println("END WRITING FILE STREAM." + filename);
			System.out.println("END TIME : " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
