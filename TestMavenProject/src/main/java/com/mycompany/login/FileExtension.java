package com.mycompany.login;
//package com.mycompany.cookieTest;
//
//public class FileExtension {
//
//	public String getFileExtensionFromString(String stringValue) {
//		if (stringValue.endsWith(ScraperConstant.PDF_EXTENSION)) {
//			return ScraperConstant.PDF_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.DOC_EXTENSION)) {
//			return ScraperConstant.DOC_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.DOCX_EXTENSION)) {
//			return ScraperConstant.DOCX_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.ODT_EXTENSION)) {
//			return ScraperConstant.ODT_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.RTF_EXTENSION)) {
//			return ScraperConstant.RTF_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.TXT_EXTENSION)) {
//			return ScraperConstant.TXT_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.MHT_EXTENSION)) {
//			return ScraperConstant.MHT_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.PPT_EXTENSION)) {
//			return ScraperConstant.PPT_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.PPTX_EXTENSION)) {
//			return ScraperConstant.PPTX_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.XLS_EXTENSION)) {
//			return ScraperConstant.XLS_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.XLSX_EXTENSION)) {
//			return ScraperConstant.XLSX_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.DOT_EXTENSION)) {
//			return ScraperConstant.DOT_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.DOTX_EXTENSION)) {
//			return ScraperConstant.DOTX_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.HWP_EXTENSION)) {
//			return ScraperConstant.HWP_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.ZIP_EXTENSION)) {
//			return ScraperConstant.ZIP_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.PNG_EXTENSION)) {
//			return ScraperConstant.PNG_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.JPG_EXTENSION)) {
//			return ScraperConstant.JPG_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.JPEG_EXTENSION)) {
//			return ScraperConstant.JPEG_EXTENSION;
//		}
//		if (stringValue.endsWith(ScraperConstant.XML_EXTENSION)) {
//			return ScraperConstant.XML_EXTENSION;
//		}
//		return null;
//	}
//
//	public String getFileNameFromContentDisposition(String contentDispositionValue) {
//		int index = contentDispositionValue.indexOf("filename=");
//		String fileName = "";
//		if (index >= 0) {
//			fileName = contentDispositionValue.substring(index + 9, contentDispositionValue.length());
//		}
//		fileName = fileName.replaceAll(";|\"", "");
//		return fileName;
//	}
//}
