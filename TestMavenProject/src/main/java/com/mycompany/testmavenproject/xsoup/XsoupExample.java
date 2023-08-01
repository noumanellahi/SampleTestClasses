/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject.xsoup;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author Noman.Alahi
 */
public class XsoupExample {

    public static void main(String[] args) {
        String url = "https://www.ema.europa.eu/en/news-events/whats-new";
        Document doc = getHtml(url);

//        System.out.println(doc.html());
//        org.w3c.dom.Document document = Xsoup.convertDocument(doc);

        Elements elements = Xsoup.select(doc.html(), "/html/body/main/div[2]/div/section/div[3]/div[2]/table").getElements();
        System.out.println(elements);
    }

    /**
     * Returns the HTML of provided URL as Document using JSOUP .get() method
     *
     * @param url
     * @return Document
     */
    public static Document getHtml(String url) {
        Document doc = Jsoup.parse("<html></html>");
        try {
            doc = Jsoup.connect(url)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3")
                    .header("Accept-Encoding", "none")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("Connection", "keep-alive")
                    .get();
            return doc;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
}
