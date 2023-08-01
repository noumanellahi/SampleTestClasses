///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.testmavenproject;
//
//import java.awt.Graphics2D;
//import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PRStream;
//import com.itextpdf.text.pdf.PdfName;
//import com.itextpdf.text.pdf.PdfNumber;
//import com.itextpdf.text.pdf.PdfObject;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//import com.itextpdf.text.pdf.parser.PdfImageObject;
///**
// *
// * @author Noman.Alahi
// */
//public class FileCompression3 {
//
//    public static final String SRC = "C:\\Users\\Noman.Alahi\\Desktop\\B2_Classification_commune_des_actes_médicaux_descriptive_à_.pdf";
//    public static final String DEST = "C:\\Users\\Noman.Alahi\\Desktop\\Compress.pdf";
//    public static final float FACTOR = 0.5f;
//
//    public static void main(String[] args) throws DocumentException, IOException {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//        new FileCompression3().manipulatePdf(SRC, DEST);
//    }
//
//    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
//        PdfReader reader = new PdfReader(src);
//        int n = reader.getXrefSize();
//        PdfObject object;
//        PRStream stream;
//        // Look for image and manipulate image stream
//        for (int i = 0; i < n; i++) {
//            object = reader.getPdfObject(i);
//            if (object == null || !object.isStream()) {
//                continue;
//            }
//            stream = (PRStream) object;
//            if (!PdfName.IMAGE.equals(stream.getAsName(PdfName.SUBTYPE))) {
//                continue;
//            }
//            if (!PdfName.DCTDECODE.equals(stream.getAsName(PdfName.FILTER))) {
//                continue;
//            }
//            PdfImageObject image = new PdfImageObject(stream);
//            BufferedImage bi = image.getBufferedImage();
//            if (bi == null) {
//                continue;
//            }
//            int width = (int) (bi.getWidth() * FACTOR);
//            int height = (int) (bi.getHeight() * FACTOR);
//            if (width <= 0 || height <= 0) {
//                continue;
//            }
//            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
//            Graphics2D g = img.createGraphics();
//            g.drawRenderedImage(bi, at);
//            ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
//            ImageIO.write(img, "JPG", imgBytes);
//            stream.clear();
//            stream.setData(imgBytes.toByteArray(), false, PRStream.NO_COMPRESSION);
//            stream.put(PdfName.TYPE, PdfName.XOBJECT);
//            stream.put(PdfName.SUBTYPE, PdfName.IMAGE);
//            stream.put(PdfName.FILTER, PdfName.DCTDECODE);
//            stream.put(PdfName.WIDTH, new PdfNumber(width));
//            stream.put(PdfName.HEIGHT, new PdfNumber(height));
//            stream.put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
//            stream.put(PdfName.COLORSPACE, PdfName.DEVICERGB);
//        }
//        reader.removeUnusedObjects();
//        // Save altered PDF
//        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
//        stamper.setFullCompression();
//        stamper.close();
//        reader.close();
//    }
//
//}
