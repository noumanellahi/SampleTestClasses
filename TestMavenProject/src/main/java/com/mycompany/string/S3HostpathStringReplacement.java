package com.mycompany.string;

import org.apache.commons.lang3.StringUtils;

public class S3HostpathStringReplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String HOST_PATH_TAG_VALUE_IDENTIFIER = "{siteNav_TagsInclude-%level%}";

		final String HOST_PATH_TAG_VALUE_IDENTIFIER_WITHOUT_LEVEL = "{siteNav_TagsInclude";

		String completePath = "raw/patents/AU/XMLPDF/SupplementaryReleases/{siteNav_TagsInclude-3}";

		String value = "";

		for (int i = 0; i <= 3; i++) {

			if (i == 0) {
				System.out.println(completePath.substring(0,
						completePath.indexOf(HOST_PATH_TAG_VALUE_IDENTIFIER_WITHOUT_LEVEL) - 1));
				
				System.out.println("***************************************");
			} else {

				String identifier = HOST_PATH_TAG_VALUE_IDENTIFIER.replace("%level%", String.valueOf(i));

				String currentLevelPath = completePath.substring(0,
						completePath.indexOf(identifier) + identifier.length());

				if (StringUtils.isNotBlank(value)) {
					completePath = completePath.replace(identifier, value + i);
					currentLevelPath = currentLevelPath.replace(identifier, value + i);

					System.out.println(completePath);
					System.out.println(currentLevelPath);
					
					System.out.println("***************************************");
				} else {
					completePath = completePath.replace("/" + identifier, "");
					currentLevelPath = currentLevelPath.replace("/" + identifier, "");

					System.out.println(completePath);
					System.out.println(currentLevelPath);
					System.out.println("***************************************");
				}

			}

		}

	}

}
