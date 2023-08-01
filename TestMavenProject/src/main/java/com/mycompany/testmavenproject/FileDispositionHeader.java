package com.mycompany.testmavenproject;

public class FileDispositionHeader {
	public static void main(String args[]) {
		String contentDispositionValue = "inline;filename=\"fileformatinfo.mht\"";
		int index = contentDispositionValue.indexOf("filename=");
		String fileName = "";
        if (index >= 0) {
            fileName = contentDispositionValue.substring(index + 9,
            		contentDispositionValue.length() );
        }
        fileName = fileName.replaceAll(";|\"", "");
        System.out.println(fileName);
	}
}
