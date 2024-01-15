package com.mycompany.extension;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileNameLogicOld {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			List<String> generatedFileNamesList = new ArrayList<>();
			generatedFileNamesList.add(" Udělený patent");
			generatedFileNamesList.add("World");
			generatedFileNamesList.add("descriptions");
			
//			String stringURL = "https://www.j-platpat.inpit.go.jp/cache/bulk/bulk1/JPPDFA/20230816/749777A4DA3F69720028B6F66E079F91C06C47DBBD6AA1672087E8EADA7ED7D4/JPPDFA_20230815.tar.gz";
			String stringURL = "http://siipris03.cofepris.gob.mx/Resoluciones/Consultas/ConWebRegEnsayosClinicosDetalle.asp?idsolicitud=1069";

			
			URL webURL = new URL(stringURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) webURL.openConnection();
			
			String fileName = getFileName(12345L, "test152345", generatedFileNamesList, stringURL,
					httpURLConnection, "#urlUniquePart");
			
			System.out.println(fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Return a file name which doesn't exist in scraper log.
	 * 
	 * @param value
	 * @param scraperLogs
	 * @param stringURL
	 * @param extension
	 * @return
	 * @throws MalformedURLException 
	 */
	public static String getFileName(Long masterId, String description, List<String> generatedFileNamesList, String stringURL,
			HttpURLConnection httpUrlConnection, String userPreference) throws MalformedURLException {
		String headerFileName = "";
		String urlUniquePart = "";
		String appGeneratedFileName = "";

		/*
		 * Get file name from content disposition header. It's most preferred option in
		 * default naming convention or User only want content disposition as header
		 * value file name.
		 */
		if ((httpUrlConnection != null) && (userPreference == null
				|| userPreference.equals(ScraperConstant.FILE_NAME_USER_PREFERENCE_HEADER))) {
			headerFileName = getFileNameFromHeader(masterId, httpUrlConnection);
		}

		/*
		 * In case there is no text available on page for anchor tag and no file name
		 * value in content disposition header or User only want unique part url as file
		 * name.
		 */
		if ((StringUtils.isBlank(description) && StringUtils.isBlank(headerFileName) && userPreference == null)
				|| (userPreference != null && userPreference.equals(ScraperConstant.FILE_NAME_USER_PREFERENCE_URL))) {
			urlUniquePart = getUniquePartOfURL(generatedFileNamesList, stringURL);
		}

		if (userPreference != null) {
			switch (userPreference) {
			case ScraperConstant.FILE_NAME_USER_PREFERENCE_HEADER: {
				appGeneratedFileName = headerFileName;
			}
				break;
			case ScraperConstant.FILE_NAME_USER_PREFERENCE_URL: {
				appGeneratedFileName = urlUniquePart;
			}
				break;
			case ScraperConstant.FILE_NAME_USER_PREFERENCE_DESCRIPTION: {
				appGeneratedFileName = description;
			}
				break;
			}
		} else {
			if (StringUtils.isNotBlank(headerFileName)) {
				appGeneratedFileName = headerFileName;
			} else if (StringUtils.isNotBlank(urlUniquePart)) {
				appGeneratedFileName = urlUniquePart;
			} else {
				appGeneratedFileName = FilenameUtils.getBaseName(description);
			}
		}

		/*
		 * In case of .tar.gz there will be .tar at end of file also remove it.
		 */
		if (appGeneratedFileName.endsWith(ScraperConstant.TAR_EXTENSION)) {
			appGeneratedFileName = appGeneratedFileName.replace(ScraperConstant.TAR_EXTENSION, "");
		}

		/*
		 * Replace everything that is not word character in file name.
		 */
		appGeneratedFileName = appGeneratedFileName.replaceAll(ScraperConstant.SPECIAL_CHARACTER_REGEX, "_");

		/*
		 * If file name has Chinese characters and greater than 95 only get first 95
		 * characters.
		 */
		if (containsHanScript(appGeneratedFileName) && appGeneratedFileName.length() > 50) {
			appGeneratedFileName = appGeneratedFileName.substring(0, 50);
		}

		/*
		 * If file name is greater than 100 only get first 100 characters.
		 */
		else if (appGeneratedFileName.length() > 100) {
			appGeneratedFileName = appGeneratedFileName.substring(0, 99);
		}

		/*
		 * Check the uniqueness of file name.
		 */
		for (String fileName : generatedFileNamesList) {
			if (fileName.equals(appGeneratedFileName)) {
				appGeneratedFileName = getUniquePartOfURL(generatedFileNamesList, stringURL);
			}
		}
		return appGeneratedFileName;
	}
	
	/**
	 * 
	 * @param masterId
	 * @param httpUrlConnection
	 */
	public static String getFileNameFromHeader(Long masterId, HttpURLConnection httpUrlConnection) {
		String contentDispositionHeaderFieldValue = "";
		ContentDispositionFileNameParser contentDispositionFileNameParser = new ContentDispositionFileNameParser();
		String fileName = "";
		try {
			// Get Content-Disposition header value from URL
			for (String headerName : ScraperConstant.contentDispositionHeaderName) {
				if (httpUrlConnection.getHeaderField(headerName) != null) {
					contentDispositionHeaderFieldValue = httpUrlConnection.getHeaderField(headerName);
					break;
				}
			}
			// Get file name from Content-Disposition header value
			if (StringUtils.isNotEmpty(contentDispositionHeaderFieldValue)) {
				fileName = contentDispositionFileNameParser.parse(contentDispositionHeaderFieldValue);
				fileName = FilenameUtils.getBaseName(fileName);
			}
		} catch (Exception ex) {
			System.out.println(masterId + " : Error occurred WHILE GETTING FILE NAME FROM HEADER : " + ex);
		}
		return fileName;
	}
	
	/**
	 * Return a unique part of URL which doesn't exist in scraper log.
	 * 
	 * @param scraperLogs
	 * @param stringURL
	 * @param extension
	 * @return
	 * @throws MalformedURLException 
	 */
	public static String getUniquePartOfURL(List<String> generatedFileNamesList, String stringURL) throws MalformedURLException {
		String[] urlParts = stringURL.split("/");
		boolean duplicate = false;
		String appGeneratedFileName = "";

		for (int i = urlParts.length - 1; i >= 0; i--) {
			if (i == urlParts.length - 1) {
				appGeneratedFileName = urlParts[i];
			} else {
				appGeneratedFileName = appGeneratedFileName + "_" + urlParts[i];
			}
			
			System.out.println(new URL(stringURL).getQuery());

			/*
			 * Get only file name.
			 */
			appGeneratedFileName = FilenameUtils.getBaseName(appGeneratedFileName);

			/*
			 * In case of .tar.gz there will be .tar at end of file also remove it.
			 */
			if (appGeneratedFileName.endsWith(ScraperConstant.TAR_EXTENSION)) {
				appGeneratedFileName = appGeneratedFileName.replace(ScraperConstant.TAR_EXTENSION, "");
			}

			// if file name is greater than 100 only get first 100 characters
			if (appGeneratedFileName.length() > 100) {
				appGeneratedFileName = appGeneratedFileName.substring(0, 99);
			}

			/*
			 * Replace everything that is not word character in file name.
			 */
			appGeneratedFileName = appGeneratedFileName.replaceAll(ScraperConstant.SPECIAL_CHARACTER_REGEX, "_");

//			appGeneratedFileName = FilenameUtils.getBaseName(appGeneratedFileName);

			for (String fileName : generatedFileNamesList) {
				duplicate = false;
				if (fileName.equals(appGeneratedFileName)) {
					duplicate = true;
					break;
				}
			}
			if (!duplicate) {
				break;
			}
		}
		return appGeneratedFileName;
	}

	/**
	 * Check string contains Chinese characters
	 * 
	 * @param s
	 * @return
	 */
	public static boolean containsHanScript(String s) {
		return s.codePoints()
				.anyMatch(codepoint -> Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN);
	}
}
