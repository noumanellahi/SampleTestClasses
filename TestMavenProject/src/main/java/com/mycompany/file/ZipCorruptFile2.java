package com.mycompany.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipCorruptFile2 {
	public static void main(String args[]) {
		ZipFile zipfile = null;
	    ZipInputStream zis = null;
	    try {
	        zipfile = new ZipFile(new File("C:\\Users\\NomanAlahi\\Desktop\\JPN_2022133 (1).ZIP"));
	        zis = new ZipInputStream(new FileInputStream(new File("C:\\Users\\NomanAlahi\\Desktop\\JPN_2022133 (1).ZIP")));
	        ZipEntry ze = zis.getNextEntry();
	        if(ze == null) {
	           System.out.println("false");
	        }
	        while(ze != null) {
	            // if it throws an exception fetching any of the following then we know the file is corrupted.
	            zipfile.getInputStream(ze);
	            ze.getCrc();
	            ze.getCompressedSize();
	            ze.getName();
	            ze = zis.getNextEntry();
	        } 
	    } catch (ZipException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	    	 e.printStackTrace();
	    } finally {
	        try {
	            if (zipfile != null) {
	                zipfile.close();
	                zipfile = null;
	            }
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        } try {
	            if (zis != null) {
	                zis.close();
	                zis = null;
	            }
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }

	    }
	}
}
