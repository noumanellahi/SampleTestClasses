///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.testmavenproject;
//
//import com.itextpdf.kernel.geom.AffineTransform;
//import com.itextpdf.kernel.geom.Rectangle;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfPage;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.WriterProperties;
//import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
//import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
//import com.itextpdf.layout.Document;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.pdf.PdfDocument;
//import com.itextpdf.text.pdf.PdfFormXObject;
//import com.itextpdf.text.pdf.PdfPage;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.awt.Rectangle;
//import java.awt.geom.AffineTransform;
//
///**
// *
// * @author Noman.Alahi
// */
//public class Compression {
//
//    public static void main(String[] args) {
//        try {
//            // Creating a PdfWriter object
//            String dest = "C:\\Users\\Noman.Alahi\\Desktop\\Compress.pdf";
//            PdfWriter writer = new PdfWriter(dest, new WriterProperties().setFullCompressionMode(true));
//
//            // Creating a PdfReader
//            String src = "C:\\Users\\Noman.Alahi\\Desktop\\B2_Classification_commune_des_actes_médicaux_descriptive_à_.pdf";
//            PdfReader reader = new PdfReader(src);
//
//            // Creating a PdfDocument objects
//            PdfDocument destpdf = new PdfDocument(writer);
//            PdfDocument srcPdf = new PdfDocument(reader);
//
//            System.out.println(srcPdf.getNumberOfPages());
//
//            for (int i = 1; i <= srcPdf.getNumberOfPages(); i++) {
//                // Opening a page from the existing PDF 
//                PdfPage origPage = srcPdf.getPage(i);
//
//                // Getting the page size
//                Rectangle orig = origPage.getPageSizeWithRotation();
//
//                // Adding a page to destination Pdf
//                PdfPage page = destpdf.addNewPage();
//
//                // Scaling the image in a Pdf page     
//                AffineTransform transformationMatrix = AffineTransform.getScaleInstance(
//                        page.getPageSize().getWidth() / orig.getWidth() / 2,
//                        page.getPageSize().getHeight() / orig.getHeight() / 2);
//
//                // Shrink original page content using transformation matrix
//                PdfCanvas canvas = new PdfCanvas(page);
//                canvas.concatMatrix(transformationMatrix);
//
//                // Add the object to the canvas
//                PdfFormXObject pageCopy = origPage.copyAsFormXObject(destpdf);
//                canvas.addXObject(pageCopy, 0, 0);
//            }
//            // Creating a Document object
//            Document doc = new Document(destpdf);
//
//            // Closing the document
//            doc.close();
//
//            System.out.println("Table created successfully..");
//        } catch (Exception ex) {
//
//        }
//    }
//
//}
