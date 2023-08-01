/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.MagicDetector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;

/**
 *
 * @author Noman.Alahi
 */
public class tikaTest {

    public static void main(String[] arg) {
        try {
        	
        	
        	
        	
        	
////        	String url = "http://www.xn--spezialittenliste-yqb.ch/";
//            URL webURL = new URL("https://www.has-sante.fr/plugins/ModuleHAS2019/jsp/ajax/monCompte.jsp");
////        	URL webURL = new URL("http://www.xn--spezialittenliste-yqb.ch/");
//            URLConnection urlConnection = webURL.openConnection();
//            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
//            String extension = "";
//            
//            System.out.println(MediaType.parse(urlConnection.getHeaderField("Content-Type")).getBaseType());
//            
//			extension = allTypes.forName(MediaType.parse(urlConnection.getHeaderField("Content-Type")).getBaseType().toString()).getExtension();
////			if (StringUtils.isEmpty(extension)) {
////				extension = allTypes
////						.forName((new Tika()).detect(TikaInputStream.get(urlConnection.getInputStream())))
////						.getExtension();
////			}
//			System.out.println(extension);
//            
////            extension = allTypes.forName("text/html").getExtension();
////			if (StringUtils.isEmpty(extension)) {
////				extension = allTypes
////						.forName((new Tika()).detect(TikaInputStream.get(urlConnection.getInputStream())))
////						.getExtension();
////			}
//            
////            System.out.println(extension);
//            
////			extension = allTypes.forName(val).getExtension();
////			if (StringUtils.isEmpty(extension)) {
////				extension = allTypes
////						.forName((new Tika()).detect(urlConnection.getInputStream()))
////						.getExtension();
////			}
////			System.out.println(extension);
//            
////			extension = allTypes.forName(urlConnection.getHeaderField("Content-Type")).getExtension();
////			if (StringUtils.isEmpty(extension)) {
////				extension = allTypes
////						.forName((new Tika()).detect(TikaInputStream.get(urlConnection.getInputStream())))
////						.getExtension();
////			} if (StringUtils.isEmpty(extension)) {
////	             Tika tika = new Tika();
////	             System.out.println("2 ------> " + tika.detect(urlConnection.getInputStream()));
////			}
//			
////			Tika tika = new Tika();
////			InputStream is = urlConnection.getInputStream();
////			BufferedInputStream stream = new BufferedInputStream(is);
////
////			
////			MagicDetector md = new MagicDetector(MediaType.OCTET_STREAM,);
////            
////            System.out.println( );
//            
//            //1
////            MimeType mimeType = allTypes.forName(urlConnection.getHeaderField("Content-Type"));
////            extension = mimeType.getExtension();
////            System.out.println( "1 ------> " + extension);
//             
//            //2
////             Tika tika = new Tika();
////             System.out.println("2 ------> " + tika.detect(webURL));
//             
//             //3
////             MimeType mimeType2 = allTypes.forName(tika.detect(urlConnection.getInputStream()));
////             System.out.println("3 ------> " + allTypes.forName((new Tika()).detect(TikaInputStream.get(webURL))).getExtension());
//             
//             
////             System.out.println(URLConnection.guessContentTypeFromStream(urlConnection.getInputStream()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
