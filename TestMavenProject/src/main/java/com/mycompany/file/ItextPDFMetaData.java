package com.mycompany.file;

import java.nio.charset.StandardCharsets;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

public class ItextPDFMetaData {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			String src = "C:\\\\Users\\\\NomanAlahi\\\\Desktop\\\\HasH_Test\\\\Journal_Articles_JA_D0494J_AAA34_J_Issue-Web_20221207__0.pdf";

			PdfReader reader = new PdfReader(src);
			PdfDocument doc = new PdfDocument(reader);

			byte[] xmp = doc.getXmpMetadata();
			String xmpString = new String(xmp, StandardCharsets.UTF_8);
			System.out.println(xmpString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
