import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jsoup.Connection;
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

public class NoHeaderFooterHtmlToPDF {

	public static void main(String[] args) {
		String url = "https://s3.amazonaws.com/www.webacq-sample.com/3_level_crawling/page-1.html";
		Document document = Jsoup.parse("<html></html>");

		String htmlFilePath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\Margin\\NO_MARGIN.html";
		String chromePdfFilePath = "C:\\Users\\Noman.Alahi\\Desktop\\Testing\\Margin\\NO_MARGIN.pdf";

		try {

			Connection.Response execute = Jsoup.connect(url).header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3").header("Accept-Encoding", "none")
					.header("Accept-Language", "en-US,en;q=0.9").header("Connection", "keep-alive").execute();
			document = Jsoup.parse(execute.body(), url);

			document = absoluteUrls(document);

			document.getElementsByTag("head")
					.append("<style media=\"print\"> @page { size: auto; margin: 0; padding: 0;}</style>");

			StringBuilder str = new StringBuilder();
			str.append("<html>");
//			str.append(document.getElementsByTag("head"));
			str.append("<body>");
			str.append(document);
			str.append("</body></html>");

			// create an html file on given file path
			Writer unicodeFileWriter = new OutputStreamWriter(new FileOutputStream(htmlFilePath), "UTF-8");
			unicodeFileWriter.write(str.toString());
			unicodeFileWriter.close();

			Process p = Runtime.getRuntime().exec("cmd /C start chrome --headless --no-sandbox --print-to-pdf="
					+ chromePdfFilePath + " --no-margins " + htmlFilePath);

			Thread.sleep(5000);
			System.out.println(document.baseUri());
			System.out.println("END");
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
