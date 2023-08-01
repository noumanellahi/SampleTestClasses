package com.mycompany.file;

import java.io.File;
import java.io.RandomAccessFile;

public class MergePartsOfFileUsingRAF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			long leninfile = 0, leng = 0;
			int count = 0, data = 0;
			try {
				File outfilename = new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part_2\\test.zip");
				RandomAccessFile outfile = new RandomAccessFile(outfilename, "rw");
				while (true) {
					File inputFilename = new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part_2\\test" + count);
					if (inputFilename.exists()) {
						RandomAccessFile infile = new RandomAccessFile(inputFilename, "r");
						data = infile.read();
						while (data != -1) {
							outfile.write(data);
							data = infile.read();
						}
						leng++;
						infile.close();
						count++;
					} else
						break;
				}
				outfile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
