package com.mycompany.testmavenproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;

public class HtmlToPdf {
	public static void main(String[] args) {

//		String url = "https://elaws.e-gov.go.jp/search/elawsSearch/elaws_search/lsg0500/detail?lawId=336CO0000000011";
//		String url = "https://elaws.e-gov.go.jp/search/elawsSearch/elaws_search/lsg0500/viewContents?lawId=336CO0000000011_20181230_429CO0000000008";
//		String url = "https://www.has-sante.fr/jcms/fc_2875171/fr/resultat-de-recherche-antidot-2019?text=commission+de+la+transparence&tmpParam=typesf%3Dnews&typesf=news&opSearch=";
		
		String url = "https://s3.amazonaws.com/www.webacq-sample.com/3_level_crawling/page-2.html";
		Document document = Jsoup.parse("<html></html>");

		String htmlFilePath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\testing.html";
//		String htmlUnitPdfFilePath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\testing.pdf";
		String chromePdfFilePath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\testingChrome2.pdf";

		try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {

			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setTimeout(100 * 1000);
			// new change added by nabeel
			webClient.getOptions().setAppletEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setCssErrorHandler(new SilentCssErrorHandler());
			webClient.setHTMLParserListener(null);
			webClient.setJavaScriptErrorListener(null);
			webClient.setJavaScriptTimeout(10000);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			HtmlPage page = webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(10 * 1000);

			document = Jsoup.parse(page.asXml(), url);
			
			document.getElementsByTag("head").append("<style media=\"print\">\r\n" + 
					" @page {\r\n" + 
					"  size: auto;\r\n" + 
					"  margin: 0;\r\n" + 
					"       }\r\n" + 
					"</style>");
			System.out.println(document);

//			for (Element element : document.select("div.actions")) {
//				element.remove();
//			}
			
			
//			System.out.println(document.select("#fc_2874863_0_fc_2874867_1_fc_2875171_0 > section > div > table > tbody > tr > td > div.blocCentral > div.clusterSearch.detailTheme > div > div.cluster--results > ul > li:nth-child(1) > div"));

//			document = absoluteUrls(document);

			Elements mainDiv = document.select("#lsg0500 > div.container-fluid > table:nth-child(2),#right_content > div > div");
//			Elements mainDiv = document.select("#right_content > div > div");
//			Elements mainDiv = document.select("#lsg0501");
			
//			StringBuilder str = new StringBuilder();
//			str.append("<html>");
//			str.append(document.getElementsByTag("head"));
//			str.append("<body>");
//			str.append(mainDiv);
//			str.append("</body></html>");

//			System.out.println(str);
//
//			// create an html file on given file path
//			Writer unicodeFileWriter = new OutputStreamWriter(new FileOutputStream(htmlFilePath), "UTF-8");
//			unicodeFileWriter.write(str.toString());
//			unicodeFileWriter.close();
//
//			ConverterProperties properties = new ConverterProperties();
//			properties.setCharset("UTF-8");
//			if (url.contains(".kr") || url.contains(".tw") || url.contains(".cn") || url.contains(".jp")) {
//				properties.setFontProvider(new DefaultFontProvider(false, false, true));
//			}
//			properties.setMediaDeviceDescription(new MediaDeviceDescription(MediaType.PRINT));
//			properties.setBaseUri(url);
//			
//			
//		    PdfWriter writer = new PdfWriter(htmlUnitPdfFilePath);
//		    PdfDocument pdf = new PdfDocument(writer);
//		    pdf.setTagged();
//		    PageSize pageSize = PageSize.A4.rotate();
//		    pdf.setDefaultPageSize(pageSize);
//
//		    MediaDeviceDescription mediaDeviceDescription
//		        = new MediaDeviceDescription(MediaType.SCREEN);
//		    mediaDeviceDescription.setWidth(100000);
//		    properties.setMediaDeviceDescription(mediaDeviceDescription);
//		    HtmlConverter.convertToPdf(new FileInputStream(htmlFilePath), pdf, properties);

//			 convert the html file to pdf file.
//			HtmlConverter.convertToPdf(new File(htmlFilePath), new File(htmlUnitPdfFilePath), properties);
//
//			Process p = Runtime.getRuntime().exec("cmd /C start chrome --headless --no-sandbox --print-to-pdf="
//					+ chromePdfFilePath + " " + htmlFilePath);
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String line = null;
//			while ((line = in.readLine()) != null) {
//				System.out.println(line);
//			}
//
//			Thread.sleep(5000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Document absoluteUrls(Document originalDocument) {
		Elements cssTags = originalDocument.getElementsByAttributeValue("rel", "stylesheet");
		Elements hyperLinks = originalDocument.getElementsByTag("a");
		for (Element link : hyperLinks) {
			link.attr("href", link.attr("abs:href"));
		}
		for (Element cssTag : cssTags) {
			cssTag.attr("href", cssTag.attr("abs:href"));
		}
		return originalDocument;
	}

}
