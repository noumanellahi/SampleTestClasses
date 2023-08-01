package com.mycompany.api.acq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathFinder2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String jsonPath = "$.results.*";
			String jsonPath2 = "$..MarkImageFilename";
			URL url = new URL(
					"https://sis.ukrpatent.org/api/v1/open-data/?&obj_type=4&app_date_from=22.07.2022&app_date_to=24.07.2022&page=1");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");
			httpURLConnection.setRequestMethod("GET");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			DocumentContext jsonContext = getJsonDocument(httpURLConnection);

			List<Object> nodeList = getNodeList(jsonContext, jsonPath);
			List<Object> downloadList = getNodeList(jsonContext, jsonPath2);

			if (nodeList != null && !nodeList.isEmpty()) {
				int i2 = 0;
				for (int i = 0; i < nodeList.size(); i++) {
					System.out.println(nodeList.get(i).toString());
					if (i2 < downloadList.size()
							&& nodeList.get(i).toString().contains(downloadList.get(i2).toString())) {
						System.out.println(downloadList.get(i2).toString());
						i2++;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static DocumentContext getJsonDocument(HttpURLConnection httpURLConnection) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				content.append(inputLine);
			}
			return JsonPath.parse(content.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static List<Object> getNodeList(DocumentContext jsonContext, String jsonPath) {
		try {
			Object jsonNodes = jsonContext.read(jsonPath);

			if (jsonNodes instanceof List) {
				return (List<Object>) jsonNodes;
			} else {
				List<Object> jsonNodesList = new ArrayList<>();
				jsonNodesList.add(jsonNodes);
				return jsonNodesList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
