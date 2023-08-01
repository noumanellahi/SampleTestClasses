package com.mycompany.url;

import org.apache.commons.lang3.StringUtils;

public class ContentDispositionHeaderValueParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String contentDispositionValue = "asdadasdadasdadadadad";
			String fileName = ContentDispositionFileNameParser.parse(contentDispositionValue);
			if (StringUtils.isNotBlank(fileName)) {
				System.out.println(fileName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

//		String[] contentDispositionValueParts = contentDispositionValue.split(";");
//		String fileName = "";
//		for (String part : contentDispositionValueParts) {
//			int index = part.indexOf("filename=");
//			if (index >= 0) {
//				fileName = part.substring(index + 9, part.length());
//			}
//
//			if (StringUtils.isNotBlank(fileName)) {
//				fileName = fileName.replaceAll("\"", "");
//				System.out.println(fileName);
//				break;
//			}
//		}
	}

}
