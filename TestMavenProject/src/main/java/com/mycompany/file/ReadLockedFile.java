package com.mycompany.file;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import net.lingala.zip4j.ZipFile;

public class ReadLockedFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
//			   String tarFile = "C:\\Users\\Noman.Alahi\\Desktop\\tar_sample.tar";
//
//			   // Create a TarInputStream
//			   TarArchiveInputStream tis = new TarArchiveInputStream(new BufferedInputStream(new FileInputStream(tarFile)));
//			   TarArchiveEntry entry;
//			   while((entry = (TarArchiveEntry) tis.getNextEntry()) != null) {
//
//			      System.out.println(entry.getName());
//
//			      // The following might not be required, but arguably does 
//			      // additionally check the content of the archive.
////			      int count;
////			      byte data[] = new byte[2048];
////
////			      while((count = tis.read(data)) != -1) {
////			      }
//			   }

//			   tis.close();
			String password = "ExP@rTUT21334";
			ZipFile zipFile = new ZipFile("C:\\Users\\Noman.Alahi\\Desktop\\null_ORIG.zip");
			if (zipFile.isEncrypted()) {
				System.out.println("yes");
				zipFile.setPassword(password.toCharArray());
				zipFile.extractAll("C:\\Users\\Noman.Alahi\\Desktop\\here");
			}
			zipFile.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
