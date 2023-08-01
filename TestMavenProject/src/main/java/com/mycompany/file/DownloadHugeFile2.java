package com.mycompany.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

public class DownloadHugeFile2 implements Runnable {

	public static int threadRunCount = 0;

	private String filename;
	private URL webURL;
	private String startRange;
	private String endRange;
	private int retry;
	private int iteration;

	public DownloadHugeFile2(String filename, URL webURL, String startRange, String endRange, int retry,
			int iteration) {
		this.filename = filename;
		this.webURL = webURL;
		this.startRange = startRange;
		this.endRange = endRange;
		this.retry = retry;
		this.iteration = iteration;
	}

	public void run() {
		threadRunCount++;
		startDownloading(filename, webURL, startRange, endRange, 1, iteration);
		threadRunCount--;
	}

	public static Boolean startDownloading(String filename, URL webURL, String startRange, String endRange, int retry,
			int iteration) {
		try {
			if (retry <= 3) {
				if (threadRunCount == 9 && retry < 3) {
					throw new Exception();
				}
				String byteRange = startRange + "-" + endRange;
				System.out.println(iteration + " - " + byteRange);

//				HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
//				httpURLConnection.setRequestProperty("Range", "bytes=" + byteRange);


//				System.out.println("RETRY NO : " + retry + "START WRITING FILE STREAM ON FILE : " + filename
//						+ " : START POSITION : " + startRange + " : END POSITION : " + endRange);
//				try (FileOutputStream fos = new FileOutputStream(
//						new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part_3\\" + filename))) {
//					ReadableByteChannel rbc = null;
//					try {
//						rbc = Channels.newChannel(httpURLConnection.getInputStream());
//						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//					} finally {
//						if (rbc != null && rbc.isOpen()) {
//							rbc.close();
//						}
//					}
//				}
//				System.out.println("END WRITING FILE STREAM ON FILE : " + filename + " : START POSITION : " + startRange
//						+ " : END POSITION : " + endRange);
			}
		} catch (Exception ex) {
			if (retry >= 3) {
//				ex.printStackTrace();
			} else {
//				System.out.println("try retry");
//				startDownloading(filename, webURL, startRange, endRange, retry + 1, iteration);
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		try {
//			System.out.println("START TIME : " + new Date());
//			URL webURL = new URL("https://speed.hetzner.de/10GB.bin");
			URL webURL = new URL(
					"https://bulkdata.uspto.gov/data/patent/application/yellowbook/2023/app_yb2_20230323.tar");
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
			long fileSize = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
			httpURLConnection.disconnect();
			httpURLConnection = null;

			long y = -1;
			int i = 0;

			while (fileSize >= y) {
//				Thread.sleep(2000);
				long x = y + 1;
				y = y + 200000000;
//				y = y + 900000000;
//				String fileName = "test" + i;
				String fileName = "test" + i;
				
				long range = x - y;
				System.out.println(i + " SIZE REQUESTED " + range);

				DownloadHugeFile2 object = new DownloadHugeFile2(fileName, webURL, String.valueOf(x), String.valueOf(y),
						1, i);
				Thread t1 = new Thread(object);
				t1.start();
				i++;
			}
			Thread.sleep(5000);
//			System.out.print("Downloading ");
			while (threadRunCount > 0) {
				Thread.sleep(10000);
				System.out.print(".");
			}

//			System.out.println("END TIME : " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
