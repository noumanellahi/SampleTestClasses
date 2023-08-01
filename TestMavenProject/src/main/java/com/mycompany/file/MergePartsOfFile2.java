package com.mycompany.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class MergePartsOfFile2 {

	public static void main(String[] args) throws IOException {
		int maxReadBufferSize = 8 * 1024;
		int count = 0;

		BufferedOutputStream bw = new BufferedOutputStream(
				new FileOutputStream("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part_3\\test.zip"));

		RandomAccessFile raf = null;
		while (true) {
			File file = new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part_3\\test" + count);
			if (file.exists()) {
				raf = new RandomAccessFile(file, "r");
				long numReads = raf.length() / maxReadBufferSize;
				long numRemainingRead = raf.length() % maxReadBufferSize;
				for (int i = 0; i < numReads; i++) {
					readWrite(raf, bw, maxReadBufferSize);
				}
				if (numRemainingRead > 0) {
					readWrite(raf, bw, numRemainingRead);
				}
				raf.close();
				count++;
			} else {
				break;
			}
		}
		bw.close();
	}

	static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
		byte[] buf = new byte[(int) numBytes];
		int val = raf.read(buf);
		if (val != -1) {
			bw.write(buf);
		}
	}
}
