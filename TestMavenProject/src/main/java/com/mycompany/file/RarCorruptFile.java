package com.mycompany.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

public class RarCorruptFile {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			final Archive archive = new Archive(
					new FileInputStream(new File("C:\\Users\\NomanAlahi\\Desktop\\Wallpapers.rar")));
//			archive.getMainHeader().print();
			FileHeader fh = archive.nextFileHeader();
//			while (fh != null) {
//				File fileEntry = new File(fh.getFileNameString().trim());
//				System.out.println(fileEntry.getAbsolutePath());
//				FileOutputStream os = new FileOutputStream(fileEntry);
//				archive.extractFile(fh, os);
//				os.close();
//				fh = archive.nextFileHeader();
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
