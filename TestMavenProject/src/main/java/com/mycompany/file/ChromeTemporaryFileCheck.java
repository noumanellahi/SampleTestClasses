package com.mycompany.file;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class ChromeTemporaryFileCheck {

	public static void main(String[] args) {
		try {
			 String[] DOWNLOAD_FILE_EXTENSION = new String[] { "crdownload" };
			 System.out.println(DOWNLOAD_FILE_EXTENSION[0]);
				File chromeDownloadDir = new File("C:\\Users\\NomanAlahi\\Desktop\\USPTO");
				// TODO Auto-generated method stub
				do {
					System.out.print("'");
					Thread.sleep(2000);
				} while (!FileUtils
						.listFiles(chromeDownloadDir,
								DOWNLOAD_FILE_EXTENSION, false)
						.isEmpty());
				{
					System.out.println("download complete");
					
				}
		} catch (Exception ex) {
			
		}

	}

}
