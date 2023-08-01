package com.mycompany.string;

import org.apache.commons.lang3.StringUtils;

public class ReplaceUnicodeCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "<For some reason my �double quotes� were lost.>";
//		str = str.replaceAll(
//				"[\u0000-\u0008\u000E-\u001F\u007F-\u0084\u0086-\u009F\uD800-\uDFFF\uFDD0-\uFDEF\u000B\u000C\uFFFE\uFFFF]",
//				"");

		String fixed_path_replacement_identifier = "{DOCUMENT_PATH}";
		String downloadUrl = "/download/from/here/test.pdf";
		String fixedPath = "{DOCUMENT_PATH}https://singularity-portal-dev.dev.singularity.clarivate.com{DOCUMENT_PATH}";

		if (StringUtils.isNotBlank(fixedPath)) {
			if (fixedPath.contains(fixed_path_replacement_identifier)) {
				System.out.println("oh yes baby");
				downloadUrl = fixedPath.replace(fixed_path_replacement_identifier, downloadUrl);
			} else {
				downloadUrl = fixedPath + downloadUrl;
			}
		}

		System.out.println(downloadUrl);
	}

}
