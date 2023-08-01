///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.testmavenproject.OCR;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Paragraph;
//import static com.itextpdf.text.pdf.PdfName.STATE;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.hwpf.usermodel.Range;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//
///**
// *
// * @author Noman.Alahi
// */
//public class DOCCONVERTER {
//
//    public static void main(String[] args) throws FileNotFoundException {
//        InputStream documentInputStream = new FileInputStream(new File("C:\\Users\\Noman.Alahi\\Desktop\\Converter\\Test.doc"));
//        Document document = null;
//        WordExtractor we = null;
//        ByteArrayOutputStream out = null;
//        byte[] documentByteArray = null;
//        try {
//            document = new Document();
//            OutputStream outputStream = new FileOutputStream("C:\\Users\\Noman.Alahi\\Desktop\\Converter\\doc.pdf");
//            POIFSFileSystem fs = new POIFSFileSystem(documentInputStream);
//
//            HWPFDocument doc = new HWPFDocument(fs);
//            we = new WordExtractor(doc);
//            out = new ByteArrayOutputStream();
//            PdfWriter writer = PdfWriter.getInstance(document, out);
//
//            Range range = doc.getRange();
//            document.open();
//            writer.setPageEmpty(true);
//            document.newPage();
//            writer.setPageEmpty(true);
//
//            String[] paragraphs = we.getParagraphText();
//            for (int i = 0; i < paragraphs.length; i++) {
//                org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
//                paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
//                document.add(new Paragraph(paragraphs[i]));
//            }
//            PdfWriter.getInstance(document, outputStream);
////            documentByteArray = out.toByteArray();
////            outputStream.write(documentByteArray);
//            
//        } catch (Exception ex) {
//            ex.printStackTrace(System.out);
//        } finally {
////            document.close();
//            try {
//                we.close();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        
//    }
//}
