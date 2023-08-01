package com.mycompany.string;

public class S3DynamicHostLocationForDraft {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String HOST_PATH_TAG_VALUE_IDENTIFIER_WITHOUT_LEVEL = "{siteNav_TagsInclude";

		String completePath = "raw/patents/AU/XMLPDF/SupplementaryReleases/{siteNav_TagsInclude-3}";

		completePath = completePath.substring(0, completePath.indexOf(HOST_PATH_TAG_VALUE_IDENTIFIER_WITHOUT_LEVEL))
				+ "draft/" + completePath.substring(completePath.indexOf(HOST_PATH_TAG_VALUE_IDENTIFIER_WITHOUT_LEVEL),
						completePath.length());

		System.out.println(completePath);

	}

}
