package com.mycompany.url;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utf8SpecialCharacterChecker {
	public static void main(String[] args) {
		try {
//			String inputString = "https://api.hugverk.is/media/wd0c5bax/júlí2022.pdf";
        String inputString = "https://api.hugverk.is";

			boolean containsUtf8SpecialCharacters = hasUtf8SpecialCharacters(inputString);

			if (containsUtf8SpecialCharacters) {
				System.out.println("The string contains UTF-8 special characters.");
			} else {
				System.out.println("The string does not contain UTF-8 special characters.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean hasUtf8SpecialCharacters(String input)
			throws UnsupportedEncodingException, MalformedURLException {
		URL url = new URL(input);
		String urlPath = "";

		Charset utf8Charset = StandardCharsets.UTF_8;
		Pattern utf8Pattern = Pattern.compile("[^\\x00-\\x7F]", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = utf8Pattern.matcher(url.getPath());

		if (matcher.find()) {

			String[] splitURL = url.getPath().split("/");
			for (String splitInput : splitURL) {
				matcher = utf8Pattern.matcher(splitInput);
				if (matcher.find()) {
					System.out.println(splitInput);
					urlPath = urlPath + "/" + URLEncoder.encode(splitInput, "UTF-8");
				} else {
					urlPath = urlPath + "/" + splitInput;
				}
			}
		}

		String encodedUrl = url.getProtocol() + "://" + url.getHost() + urlPath;

		System.out.println(encodedUrl);

		return true;
	}
}
