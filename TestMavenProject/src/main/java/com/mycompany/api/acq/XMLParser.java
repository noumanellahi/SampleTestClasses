package com.mycompany.api.acq;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL(
					"https://csu-sfsu.alma.exlibrisgroup.com/rep/getFile?institution_code=institutional_links&file=01CALS_SFR");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

//			factory.setValidating(false);
//			factory.setNamespaceAware(true);
//			factory.setFeature("http://xml.org/sax/features/namespaces", false);
//			factory.setFeature("http://xml.org/sax/features/validation", false);
//			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder builder = factory.newDocumentBuilder();

//			InputStream in = httpURLConnection.getInputStream();
//			String encoding = httpURLConnection.getContentEncoding();
//			encoding = encoding == null ? "UTF-8" : encoding;
//			String body = IOUtils.toString(in, encoding);
//			System.out.println(body);

			Document document = builder.parse(httpURLConnection.getInputStream());

			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.evaluate("//url", document, XPathConstants.NODESET);

			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);

				System.out.println(node.getTextContent());

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
