package com.mycompany.api.acq;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class CommaSeparatedXpath2 {

	public static void main(String[] args) throws Exception {

		try {
			String xml = "<publication-reference>\n" + "    <document-id document-id-type=\"docdb\">\n"
					+ "        <country>CH</country>\n" + "        <doc-number>718740</doc-number>\n"
					+ "        <kind>B1</kind>\n" + "    </document-id>\n" + "</publication-reference>";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));

			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();

			XPathExpression expression = xpath.compile(
					"//publication-reference//document-id[@document-id-type=\"docdb\"]/(country | doc-number)");
			NodeList nodeList = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getNodeName() + ": " + nodeList.item(i).getTextContent());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
