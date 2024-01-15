package com.mycompany.api.acq;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CommaSeparatedXPath {
	private static final String dynamic_fixed_path_replacement_identifier = "{value_%d}";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = new File("C:\\Users\\NomanAlahi\\Desktop\\ops_test.xml");
			String xPathString = "//publication-reference//document-id[@document-id-type=\"docdb\"]/doc-number, //publication-reference//document-id[@document-id-type=\"docdb\"]/kind, //publication-reference//document-id[@document-id-type=\"docdb\"]/country";
			String fixedPath = "http://ops.epo.org/3.2/rest-services/published-data/images/{value_1}/{value_2}/{value_3}/fullimage.tiff?Range=1";

			String fileNamePath = "//publication-reference//document-id[@document-id-type=\"epodoc\"]/dog-number|//publication-reference//document-id[@document-id-type=\"epodoc\"]/fsdf";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new FileInputStream(file));
			List<String> multipleDynamicValuesList = new ArrayList<>();

			xPathString = xPathString.replaceAll(",", "|");

			int values = StringUtils.countMatches(xPathString, "|") + 1;
			int nameValues = StringUtils.countMatches(fileNamePath, "|") + 1;

			/**
			 * With X-Path
			 */
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.evaluate(xPathString, document, XPathConstants.NODESET);
			NodeList nameNodeList = (NodeList) xPath.evaluate(fileNamePath, document, XPathConstants.NODESET);

			for (int temp = 0; temp < nodeList.getLength(); temp += values) {
				String commaSeparatedValues = "";
				for (int i = 0; i < values; i++) {
					Node node = nodeList.item(i + temp);

					commaSeparatedValues = commaSeparatedValues + node.getTextContent();

					if (i < values - 1) {
						commaSeparatedValues = commaSeparatedValues + ",";
					}
				}
				multipleDynamicValuesList.add(commaSeparatedValues);
			}

			int iteration = 0;
			for (String value : multipleDynamicValuesList) {
				replacementTestForEpOPSApiPath(value, iteration, fixedPath, nameNodeList, nameValues);
				iteration++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void replacementTestForEpOPSApiPath(String dynamicValues, int iteration, String fixedPath,
			NodeList nameNodeList, int nameValues) {
		// TODO Auto-generated method stub
		try {

			String[] dynamicValuesArray = dynamicValues.split(",");

			for (int i = 0; i < dynamicValuesArray.length; i++) {
				String valueIentifier = String.format(dynamic_fixed_path_replacement_identifier, i + 1);
				fixedPath = fixedPath.replace(valueIentifier, dynamicValuesArray[i]);
			}
//			System.out.println(fixedPath);

			String fileName = "";
			for (int i = 0; i < nameValues; i++) {
				Node node = nameNodeList.item(iteration*nameValues + i);

				fileName = fileName + node.getTextContent();

				if (i < nameValues - 1) {
					fileName = fileName + "_";
				}
			}
			
			System.out.println(fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
