package com.mycompany.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class TarCorruptFile {
	public static void main(String args[]) {
		System.out.println(new Date());
		FileInputStream fis = null;
		TarArchiveInputStream tis = null;
		try {
			fis = new FileInputStream(new File("C:\\\\Users\\\\NomanAlahi\\\\Downloads\\\\app_yb2_20230309.tar"));
			tis = new TarArchiveInputStream(fis);
			ArchiveEntry entry;
			System.out.println(tis.getNextEntry());
			System.out.println(tis.getCurrentEntry());
			while ((entry = tis.getNextEntry()) != null) {

				System.out.println(entry.getName());

				// The following might not be required, but arguably does
				// additionally check the content of the archive.
				int count;
				byte data[] = new byte[2048];

				while ((count = tis.read(data)) != -1) {
				}
			}

			tis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}

				if (tis != null) {
					tis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		System.out.println(new Date());
	}
}
