package com.mycompany.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

public class DownloadHugeFileDifferentApproach implements Runnable {

	public static int threadRunCount = 0;

	private String filename;
	private URL webURL;
	private String startRange;
	private String endRange;
	private FileOutputStream fos;

	public DownloadHugeFileDifferentApproach(String filename, URL webURL, String startRange, String endRange,
			FileOutputStream fos) {
		this.filename = filename;
		this.webURL = webURL;
		this.startRange = startRange;
		this.endRange = endRange;
		this.fos = fos;
	}

	public void run() {
		try {
			threadRunCount++;
			String byteRange = startRange + "-" + endRange;
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
			httpURLConnection.setRequestProperty("Range", "bytes=" + byteRange);

			System.out.println("START WRITING FILE STREAM ON FILE : " + filename + " : START POSITION : " + startRange
					+ " : END POSITION : " + endRange);
			ReadableByteChannel rbc = null;
			try {
				rbc = Channels.newChannel(httpURLConnection.getInputStream());
				fos.getChannel().position(Long.parseLong(startRange)).transferFrom(rbc, 0, Long.MAX_VALUE);
			} finally {
				if (rbc != null && rbc.isOpen()) {
					rbc.close();
				}
			}
			System.out.println("END WRITING FILE STREAM ON FILE : " + filename + " : START POSITION : " + startRange
					+ " : END POSITION : " + endRange);
			threadRunCount--;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = null;
		try {
			System.out.println("START TIME : " + new Date());
			URL webURL = new URL("http://212.183.159.230/50MB.zip");
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
			long fileSize = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
			httpURLConnection.disconnect();
			httpURLConnection = null;

			String fileName = "test.zip";
			fos = new FileOutputStream(new File("C:\\Users\\NomanAlahi\\Desktop\\Test2\\" + fileName));

			long y = -1;
			int i = 0;

			while (fileSize >= y) {
				long x = y + 1;
				y = y + 10000000;

				DownloadHugeFileDifferentApproach object = new DownloadHugeFileDifferentApproach(fileName, webURL,
						String.valueOf(x), String.valueOf(y), fos);
				Thread t1 = new Thread(object);
				t1.start();
				i++;
			}
			Thread.sleep(5000);
			System.out.print("Downloading ");
			while (threadRunCount > 0) {
				Thread.sleep(10000);
				System.out.print(".");
			}

			System.out.println("END TIME : " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

}
