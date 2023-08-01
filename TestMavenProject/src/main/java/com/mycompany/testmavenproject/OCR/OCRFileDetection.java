/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject.OCR;

import java.io.File;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Noman.Alahi
 */
public class OCRFileDetection {

    public static void main(String[] args) {
        try {
//            PdfReader pdfReader = new PdfReader("C:\\Users\\Noman.Alahi\\Desktop\\ocrscan.pdf");
//
//            Path file = Paths.get("C:\\Users\\Noman.Alahi\\Desktop\\ocrscan.pdf");
//            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
//
//            System.out.println("creationTime: " + attr.creationTime());
//            System.out.println("lastAccessTime: " + attr.lastAccessTime());
//            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
//
//            System.out.println("isDirectory: " + attr.isDirectory());
//            System.out.println("isOther: " + attr.isOther());
//            System.out.println("isRegularFile: " + attr.isRegularFile());
//            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
//            System.out.println("size: " + attr.size());

            String s = "";
            int scanedPages = 0;
            int totalNumberOfPages = 0;
//            PDDocument doc = PDDocument.load(new File("C:\\Users\\Noman.Alahi\\Desktop\\ocrscan.pdf"));
            
            PDDocument doc = PDDocument.load(new File("C:\\Users\\Noman.Alahi\\Desktop\\270972.pdf"));
//             PDDocument doc = PDDocument.load(new File("E:\\New-Repository\\Phase-2\\DS samples\\HE4YV000.pdf"));

            totalNumberOfPages = doc.getNumberOfPages();
            for (PDPage page : doc.getPages()) {
                PDResources resource = page.getResources();
                for (COSName xObjectName : resource.getXObjectNames()) {
                    PDXObject xObject = resource.getXObject(xObjectName);
                    if (xObject instanceof PDImageXObject) {
                        ((PDImageXObject) xObject).getImage();
                        scanedPages++;
                    }
                }
            }
            doc.close();
            if (scanedPages == totalNumberOfPages) // pdf pages if equal to the images
            {
                System.out.println( "Scanned pdf");
            } else {
                System.out.println( "Text pdf");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
