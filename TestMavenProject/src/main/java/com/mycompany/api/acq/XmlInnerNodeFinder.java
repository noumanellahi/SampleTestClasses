package com.mycompany.api.acq;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlInnerNodeFinder {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = new File("C:\\Users\\NomanAlahi\\Desktop\\XML_TEST\\test.xml");
//			URL url = new URL("https://api.biorxiv.org/details/biorxiv/2021-06-04/2021-06-06/0/xml");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
//			factory.setValidating(false);
//			factory.setNamespaceAware(true);
//			factory.setFeature("http://xml.org/sax/features/namespaces", false);
//			factory.setFeature("http://xml.org/sax/features/validation", false);
//			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
//			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			
			DocumentBuilder builder = factory.newDocumentBuilder();

//			Document document = builder.parse(con.getInputStream());
			Document document = builder.parse(new FileInputStream(file));

			/**
			 * With X-Path
			 */
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.evaluate("//Transaction", document, XPathConstants.NODESET);
			document = null;
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);
				Element element = (Element) node;
				String childFilename = "part" + temp + ".xml";

				String nodeString = createXmlFileFromNode(element, "C:\\Users\\NomanAlahi\\Desktop\\XML_TEST\\", childFilename);
				
				Document childDocument = builder.parse(new ByteArrayInputStream(nodeString.getBytes()));

//				Document childDocument = builder
//						.parse(new FileInputStream("C:\\Users\\NomanAlahi\\Desktop\\XML_TEST\\" + childFilename));

				Node innerFiles = (Node) xPath.evaluate("//MarkImageURI", childDocument, XPathConstants.NODE);

				if (innerFiles != null) {
					System.out.println(innerFiles.getTextContent());
				}

//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					XPath xPath2 = XPathFactory.newInstance().newXPath();
//					System.out.println(nodeToString(node));
//					Node dosument = (Node) xPath2.evaluate("//MarkImageURI", nodeToString(node), XPathConstants.NODE);
//
//					if (dosument != null) {
//						System.out.println(dosument.getTextContent());
//					}
//
//					if(element.getElementsByTagName("Reason").getLength() > 0) {
//						System.out.println(element.getElementsByTagName("Reason").item(0).getTextContent());
//					}
//
//				}
			}

			/**
			 * With Tag Name
			 */
//			NodeList nodeList = document.getElementsByTagName("Transaction");
//			for (int temp = 0; temp < nodeList.getLength(); temp++) {
//				Node node = nodeList.item(temp);
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					// Print each employee's detail
//					Element eElement = (Element) node;
//					System.out.println("URL : " + eElement.getElementsByTagName("MarkImageURI").item(0).getTextContent());
//				}
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String createXmlFileFromNode(Element element, String localPath, String fileName) {
		FileWriter writer = null;
		StringWriter sw = new StringWriter();
		String nodeString = null;
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(element);
			writer = new FileWriter(new File(localPath + File.separator + fileName));
			StreamResult result = new StreamResult(writer);
			transformer.transform(source, result);

			transformer.transform(source, new StreamResult(sw));

			nodeString = sw.toString();

			transformer = null;
			result = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
		return nodeString;
	}

	private static String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.out.println("nodeToString Transformer Exception");
		}
		return sw.toString();
	}
}
