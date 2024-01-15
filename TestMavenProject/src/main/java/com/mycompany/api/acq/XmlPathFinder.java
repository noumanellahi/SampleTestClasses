package com.mycompany.api.acq;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlPathFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL("https://hrcak.srce.hr/oai/?verb=ListRecords&metadataPrefix=oai_dc&from=2022-03-21");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(con.getInputStream());

			/**
			 * With X-Path
			 */
			XPath xPath = XPathFactory.newInstance().newXPath();
//			NodeList nodeList = (NodeList) xPath.evaluate("/OAI-PMH/ListRecords/record/metadata/*:identifier[1]", document, XPathConstants.NODESET);
			NodeList nodeList = (NodeList) xPath.evaluate("//*[name()='dc:identifier'][2]", document, XPathConstants.NODESET);
//			System.out.println("URL : " + nodeList.item(0).getTextContent());
//			System.out.println("URL : " + nodeList.getLength());
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// Print each employee's detail
//					NodeList nodeList2 = (NodeList) xPath.evaluate("", node, XPathConstants.NODESET);
//					System.out.println("URL : " + node.getTextContent());
					System.out.println("URL : " + node.toString());
				}
			}

			/**
			 * With Tag Name
			 */
//			NodeList nodeList = document.getElementsByTagName("record");
//			for (int temp = 0; temp < nodeList.getLength(); temp++) {
//				Node node = nodeList.item(temp);
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					// Print each employee's detail
//					Element eElement = (Element) node;
//					System.out.println("URL : " + eElement.getElementsByTagName("link_pdf").item(0).getTextContent());
//				}
//			}
			con.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String createFileNameFromMultiplePathValues(NodeList namingConventionNodeList, int numberOfValues,
			int iteration) {
		String fileName = "";
		for (int i = 0; i < numberOfValues; i++) {
			Node node = namingConventionNodeList.item(iteration * numberOfValues + i);

			fileName = fileName + node.getTextContent();

			if (i < numberOfValues - 1) {
				fileName = fileName + "_";
			}
		}
		return fileName;
	}

}
