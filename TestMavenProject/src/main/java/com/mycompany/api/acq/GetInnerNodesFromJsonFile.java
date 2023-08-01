package com.mycompany.api.acq;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class GetInnerNodesFromJsonFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//			File file = new File("C:\\Users\\NomanAlahi\\Desktop\\JSON_TEST\\test.json");
			File file = new File("C:\\Users\\NomanAlahi\\Desktop\\JSON_TEST\\test2.json");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			DocumentContext jsonContext = getJsonDocument(file);

//			List<Object> nodeList = getNodeList(jsonContext, "$.results.*");
			List<Object> nodeList = getNodeList(jsonContext, "$.itemHits.*.item");

			for (int i = 0; i < nodeList.size(); i++) {
//				String node = nodeList.get(i).toString();
				String childFilename = "part" + i + ".json";

				createJsonFileFromNode(nodeList.get(i), "C:\\Users\\NomanAlahi\\Desktop\\JSON_TEST\\", childFilename);

				List<Object> childNOdeList = getNodeList(
						getJsonDocument(
								new ByteArrayInputStream(ow.writeValueAsString(nodeList.get(i)).getBytes("UTF-8"))),
						"$..asset.original.* | $..suppItems[*].asset.original.*");

//				List<Object> childNOdeList = getNodeList(
//						getJsonDocument(
//								new ByteArrayInputStream(ow.writeValueAsString(nodeList.get(i)).getBytes("UTF-8"))),
//						"$..asset..original.*");

//				List<Object> childNOdeList2 = getNodeList(
//						getJsonDocument(new File("C:\\Users\\NomanAlahi\\Desktop\\JSON_TEST\\" + childFilename)),
//						"$..MarkImageFilename");
//
				for (int j = 0; j < childNOdeList.size(); j++) {
					System.out.println(childNOdeList.get(j));
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static DocumentContext getJsonDocument(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				content.append(inputLine);
			}
//			System.out.println(content.toString());
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

	public static DocumentContext getJsonDocument(InputStream in) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				content.append(inputLine);
			}
//			System.out.println(content.toString());
			return JsonPath.parse(content.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static List<Object> getNodeList(DocumentContext jsonContext, String jsonPath) {
		List<Object> jsonNodesList = new ArrayList<>();
		try {

			String[] jsonPathValues = jsonPath.split("\\|");

			for (String value : jsonPathValues) {
				Object jsonNodes = jsonContext.read(value.trim());

				if (jsonNodes instanceof List) {
					jsonNodesList.addAll((List<Object>) jsonNodes);
				} else {
					jsonNodesList.add(jsonNodes);
				}
			}
			return jsonNodesList;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void createJsonFileFromNode(Object node, String localPath, String fileName) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(localPath + File.separator + fileName), node);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
